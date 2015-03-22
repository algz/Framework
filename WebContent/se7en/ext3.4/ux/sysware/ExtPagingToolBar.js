var ExtPagingToolBar=Ext.extend(Ext.PagingToolbar,{
    displayInfo:true,
    initComponent:function(){
        var pageSizeItems=['每页',
				        this.inputItem = new Ext.form.NumberField({
				            cls:'x-tbar-page-number',
				            allowDecimals:false,
                            id:this.id+"numberInput",
				            allowNegative:false,
				            enableKeyEvents:true,
				            allowBlank : false,
				            invalidText : '请填写每页显示的条数!',
				            blankText : '请填写每页显示的条数!',
				            maxValue : 100,
				            maxText : '每页不能超过100条数据!',
				            minValue : 1,
				            minText : '每页至少1条!',
				            selectOnFocus:true,
				            value:this.pageSize,
				            submitValue:false,
				            listeners:{
				               scope:this,
				               keydown:this.onMyPagingKeyDown
//				               blur:this.onMyPagingKeyBlue       // 监听光标移除事件
				            }
				        }),
				        '条'];
    var userItems=this.items||[];
    this.items=userItems.concat(pageSizeItems);
        ExtPagingToolBar.superclass.initComponent.call(this);
    },
    onMyPagingKeyDown:function(field,e){
       if(field.isValid()){
           var k=e.getKey();
           if(k==e.RETURN){
               e.stopEvent();
               this.pageSize=field.getValue();
               this.moveFirst();
           }
       }
    },
    onMyPagingKeyBlue:function(field){
        if(field.isValid()){
            this.pageSize=field.getValue();
            this.moveFirst();
        }else{
        	field.setValue(this.pageSize);
        }
    }
});
