/**
 * html:
 * <table id="table" class="table table-striped table-bordered table-hover"></table>
 */
//所有dataTable 相关的事件都必须放到配置函数后,不然无法执行.
var Dtable = $('#table').DataTable();

//1.单选.首先获取datatable对象(注意大小写`DataTable()`)：
$('#table tbody').on('click', 'tr', function () {
    if ($(this).hasClass('selected') ) {
       $(this).removeClass('selected');
    } else {
       table.$('tr.selected').removeClass('selected');
       $(this).addClass('selected');
    }
})

//2多选(必须放到 Dtable配置后下面)
$('#table tbody').on('click', 'tr', function () {
	/*多选框的多选
	var curCheckbox=$(this).find(':checkbox')[0];
    if(curCheckbox==null){
	 	return;
	 }
    curCheckbox.checked=!curCheckbox.checked;
    $(this).toggleClass('selected',curCheckbox.checked);*/
    $(this).toggleClass('selected');
})

//3.获取选中的行
var Dtable = $('#demo').DataTable();
$('button').click(function () {
    alert( Dtable.rows('.selected').data().length +' row(s) selected' );
});

//4.删除选中的行
$('button').click(function () {
    //单行删除
    //Dtable.row('.selected').remove().draw(false);
    //多行删除
    Dtable.rows('.selected').remove().draw(false);
});

//5.刷新Grid
$('#table-param').DataTable()==Dtable;
Dtable.settings()[0].ajax.data={key:val};
Dtable.ajax.reload();

/*6.序号
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