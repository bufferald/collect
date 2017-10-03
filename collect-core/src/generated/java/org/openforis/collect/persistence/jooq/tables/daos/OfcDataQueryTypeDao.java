/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.daos;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.openforis.collect.persistence.jooq.tables.OfcDataQueryType;
import org.openforis.collect.persistence.jooq.tables.records.OfcDataQueryTypeRecord;


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
public class OfcDataQueryTypeDao extends DAOImpl<OfcDataQueryTypeRecord, org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType, Integer> {

	/**
	 * Create a new OfcDataQueryTypeDao without any configuration
	 */
	public OfcDataQueryTypeDao() {
		super(OfcDataQueryType.OFC_DATA_QUERY_TYPE, org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType.class);
	}

	/**
	 * Create a new OfcDataQueryTypeDao with an attached configuration
	 */
	public OfcDataQueryTypeDao(Configuration configuration) {
		super(OfcDataQueryType.OFC_DATA_QUERY_TYPE, org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchById(Integer... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType fetchOneById(Integer value) {
		return fetchOne(OfcDataQueryType.OFC_DATA_QUERY_TYPE.ID, value);
	}

	/**
	 * Fetch records that have <code>uuid IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchByUuid(String... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.UUID, values);
	}

	/**
	 * Fetch records that have <code>survey_id IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchBySurveyId(Integer... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.SURVEY_ID, values);
	}

	/**
	 * Fetch records that have <code>code IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchByCode(String... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.CODE, values);
	}

	/**
	 * Fetch records that have <code>label IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchByLabel(String... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.LABEL, values);
	}

	/**
	 * Fetch records that have <code>description IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchByDescription(String... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.DESCRIPTION, values);
	}

	/**
	 * Fetch records that have <code>creation_date IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchByCreationDate(Timestamp... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.CREATION_DATE, values);
	}

	/**
	 * Fetch records that have <code>modified_date IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataQueryType> fetchByModifiedDate(Timestamp... values) {
		return fetch(OfcDataQueryType.OFC_DATA_QUERY_TYPE.MODIFIED_DATE, values);
	}
}