package org.openforis.collect.metamodel
{
	import flash.utils.Dictionary;
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.rpc.AsyncToken;
	import mx.rpc.AsyncResponder;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.Operation;
	
	import org.openforis.collect.Application;
	import org.openforis.collect.client.ClientFactory;
	import org.openforis.collect.metamodel.proxy.TypedLanguageSpecificTextProxy;
	import org.openforis.collect.util.CollectionUtil;
	import org.openforis.collect.util.ObjectUtil;

	public class CodeListItemsCache {
	
		private var map:Dictionary = new Dictionary();
		private var loadingMap:Dictionary = new Dictionary();
		private var loadCompleteListenersMap:Dictionary = new Dictionary();
	
		public function findAssignableCodeListItems(responder:IResponder, remoteFetchOperation:Operation, attributeDefId:int, ancestorCodes:Array):void {
			var key:String = getKey(attributeDefId, ancestorCodes);
			var data:IList = map[key];
			
			if (data == null) {
				addLoadCompleteListener(key, responder);
				
				var loading:Boolean = loadingMap[key];
				if (!loading) {
					var internalResponder:IResponder = new AsyncResponder(function(event:ResultEvent, token:Object = null):void {
						notifyLoadCompleteListeners(key, event);
						map[key] = event.result;
						loadingMap[key] = false;
					}, responder.fault);
	
					// load items remotely
					loadingMap[key] = true;
					var token:AsyncToken = remoteFetchOperation.send(attributeDefId, ancestorCodes);
					token.addResponder(internalResponder);
				}
			} else {
				responder.result(new ResultEvent(ResultEvent.RESULT, false, true, data));
			}
		}
		
		private function addLoadCompleteListener(key:String, listener:IResponder):IList {
			var listeners:IList = loadCompleteListenersMap[key];
			if (listeners == null) {
				listeners = new ArrayCollection();
				loadCompleteListenersMap[key] = listeners;
			}
			listeners.addItem(listener);
			return listeners;
		}
		
		private function notifyLoadCompleteListeners(key:String, event:ResultEvent):void {
			var listeners:IList = loadCompleteListenersMap[key];
			if (listeners != null) {
				for each(var listener:IResponder in listeners) {
					listener.result(event); 
				}
				delete loadCompleteListenersMap[key];
			}
		}
		
		private function getKey(attributeDefId:int, ancestorCodes:Array):String {
			return attributeDefId + (
				ancestorCodes.length > 0 
					? ("___" + ancestorCodes.join("$$$")) 
					: ""
			);
		} 
	}
}
