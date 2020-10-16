/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public class OfcDataCleansingReport implements Serializable {

	private static final long serialVersionUID = -105650070;

	private Integer   id;
	private String    uuid;
	private Integer   cleansingChainId;
	private Integer   recordStep;
	private Integer   datasetSize;
	private Timestamp lastRecordModifiedDate;
	private Integer   cleansedRecords;
	private Integer   cleansedNodes;
	private Timestamp creationDate;
	private Timestamp modifiedDate;

	public OfcDataCleansingReport() {}

	public OfcDataCleansingReport(OfcDataCleansingReport value) {
		this.id = value.id;
		this.uuid = value.uuid;
		this.cleansingChainId = value.cleansingChainId;
		this.recordStep = value.recordStep;
		this.datasetSize = value.datasetSize;
		this.lastRecordModifiedDate = value.lastRecordModifiedDate;
		this.cleansedRecords = value.cleansedRecords;
		this.cleansedNodes = value.cleansedNodes;
		this.creationDate = value.creationDate;
		this.modifiedDate = value.modifiedDate;
	}

	public OfcDataCleansingReport(
		Integer   id,
		String    uuid,
		Integer   cleansingChainId,
		Integer   recordStep,
		Integer   datasetSize,
		Timestamp lastRecordModifiedDate,
		Integer   cleansedRecords,
		Integer   cleansedNodes,
		Timestamp creationDate,
		Timestamp modifiedDate
	) {
		this.id = id;
		this.uuid = uuid;
		this.cleansingChainId = cleansingChainId;
		this.recordStep = recordStep;
		this.datasetSize = datasetSize;
		this.lastRecordModifiedDate = lastRecordModifiedDate;
		this.cleansedRecords = cleansedRecords;
		this.cleansedNodes = cleansedNodes;
		this.creationDate = creationDate;
		this.modifiedDate = modifiedDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getCleansingChainId() {
		return this.cleansingChainId;
	}

	public void setCleansingChainId(Integer cleansingChainId) {
		this.cleansingChainId = cleansingChainId;
	}

	public Integer getRecordStep() {
		return this.recordStep;
	}

	public void setRecordStep(Integer recordStep) {
		this.recordStep = recordStep;
	}

	public Integer getDatasetSize() {
		return this.datasetSize;
	}

	public void setDatasetSize(Integer datasetSize) {
		this.datasetSize = datasetSize;
	}

	public Timestamp getLastRecordModifiedDate() {
		return this.lastRecordModifiedDate;
	}

	public void setLastRecordModifiedDate(Timestamp lastRecordModifiedDate) {
		this.lastRecordModifiedDate = lastRecordModifiedDate;
	}

	public Integer getCleansedRecords() {
		return this.cleansedRecords;
	}

	public void setCleansedRecords(Integer cleansedRecords) {
		this.cleansedRecords = cleansedRecords;
	}

	public Integer getCleansedNodes() {
		return this.cleansedNodes;
	}

	public void setCleansedNodes(Integer cleansedNodes) {
		this.cleansedNodes = cleansedNodes;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}