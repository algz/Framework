$(function(){

	//导出excel
	$('#exportExcelBtn').on('click',function(){
		bootbox.setDefaults("locale","zh_CN"); 
		bootbox.confirm("是否导出Excel?",function(result){
				if(result){
					window.location="/algz/excel/exportexcel?className=personalServiceImpl&methodName=exportReportToExcel&reportID="+$('#reportID').val()
//					$.ajax({
//						url:'/algz/excel/exportexcel',
//						data:{
//							className:'personalServiceImpl', //bean 名 =首字母小写的类名
//							methodName:'exportReportToExcel',
//							reportID:$('#reportID').val()
//						},
//						success:function(data){
//							alert(1+data)
//						}
//					})
				}
			})
	})
	
	$("#closeSpaceBtn").on('click',function(){
		if($(this).attr("checked")==null){
			//打开
			$(this).attr("checked","checked");
			
			dataTable.rows().data().each( function (data,index,meta) {
				var flag=true;
				for(var i=1;i<data.length;i++){
					if(data[i]!=null){
						flag=false;
						break;
					}
				}
				if(flag){
					dataTable.row(index).child.hide();
					$(dataTable.row(index).node()).addClass("hidden");
				}
			   //var S=" this.child( 'Row details for row: '+this.index() );"
			});
			//$("#comparisonDetail-table").addClass("hidden");
		}else{
			//关闭
			$(this).removeAttr("checked")
			
			dataTable.$('tr').removeClass("hidden");
		}
	})
	
	var dataTable=$('#comparisonDetail-table').DataTable({
				"ordering" : false, // 是否允许Datatables开启排序
				// "info": false, //控制是否显示表格左下角的信息
				searching : false, // 开启、关闭Datatables的搜索功能
        		lengthChange: false,   //去掉每页显示多少条数据方法
        		"scrollX": true,
        		dom:"t",//"t<'row'<'col-xs-6'i><'col-xs-6'p>>",
//				bAutoWidth : false,
				serverSide : true, // 开启服务器模式
				ajax : {
					url : "./report/findpersonalreportcontentgrid",
					data:{
						reportID:$('#reportID').val()
					}
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
		        },
		        "createdRow": function( row, data, dataIndex ) {
		        	for(var i=2;i<data.length;i++){
		        		if(data[1]!=data[i]){
		        			$(row).addClass('text-primary');
		        		}
		        	}
				  }
	});
	
})