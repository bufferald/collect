/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OfcDataErrorQuery extends org.jooq.impl.TableImpl<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord> {

	private static final long serialVersionUID = 245600373;

	/**
	 * The singleton instance of <code>collect.ofc_data_error_query</code>
	 */
	public static final org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery OFC_DATA_ERROR_QUERY = new org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord> getRecordType() {
		return org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord.class;
	}

	/**
	 * The column <code>collect.ofc_data_error_query.id</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.error_type_id</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.Integer> ERROR_TYPE_ID = createField("error_type_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.title</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.description</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.record_step</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.Integer> RECORD_STEP = createField("record_step", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.creation_date</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.sql.Timestamp> CREATION_DATE = createField("creation_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.entity_id</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.Integer> ENTITY_ID = createField("entity_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.attribute_id</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.Integer> ATTRIBUTE_ID = createField("attribute_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_data_error_query.conditions</code>.
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, java.lang.String> CONDITIONS = createField("conditions", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>collect.ofc_data_error_query</code> table reference
	 */
	public OfcDataErrorQuery() {
		this("ofc_data_error_query", null);
	}

	/**
	 * Create an aliased <code>collect.ofc_data_error_query</code> table reference
	 */
	public OfcDataErrorQuery(java.lang.String alias) {
		this(alias, org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery.OFC_DATA_ERROR_QUERY);
	}

	private OfcDataErrorQuery(java.lang.String alias, org.jooq.Table<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord> aliased) {
		this(alias, aliased, null);
	}

	private OfcDataErrorQuery(java.lang.String alias, org.jooq.Table<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.openforis.collect.persistence.jooq.Collect.COLLECT, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord> getPrimaryKey() {
		return org.openforis.collect.persistence.jooq.Keys.OFC_DATA_ERROR_QUERY_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord>>asList(org.openforis.collect.persistence.jooq.Keys.OFC_DATA_ERROR_QUERY_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcDataErrorQueryRecord, ?>>asList(org.openforis.collect.persistence.jooq.Keys.OFC_DATA_ERROR_QUERY__OFC_DATA_ERROR_QUERY_DATA_ERROR_TYPE_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery as(java.lang.String alias) {
		return new org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery rename(java.lang.String name) {
		return new org.openforis.collect.persistence.jooq.tables.OfcDataErrorQuery(name, null);
	}
}
