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
public class OfcDataCleansingChainRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.collect.persistence.jooq.tables.records.OfcDataCleansingChainRecord> implements org.jooq.Record7<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp> {

	private static final long serialVersionUID = 1601400599;

	/**
	 * Setter for <code>collect.ofc_data_cleansing_chain.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_cleansing_chain.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>collect.ofc_data_cleansing_chain.uuid</code>.
	 */
	public void setUuid(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_cleansing_chain.uuid</code>.
	 */
	public java.lang.String getUuid() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>collect.ofc_data_cleansing_chain.survey_id</code>.
	 */
	public void setSurveyId(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_cleansing_chain.survey_id</code>.
	 */
	public java.lang.Integer getSurveyId() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>collect.ofc_data_cleansing_chain.title</code>.
	 */
	public void setTitle(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_cleansing_chain.title</code>.
	 */
	public java.lang.String getTitle() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>collect.ofc_data_cleansing_chain.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_cleansing_chain.description</code>.
	 */
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>collect.ofc_data_cleansing_chain.creation_date</code>.
	 */
	public void setCreationDate(java.sql.Timestamp value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_cleansing_chain.creation_date</code>.
	 */
	public java.sql.Timestamp getCreationDate() {
		return (java.sql.Timestamp) getValue(5);
	}

	/**
	 * Setter for <code>collect.ofc_data_cleansing_chain.modified_date</code>.
	 */
	public void setModifiedDate(java.sql.Timestamp value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>collect.ofc_data_cleansing_chain.modified_date</code>.
	 */
	public java.sql.Timestamp getModifiedDate() {
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
	public org.jooq.Row7<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN.UUID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN.SURVEY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN.TITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field6() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN.CREATION_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field7() {
		return org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN.MODIFIED_DATE;
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
		return getSurveyId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getDescription();
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
	public java.sql.Timestamp value7() {
		return getModifiedDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord value2(java.lang.String value) {
		setUuid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord value3(java.lang.Integer value) {
		setSurveyId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord value4(java.lang.String value) {
		setTitle(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord value5(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord value6(java.sql.Timestamp value) {
		setCreationDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord value7(java.sql.Timestamp value) {
		setModifiedDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcDataCleansingChainRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.String value4, java.lang.String value5, java.sql.Timestamp value6, java.sql.Timestamp value7) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OfcDataCleansingChainRecord
	 */
	public OfcDataCleansingChainRecord() {
		super(org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN);
	}

	/**
	 * Create a detached, initialised OfcDataCleansingChainRecord
	 */
	public OfcDataCleansingChainRecord(java.lang.Integer id, java.lang.String uuid, java.lang.Integer surveyId, java.lang.String title, java.lang.String description, java.sql.Timestamp creationDate, java.sql.Timestamp modifiedDate) {
		super(org.openforis.collect.persistence.jooq.tables.OfcDataCleansingChain.OFC_DATA_CLEANSING_CHAIN);

		setValue(0, id);
		setValue(1, uuid);
		setValue(2, surveyId);
		setValue(3, title);
		setValue(4, description);
		setValue(5, creationDate);
		setValue(6, modifiedDate);
	}
}
