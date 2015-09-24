package org.openforis.collect.datacleansing.form;

import javax.validation.constraints.NotNull;

import org.openforis.collect.datacleansing.DataErrorQuery;
import org.openforis.collect.datacleansing.DataErrorQuery.Severity;

/**
 * 
 * @author S. Ricci
 *
 */
public class DataErrorQueryForm extends DataCleansingItemForm<DataErrorQuery> {

	@NotNull
	private Integer typeId;
	@NotNull
	private Integer queryId;
	@NotNull
	private Severity severity;
	
	//calculated members
	private transient String typeCode;
	private transient String queryTitle;
	private transient String queryDescription;
	private transient String prettyFormatTitle;
	
	public DataErrorQueryForm() {
		super();
	}
	
	public DataErrorQueryForm(DataErrorQuery query) {
		super(query);
		this.typeCode = query == null ? null: query.getType() == null ? null: query.getType().getCode();
		this.queryTitle = query == null ? null: query.getQuery().getTitle();
		this.queryDescription = query == null ? null: query.getQuery().getDescription();
		this.prettyFormatTitle = String.format("Error type: %s - Data Query: %s", typeCode, query.getQuery().getTitle());
	}
	
	/**
	 * Void methods: avoid deserialization issues
	 */
	public void setTypeCode(String typeCode) {
		return;
	}
	
	public void setQueryTitle(String queryTitle) {
		return;
	}
	
	public void setQueryDescription(String queryDescription) {
		return;
	}
	
	public void setPrettyFormatTitle(String prettyFormatTitle) {
		return;
	}
	
	public Severity getSeverity() {
		return severity;
	}
	
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	public String getPrettyFormatTitle() {
		return prettyFormatTitle;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	
	public String getQueryTitle() {
		return queryTitle;
	}
	
	public String getQueryDescription() {
		return queryDescription;
	}
	
	public Integer getTypeId() {
		return typeId;
	}
	
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public Integer getQueryId() {
		return queryId;
	}
	
	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}
	
}