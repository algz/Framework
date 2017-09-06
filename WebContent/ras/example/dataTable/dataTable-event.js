var dataTable = $('#table').DataTable();

//1.DataTable表格加载完.
dataTable.on( 'init.dt', function () {
	//......
})

//2.行单击
dataTable.on('click', 'tr', function () {
    var data = dataTable.row( this ).data();
    alert( 'You clicked on '+data.id+'\'s row' );
});