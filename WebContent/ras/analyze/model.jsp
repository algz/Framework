<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//JSONArray ja=(JSONArray)request.getAttribute("menus");
%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>飞机论证参照模块1</title>

		<meta name="description" content="top menu &amp; navigation" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />


		<jsp:include  page="../common/common_css.jsp"/> 
		<jsp:include  page="../common/common_js.jsp"/> 

			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
	</head>
	
	<body >					
			<div class="row">
			<input name="modelName" type="hidden"/>
				<div class="col-xs-12">
					<div class="table-header">
						筛选：
					</div>
					<div>
					<div class="row">
							<div class="col-xs-2">
								<div class="input-group">
									<input id="modelName" class="form-control search-query" type="text" placeholder="机型名称" value="">
									<span class="input-group-btn">
										<button class="btn btn-purple btn-sm" id="submitBtn" type="button">
											查询
											<i class="icon-search icon-on-right bigger-110"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
					<table id="comparison-table" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>


		<!-- inline scripts related to this page -->
		<script type="text/javascript" >
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
//							type : 'POST'
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
								"title" : "",/* data:'name', */
								data:null,
								sorting : false,
								width:50,
								render: function(data, type, row, meta){
								　　var startIndex= meta.settings._iDisplayStart;//获取到本页开始的条数
									return startIndex+meta.row+1;
								}
//								render:function(data, type, row, meta){
//								    //个人理解  --以及参数的应用场景
//								    //data:当前cell的值  --主要是操作这个参数来做渲染
//								    //type:数据类型,枚举类型dt内置定义的  --用处不大
//								    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
//								    //meta:它下面有三个参数
//								    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
//								    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
//									
//								    return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>';//"<a href='./searchSummarize'>"+data+"</a>";
//								}
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
			
			$("form").submit(function(e){
				var s=$(":hidden[name=modelName]").val();
				if(s==""){
					e.preventDefault();
					alert("请选择机型!")
				}
			})
			
			$(".table-header").on('click','button.close',function(){
				$(this).parent().remove();
				var s=$(":hidden[name=modelName]");
				s.val("");
				$(".table-header span").each(function(i){
					var v=$(this).clone().children().remove().end().text();
					s.val(s.val()+(i!=0?",":"")+v)
				})
			})
			
					/**
				     * Grid行选 多选
				     */
//				    var selectModelParamRowData=null;
					$('#comparison-table tbody').on('click', 'tr', function () {
						var data=dataTable.rows(this).data()[0];
						if(data==null){
							return;
						}
						var element=$(":hidden[name=modelName]");
						var s=true;
						element.val("");
						$(".table-header span").each(function(i){
							var v=$(this).clone().children().remove().end().text();
							if(v==data.modelName){
								s=false;
								return ;
							}
							element.val(v+(element.val()==""?"":(","+element.val())))
						})
						 if(s){
						 	$(".table-header").append('<span class="btn btn-xs">'+data.modelName+'<button class="close" type="button">×</button></span> ');
						 }
						 element.val(data.modelName+(element.val()==""?"":",")+element.val())
					});
			
			
			$("#submitBtn").click(function(){
				dataTable.settings()[0].ajax.data={modelName:$("#modelName").val().trim()};		
				dataTable.ajax.reload();
			})
		})


		
		</script>

		
	</body>
</html>
