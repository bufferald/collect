/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;
import org.openforis.collect.persistence.jooq.Collect;
import org.openforis.collect.persistence.jooq.tables.records.SamplingDesignRecord;


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
public class SamplingDesign extends TableImpl<SamplingDesignRecord> {

	private static final long serialVersionUID = 1796964811;

	/**
	 * The reference instance of <code>collect.sampling_design</code>
	 */
	public static final SamplingDesign SAMPLING_DESIGN = new SamplingDesign();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<SamplingDesignRecord> getRecordType() {
		return SamplingDesignRecord.class;
	}

	/**
	 * The column <code>collect.sampling_design.id</code>.
	 */
	public final TableField<SamplingDesignRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.sampling_design.cluster</code>.
	 */
	public final TableField<SamplingDesignRecord, String> CLUSTER = createField("cluster", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false), this, "");

	/**
	 * The column <code>collect.sampling_design.plot</code>.
	 */
	public final TableField<SamplingDesignRecord, String> PLOT = createField("plot", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>collect.sampling_design.coordinate</code>.
	 */
	public final TableField<SamplingDesignRecord, String> COORDINATE = createField("coordinate", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false), this, "");

	/**
	 * Create a <code>collect.sampling_design</code> table reference
	 */
	public SamplingDesign() {
		this("sampling_design", null);
	}

	/**
	 * Create an aliased <code>collect.sampling_design</code> table reference
	 */
	public SamplingDesign(String alias) {
		this(alias, SAMPLING_DESIGN);
	}

	private SamplingDesign(String alias, Table<SamplingDesignRecord> aliased) {
		this(alias, aliased, null);
	}

	private SamplingDesign(String alias, Table<SamplingDesignRecord> aliased, Field<?>[] parameters) {
		super(alias, Collect.COLLECT, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesign as(String alias) {
		return new SamplingDesign(alias, this);
	}

	/**
	 * Rename this table
	 */
	public SamplingDesign rename(String name) {
		return new SamplingDesign(name, null);
	}
}