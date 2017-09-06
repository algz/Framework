$(function(){
	var dataTable=$('#comparison-table').DataTable({
				//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]], //定义每页显示数据数量
				// "paging": false, //是否开启本地分页
				"ordering" : false, // 是否允许Datatables开启排序
				// "info": false, //控制是否显示表格左下角的信息
				searching : false, // 开启、关闭Datatables的搜索功能
        		lengthChange: false,   //去掉每页显示多少条数据方法
        		//dom:"t<'row'<'col-xs-6'i><'col-xs-6'p>>",
				bAutoWidth : false,
				processing: true,//"<img src='./loading.gif' />"
				serverSide : true, // 开启服务器模式
				ajax : {
					url : "../comparison/findmodelgird"//,
//					type : 'POST'
				},
				deferLoading:0,
				columnDefs: [{
		            orderable: false,
		            className: 'select-checkbox',
		            targets:   0
		        }],
		        select: {
		            style:    'os',
		            selector: 'td:first-child'
		        },							
				//deferLoading:0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
				"columns" : [{
									data:null,
									class : "center",
									width:50,
//									"defaultContent": '',
									render: function(data, type, row, meta){
										//icon expand-icon glyphicon glyphicon-minus -
										//icon expand-icon glyphicon glyphicon-plus + 
										return '<i class="icon expand-icon glyphicon glyphicon-plus "></i>';
									}
								},{
						"title" : "",/* data:'name', */
						data:null,
						sorting : false,
						width:50,
						render: function(data, type, row, meta){
						　　var startIndex= meta.settings._iDisplayStart;//获取到本页开始的条数
							return startIndex+meta.row+1;
						}
//						render:function(data, type, row, meta){
//						    //个人理解  --以及参数的应用场景
//						    //data:当前cell的值  --主要是操作这个参数来做渲染
//						    //type:数据类型,枚举类型dt内置定义的  --用处不大
//						    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
//						    //meta:它下面有三个参数
//						    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
//						    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
//							
//						    return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>';//"<a href='./searchSummarize'>"+data+"</a>";
//						}
					}, {
						"title" : "机型名称",
						data:'modelName',
						sorting : false
					}, {
						"title" : "中文名称", 
						data:'modelCname', 
						sorting : false
					},{
						"title" : "英文名称", 
						data:'modelEname', 
						sorting : false
					}],
				"language" : {
					"lengthMenu" : "每页显示 _MENU_ 条记录",
					"zeroRecords" : "对不起，查询不到任何相关数据。",
					"info" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
					"infoEmpty" : "找不到相关数据",
					"infoFiltered" : "(从 _MAX_ 条数据中检索)",
					"processing" : "正在加载中...",
					"search" : "搜索",
					"url" : "", // 多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
					"paginate" : {
						"first" : "第一页",
						"previous" : " 上一页 ",
						"next" : " 下一页 ",
						"last" : " 最后一页 "
					}
				}
	});
	
	function template (data ) {
		var template='<table class="subTable table table-striped table-bordered table-hover" ><tbody>';
		for(var i=0;i<data.length;i++){
			template+='<tr>'+
			        	'<td width="50">' +
			        	'<label class="pos-rel"><input class="ace" type="checkbox" value="'+data[i].basicID+'"><span class="lbl"></span></label></td>'+
			            '<td>'+
			            	"<a href='./searchsummarize?overviewID="+data[i].overviewID+"&basicID="+data[i].basicID+"&option=load'>"+data[i].dataSource+"</a>"+
			            '</td>'+
			        '</tr>';
		}
		template+='</tbody></table>';
		return template;
	}
	
	$('#comparison-table tbody').on('click', 'td .expand-icon', function () {
        var tr = $(this).closest('tr');
        var row = dataTable.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            $(this).removeClass('glyphicon-minus');
            
        }else {
            // Open this row
        	if(row.child()==null){
            	$.ajax({
            		method:'Post',
            		url:'../searchparam/searchcriteriasubgird',
            		data:{
            			overviewID:row.data().overviewID
            		},
            		success:function(data){
            			row.child(template(data.data) ).show();
            		}
            	})
            }else{
            	row.child.show();
            }
            
            $(this).addClass('glyphicon-minus');
        }
    } );
	
	$("form").submit(function(e){
		var s=$(":hidden[name=modelName]").val();
		
		if(s==""){
			e.preventDefault();
			alert("请选择机型!")
		}
	})
	
//	$(".table-header").on('click','button.close',function(){
//		$(this).parent().remove();
//		var s=$(":hidden[name=modelName]");
//		s.val("");
//		$(".table-header span").each(function(i){
//			var v=$(this).clone().children().remove().end().text();
//			s.val(s.val()+(i!=0?",":"")+v)
//		})
//	})
	
			/**
		     * Grid行选 多选
		     */
//		    var selectModelParamRowData=null;
//			$('#comparison-table tbody').on('click', 'tr', function () {
//				var data=dataTable.rows(this).data()[0];
//				if(data==null){
//					return;
//				}
//				var element=$(":hidden[name=modelName]");
//				var s=true;
//				element.val("");
//				$(".table-header span").each(function(i){
//					var v=$(this).clone().children().remove().end().text();
//					if(v==data.modelName){
//						s=false;
//						return ;
//					}
//					element.val(v+(element.val()==""?"":(","+element.val())))
//				})
//				 if(s){
//				 	$(".table-header").append('<span class="btn btn-xs">'+data.modelName+'<button class="close" type="button">×</button></span> ');
//				 }
//				 element.val(data.modelName+(element.val()==""?"":",")+element.val())
//			});
	
	$('#comparison-table').on('click','.subTable :checkbox',function(){
		var modelName=$($(this).closest('.subTable').closest('tr').prev('tr').children('td')[2]).text();
		if(modelName==null){
			return;
		}
		var element=$(":hidden[name=modelName]");
//		var s=true;
//		element.val("");
//		$(".table-header span").each(function(i){
//			var v=$(this).clone().children().remove().end().text();
//			if(v==modelName){
//				s=false;
//				return ;
//			}
//			element.val(v+(element.val()==""?"":(","+element.val())))
//		})
//		 if(s){
//		 	$(".table-header").append('<span class="btn btn-xs">'+modelName+'<button class="close" type="button">×</button></span> ');
//		 }
//		 element.val(modelName+(element.val()==""?"":",")+element.val())
		///////////////////
		var org_text=$(":hidden[name=modelName]").val();
		var org_val=$(":hidden[name=basicID]").val() 
		var text=$(this).closest('tr').find('a').text()+"("+modelName+")";
		var val=$(this).val();
		if(this.checked){
			//添加
			if(org_val.indexOf(val)=="-1"){
				$(".table-header").append('<span class="btn btn-xs">'+modelName+'<button class="close" type="button"></button></span> ');
			 	element.val(modelName+(element.val()==""?"":",")+element.val())
				$(":hidden[name=modelName]").val(org_text==""?text:(org_text+","+text));
				$(":hidden[name=basicID]").val(org_val==""?val:org_val+","+val);
			}

		}else{
			//删除
			$(".table-header span").each(function(i){
				var v=$(this).clone().children().remove().end().text();
				if(v==modelName){
					$(this).remove();
				}
				//element.val(v+(element.val()==""?"":(","+element.val())))
			})
			org_text=org_text.replace(","+text,"").replace(text,"").replace(",,",",");
			org_text=org_text.indexOf(",")==0?org_text.substring(1):org_text;
			$(":hidden[name=modelName]").val(org_text);
			org_val=org_val.replace(","+val,"").replace(val,"").replace(",,",",");
			org_val=org_val.indexOf(",")==0?org_val.substring(1):org_val;
			$(":hidden[name=basicID]").val(org_val);
		}
	})
			
	
	$("#submitBtn").click(function(){
		dataTable.settings()[0].ajax.data={
			modelName:$("#modelName").val(),//$(":hidden[name=modelName]").val().trim(),
			basicID:$(":hidden[name=basicID]").val().trim()
			};		
		dataTable.ajax.reload();
	})
})


