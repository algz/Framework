ExtItemSelector = Ext.extend(Ext.form.FormPanel,{
	 ItemSelectorStore : null,
	 
	 ItemSelectorFormItem : null,
	 
	 ItemSelectorFormTbar : null,
	 
	 MultselectStore : null,
	 
	 ItemSelectorRecord : null,
	 
	 StoreLimit : 5,
	 
	 ItemSelectorCurrentPage : 1,
	 
	 ItemSelectorPage : 0,
	 
     constructor : function(config){
     	//覆盖itemSelectorOnRender方法
     	this.overrideOnRender();
     	
     	this.createItemSelectorStore();
     	
     	this.createMultselectStore();
     	
     	this.createItemSelectorRecord();
     	
     	this.createFormTbar();
     	
     	this.createFormItems();
     	
     	// copy其它配置项
        config = Ext.apply({
            store : this.ItemSelectorStore,
            items : this.ItemSelectorFormItem
        }, config);
        //调用父类构造方法
     	ExtItemSelector.superclass.constructor.call(this, config);
     },
     overrideOnRender : function(){
     	Ext.ux.Multiselect.override({
				onRender : function(ct, position) {
					Ext.ux.form.MultiSelect.superclass.onRender.call(this, ct,
							position);
					var fs = this.fs = new Ext.form.FieldSet({
								renderTo : this.el,
								title : this.legend,
								height : this.height,
								width : this.width,
								style : "padding:0;",
								tbar : this.tbar
							});
					fs.body.addClass('ux-mselect');
					this.view = new Ext.ListView({
								multiSelect : true,
								store : this.store,
								columns : [{
											header : 'truename',
											width : .60,
											dataIndex : 'truename'
										}, {
											header : 'department',
											width : .38,
											dataIndex : 'department'
										}],
								hideHeaders : true
							});

					fs.add(this.view);

					this.view.on('click', this.onViewClick, this);
					this.view.on('beforeclick', this.onViewBeforeClick, this);
					this.view.on('dblclick', this.onViewDblClick, this);

					this.hiddenName = this.name || Ext.id();
					var hiddenTag = {
						tag : "input",
						type : "hidden",
						value : "",
						name : this.hiddenName
					};
					this.hiddenField = this.el.createChild(hiddenTag);
					this.hiddenField.dom.disabled = this.hiddenName != this.name;
					fs.doLayout();
				}
			});
     },
     createItemSelectorStore : function(){
     	var scope = this;
    	this.ItemSelectorStore = new Ext.data.Store({
	   		proxy :  new Ext.data.HttpProxy({
		   		url : basePath + '/uiframe/jsp/samples/json/itemselector-data.jsp',
		   		method : 'POST'
	   		}), 
	   		reader : new Ext.data.JsonReader({
				root : 'resultSet',
				totalProperty : 'recordtotal'
			},
			    [
			    	{name : 'userid', mapping : 'userid',type : 'string'},
			        {name : 'truename', mapping : 'truename',type : 'string'},
			        {name : 'department', mapping : 'department',type : 'string'}
			    ]
			), 
            baseParams : {
                start : 0,
                limit : scope.StoreLimit
            },
        	remoteStore : true,
		    autoLoad : true
		});
		return this.ItemSelectorStore;
     },
     createPageFirstButton : function(){
     	  var scope = this;
     	  var pageFirstButton = new Ext.Button({
     	  	    text : "<image src=../uiframe/js/ext3.4/resources/images/default/grid/page-first.gif>",
			    handler : function() {
			    		var totalCount = scope.getStoreTotalCount();
			    		scope.ItemSelectorPage = parseInt((totalCount + scope.StoreLimit- 1)/scope.StoreLimit);
			    		var store = scope.ItemSelectorStore;
			    		store.baseParams = {
			    			start : 0,
			    			limit : scope.StoreLimit
			    		}
			    		store.reload();
						document.getElementById("showpage").innerHTML = "1/" + scope.ItemSelectorPage;
			    }
     	  });
     	  return pageFirstButton;
     },
     createPagePrevButton : function(){
     	  var scope = this;
     	  var pagePrevButton = new Ext.Button({
     	  	    text : "<image src=../uiframe/js/ext3.4/resources/images/default/grid/page-prev.gif>",
			    handler : function() {
			            if (scope.ItemSelectorCurrentPage == 1) {
								return;
							}
							var totalCount = scope.getStoreTotalCount();
							scope.ItemSelectorPage = parseInt((totalCount + scope.StoreLimit- 1) / scope.StoreLimit);
							var start = (scope.ItemSelectorCurrentPage * scope.StoreLimit) - scope.StoreLimit * 2;
							var store = scope.ItemSelectorStore;
				    		store.baseParams = {
				    			start : start,
				    			limit : scope.StoreLimit
				    		}
				    		store.reload();
							scope.ItemSelectorCurrentPage -= 1;
							document.getElementById("showpage").innerHTML = scope.ItemSelectorCurrentPage
									+ "/" + scope.ItemSelectorPage;
			    }
     	  });
     	  return pagePrevButton;
     },
     createPageNextButton : function(){
     	  var scope = this;
     	  var pageNextButton = new Ext.Button({
     	  	    text : "<image src=../uiframe/js/ext3.4/resources/images/default/grid/page-next.gif>",
			    handler : function() {
			    			if (scope.ItemSelectorCurrentPage == scope.ItemSelectorPage) {
								return;
							}
							var totalCount = scope.getStoreTotalCount();
							scope.ItemSelectorPage = parseInt((totalCount + scope.StoreLimit- 1) / scope.StoreLimit);
							var start = (scope.ItemSelectorCurrentPage * scope.StoreLimit);
							var store = scope.ItemSelectorStore;
							store.baseParams = {
				    			start : start,
				    			limit : scope.StoreLimit
				    		}
				    		store.reload();
							scope.ItemSelectorCurrentPage += 1;
							document.getElementById("showpage").innerHTML = scope.ItemSelectorCurrentPage
									+ "/" + scope.ItemSelectorPage;
			    }
     	  });
     	  return pageNextButton;
     },
     createPageLastButton : function(){
     	  var scope = this;
     	  var pageLastButton = new Ext.Button({
     	  	    text : "<image  src=../uiframe/js/ext3.4/resources/images/default/grid/page-last.gif>",
			    handler : function() {
			    	        var totalCount = scope.getStoreTotalCount();
							scope.ItemSelectorPage = parseInt((totalCount + scope.StoreLimit- 1) / scope.StoreLimit);
							var start = (scope.ItemSelectorCurrentPage * scope.StoreLimit) - scope.StoreLimit;
							var store = scope.ItemSelectorStore;
							store.baseParams = {
				    			start : start,
				    			limit : scope.StoreLimit
				    		}
				    		store.reload();
							scope.ItemSelectorCurrentPage = scope.ItemSelectorPage;
							document.getElementById("showpage").innerHTML = scope.ItemSelectorCurrentPage
									+ "/" + scope.ItemSelectorPage;
			    }
     	  });
     	  return pageLastButton;
     },
     createFormTbar : function(){
     	var firstBtn = this.createPageFirstButton();
     	var prevBtn = this.createPagePrevButton();
     	var nextBtn = this.createPageNextButton();
     	var lastBtn = this.createPageLastButton();
     	this.ItemSelectorFormTbar = ["",firstBtn,prevBtn,'<div id="showpage" style="width:47px;text-align:center" >0/3</font></div>',nextBtn,lastBtn]
			return this.ItemSelectorFormTbar;
     },
     createMultselectStore : function(){
     	this.MultselectStore = new Ext.data.Store({
				reader : new Ext.data.JsonReader({
					root : 'resultSet',
					totalProperty : "recordtotal"
						}, [
			    	{name : 'userid', mapping : 'userid',type : 'string'},
			        {name : 'truename', mapping : 'truename',type : 'string'},
			        {name : 'department', mapping : 'department',type : 'string'}
			    ])
	    });
	    
	    return this.MultselectStore;
     },
     createFormItems : function(){
     	var scope = this;
     	this.ItemSelectorFormItem = new Ext.ux.ItemSelector({
     		fieldLabel: 'ItemSelector',
	        imagePath: basePath + '/uiframe/js/ext3.4/ux/images/',
            dataFields : ["userid", "truename"],
			msWidth : 170,
			msHeight : 200,
			valueField : "userid",
			displayField : "truename",
			toLegend : "已选择",
			fromLegend : "未选择",
			fromStore : scope.ItemSelectorStore,
			toStore : scope.MultselectStore,
			fromTBar : scope.ItemSelectorFormTbar
			
     	});
     	return this.ItemSelectorFormItem;
     },
     createItemSelectorRecord : function(){
     	this.ItemSelectorRecord  =  new Ext.data.Record.create([{
				name : 'userid',
				type : 'string'
			}, {
				name : 'truename',
				type : 'string'
			}])
		return this.ItemSelectorRecord
     },
     getStoreTotalCount : function(){
     	return this.ItemSelectorStore.getTotalCount();
     }
     
});