//1.单选.首先获取datatable对象(注意大小写`DataTable()`)：
var Dtable = $('#table').DataTable();
$('#table tbody').on('click', 'tr', function () {
    if ($(this).hasClass('selected') ) {
       $(this).removeClass('selected');
    } else {
       table.$('tr.selected').removeClass('selected');
       $(this).addClass('selected');
    }
})

//2.多选
var Dtable = $('#demo').DataTable();
$('#demo tbody').on('click', 'tr', function () {
    $(this).toggleClass('selected');
})

//3.获取选中的行
var Dtable = $('#demo').DataTable();
$('button').click(function () {
    alert( Dtable.rows('.selected').data().length +' row(s) selected' );
});

//4.删除选中的行
var Dtable = $('#demo').DataTable();
$('button').click(function () {
    //单行删除
    //Dtable.row('.selected').remove().draw(false);
    //多行删除
    Dtable.rows('.selected').remove().draw(false);
});