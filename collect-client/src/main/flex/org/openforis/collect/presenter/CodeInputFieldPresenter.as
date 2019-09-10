package org.openforis.collect.presenter {
	import mx.binding.utils.ChangeWatcher;
	import mx.collections.IList;
	import mx.events.PropertyChangeEvent;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	import org.openforis.collect.Application;
	import org.openforis.collect.metamodel.proxy.CodeAttributeDefinitionProxy;
	import org.openforis.collect.metamodel.proxy.CodeListItemProxy;
	import org.openforis.collect.model.proxy.AttributeProxy;
	import org.openforis.collect.model.proxy.CodeAttributeProxy;
	import org.openforis.collect.model.proxy.EntityProxy;
	import org.openforis.collect.model.proxy.FieldProxy;
	import org.openforis.collect.ui.component.input.CodeInputField;
	import org.openforis.collect.util.CollectionUtil;
	import org.openforis.collect.util.ObjectUtil;
	import org.openforis.collect.util.StringUtil;
	
	/**
	 * 
	 * @author S. Ricci
	 * @author M. Togna
	 * */
	public class CodeInputFieldPresenter extends InputFieldPresenter {
		
		private var _lastLoadCodesAsyncToken:AsyncToken;
		
		public function CodeInputFieldPresenter(view:CodeInputField) {
			super(view);
			view.fieldIndex = -1;
		}
		
		private function get view():CodeInputField {
			return CodeInputField(_view);
		}

		override protected function initEventListeners():void {
			super.initEventListeners();
			ChangeWatcher.watch(view, "attributes", attributesChangeHandler);
		}
		
		public function loadCodes(view:CodeInputField, resultHandler:Function):void {
			var codeAttributeDef:CodeAttributeDefinitionProxy = view.attributeDefinition as CodeAttributeDefinitionProxy;
			var parentEntityId:int = view.parentEntity.id;
			var ancestorCodes:Array = getAncestorCodes();
			function loadCodesResultHandler(event:ResultEvent, token:Object = null):void {
				var selectedCodes:Array = getSelectedCodes();
				for each (var item:CodeListItemProxy in event.result) {
					item.selected = selectedCodes.indexOf(item.code) >= 0;
				}
				resultHandler(event);
			}
			var responder:IResponder = new AsyncResponder(loadCodesResultHandler, faultHandler);
			_lastLoadCodesAsyncToken = dataClient.findAssignableCodeListItems(responder, codeAttributeDef.id, ancestorCodes);
		}
		
		override protected function getTextFromValue():String {
			if(view.attributeDefinition == null) {
				return "";
			} else {
				return codeAttributeToText(view.attribute);
			}
		}
		
		protected function codeAttributeToText(attribute:AttributeProxy):String {
			if(attribute != null) {
				var field:FieldProxy = attribute.getField(0);
				if(field.symbol != null) {
					var shortCut:String = FieldProxy.getShortCutForReasonBlank(field.symbol);
					if(shortCut != null) {
						return shortCut;
					}
				}
				var code:String = field.value as String;
				var qualifierField:FieldProxy = attribute.getField(1);
				var qualifier:String = qualifierField.value as String;
				return getTextValue(code, qualifier);
			}
			return "";
		}
		
		protected function getTextValue(code:String, qualifier:String):String {
			return StringUtil.concat(": ", code, qualifier);
		}
		
		override protected function getField():FieldProxy {
			if (view.hasOwnProperty("attributes")) {
				var attributes:IList = ObjectUtil.getValue(view, "attributes");
				if (CollectionUtil.isEmpty(attributes)) {
					return super.getField();
				} else {
					var attr:AttributeProxy = AttributeProxy(attributes.getItemAt(0));
					var field:FieldProxy = attr.getField(0);
					return field;
				}
			} else {
				return super.getField();
			}
		}

		protected function attributesChangeHandler(event:PropertyChangeEvent):void {
			view.changed = false;
			view.visited = false;
			view.updating = false;
			updateView();
		}
		
		private function getAncestorCodes():Array {
			var result = new Array();
			var codeAttributeDef:CodeAttributeDefinitionProxy = view.attributeDefinition as CodeAttributeDefinitionProxy;
			var currentCodeAttributeDef:CodeAttributeDefinitionProxy = codeAttributeDef;
			while(!isNaN(currentCodeAttributeDef.parentCodeDefinitionId)) {
				var parentCodeAttributeDef:CodeAttributeDefinitionProxy = Application.activeSurvey.schema.getDefinitionById(codeAttributeDef.parentCodeDefinitionId) as CodeAttributeDefinitionProxy;
				var parentCodeAttribute:CodeAttributeProxy = findCodeAttributeInAncestors(parentCodeAttributeDef);
				result.unshift(parentCodeAttribute.code);
				currentCodeAttributeDef = parentCodeAttributeDef;
			}
			return result;
		}
		
		private function findCodeAttributeInAncestors(codeAttributeDef:CodeAttributeDefinitionProxy):CodeAttributeProxy {
			var ancestor:EntityProxy = view.parentEntity;
			while (ancestor != null && ancestor.definition.id != codeAttributeDef.parent.id) {
				ancestor = ancestor.parent;
			}
			return ancestor == null ? null : ancestor.getChild(codeAttributeDef) as CodeAttributeProxy;
		}
		
		private function getSelectedCodes():Array {
			var selectedCodes:Array = new Array();
			var codeAttributeDef:CodeAttributeDefinitionProxy = view.attributeDefinition as CodeAttributeDefinitionProxy;
			var siblings:IList = view.parentEntity.getChildren(codeAttributeDef);
			for each (var sibling:CodeAttributeProxy in siblings) {
				var code:String = sibling.code;
				if (code != null && code.length > 0) {
					selectedCodes.push(code);
				}
			}
			return selectedCodes;
		}
	}
}
