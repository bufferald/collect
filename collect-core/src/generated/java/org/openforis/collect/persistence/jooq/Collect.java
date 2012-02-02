/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.1"},
                            comments = "This class is generated by jOOQ")
public class Collect extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -557916729;

	/**
	 * The singleton instance of collect
	 */
	public static final Collect COLLECT = new Collect();

	/**
	 * No further instances allowed
	 */
	private Collect() {
		super("collect");
	}

	@Override
	public final java.util.List<org.jooq.Sequence<?>> getSequences() {
		return java.util.Arrays.<org.jooq.Sequence<?>>asList(
			org.openforis.collect.persistence.jooq.Sequences.DATA_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.RECORD_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.SCHEMA_DEFINITION_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.SURVEY_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.TAXON_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.TAXON_NAME_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.TAXONOMY_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.TAXON_VERNACULAR_NAME_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.USER_ID_SEQ,
			org.openforis.collect.persistence.jooq.Sequences.USER_ROLE_ID_SEQ);
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			org.openforis.collect.persistence.jooq.tables.Data.DATA,
			org.openforis.collect.persistence.jooq.tables.Logo.LOGO,
			org.openforis.collect.persistence.jooq.tables.Record.RECORD,
			org.openforis.collect.persistence.jooq.tables.SchemaDefinition.SCHEMA_DEFINITION,
			org.openforis.collect.persistence.jooq.tables.Survey.SURVEY,
			org.openforis.collect.persistence.jooq.tables.Taxon.TAXON,
			org.openforis.collect.persistence.jooq.tables.Taxonomy.TAXONOMY,
			org.openforis.collect.persistence.jooq.tables.TaxonVernacularName.TAXON_VERNACULAR_NAME,
			org.openforis.collect.persistence.jooq.tables.UserAccount.USER_ACCOUNT,
			org.openforis.collect.persistence.jooq.tables.UserRole.USER_ROLE);
	}
}
