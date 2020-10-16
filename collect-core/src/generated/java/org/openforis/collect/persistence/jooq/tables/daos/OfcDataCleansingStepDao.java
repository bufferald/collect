/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.daos;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.openforis.collect.persistence.jooq.tables.OfcDataCleansingStep;
import org.openforis.collect.persistence.jooq.tables.records.OfcDataCleansingStepRecord;


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
public class OfcDataCleansingStepDao extends DAOImpl<OfcDataCleansingStepRecord, org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep, Integer> {

	/**
	 * Create a new OfcDataCleansingStepDao without any configuration
	 */
	public OfcDataCleansingStepDao() {
		super(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP, org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep.class);
	}

	/**
	 * Create a new OfcDataCleansingStepDao with an attached configuration
	 */
	public OfcDataCleansingStepDao(Configuration configuration) {
		super(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP, org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchById(Integer... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep fetchOneById(Integer value) {
		return fetchOne(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.ID, value);
	}

	/**
	 * Fetch records that have <code>uuid IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchByUuid(String... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.UUID, values);
	}

	/**
	 * Fetch records that have <code>query_id IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchByQueryId(Integer... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.QUERY_ID, values);
	}

	/**
	 * Fetch records that have <code>title IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchByTitle(String... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.TITLE, values);
	}

	/**
	 * Fetch records that have <code>description IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchByDescription(String... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.DESCRIPTION, values);
	}

	/**
	 * Fetch records that have <code>creation_date IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchByCreationDate(Timestamp... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.CREATION_DATE, values);
	}

	/**
	 * Fetch records that have <code>modified_date IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchByModifiedDate(Timestamp... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.MODIFIED_DATE, values);
	}

	/**
	 * Fetch records that have <code>type IN (values)</code>
	 */
	public List<org.openforis.collect.persistence.jooq.tables.pojos.OfcDataCleansingStep> fetchByType(String... values) {
		return fetch(OfcDataCleansingStep.OFC_DATA_CLEANSING_STEP.TYPE, values);
	}
}