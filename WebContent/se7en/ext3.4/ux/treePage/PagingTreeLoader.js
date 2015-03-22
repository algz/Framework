Ext.ns("Ext.ux.tree");

Ext.ux.tree.PagingTreeLoader = function(config) {
	this.pagingModel = config.pagingModel || "local";
	this.pageSize = config.pageSize || 20;
	this.ptb = false;
	this.mask = new Ext.LoadMask(Ext.getBody(), {
		msg : "加载中..."
	});
	this.ptbConfig = {enableTextPaging:config.enableTextPaging,hideMode:'visibility'};
	
	Ext.ux.tree.PagingTreeLoader.superclass.constructor.apply(this, arguments);
};

Ext.extend(Ext.ux.tree.PagingTreeLoader, Ext.tree.TreeLoader, {

    doPreload : function(node){
		var pi = node.attributes.pagingInfo;
		if(pi == undefined){
			node.attributes.pagingInfo = pi = {limit: this.pageSize,start: 0};
		}
		/*
		 * 动态更新分页参数
		 */
		Ext.apply(pi,{limit:this.pageSize});
		Ext.apply(node.attributes.pagingInfo,{limit:this.pageSize});
		if(this.pagingModel == "local"){
			var children = node.attributes.children;
			if(children){
				var limit = pi.limit;
				var start = pi.start;
				var total = pi.total = children.length;
				
				node.beginUpdate();
				for(var len = (start + limit); start < len && start < total; start++){
					var cn = node.appendChild(this.createNode(children[start]));
					if(this.preloadChildren){
                        this.doPreload(cn);
                    }
				}
				node.endUpdate();
				if(limit < total){
					this.createToolbar(node);
				}
				return true;
			}
		}
		Ext.apply(this.baseParams,pi);
		return false;
    },
		
    processResponse : function(response, node, callback){
        var json = response.responseText;
        try {
            var o = eval("("+json+")");
			var pi = node.attributes.pagingInfo;
			if(this.isArray(o)){
				pi.total = o.length;
			}else{
				pi.total = o.total || o.nodes.length;
				o = o.nodes;
			}
			if(this.pagingModel == 'local'){
				node.attributes.children = o;
			}
			node.beginUpdate();
            for(var i = 0, len = o.length; i < len && i < pi.limit; i++){
				var cn = this.createNode(o[i]);
                if(cn){
					cn = node.appendChild(cn);
                }
            }
            node.endUpdate();

			if(pi.limit < pi.total){
				this.createToolbar(node);
				if(node.attributes.ptb){
					node.attributes.ptb.setVisible(true);
				}
			}else{
				//edit by suny������Ҫʱ����pagingbar
				if(node.attributes.ptb){
					node.attributes.ptb.hide();
				}
			}
			
            if(typeof callback == "function"){
                callback(this, node);
            }
        }catch(e){
            this.handleFailure(response);
        }
    },

	isArray : function(v){
		return v && typeof v.length == 'number' && typeof v.splice == 'function';
	},
	
    handleResponse : function(response){
        this.transId = false;
        var arg = response.argument;
        this.processResponse(response, arg.node, arg.callback);
		this.fireEvent("load", this, arg.node, response);

		this.addMouseOverEvent(arg.node);			
    },

	addMouseOverEvent : function(node){
		var tree = node.ownerTree;
		if(!tree.hasListener('mouseover')){
			tree.on('mouseover',this.onMouseOver,this);
		}
	},
	
	onMouseOver : function(node){
		if(node.isLeaf() ){
			return;
		}
		var ptb = node.attributes.ptb;
		if(ptb){
			if(this.ptb !== ptb){
				if(this.ptb)
					this.ptb.hide();
				this.ptb = ptb;
			}
			var pi = node.attributes.pagingInfo;
			if(pi.limit < pi.total){
				this.ptb.show();
			}else{
				this.ptb.hide();
			}
		}
	},
	
	createToolbar : function(node){
		var ptb = node.attributes.ptb;
		
		if(this.ptb !== ptb){
			if(this.ptb){
				this.ptb.hide();
			}
			var showOnTop = (!node.ownerTree.rootVisible && node.isRoot);
			if(ptb == undefined){
				node.attributes.ptb = ptb = new Ext.ux.tree.PagingTreeToolbar(this.ptbConfig);
				var el = node.getUI().getEl();
				if(!showOnTop){
					el = Ext.get(el.firstChild);
					if(Ext.isIE){
						el = Ext.DomHelper.append(el,{tag:'div',style:'display: inline;white-space:nowrap;position:absolute'},true);
					}else{
						el.addClass('x-grid3-header-inner');
						el = Ext.DomHelper.insertAfter(el,{tag:'div',style:'display: inline;white-space:nowrap;'},true);
					}
				}
				ptb.render(el);
				if(!Ext.isIE){
					var pdom = ptb.getEl().dom;
					pdom.childNodes[0].removeAttribute('class');
				}
			}
			this.ptb = showOnTop ? this.ptb : ptb;
		}
		ptb.setTreeNode(node);
	}
});

Ext.ux.tree.TreeNodeMouseoverPlugin = Ext.extend(Object, {
	init: function(tree) {
		if (!tree.rendered) {
			tree.on('render', function() {this.init(tree)}, this);
		}else{
			this.tree = tree;
			tree.body.on('mouseover', this.onTreeMouseover, this, {delegate: 'div.x-tree-node-el'});
		}
	},
	onTreeMouseover: function(e, t) {
		var nodeEl = Ext.fly(t);
		if (nodeEl) {
			var nodeId = nodeEl.getAttributeNS('ext', 'tree-node-id');
			if (nodeId) {
				this.tree.fireEvent('mouseover', this.tree.getNodeById(nodeId), e);
			}
		}
	}
});



Ext.ux.tree.PagingTreeToolbar = Ext.extend(Ext.Toolbar, {

    firstText : Ext.PagingToolbar.prototype.firstText,
    prevText : Ext.PagingToolbar.prototype.prevText,
    nextText : Ext.PagingToolbar.prototype.nextText,
    lastText : Ext.PagingToolbar.prototype.lastText,
	afterPageText : "<font style='text-align:right;vertical-align:middle;13px arial,tahoma,helvetica,sans-serif;height:18px;'>/{0}</font>",
	
	autoCreate: {style:(Ext.isIE ? 'display: inline;white-space:nowrap;position:absolute;margin:-2px 0px;vertical-align:middle':'')},
	
    // private
    constructor: function(config) {
		
	    var pagingItems = [this.first = new Ext.PagingButton({
	        tooltip: this.firstText,
	        iconCls: "x-tbar-page-first",
	        disabled: true,
	        handler: this.onClick.createDelegate(this, ["first"]),
	        scope: this
	    }), this.prev = new Ext.PagingButton({
	        tooltip: this.prevText,
	        iconCls: "x-tbar-page-prev",
	        disabled: true,
	        handler: this.onClick.createDelegate(this, ["prev"]),
	        scope: this
	    })];
		
		if(config.enableTextPaging === true){
			pagingItems.push(
				this.inputItem = new Ext.Toolbar.Item({
					height: 18,
					autoEl: {
						tag: "input",
						type: "text",
      					size: "5",
						value: "1",
						style: "width:24px;height:20px;text-align:right;vertical-align:middle;font:13px arial,tahoma,helvetica,sans-serif;"
//						cls: "x-tbar-page-number"
					}
				}), this.afterTextItem = new Ext.Toolbar.TextItem({
					text: String.format(this.afterPageText, 1)
				})
			);
			config.width = (Ext.isIE ? 200 : this.width);
		}else{
			pagingItems.push(new Ext.Toolbar.TextItem({text: "&nbsp;"}));
			config.width = (Ext.isIE ? 40 : this.width);
		}
		pagingItems.push(
				this.next = new Ext.PagingButton({
		            tooltip: this.nextText,
			        iconCls: "x-tbar-treepage-next",
			        disabled: true,
			        handler: this.onClick.createDelegate(this, ["next"]),
			        scope: this
			    }), this.last = new Ext.PagingButton({
			        tooltip: this.lastText,
			        iconCls: "x-tbar-page-last",
			        disabled: true,
			        handler: this.onClick.createDelegate(this, ["last"]),
			        scope: this
			    })
		);
		
		config.items = pagingItems;
		delete config.buttons;
	    Ext.ux.tree.PagingTreeToolbar.superclass.constructor.apply(this, arguments);
	},
    
    initComponent: function(){
        Ext.ux.tree.PagingTreeToolbar.superclass.initComponent.call(this);
		if(this.enableTextPaging === true){
			this.on('afterlayout', this.onFirstLayout, this, {single: true});
		}
    },

    // private
	onFirstLayout: function(ii) {
		this.mon(this.inputItem.el, "keydown", this.onPagingKeyDown, this);
		this.mon(this.inputItem.el, "focus", function(){this.dom.select()});
        this.field = this.inputItem.el.dom;
	},
	
    // private
    onClick : function(which){
		switch(which){
            case "first":
				this.pi.start = 0;
            break;
            case "prev":
				this.pi.start = Math.max(0, this.pi.start-this.pi.limit);
            break;
            case "next":
				this.pi.start = this.pi.start+this.pi.limit;
            break;
            case "last":
                var total = this.pi.total;
                var extra = total % this.pi.limit;
                var lastStart = extra ? (total - extra) : (total-this.pi.limit);
				this.pi.start = lastStart;
            break;
        }

		this.updateField();
		if(!this.treeNode.reload){
			this.toAsynTreeNode();
		}
		this.treeNode.reload();
    },

	// private
	updateField : function(){
		if(this.enableTextPaging === true){
			var d = this.getPageData(), ap = d.activePage, ps = d.pages;
			this.afterTextItem.setText(String.format(this.afterPageText, d.pages));
			this.field.value = ap;
		}
	},

	// private
    onPagingKeyDown : function(e){
		var k = e.getKey(), d = this.getPageData(), pageNum;
		if (k == e.RETURN) {
			e.stopEvent();
			pageNum = this.readPage(d);
			if(pageNum !== false){
				pageNum = Math.min(Math.max(1, pageNum), d.pages) - 1;
				this.pi.start = pageNum * this.pi.limit;
				if(!this.treeNode.reload){
					this.toAsynTreeNode();
				}
                this.treeNode.reload();
            }
        }else if (k == e.HOME || k == e.END){
            e.stopEvent();
            pageNum = k == e.HOME ? 1 : d.pages;
			this.field.value = pageNum;
        }else if (k == e.UP || k == e.PAGEUP || k == e.DOWN || k == e.PAGEDOWN){
            e.stopEvent();
            if(pageNum = this.readPage(d)){
                var increment = e.shiftKey ? 10 : 1;
                if(k == e.DOWN || k == e.PAGEDOWN){
                    increment *= -1;
                }
                pageNum += increment;
                if(pageNum >= 1 & pageNum <= d.pages){
					this.field.value = pageNum;
                }
            }
        }
    },
   
    // private
    onDestroy : function(){
        Ext.ux.tree.PagingTreeToolbar.superclass.onDestroy.call(this);
    },

	// private
    readPage : function(d){
		var v = this.field.value,pageNum;
        if (!v || isNaN(pageNum = parseInt(v, 10))) {
			this.field.value = d.activePage;
            return false;
        }
        return pageNum;
    },

    // private
    getPageData : function(){
		var pi = this.pi;
        var total = pi.total;
        return {
            total : total,
            activePage : Math.ceil((pi.start+pi.limit)/pi.limit),
            pages :  total < pi.limit ? 1 : Math.ceil(total/pi.limit)
        };
    },

	// private
	resetToolBar : function(){
		var fp = this.pi.start == 0;
		var nl = (this.pi.start + this.pi.limit) >= this.pi.total;

		this.first.setDisabled(fp);
        this.prev.setDisabled(fp);
        this.next.setDisabled(nl);
        this.last.setDisabled(nl);
		
		this.updateField();
	},

	setTreeNode : function(node){
		this.treeNode = node;
		this.pi = this.treeNode.attributes.pagingInfo;

		this.resetToolBar();
	},
	
	toAsynTreeNode : function(){
		this.treeNode.expand=function(deep, anim, callback, scope){
	        if(this.loading){ // if an async load is already running, waiting til it's done
	            var timer;
	            var f = function(){
	                if(!this.loading){ // done loading
	                    clearInterval(timer);
	                    this.expand(deep, anim, callback, scope);
	                }
	            }.createDelegate(this);
	            timer = setInterval(f, 200);
	            return;
	        }
	        if(!this.loaded){
	            if(this.fireEvent("beforeload", this) === false){
	                return;
	            }
	            this.loading = true;
	            this.ui.beforeLoad(this);
	            var loader = this.loader || this.attributes.loader || this.getOwnerTree().getLoader();
	            if(loader){
	                loader.load(this, this.loadComplete.createDelegate(this, [deep, anim, callback, scope]), this);
	                return;
	            }
	        }
	        
	        if(!this.expanded){
	            if(this.fireEvent('beforeexpand', this, deep, anim) === false){
	                return;
	            }
	            if(!this.childrenRendered){
	                this.renderChildren();
	            }
	            this.expanded = true;
	            if(!this.isHiddenRoot() && (this.getOwnerTree().animate && anim !== false) || anim){
	                this.ui.animExpand(function(){
	                    this.fireEvent('expand', this);
	                    this.runCallback(callback, scope || this, [this]);
	                    if(deep === true){
	                        this.expandChildNodes(true);
	                    }
	                }.createDelegate(this));
	                return;
	            }else{
	                this.ui.expand();
	                this.fireEvent('expand', this);
	                this.runCallback(callback, scope || this, [this]);
	            }
	        }else{
	           this.runCallback(callback, scope || this, [this]);
	        }
	        if(deep === true){
	            this.expandChildNodes(true);
	        }
	    };
	    this.treeNode.isLoading=function(){
	        return this.loading;  
	    };
	    this.treeNode.loadComplete=function(deep, anim, callback, scope){
	        this.loading = false;
	        this.loaded = true;
	        this.ui.afterLoad(this);
	        this.fireEvent("load", this);
	        this.expand(deep, anim, callback, scope);
	    };
	    this.treeNode.isLoaded=function(){
	        return this.loaded;
	    };
	    this.treeNode.hasChildNodes=function(){
	        if(!this.isLeaf() && !this.loaded){
                 return true;
            }else{
                 return Ext.tree.TreeNode.superclass.hasChildNodes.call(this);
             }
        };
	    this.treeNode.isLoading=function(){
	        return this.loading;  
	    };
	    this.treeNode.reload=function(callback, scope){
	        this.collapse(false, false);
	        while(this.firstChild){
	            this.removeChild(this.firstChild).destroy();
	        }
	        this.childrenRendered = false;
	        this.loaded = false;
	        if(this.isHiddenRoot()){
	            this.expanded = false;
	        }
	        this.expand(false, false, callback, scope);
	    };
	}
	
	
	
});


Ext.PagingButton = Ext.extend(Ext.Button,{
    // private
    onRender : function(ct, position){
        if(!this.template){
			this.template = new Ext.Template(
				'<table cellspacing="0" class="x-btn {3}"><tbody class="{4}">',
				'<tr><td class="x-btn-mc"><em class="{5}" unselectable="on"><button class="x-btn-text {2}" type="{1}" style="width:15px">{0}</button></em></td></tr>',
				"</tbody></table>");
			this.template.compile();
        }
	    Ext.PagingButton.superclass.onRender.apply(this, arguments);
    }
});
