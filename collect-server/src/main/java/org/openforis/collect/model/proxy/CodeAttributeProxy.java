/**
 * 
 */
package org.openforis.collect.model.proxy;

import org.granite.messaging.amf.io.util.externalizer.annotation.ExternalizedProperty;
import org.openforis.collect.ProxyContext;
import org.openforis.collect.metamodel.proxy.CodeListItemProxy;
import org.openforis.idm.metamodel.CodeAttributeDefinition;
import org.openforis.idm.metamodel.CodeList;
import org.openforis.idm.metamodel.CodeListItem;
import org.openforis.idm.metamodel.CodeListService;
import org.openforis.idm.model.CodeAttribute;

/**
 * @author S. Ricci
 *
 */
public class CodeAttributeProxy extends AttributeProxy {

	private transient CodeAttribute codeAttribute;
	private CodeListItemProxy codeListItem;
	
	public CodeAttributeProxy(EntityProxy parent,
			CodeAttribute attribute, ProxyContext context) {
		super(parent, attribute, context);
		this.codeAttribute = attribute;
		CodeListService codeListService = getCodeListService();
		CodeListItem item = codeListService.loadItem(codeAttribute);
		this.codeListItem = item == null ? null: new CodeListItemProxy(item);
	}

	@ExternalizedProperty
	public CodeListItemProxy getCodeListItem() {
		return codeListItem;
	}
	
	public void setCodeListItem(CodeListItemProxy codeListItem) {
		this.codeListItem = codeListItem;
	}
	
	@ExternalizedProperty
	public Integer getParentCodeAttributeId() {
		CodeAttribute parentCodeAttribute = this.codeAttribute.getRecord().determineParentCodeAttribute(codeAttribute);
		return parentCodeAttribute == null ? null : parentCodeAttribute.getInternalId();
	}

	private CodeListService getCodeListService() {
		return context.getSurveyContext().getCodeListService();
	}

	@ExternalizedProperty
	public boolean isEnumerator() {
		return codeAttribute.isEnumerator();
	}

	protected boolean isExternalCodeList() {
		CodeAttributeDefinition defn = codeAttribute.getDefinition();
		CodeList list = defn.getList();
		return list.isExternal();
	}
	
}
