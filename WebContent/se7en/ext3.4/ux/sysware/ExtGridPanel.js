/**
 * sysware框架使用的extGridPanel扩展
 * @class ExtGridPanel
 * @extends Ext.grid.GridPanel
 */
ExtGridPanel = Ext.extend(Ext.grid.GridPanel, {
	//是否自动加载列表数据（默认自动）
	dataAutoLoad : false,
	//store url
	url : null,
	//store 请求方式默认post
	method : 'POST',
	//store 数据模型数组
	dataMapping : null,
	//列表store
	gridPanelStore : null,
	//每页加载数据的条数（默认20）
	gridPanelPageSize : 20,
	//列表列模型
	gridPanelColumnModel : null,
	//gridbber
	gridPanelBbar : null,

    isBbarInput:true,     //是否显示可输入每页条数
	
	gridPanelSelectModel : null,
	
	columnModelMapping : [],
	
	isRowNumber : false,
	
	selectChangeCallback : undefined,

    rowSelectCallback : undefined,
	
	singleSelect : false,

    loadMask:true,
	//选择框控制
	isCheckboxSelection : true,
	
	isGridPanelBar : true,
	
	sortable : false,

    idProperty : "id",  // 数据唯一标识

	menuDisabled : true,

    listeners:{
        afterrender:function(comp){
            //修改tabpanel加载表格，分页栏宽度0的bug
            var tbp= $("#"+comp.id).find(".x-panel-bbar");
            tbp.css("width","100%");
            tbp.children(":first").css("width","100%");

        }
    },
    constructor : function(config) { // 构造方法
		var scope = this;
		if(config && config.gridPanelPageSize){
			this.gridPanelPageSize = config.gridPanelPageSize
		}
		
		if(config && config.selectChangeCallback){
			this.selectChangeCallback = config.selectChangeCallback;
		}
		if(config && config.rowSelectCallback){
			this.rowSelectCallback = config.rowSelectCallback;
		}
		if(config && config.singleSelect == true){
			this.singleSelect = config.singleSelect;
		}
		if(config && config.isCheckboxSelection == false){
			this.isCheckboxSelection = config.isCheckboxSelection;
		}
		if(this.isCheckboxSelection){
			this.gridPanelSelectModel = new Ext.grid.CheckboxSelectionModel({
		    	 singleSelect : scope.singleSelect,
		    	 header : scope.singleSelect ? '<div></div>' : '<div class="x-grid3-hd-checker">&#160;</div>',
		         listeners : {
		         	selectionchange : function(sm){
		         		var smArrays = sm.getSelections(); 
		         		if(scope.selectChangeCallback){
		         			scope.selectChangeCallback(smArrays);
		         		}
		         	},
                     rowselect : function(sm) {
                         if(scope.rowSelectCallback){
                             scope.rowSelectCallback(sm);
                         }
                     }
                 }
		    });
		}
	    if(config && config.isRowNumber == true){
	    	this.isRowNumber = config.isRowNumber;
	    }
	    
		if(config && config.dataAutoLoad == true){
			this.dataAutoLoad = config.dataAutoLoad
		}
        if(config && config.idProperty && config.idProperty != ""){
            this.idProperty = config.idProperty;
        }
		if(config && config.url && config.dataMapping){
			// 列表数据源
			this.url = config.url;
			this.method = config.method;
			this.dataMapping = config.dataMapping;
			this.createGridPanelStore();
		}
		if(config && config.columnModelMapping.length > 0){
			this.columnModelMapping = config.columnModelMapping;
		}
		
		if(config && config.isGridPanelBar == false){
			this.isGridPanelBar = config.isGridPanelBar
		}

		if(config && config.isBbarInput == false){
			this.isBbarInput = config.isBbarInput;
		} else {
            this.isBbarInput = true;
            config.isBbarInput = true;
        }
		
		if(config && config.sortable === true) {
			this.sortable = true;
		}

		
		if(config && config.menuDisabled === false) {
			this.menuDisabled = false;
		}
		// 列表列模型
	 	this.createColumnModel();
	 	
	 	if(this.isGridPanelBar){
	 		// 列表底部工具栏
	 		//控制grid的底部分页栏显示
	 		if(config.isBbarInput == true){
                 this.gridPanelBbar =new ExtPagingToolBar( {
                     pageSize : this.gridPanelPageSize,
                     store : this.gridPanelStore,
                     displayInfo : true,
                     displayMsg : '显示{0}-{1}条，共{2}条',
                     emptyMsg : "无数据"
                 });
	 		}else{
                 this.gridPanelBbar = new Ext.PagingToolbar({ // 定义下方工作面板
                     pageSize : this.gridPanelPageSize,
                     store : this.gridPanelStore,
                     displayInfo : true,
                     displayMsg : '显示{0}-{1}条，共{2}条',
                     emptyMsg : "无数据"
                 });
	 		}
			
	 	}
		// copy其它配置项
        config = Ext.apply({
        	sm : this.gridPanelSelectModel,
            store : this.gridPanelStore,
            cm : this.gridPanelColumnModel,
            bbar : this.gridPanelBbar,
            height : 400,
            border:false,
            enableHdMenu : true,
            enableColumnMove : false,
            stripeRows:true
        }, config);
        // 调用父类构造函数创建实例
        ExtGridPanel.superclass.constructor.call(this, config);
    },
    createGridPanelStore : function(){
    	var scope = this;
    	this.gridPanelStore = new Ext.data.Store({
	   		proxy :  new Ext.data.HttpProxy({
		   		url : scope.url,
		   		method : scope.method
	   		}), 
	   		reader : new Ext.data.JsonReader({
                idProperty :  scope.idProperty,
				root : 'root.datas[0].resultSet',
				totalProperty : 'root.datas[0].recordtotal'
			},
			    scope.dataMapping
			), 
            baseParams : {
                start : 0,
                limit : scope.gridPanelPageSize
            },
        	remoteStore : true,
		    autoLoad : scope.dataAutoLoad
		});
		return this.gridPanelStore;
    },
    createColumnModel : function(){
    	var scope = this;
    	this.gridPanelColumnModel = new Ext.grid.ColumnModel({
    		defaults : {
                sortable : this.sortable,
                menuDisabled : this.menuDisabled
            },
            columns : this.getColumnModelMapping()
    	});
    	return this.gridPanelColumnModel;
    },
    storeLoad : function(queryParams){
        var scope =this;
    	var params = Ext.isObject(queryParams) ? queryParams  : {};
    	var store = this.getStore();
    	store.on('beforeload',function(store,options){
//            store.baseParams = null;
//            store.baseParams = params;
            Ext.apply(options.params,params);
    	});
    	store.load();
    },
    getColumnModelMapping : function(){
    	  var columnModelArray = [];
    	  if(this.isRowNumber){
    	  	 columnModelArray.push(new Ext.grid.RowNumberer());
    	  }
      	  if(this.gridPanelSelectModel){
      	  	 columnModelArray.push(this.gridPanelSelectModel);
      	  }
      	  if(Ext.isArray(this.columnModelMapping)){
      	  	 for(var i = 0 ; i < this.columnModelMapping.length;i++){
      	  	 	columnModelArray.push(this.columnModelMapping[i]);
      	  	 }
      	  }
       return columnModelArray;
    }
    
});