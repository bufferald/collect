/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.4"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OfcDataErrorReportRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorReportRecord> implements org.jooq.Record7<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.sql.Timestamp, java.lang.Integer, java.sql.Timestamp> {

	private static final long serialVersionUID = 1030147230;

	/**
	 * Setter for <code>collect.ofc_data_error_report.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_report.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_report.uuid</code>.
	 */
	public void setUuid(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_report.uuid</code>.
	 */
	public java.lang.String getUuid() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_report.query_group_id</code>.
	 */
	public void setQueryGroupId(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_report.query_group_id</code>.
	 */
	public java.lang.Integer getQueryGroupId() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_report.record_step</code>.
	 */
	public void setRecordStep(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_report.record_step</code>.
	 */
	public java.lang.Integer getRecordStep() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_report.creation_date</code>.
	 */
	public void setCreationDate(java.sql.Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_report.creation_date</code>.
	 */
	public java.sql.Timestamp getCreationDate() {
		return (java.sql.Timestamp) getValue(4);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_report.dataset_size</code>.
	 */
	public void setDatasetSize(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_report.dataset_size</code>.
	 */
	public java.lang.Integer getDatasetSize() {
		return (java.lang.Integer) getValue(5);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_report.last_record_modified_date</code>.
	 */
	public void setLastRecordModifiedDate(java.sql.Timestamp value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_report.last_record_modified_date</code>.
	 */
	public java.sql.Timestamp getLastRecordModifiedDate() {
		return (java.sql.Timestamp) getValue(6);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record7 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.sql.Timestamp, java.lang.Integer, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.sql.Timestamp, java.lang.Integer, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT.UUID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT.QUERY_GROUP_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT.RECORD_STEP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field5() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT.CREATION_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT.DATASET_SIZE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field7() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT.LAST_RECORD_MODIFIED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getUuid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getQueryGroupId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getRecordStep();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value5() {
		return getCreationDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getDatasetSize();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value7() {
		return getLastRecordModifiedDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord value2(java.lang.String value) {
		setUuid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord value3(java.lang.Integer value) {
		setQueryGroupId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord value4(java.lang.Integer value) {
		setRecordStep(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord value5(java.sql.Timestamp value) {
		setCreationDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord value6(java.lang.Integer value) {
		setDatasetSize(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord value7(java.sql.Timestamp value) {
		setLastRecordModifiedDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorReportRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.Integer value4, java.sql.Timestamp value5, java.lang.Integer value6, java.sql.Timestamp value7) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OfcDataErrorReportRecord
	 */
	public OfcDataErrorReportRecord() {
		super(org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT);
	}

	/**
	 * Create a detached, initialised OfcDataErrorReportRecord
	 */
	public OfcDataErrorReportRecord(java.lang.Integer id, java.lang.String uuid, java.lang.Integer queryGroupId, java.lang.Integer recordStep, java.sql.Timestamp creationDate, java.lang.Integer datasetSize, java.sql.Timestamp lastRecordModifiedDate) {
		super(org.openforis.collect.persistence.jooq.tables.OfcDataErrorReport.OFC_DATA_ERROR_REPORT);

		setValue(0, id);
		setValue(1, uuid);
		setValue(2, queryGroupId);
		setValue(3, recordStep);
		setValue(4, creationDate);
		setValue(5, datasetSize);
		setValue(6, lastRecordModifiedDate);
	}
}
