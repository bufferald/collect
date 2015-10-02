package org.openforis.collect.io.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.openforis.collect.io.NewBackupFileExtractor;
import org.openforis.collect.io.SurveyBackupJob;
import org.openforis.collect.io.data.BackupDataExtractor.BackupRecordEntry;
import org.openforis.collect.io.exception.DataImportExeption;
import org.openforis.collect.manager.RecordManager;
import org.openforis.collect.manager.UserManager;
import org.openforis.collect.model.CollectRecord;
import org.openforis.collect.model.CollectRecord.Step;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.model.RecordUpdater;
import org.openforis.collect.persistence.RecordDao.RecordStoreQuery;
import org.openforis.collect.persistence.RecordPersistenceException;
import org.openforis.collect.persistence.SurveyImportException;
import org.openforis.collect.persistence.xml.DataHandler;
import org.openforis.collect.persistence.xml.DataUnmarshaller;
import org.openforis.collect.persistence.xml.DataUnmarshaller.ParseRecordResult;
import org.openforis.commons.io.OpenForisIOUtils;
import org.openforis.concurrency.Task;
import org.openforis.idm.model.Entity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author S. Ricci
 * 
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DataRestoreTask extends Task {

	private RecordManager recordManager;
	private UserManager userManager;

	//input
	private File file;
	
	private CollectSurvey packagedSurvey;
	private CollectSurvey existingSurvey;
	private List<Integer> entryIdsToImport;
	private boolean overwriteAll;
	
	//temporary instance variables
	private DataUnmarshaller dataUnmarshaller;
	private RecordUpdater recordUpdater;
	private List<Integer> processedRecords;
	private HashMap<String, String> errorByEntryName;
	private NewBackupFileExtractor backupFileExtractor;
	private boolean oldBackupFormat;
	private QueryBuffer queryBuffer;
	private Integer nextRecordId;
	private boolean validateRecords;
	
	public DataRestoreTask() {
		super();
		this.processedRecords = new ArrayList<Integer>();
		this.errorByEntryName = new HashMap<String, String>();
		this.oldBackupFormat = false;
		this.validateRecords = true;
		this.queryBuffer = new QueryBuffer();
		this.recordUpdater = new RecordUpdater();
	}

	@Override
	protected void createInternalVariables() throws Throwable {
		dataUnmarshaller = initDataUnmarshaller(packagedSurvey, existingSurvey);
		backupFileExtractor = new NewBackupFileExtractor(file);
		backupFileExtractor.init();
		oldBackupFormat = backupFileExtractor.isOldFormat();
		super.createInternalVariables();
	}
	
	@Override
	protected long countTotalItems() {
		List<Integer> idsToImport = calculateEntryIdsToImport();
		return idsToImport.size();
	}

	private List<Integer> calculateEntryIdsToImport() {
		List<Integer> result = new ArrayList<Integer>();
		if ( entryIdsToImport == null ) {
			if ( overwriteAll ) {
				for (Step step : Step.values()) {
					int stepNumber = step.getStepNumber();
					String path = SurveyBackupJob.DATA_FOLDER + SurveyBackupJob.ZIP_FOLDER_SEPARATOR + stepNumber;
					if ( backupFileExtractor.containsEntriesInPath(path) ) {
						List<String> listEntriesInPath = backupFileExtractor.listFilesInFolder(path);
						for (String entry : listEntriesInPath) {
							String entryId = FilenameUtils.getBaseName(entry);
							result.add(Integer.parseInt(entryId));
						}
						return result;
					}
				}
				return Collections.emptyList();
			} else {
				throw new IllegalArgumentException("No entries to import specified and overwriteAll parameter is 'false'");
			}
		} else {
			return entryIdsToImport;
		}
	}
	
	@Override
	protected void execute() throws Throwable {
		processedRecords = new ArrayList<Integer>();
		nextRecordId = recordManager.nextId();
		try {
			List<Integer> idsToImport = calculateEntryIdsToImport();
			for (Integer entryId : idsToImport) {
				if ( isRunning() && ! processedRecords.contains(entryId) ) {
					importEntries(entryId);
					processedRecords.add(entryId);
					incrementItemsProcessed();
				} else {
					break;
				}
			}
			queryBuffer.flush();
		} finally {
			if (nextRecordId != null) {
				recordManager.restartIdSequence(nextRecordId);
			}
		}
	}
	
	@Override
	protected void onEnd() {
		super.onEnd();
		IOUtils.closeQuietly(backupFileExtractor);
	}
	
	private void importEntries(int entryId) throws IOException, DataImportExeption, RecordPersistenceException {
		CollectRecord lastProcessedRecord = null;
		Step originalRecordStep = null;
		Step[] steps = Step.values();
		for (Step step : steps) {
			String entryName = getBackupEntryName(entryId, step);
			InputStream entryIS = backupFileExtractor.findEntryInputStream(entryName);
			if ( entryIS != null ) {
				InputStreamReader reader = OpenForisIOUtils.toReader(entryIS);
				ParseRecordResult parseRecordResult = parseRecord(reader);
				CollectRecord parsedRecord = parseRecordResult.getRecord();
				if (parsedRecord == null) {
					//error parsing record
					String message = parseRecordResult.getMessage();
					addError(entryName, message);
				} else {
					//record parsed successfully
					parsedRecord.setStep(step);
					
					if ( lastProcessedRecord == null ) {
						CollectRecord oldRecordSummary = findAlreadyExistingRecordSummary(parsedRecord);
						if (oldRecordSummary == null) {
							//insert new record
							parsedRecord.setId(nextRecordId ++);
							insertRecordDataUntilStep(parsedRecord, step);
						} else {
							//overwrite existing record
							originalRecordStep = oldRecordSummary.getStep();
							parsedRecord.setId(oldRecordSummary.getId());
							queryBuffer.append(recordManager.createUpdateQuery(parsedRecord));
						}
						lastProcessedRecord = parsedRecord;
					} else {
						replaceData(parsedRecord, lastProcessedRecord);
						queryBuffer.append(recordManager.createUpdateQuery(lastProcessedRecord));
					}
//					if ( parseRecordResult.hasWarnings() ) {
//						addWarnings(entryName, parseRecordResult.getWarnings());
//					}
				}
			}
		}
		if ( lastProcessedRecord != null ) {
			//if the original record step is after the imported record one, 
			//restore record step to the original one and revalidate the record
			//e.g. importing data from data entry step and the original record was in analysis step
			if ( originalRecordStep != null && originalRecordStep.after(lastProcessedRecord.getStep()) ) {
				restoreRecordStep(lastProcessedRecord, originalRecordStep);
			} else {
				//validate record and save the validation result
				initializeRecord(lastProcessedRecord);
				queryBuffer.append(recordManager.createUpdateQuery(lastProcessedRecord));
			}
		}
	}

	private void restoreRecordStep(CollectRecord record, Step originalRecordStep) {
		CollectSurvey survey = (CollectSurvey) record.getSurvey();
		CollectRecord originalRecord = recordManager.load(survey, record.getId(), originalRecordStep);
		originalRecord.setStep(originalRecordStep);
		initializeRecord(originalRecord);
		queryBuffer.append(recordManager.createUpdateQuery(originalRecord));
	}

	private void insertRecordDataUntilStep(CollectRecord record, Step step) {
		List<Step> previousSteps = new ArrayList<Step>();
		for (Step s : Step.values()) {
			if (s.beforeEqual(step)) {
				previousSteps.add(s);
			}
		}
		for (Step previousStep : previousSteps) {
			record.setStep(previousStep);
			switch(previousStep) {
			case ENTRY:
				queryBuffer.append(recordManager.createInsertQuery(record));
				break;
			default:
				queryBuffer.append(recordManager.createUpdateQuery(record));
			}
		}
	}


	protected String getBackupEntryName(int entryId, Step step) {
		if ( oldBackupFormat ) {
			return step.getStepNumber() + "/" + entryId + ".xml";
		} else {
			BackupRecordEntry recordEntry = new BackupRecordEntry(step, entryId);
			String entryName = recordEntry.getName();
			return entryName;
		}
	}

	private void addError(String entryName, String message) {
		errorByEntryName.put(entryName, message);
	}

	private DataUnmarshaller initDataUnmarshaller(CollectSurvey packagedSurvey, CollectSurvey existingSurvey) throws SurveyImportException {
		CollectSurvey currentSurvey = existingSurvey == null ? packagedSurvey : existingSurvey;
		DataHandler handler = new DataHandler(userManager, currentSurvey, packagedSurvey, validateRecords);
		DataUnmarshaller dataUnmarshaller = new DataUnmarshaller(handler);
		return dataUnmarshaller;
	}

	private CollectRecord findAlreadyExistingRecordSummary(CollectRecord parsedRecord) {
		CollectSurvey survey = (CollectSurvey) parsedRecord.getSurvey();
		List<String> keyValues = parsedRecord.getRootEntityKeyValues();
		Entity rootEntity = parsedRecord.getRootEntity();
		String rootEntityName = rootEntity.getName();
		List<CollectRecord> oldRecords = recordManager.loadSummaries(survey, rootEntityName, keyValues.toArray(new String[keyValues.size()]));
		if ( oldRecords == null || oldRecords.isEmpty() ) {
			return null;
		} else if ( oldRecords.size() == 1 ) {
			return oldRecords.get(0);
		} else {
			throw new IllegalStateException(String.format("Multiple records found in survey %s with key(s): %s", survey.getName(), keyValues));
		}
	}

	private ParseRecordResult parseRecord(Reader reader) throws IOException {
		ParseRecordResult result = dataUnmarshaller.parse(reader);
		return result;
	}

	private void initializeRecord(CollectRecord record) {
		try {
			if (validateRecords) {
				recordManager.validate(record);
			} else {
				recordUpdater.initializeRecord(record, false);
			}
		} catch (Exception e) {
			log().warn("Error validating record: " + record.getRootEntityKeyValues(), e);
		}
	}

	private void replaceData(CollectRecord fromRecord, CollectRecord toRecord) {
		toRecord.setCreatedBy(fromRecord.getCreatedBy());
		toRecord.setCreationDate(fromRecord.getCreationDate());
		toRecord.setModifiedBy(fromRecord.getModifiedBy());
		toRecord.setModifiedDate(fromRecord.getModifiedDate());
		toRecord.setStep(fromRecord.getStep());
		toRecord.setState(fromRecord.getState());
		toRecord.replaceRootEntity(fromRecord.getRootEntity());
		initializeRecord(toRecord);
	}
	
	public RecordManager getRecordManager() {
		return recordManager;
	}
	
	public void setRecordManager(RecordManager recordManager) {
		this.recordManager = recordManager;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public CollectSurvey getPackagedSurvey() {
		return packagedSurvey;
	}
	
	public void setPackagedSurvey(CollectSurvey packagedSurvey) {
		this.packagedSurvey = packagedSurvey;
	}
	
	public CollectSurvey getExistingSurvey() {
		return existingSurvey;
	}
	
	public void setExistingSurvey(CollectSurvey existingSurvey) {
		this.existingSurvey = existingSurvey;
	}
	
	public boolean isOverwriteAll() {
		return overwriteAll;
	}

	public void setOverwriteAll(boolean overwriteAll) {
		this.overwriteAll = overwriteAll;
	}

	public List<Integer> getEntryIdsToImport() {
		return entryIdsToImport;
	}

	public void setEntryIdsToImport(List<Integer> entryIdsToImport) {
		this.entryIdsToImport = entryIdsToImport;
	}

	public void setValidateRecords(boolean validateRecords) {
		this.validateRecords = validateRecords;
	}

	private class QueryBuffer {
		
		private static final int DEFAULT_BATCH_SIZE = 100;
		
		private int bufferSize;
		private List<RecordStoreQuery> buffer;
		
		public QueryBuffer() {
			this(DEFAULT_BATCH_SIZE);
		}
		
		public QueryBuffer(int size) {
			this.bufferSize = size;
			this.buffer = new ArrayList<RecordStoreQuery>(size);
		}
		
		void append(RecordStoreQuery query) {
			buffer.add(query);
			if (buffer.size() == bufferSize) {
				flush();
			}
		}

		void flush() {
			recordManager.execute(buffer);
			buffer.clear();
		}
	}

}
