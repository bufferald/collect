/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OfcDataErrorQueryRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord> implements org.jooq.Record9<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.sql.Timestamp, java.lang.Integer, java.lang.Integer, java.lang.String> {

	private static final long serialVersionUID = -129929144;

	/**
	 * Setter for <code>collect.ofc_data_error_query.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.error_type_id</code>.
	 */
	public void setErrorTypeId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.error_type_id</code>.
	 */
	public java.lang.Integer getErrorTypeId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.title</code>.
	 */
	public void setTitle(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.title</code>.
	 */
	public java.lang.String getTitle() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.description</code>.
	 */
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.record_step</code>.
	 */
	public void setRecordStep(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.record_step</code>.
	 */
	public java.lang.Integer getRecordStep() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.creation_date</code>.
	 */
	public void setCreationDate(java.sql.Timestamp value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.creation_date</code>.
	 */
	public java.sql.Timestamp getCreationDate() {
		return (java.sql.Timestamp) getValue(5);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.entity_id</code>.
	 */
	public void setEntityId(java.lang.Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.entity_id</code>.
	 */
	public java.lang.Integer getEntityId() {
		return (java.lang.Integer) getValue(6);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.attribute_id</code>.
	 */
	public void setAttributeId(java.lang.Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.attribute_id</code>.
	 */
	public java.lang.Integer getAttributeId() {
		return (java.lang.Integer) getValue(7);
	}

	/**
	 * Setter for <code>collect.ofc_data_error_query.conditions</code>.
	 */
	public void setConditions(java.lang.String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_error_query.conditions</code>.
	 */
	public java.lang.String getConditions() {
		return (java.lang.String) getValue(8);
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
	// Record9 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row9<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.sql.Timestamp, java.lang.Integer, java.lang.Integer, java.lang.String> fieldsRow() {
		return (org.jooq.Row9) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row9<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.sql.Timestamp, java.lang.Integer, java.lang.Integer, java.lang.String> valuesRow() {
		return (org.jooq.Row9) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.ERROR_TYPE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.TITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.RECORD_STEP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field6() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.CREATION_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field7() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.ENTITY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field8() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.ATTRIBUTE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY.CONDITIONS;
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
	public java.lang.Integer value2() {
		return getErrorTypeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getRecordStep();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value6() {
		return getCreationDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value7() {
		return getEntityId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value8() {
		return getAttributeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getConditions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value2(java.lang.Integer value) {
		setErrorTypeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value3(java.lang.String value) {
		setTitle(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value4(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value5(java.lang.Integer value) {
		setRecordStep(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value6(java.sql.Timestamp value) {
		setCreationDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value7(java.lang.Integer value) {
		setEntityId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value8(java.lang.Integer value) {
		setAttributeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord value9(java.lang.String value) {
		setConditions(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataErrorQueryRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.String value3, java.lang.String value4, java.lang.Integer value5, java.sql.Timestamp value6, java.lang.Integer value7, java.lang.Integer value8, java.lang.String value9) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OfcDataErrorQueryRecord
	 */
	public OfcDataErrorQueryRecord() {
		super(org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY);
	}

	/**
	 * Create a detached, initialised OfcDataErrorQueryRecord
	 */
	public OfcDataErrorQueryRecord(java.lang.Integer id, java.lang.Integer errorTypeId, java.lang.String title, java.lang.String description, java.lang.Integer recordStep, java.sql.Timestamp creationDate, java.lang.Integer entityId, java.lang.Integer attributeId, java.lang.String conditions) {
		super(org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY);

		setValue(0, id);
		setValue(1, errorTypeId);
		setValue(2, title);
		setValue(3, description);
		setValue(4, recordStep);
		setValue(5, creationDate);
		setValue(6, entityId);
		setValue(7, attributeId);
		setValue(8, conditions);
	}
}
