/**
 * 
 */
package org.openforis.collect.metamodel.proxy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.granite.messaging.amf.io.util.externalizer.annotation.ExternalizedProperty;
import org.openforis.collect.metamodel.ui.UIOptions;
import org.openforis.collect.metamodel.ui.UIOptions.Layout;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.idm.metamodel.EntityDefinition;

/**
 * @author M. Togna
 * @author S. Ricci
 * 
 */
public class EntityDefinitionProxy extends NodeDefinitionProxy {

	private transient EntityDefinition entityDefinition;

	public EntityDefinitionProxy(EntityDefinitionProxy parent, EntityDefinition entityDefinition) {
		super(parent, entityDefinition);
		this.entityDefinition = entityDefinition;
	}

	@ExternalizedProperty
	public List<NodeDefinitionProxy> getChildDefinitions() {
		return NodeDefinitionProxy.fromList(this, entityDefinition.getChildDefinitions());
	}

	@ExternalizedProperty
	public boolean isCountInSummaryList() {
		String annotation = entityDefinition.getAnnotation(UIOptions.Annotation.COUNT_IN_SUMMARY_LIST.getQName());
		return annotation != null && Boolean.parseBoolean(annotation);
	}

	@ExternalizedProperty
	public boolean isEnumerable() {
		return entityDefinition.isMultiple() && entityDefinition.isEnumerable();
	}

	@ExternalizedProperty
	public String getLayout() {
		CollectSurvey survey = (CollectSurvey) entityDefinition.getSurvey();
		UIOptions uiOpts = survey.getUIOptions();
		Layout layout = uiOpts.getLayout(entityDefinition);
		return layout.name().toLowerCase();
	}
	
	@ExternalizedProperty
	public boolean isShowRowNumbers() {
		String showRowNumbersString = entityDefinition.getAnnotation(UIOptions.Annotation.SHOW_ROW_NUMBERS.getQName());
		if ( StringUtils.isNotBlank(showRowNumbersString) ) {
			boolean result = Boolean.parseBoolean(showRowNumbersString);
			return result;
		} else {
			return false;
		}
	}
	
	@ExternalizedProperty
	public String getRootTabSetName() {
		if ( parent != null ) {
			return parent.getRootTabSetName();
		} else {
			String tabSetName = entityDefinition.getAnnotation(UIOptions.Annotation.TAB_SET.getQName());
			return tabSetName;
		}
	}
	
}
