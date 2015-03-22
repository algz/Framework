ExtTreePanel = Ext.extend(Ext.tree.TreePanel,{
	
	treeLoader : null,
	
	treeRootVisible : false,
	
	treeRoot:{
                nodeType: 'async',
                text: 'Ext JS Tree'
            },
	
	constructor : function(config) {
		var scope = this;
//		this.createTreeLoader();
		 config = Ext.apply({
			frame : false,
			borde : false,
            autoScroll: true,
            border : false,
            rootVisible : scope.treeRootVisible,
            root : scope.treeRoot
        }, config);
		
		ExtTreePanel.superclass.constructor.call(this, config);
	},
	createTreeLoader : function() { // 树数据加载器
		var scope = this;
		ExtTreeLoader = function(config){
			ExtTreeLoader.superclass.constructor.call(this, config);
		}
		Ext.extend(ExtTreeLoader,Ext.tree.TreeLoader,{
			processResponse : function(response,node,callback){
				var json = response.responseText;
				try{
					var o = response.responseData || Ext.decode(json);
					if(Ext.type(o) == "object"){
						o = o[this.rootName || "root"].datas;
					}
					node.beginUpdate();
					for(var i=0,len = o.length;i<len;i++){
						var n= this.createNode(o[i]);
						if(n){
							node.appendChild(n);
						}
					}
					this.runCallback(callback,scope || node,[node]);
				}catch(e){
					this.handleFailure(response);
				}
			}
		});
		this.treeLoader = new ExtTreeLoader({
			dataUrl : scope.url
		});
	}
})