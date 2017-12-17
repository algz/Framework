/**
 * html:
 * <table id="table" class="table table-striped table-bordered table-hover"></table>
 */
//所有dataTable 相关的事件都必须放到配置函数后,不然无法执行.
var Dtable = $('#table').DataTable();

//1.单选.首先获取datatable对象(注意大小写`DataTable()`)：所有 table 有效,需放在所有 dataTable 后面
$('table tbody').on('click', 'tr', function () {
	/*选中框
	var curCheckbox=$(this).find(':checkbox')[0];
	var curCheckVal=curCheckbox.checked
	$(this).closest('table').find(':checkbox').attr('checked',false); //清除所有的选中框
    if(curCheckbox==null){
	 	return;
	 }
    curCheckbox.checked=!curCheckVal;
    */
	
	/* 
	if ($(this).hasClass('selected') ) {
       $(this).removeClass('selected');
    } else {
       $(this).siblings(".selected").removeClass('selected');
       $(this).addClass('selected');
    } */
	$(this).siblings(".selected").removeClass('selected'); //.siblings() 筛选所有同辈元素
	$(this).toggleClass('selected');
})


//2.多选(必须放到 Dtable配置后下面);所有 table 有效,需放在所有 dataTable 后面
$('table tbody').on('click', 'tr', function () {
	/*多选框的多选
	var curCheckbox=$(this).find(':checkbox')[0];
    if(curCheckbox==null){
	 	return;
	 }
    curCheckbox.checked=!curCheckbox.checked;
    $(this).toggleClass('selected',curCheckbox.checked);*/
    $(this).toggleClass('selected');
})

var Dtable = $('#demo').DataTable();
//3.获取选中的行
Dtable.rows('.selected').data().length;
//获取当前行: Dtable.rows(this).data()[0]

//4.删除选中的行
//单行删除
Dtable.row('.selected').remove().draw(false);
//多行删除
Dtable.rows('.selected').remove().draw(false);


//5.刷新Grid
Dtable.settings()[0].ajax.data={key:val};
Dtable.ajax.reload(); //如果Dtable = $('#table').dataTable(); 刷新代码为 Dtable.api().ajax.reload();

/**6.序号
{
	class : "center",
	title : "序号", 
	width:70,
	//data:null,
	render: function(data, type, row, meta){
		　　var startIndex= meta.settings._iDisplayStart;//获取到本页开始的条数
			return startIndex+meta.row+1;
	}
}*/

/**7.日期
 * java 返回Date类型,通过JSON转为:"modifyDate":{"date":2,"day":0,"hours":17,"minutes":39,"month":6,"nanos":0,"seconds":10,"time":1498988350000,"timezoneOffset":-480,"year":117}

{
	"title" : "编辑日期",
	data:'modifyDate',
//	type:'date',
	sorting : false,
	render:function(data, type, row, meta){
	    if(data==null){
	    	return "";
	    }
	    var d=new Date(data.time); //new Date().setTime(data.time);
	    return d.getFullYear()+"-"+d.getMonth()+"-"+d.getDate();
	}
}
 */

//8.表格一行中按钮点击函数
$('#table-model tbody').on('click','tr button',function(){
	var data = $('#table-model').DataTable().row($(this).parents('tr')).data();
    alert("查看修改："+data.modelCname +","+ data.modelName );
})	