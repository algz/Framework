ExtDragDrop = Ext.extend(Ext.dd.DDProxy,{
    constructor : function(config) { // 构造函数
    	config = Ext.apply({
            ignoreSelf : false
        }, config);
        ExtDragDrop.superclass.constructor.call(this, config);
    }
})