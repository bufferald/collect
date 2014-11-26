package org.openforis.collect.datacleansing;

import java.util.Date;

import org.openforis.collect.model.CollectRecord.Step;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.idm.metamodel.AttributeDefinition;
import org.openforis.idm.metamodel.EntityDefinition;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.SurveyObject;

/**
 * 
 * @author S. Ricci
 *
 */
public class Query extends SurveyObject {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date creationDate;
	private String title;
	private String description;
	private int entityDefinitionId;
	private int attributeDefinitionId;
	private Step step;
	private String conditions;

	public Query(CollectSurvey survey) {
		super(survey);
	}
	
	public EntityDefinition getEntityDefinition() {
		NodeDefinition def = getSurvey().getSchema().getDefinitionById(entityDefinitionId);
		return (EntityDefinition) def;
	}
	
	public AttributeDefinition getAttributeDefinition() {
		NodeDefinition def = getSurvey().getSchema().getDefinitionById(attributeDefinitionId);
		return (AttributeDefinition) def;
	}
	
	public void setAttributeDefinition(AttributeDefinition def) {
		attributeDefinitionId = def.getId();
	}
	
	public void setEntityDefinition(EntityDefinition def) {
		entityDefinitionId = def.getId();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEntityDefinitionId() {
		return entityDefinitionId;
	}

	public void setEntityDefinitionId(int entityDefinitionId) {
		this.entityDefinitionId = entityDefinitionId;
	}

	public int getAttributeDefinitionId() {
		return attributeDefinitionId;
	}

	public void setAttributeDefinitionId(int attributeDefinitionId) {
		this.attributeDefinitionId = attributeDefinitionId;
	}

	public Step getStep() {
		return step;
	}
	
	public void setStep(Step step) {
		this.step = step;
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

}