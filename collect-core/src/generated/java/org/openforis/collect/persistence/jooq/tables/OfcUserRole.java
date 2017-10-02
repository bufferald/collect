/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables;


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
import org.openforis.collect.persistence.jooq.tables.records.OfcUserRoleRecord;


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
public class OfcUserRole extends TableImpl<OfcUserRoleRecord> {

	private static final long serialVersionUID = -1172482075;

	/**
	 * The reference instance of <code>collect.ofc_user_role</code>
	 */
	public static final OfcUserRole OFC_USER_ROLE = new OfcUserRole();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<OfcUserRoleRecord> getRecordType() {
		return OfcUserRoleRecord.class;
	}

	/**
	 * The column <code>collect.ofc_user_role.id</code>.
	 */
	public final TableField<OfcUserRoleRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_user_role.user_id</code>.
	 */
	public final TableField<OfcUserRoleRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>collect.ofc_user_role.role</code>.
	 */
	public final TableField<OfcUserRoleRecord, String> ROLE = createField("role", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * Create a <code>collect.ofc_user_role</code> table reference
	 */
	public OfcUserRole() {
		this("ofc_user_role", null);
	}

	/**
	 * Create an aliased <code>collect.ofc_user_role</code> table reference
	 */
	public OfcUserRole(String alias) {
		this(alias, OFC_USER_ROLE);
	}

	private OfcUserRole(String alias, Table<OfcUserRoleRecord> aliased) {
		this(alias, aliased, null);
	}

	private OfcUserRole(String alias, Table<OfcUserRoleRecord> aliased, Field<?>[] parameters) {
		super(alias, Collect.COLLECT, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<OfcUserRoleRecord> getPrimaryKey() {
		return Keys.OFC_USER_ROLE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<OfcUserRoleRecord>> getKeys() {
		return Arrays.<UniqueKey<OfcUserRoleRecord>>asList(Keys.OFC_USER_ROLE_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<OfcUserRoleRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<OfcUserRoleRecord, ?>>asList(Keys.OFC_USER_ROLE__OFC_USER_USER_ROLE_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfcUserRole as(String alias) {
		return new OfcUserRole(alias, this);
	}

	/**
	 * Rename this table
	 */
	public OfcUserRole rename(String name) {
		return new OfcUserRole(name, null);
	}
}
