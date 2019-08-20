package org.openforis.collect.metamodel
{
	import flash.utils.Dictionary;
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.rpc.AsyncToken;
	import mx.rpc.AsyncResponder;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	import org.openforis.collect.Application;
	import org.openforis.collect.client.ClientFactory;
	import org.openforis.collect.metamodel.proxy.TypedLanguageSpecificTextProxy;
	import org.openforis.collect.util.CollectionUtil;
	import org.openforis.collect.util.ObjectUtil;

	public class CodeListItemsCache {
	
		private var map:Dictionary = new Dictionary();
		private var loadingMap:Dictionary = new Dictionary();
		private var loadCompleteListenersMap:Dictionary = new Dictionary();
	
		public function findAssignableCodeListItems(responder:IResponder, remoteFetchFunction:Function, attributeDefId:int, parentCodeListItemId:int = 0):void {
			var key:Key = new Key(attributeDefId, parentCodeListItemId);
			var data:IList = map[key];
			if (data != null) {
				responder.result(data);
			} else if (loadingMap[key] == true) {
				var listeners:IList = loadCompleteListenersMap[key];
				if (listeners == null) {
					listeners = new ArrayCollection();
					loadCompleteListenersMap[key] = listeners;
				}
				listeners.addItem(responder);
			} else {
				var internalResponder:IResponder = new AsyncResponder(function(event:ResultEvent):void {
					var listeners:IList = loadCompleteListenersMap[key];
					for each(var listener:IResponder in listeners) {
						listener.result(event.result); 
					}
					listeners.clear();
					map[key] = event.result;
					loadingMap[key] = false;
					loadCompleteListenersMap[key] = null;
				}, responder.fault);

				// load items remotely
				loadingMap[key] = true;
				var token:AsyncToken = remoteFetchFunction(attributeDefId, parentCodeListItemId);
				token.addResponder(internalResponder);
			}
		}
	}
}

class Key {
	private var parentCodeListItemId:int;
	private var attributeDefId:int;
	
	public function Key(attributeDefId:int, parentCodeListItemId:int) {
		this.attributeDefId = attributeDefId;
		this.parentCodeListItemId = parentCodeListItemId;
	}
}
