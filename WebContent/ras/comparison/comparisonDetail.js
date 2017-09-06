$(function(){

//	$.ajax({
//		url:'./comparison/findmodelgird',
//		type:'post',
//		data:'',
//		success:function(data){
//			alert(data)
//		}
//	})
	
	//创建报告
	$('#createReportBtn').on('click',function(){
		$('#reportDes').val(""); //结论
		$('#reportName').val(""); //报告名称
		$("#createReport-form").modal();
	})
	
	$('#saveReport').on('click',function(){
		var reportDes=$('#reportDes').val(); //结论
		var reportName=$('#reportName').val(); //报告名称
		var reportContent=[];
		dataTable.rows().data().each(function (data,index,meta) {
			var val="";
			if(!$(dataTable.row(index).node()).hasClass('hidden')){
				for(var i=0;i<data.length;i++){
					if(i!=0){
						val+=",";
					}
					val+=data[i]||"";
				}
				reportContent.push(val);
			}
			
			
				/*var flag=true;
				for(var i=1;i<data.length;i++){
					if(data[i]!=null){
						flag=false;
						break;
					}
				}
				if(flag){
					dataTable.row(index).child.hide();
					$(dataTable.row(index).node()).addClass("hidden");
				}*/
			   //var S=" this.child( 'Row details for row: '+this.index() );"
		});
		
		$.ajax({
			url:'./savereport',
			data:{
				reportDes:reportDes,
				reportName:reportName,
				reportContent:reportContent
			},
			success:function(data){
			}
		})
		$("#createReport-form").modal('hide');
	})
	
	//筛选机型参数
	$("#selectModelParamBtn").on('click',function(){
		$(".modal-body label").removeClass("active");
		$('#modal-form').modal();
		$('#modal-form .modal-title').text("筛选机型参数")
	})
	
	//弹出窗的确定按钮
	$("#confirmBtn").on('click',function(e){
		var els=$(".modal-body label.active");
		dataTable.rows().data().each( function (data,index,meta) {
//			$(dataTable.row(index).node()).removeClass("hidden");
			
			for(var i=0;i<els.length;i++){
				var el=els[i];
//				var name=$(el).children('input').attr('name');
				var txt=$(el).children('span').text();
				$(dataTable.row(index).node()).addClass("hidden");
				if(data[0]==txt){
					$(dataTable.row(index).node()).removeClass("hidden");
					break;
				}
			}

		   //var S=" this.child( 'Row details for row: '+this.index() );"
		});
		$('#modal-form').modal("hide");
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
					url : "./findcomparisondetailgrid",
					data:{
						modelName:$("#modelName").val(),
						basicID:$("#basicID").val()
					}
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