/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.openforis.collect.persistence.jooq.Collect;
import org.openforis.collect.persistence.jooq.Keys;
import org.openforis.collect.persistence.jooq.tables.records.OfcRecordRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OfcRecord extends TableImpl<OfcRecordRecord> {

	private static final long serialVersionUID = 1724489005;

	/**
	 * The reference instance of <code>collect.ofc_record</code>
	 */
	public static final OfcRecord OFC_RECORD = new OfcRecord();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<OfcRecordRecord> getRecordType() {
		return OfcRecordRecord.class;
	}

	/**
	 * The column <code>collect.ofc_record.id</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_record.survey_id</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> SURVEY_ID = createField("survey_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_record.root_entity_definition_id</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> ROOT_ENTITY_DEFINITION_ID = createField("root_entity_definition_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_record.date_created</code>.
	 */
	public final TableField<OfcRecordRecord, Timestamp> DATE_CREATED = createField("date_created", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>collect.ofc_record.created_by_id</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> CREATED_BY_ID = createField("created_by_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.date_modified</code>.
	 */
	public final TableField<OfcRecordRecord, Timestamp> DATE_MODIFIED = createField("date_modified", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>collect.ofc_record.modified_by_id</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> MODIFIED_BY_ID = createField("modified_by_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.model_version</code>.
	 */
	public final TableField<OfcRecordRecord, String> MODEL_VERSION = createField("model_version", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>collect.ofc_record.step</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> STEP = createField("step", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.state</code>.
	 */
	public final TableField<OfcRecordRecord, String> STATE = createField("state", org.jooq.impl.SQLDataType.CHAR.length(1), this, "");

	/**
	 * The column <code>collect.ofc_record.skipped</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> SKIPPED = createField("skipped", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.missing</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> MISSING = createField("missing", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.errors</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> ERRORS = createField("errors", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.warnings</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> WARNINGS = createField("warnings", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.key1</code>.
	 */
	public final TableField<OfcRecordRecord, String> KEY1 = createField("key1", org.jooq.impl.SQLDataType.VARCHAR.length(2048), this, "");

	/**
	 * The column <code>collect.ofc_record.key2</code>.
	 */
	public final TableField<OfcRecordRecord, String> KEY2 = createField("key2", org.jooq.impl.SQLDataType.VARCHAR.length(2048), this, "");

	/**
	 * The column <code>collect.ofc_record.key3</code>.
	 */
	public final TableField<OfcRecordRecord, String> KEY3 = createField("key3", org.jooq.impl.SQLDataType.VARCHAR.length(2048), this, "");

	/**
	 * The column <code>collect.ofc_record.count1</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> COUNT1 = createField("count1", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.count2</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> COUNT2 = createField("count2", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.count3</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> COUNT3 = createField("count3", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.count4</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> COUNT4 = createField("count4", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.count5</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> COUNT5 = createField("count5", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.owner_id</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> OWNER_ID = createField("owner_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.app_version</code>.
	 */
	public final TableField<OfcRecordRecord, String> APP_VERSION = createField("app_version", org.jooq.impl.SQLDataType.VARCHAR.length(63).defaulted(true), this, "");

	/**
	 * The column <code>collect.ofc_record.data_seq_num</code>.
	 */
	public final TableField<OfcRecordRecord, Integer> DATA_SEQ_NUM = createField("data_seq_num", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_record.qualifier1</code>.
	 */
	public final TableField<OfcRecordRecord, String> QUALIFIER1 = createField("qualifier1", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>collect.ofc_record.qualifier2</code>.
	 */
	public final TableField<OfcRecordRecord, String> QUALIFIER2 = createField("qualifier2", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>collect.ofc_record.qualifier3</code>.
	 */
	public final TableField<OfcRecordRecord, String> QUALIFIER3 = createField("qualifier3", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>collect.ofc_record.summary1</code>.
	 */
	public final TableField<OfcRecordRecord, String> SUMMARY1 = createField("summary1", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>collect.ofc_record.summary2</code>.
	 */
	public final TableField<OfcRecordRecord, String> SUMMARY2 = createField("summary2", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>collect.ofc_record.summary3</code>.
	 */
	public final TableField<OfcRecordRecord, String> SUMMARY3 = createField("summary3", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * Create a <code>collect.ofc_record</code> table reference
	 */
	public OfcRecord() {
		this("ofc_record", null);
	}

	/**
	 * Create an aliased <code>collect.ofc_record</code> table reference
	 */
	public OfcRecord(String alias) {
		this(alias, OFC_RECORD);
	}

	private OfcRecord(String alias, Table<OfcRecordRecord> aliased) {
		this(alias, aliased, null);
	}

	private OfcRecord(String alias, Table<OfcRecordRecord> aliased, Field<?>[] parameters) {
		super(alias, Collect.COLLECT, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<OfcRecordRecord> getPrimaryKey() {
		return Keys.OFC_RECORD_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<OfcRecordRecord>> getKeys() {
		return Arrays.<UniqueKey<OfcRecordRecord>>asList(Keys.OFC_RECORD_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<OfcRecordRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<OfcRecordRecord, ?>>asList(Keys.OFC_RECORD__OFC_RECORD_SURVEY_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcRecord as(String alias) {
		return new OfcRecord(alias, this);
	}

	/**
	 * Rename this table
	 */
	public OfcRecord rename(String name) {
		return new OfcRecord(name, null);
	}
}
