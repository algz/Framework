$(function(){

//	$.ajax({
//		url:'./comparison/findmodelgird',
//		type:'post',
//		data:'',
//		success:function(data){
//			alert(data)
//		}
//	})
	var dataTable=$('#comparisonDetail-table').DataTable({
				"ordering" : false, // 是否允许Datatables开启排序
				// "info": false, //控制是否显示表格左下角的信息
				searching : false, // 开启、关闭Datatables的搜索功能
        		lengthChange: false,   //去掉每页显示多少条数据方法
        		dom:"t",//"t<'row'<'col-xs-6'i><'col-xs-6'p>>",
				bAutoWidth : false,
				serverSide : true, // 开启服务器模式
				ajax : {
					url : "./findcomparisondetailgrid",
					data:{modelName:$("#modelName").val()}
//					type : 'POST'
				},
				//deferLoading:0,
				columnDefs: [{
		            orderable: false,
		            className: 'select-checkbox',
		            targets:   0
		        }],
		        select: {
		            style:    'os',
		            selector: 'td:first-child'
		        }
	});
	
})