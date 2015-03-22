ExtComboxTree = Ext.extend(Ext.ux.ComboBoxTree,{
	treeUrl : null,// 数的url
	
	comboxTreeLoader : null, // 树数据源
	
	treePanel : null,// 树panel
	
	treeHeight : 150,

    treeBodyStyle: null, // 定义树的行内样式

    id:undefined,

    ischecked:false,       //是否多选，默认false

    checkAllButton:false,       //是否显示多选按钮，默认false

    treeWidth  : 200,
    // 设置根节点是否隐藏（默认true）
    treeRootVisible : true,
    
    treeRoot : {
    	id : '0',
        nodeType: 'async',
        text: 'Ext JS Tree'
    },
    clear : null,
	
	constructor : function(config) { // 构造函数
		var scope = this;
		
		if(config && config.id) {
			this.id = config.id;
		} else {
            this.id = "comboxTreeId" + new Date().getTime();
        }

		if(config && config.treeUrl) {
			this.treeUrl = config.treeUrl;
		}
		
		if(config && config.treeHeight) {
			this.treeHeight = config.treeHeight;
		}
		
		if(config && config.treeWidth) {
			this.treeWidth = config.treeWidth;
            this.listWidth = config.treeWidth;
		}

        if(config && config.listWidth) {
			this.listWidth = config.listWidth;
		}
		
		if(config && config.treeRootVisible == false) {
			this.treeRootVisible = config.treeRootVisible;
		}
		
		if(config && config.treeRoot) {
			this.treeRoot = config.treeRoot;
		}

		if(config && config.ischecked) {
			this.ischecked = config.ischecked;
		}

		if(config && config.checkAllButton) {
			this.checkAllButton = config.checkAllButton;
		}

		if(config && config.treeBodyStyle) {
			this.treeBodyStyle = config.treeBodyStyle;
		}
		
		if(config && config.autoExpand) {
			this.autoExpand = config.autoExpand;
		}

        if(config && config.clear) {
            this.clear = config.clear;
        }
		this.createComboxTreeLoader();
		this.createTreePanel();
		
		config = Ext.apply({
            lazyRender:true,
            tree : scope.treePanel,
            height : scope.treeHeight,
            ischecked : scope.ischecked,
            width : scope.treeWidth
        }, config);



        // 调用父类构造函数创建实例
        ExtComboxTree.superclass.constructor.call(this, config);
        if($.browser.msie && $.browser.version==6){
         $("#"+config.renderTo).find(".x-form-trigger").css("margin-left", this.treeWidth - 24);
        } else {
         $("#"+config.renderTo).find(".x-form-trigger").css("margin-left", this.treeWidth - 23);
        }
	},
	
	createComboxTreeLoader : function() { // 树数据加载器
		var scope = this;
		ExtComboxTreeLoader = function(config){
			ExtComboxTreeLoader.superclass.constructor.call(this, config);
		}
		Ext.extend(ExtComboxTreeLoader,Ext.tree.TreeLoader,{
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
		this.comboxTreeLoader = new ExtComboxTreeLoader({
			dataUrl : scope.treeUrl
		});
	},
	createTreePanel : function() { //数据加载器
		var scope = this;
        var buttons = [];
        if (scope.checkAllButton) {
            buttons =  [{
                text : '全选',
                handler : function(){
                    var rootNode = Ext.getCmp(scope.id +"Tree").getRootNode();
                    scope.checkNode(rootNode)
                }
            },{
                text : '清空',
                handler : function(){
                    scope.clearValue();
                    var nodes = Ext.getCmp(scope.id +"Tree").getChecked();
                    if (nodes && nodes.length) {
                        for(var i = 0; i < nodes.length; i++){
                            nodes[i].getUI().toggleCheck(false);
                            nodes[i].attributes.checked = false;
                        }
                    }
                    if(scope.clear){
                        scope.clear();
                    }
                }
            }]
        } else {
            buttons = [{
                text : '清空',
                handler : function(){
                    scope.clearValue();
                    var nodes = Ext.getCmp(scope.id +"Tree").getChecked();
                    if (nodes && nodes.length) {
                        for(var i = 0; i < nodes.length; i++){
                            nodes[i].getUI().toggleCheck(false);
                            nodes[i].attributes.checked = false;
                        }
                    }
                    if(scope.clear){
                        scope.clear();
                    }
                }
            }]
        }
		this.treePanel = new Ext.tree.TreePanel({
			loader : scope.comboxTreeLoader,
            bodyStyle:scope.treeBodyStyle,
            id:scope.id +"Tree",
            autoScroll: true,
            height: scope.treeHeight,
            width : scope.treeWidth,
            border : false,
            rootVisible : scope.treeRootVisible,
            root : scope.treeRoot,
            buttons : buttons,
            //extjs下拉树动态改变按钮table宽度和位置及显示样式
            listeners:{'afterrender':function(){
                $("#"+scope.id +"Tree").find(".x-toolbar-ct").width(50);
                $("#"+scope.id +"Tree").find(".x-btn").addClass("uiframe-comboxTreeBtn");
                $("#"+scope.id +"Tree").find(".x-btn-noicon").addClass("uiframe-comboxTreeNoicon");
                $("#"+scope.id +"Tree").find(".x-btn-noicon").width(50);
                $("#"+scope.id +"Tree").find("button").css({padding:"0 8px",width:"46px"});
            }}
		});
	},
	setValue:function(node) {
        if(node==undefined||node.id=='treeRootNode')return;
        Ext.form.ComboBox.superclass.setValue.call(this, node.text);
        this.treeIds = node.id;
        this.treeTexts = node.text;
        if (this.hiddenField) {
            this.hiddenField.value = node.id;
        }
        sywFunction.removeError(this.el.id);
    },
    getValue:function() {
        return typeof this.value != 'undefined' ? this.value : '';
    },
    checkNode:function(node) {
        var scope = this;
        node.eachChild(function(child){
            child.getUI().toggleCheck(true);
            child.attributes.checked = true;
            scope.checkNode(child);
        });
    },
    nodeSelect : function(arrId, pos, dest){
    	var scope = this;
    	var node = this.treePanel.getNodeById(arrId[pos]);
		if (node) {
			node.expand(false, false, function(node){
				var tmpNode = scope.treePanel.getNodeById(dest);
				if (tmpNode) {
					tmpNode.select();
				} else {
					nodeSelect(arrId, (pos + 1), dest);
				}
			});
		}
    },
    treeNodeInvertSelect : function(nodeids){
    	if(nodeids){
	    	var scope = this;
	    	this.on("expand",function(){
	    	    this.treePanel.on("expandnode",function(node){
	    	         node.expand(false, false, function(node){
	    	         	var arr = nodeids.split('/');
						var dest = arr[arr.length - 1];
						arr.length = arr.length - 1;
						scope.nodeSelect(arr, 0, dest);
					});
	    	    });
	    	})
    	}
	},
	//为用户模块访问后台提供的函数
	getTreeNodeByNodeId : function(nodeid,loadurl,callback){
		var result = null;
		if(nodeid && loadurl){
			  Ext.Ajax.request({
				   url : loadurl,
				   method : 'POST', 
				   success: function(response, opts) {
				       var obj = Ext.decode(response.responseText);
				       result = obj.root.datas[0];
				       callback(result);
				   },
				   failure: function(response, opts) {
				   },
				   params: { 
				   		depId : nodeid
				   }
			  });
		}
		return result
	},
    //extjs下拉树增加uiframe的表单验证2012-07-08  hemq
    listeners:{'afterrender':function(panel){
        if(panel.renderTo && $("#"+panel.renderTo).attr("cls")){
            $("#"+panel.el.id).addClass($("#"+panel.renderTo).attr("cls"));
        }
    }}
});

Ext.reg("extComboxTree", ExtComboxTree);