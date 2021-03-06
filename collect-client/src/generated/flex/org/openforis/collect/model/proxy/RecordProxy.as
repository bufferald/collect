/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package org.openforis.collect.model.proxy {
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	
	import org.openforis.collect.event.ApplicationEvent;
	import org.openforis.collect.event.EventDispatcherFactory;
	import org.openforis.collect.metamodel.proxy.EntityDefinitionProxy;
	import org.openforis.collect.metamodel.proxy.NodeDefinitionProxy;
	import org.openforis.collect.metamodel.proxy.SurveyProxy;
	import org.openforis.collect.util.ArrayUtil;

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.model.proxy.RecordProxy")]
    public class RecordProxy extends RecordProxyBase {
		
		private var _survey:SurveyProxy;
		private var _nodesMap:Dictionary;
		private var _updated:Boolean;
		
		private var validationResults:ValidationResultsProxy;

		public function RecordProxy():void {
			super();
			_updated = false;
		}
		
		public function init():void {
			_nodesMap = new Dictionary();
			traverse(associateDefinition);
			traverse(initNode);
		}
		
		/**
		 * Traverse all the record's nodes and execute the argument function passing
		 * the visited node to it
		 * */
		public function traverse(funct:Function):void {
			if ( rootEntity != null ) {
				funct(rootEntity);
				rootEntity.traverse(funct);
			}
		}
		
		protected function associateDefinition(node:NodeProxy):void {
			var defn:NodeDefinitionProxy = survey.schema.getDefinitionById(node.definitionId);
			node.definition = defn;
		}
		
		protected function initNode(node:NodeProxy):void {
			_nodesMap[node.id] = node;
			node.record = this;
			node.init();
		}
		
		public function getNode(id:int):NodeProxy {
			return _nodesMap[id];
		}
		
		public function update(changeSet:NodeChangeSetProxy, requestSet:NodeUpdateRequestSetProxy):void {
			updateNodes(requestSet);
			this.errors = changeSet.errors;
			this.skipped = changeSet.skipped;
			this.missing = changeSet.missing;
			this.missingErrors = changeSet.missingErrors;
			this.missingWarnings = changeSet.missingWarnings;
			this.warnings = changeSet.warnings;
			
			for each (var change:NodeChangeProxy in changeSet.changes)	{
				applyChange(change);
			}
			_updated = ! changeSet.recordSaved;
			var appEvt:ApplicationEvent = new ApplicationEvent(ApplicationEvent.UPDATE_RESPONSE_RECEIVED);
			appEvt.result = changeSet;
			EventDispatcherFactory.getEventDispatcher().dispatchEvent(appEvt);
		}
		
		private function updateNodes(reqSet:NodeUpdateRequestSetProxy):void {
			var attr:AttributeProxy;
			var field:FieldProxy;
			for each (var req:NodeUpdateRequestProxy in reqSet.requests) {
				if ( req is RemarksUpdateRequestProxy ) {
					var updRemarksReq:RemarksUpdateRequestProxy = RemarksUpdateRequestProxy(req);
					attr = getNode(updRemarksReq.nodeId) as AttributeProxy;
					field = attr.getField(updRemarksReq.fieldIndex);
					field.remarks = updRemarksReq.remarks;
				} else if ( req is FieldUpdateRequestProxy ) {
					var updFieldReq:FieldUpdateRequestProxy = FieldUpdateRequestProxy(req);
					attr = getNode(updFieldReq.nodeId) as AttributeProxy;
					field = attr.getField(updFieldReq.fieldIndex);
					field.symbol = updFieldReq.symbol;
					field.remarks = updFieldReq.remarks;
				} else if ( req is AttributeUpdateRequestProxy ) {
					var updAttrReq:AttributeUpdateRequestProxy = AttributeUpdateRequestProxy(req);
					attr = getNode(updAttrReq.nodeId) as AttributeProxy;
					for each (field in attr.fields) {
						field.symbol = updAttrReq.symbol;
						field.remarks = updAttrReq.remarks;
					}
				} else if ( req is DefaultValueApplyRequestProxy ) {
					attr = getNode(DefaultValueApplyRequestProxy(req).nodeId) as AttributeProxy;
					for each (field in attr.fields) {
						field.symbol = null;
					}
				} else if ( req is ConfirmErrorRequestProxy ) {
					attr = getNode(ConfirmErrorRequestProxy(req).nodeId) as AttributeProxy;
					if ( attr != null ) {
						attr.errorConfirmed = true;
					}
				}
			}
		}
		
		private function applyChange(change:NodeChangeProxy):void {
			var node:NodeProxy = getNode(change.nodeId);
			if (node != null) {
				change.nodeName = node.name;
				change.parentEntityId = node.parent == null ? NaN : node.parent.id;
			}
			
			if ( change is NodeDeleteChangeProxy ) {
				processNodeDeleteResponse(NodeDeleteChangeProxy(change));
			} else {
				if ( change is NodeAddChangeProxy ) {
					processNodeAddResponse(NodeAddChangeProxy(change));
				}
				if ( change is AttributeChangeProxy ) {
					processAttributeUpdateResponse(AttributeChangeProxy(change));
				} else if ( change is EntityChangeProxy ) {
					processEntityUpdateResponse(EntityChangeProxy(change));
				}
			}
		}
		
		protected function processNodeAddResponse(response:NodeAddChangeProxy):void {
			var node:NodeProxy = NodeAddChangeProxy(response).createdNode;
			associateDefinition(node);
			if ( node is EntityProxy ) {
				EntityProxy(node).traverse(associateDefinition);
			}
			var parent:EntityProxy = getNode(node.parentId) as EntityProxy;
			parent.addChild(node);
			initNode(node);
			if(node is EntityProxy) {
				//initialize descendants
				EntityProxy(node).traverse(initNode);
				//show errors on descendants if they are not empty (default value applied)
				EntityProxy(node).showErrorsOnNotEmptyDescendants();
			}
		}
		
		protected function processNodeDeleteResponse(change:NodeDeleteChangeProxy):void {
			if ( change.deletedNodeId > 0 ) {
				var node:NodeProxy = getNode(change.deletedNodeId);
				if (node != null ) {
					if ( node is EntityProxy ) {
						EntityProxy(node).traverse(function(descendant:NodeProxy):void {
							_nodesMap[descendant.id] = null;
						});
					}
					var parent:EntityProxy = getNode(node.parentId) as EntityProxy;
					parent.removeChild(node);
					_nodesMap[node.id] = null;
				}
			}
		}
		
		protected function processAttributeUpdateResponse(change:AttributeChangeProxy):void {
			var node:NodeProxy = getNode(change.nodeId);
			if ( node == null ) {
				//node not shown in UI: skip it
				return;
			}
			var a:AttributeProxy = AttributeProxy(node);

			if ( change.validationResults != null ) {
				a.validationResults = change.validationResults;
			}
			if ( change.updatedFieldValues != null ) {
				var fieldIdxs:ArrayCollection = change.updatedFieldValues.keySet;
				for each (var i:int in fieldIdxs) {
					var f:FieldProxy = a.getField(i);
					f.value = change.updatedFieldValues.get(i);
				}
				a.errorConfirmed = false;
				var parent:EntityProxy = getNode(node.parentId) as EntityProxy;
				if ( parent != null ) {
					parent.updateKeyText();
				}
			}
		}
		
		protected function processEntityUpdateResponse(change:EntityChangeProxy):void {
			var node:NodeProxy = getNode(change.nodeId);
			var e:EntityProxy = node as EntityProxy;
			if ( change.maxCountValidation != null && change.maxCountValidation.length > 0 ) {
				e.updateChildrenMaxCountValiditation(change.maxCountValidation);
			}
			if ( change.minCountValidation != null && change.minCountValidation.length > 0 ) {
				e.updateChildrenMinCountValiditation(change.minCountValidation);
			}
			if ( change.relevant != null && change.relevant.length > 0 ) {
				e.updateChildrenRelevance(change.relevant);
			}
			if ( change.minCountByChildDefinitionId != null && change.minCountByChildDefinitionId.length > 0 ) {
				e.updateChildrenMinCount(change.minCountByChildDefinitionId);
			}
			if ( change.maxCountByChildDefinitionId != null && change.maxCountByChildDefinitionId.length > 0 ) {
				e.updateChildrenMaxCount(change.maxCountByChildDefinitionId);
			}
		}
		
		public function showErrors():void {
			traverse(function (node:NodeProxy):void {
				if (node is EntityProxy) {
					EntityProxy(node).showErrorsOnChildren();
				}
			});
		}
		
		public function get unassigned():Boolean {
			return owner == null;
		}
		
		public function get survey():SurveyProxy {
			return _survey;
		}
		
		public function set survey(value:SurveyProxy):void {
			_survey = value;
		}
		
		public function get updated():Boolean {
			return _updated;
		}
		
		public function set updated(value:Boolean):void {
			 _updated = value;
		}

    }
}