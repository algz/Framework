<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
<%@taglib prefix="approval" tagdir="/WEB-INF/tags/customize/approval" %> 
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
		<title>飞机论证参照模块</title>

		<meta name="description" content="top menu &amp; navigation" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<jsp:include  page="../../common/common_css.jsp"/> 
		<jsp:include  page="../../common/common_js.jsp"/> 
	</head>

	<body >
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-xs-12">
				<input type="hidden" name="modelName" value="${modelName}">
				<input type="hidden" name="overviewID" value="${ overviewID}">
					<h3 class="header smaller lighter purple">
					全部文档
					</h3>
					<div class="table-header">
						
					</div>

					<!-- <div class="table-responsive"> -->

					<!-- <div class="dataTables_borderWrap"> -->
					<div id="docTool" class="btn-group">
						<button id="upDoc" class="btn btn-sm" type="button">上传</button>
						<button id="delDoc" class="btn btn-sm" type="button">删除</button>
						<button id="submitApprovalBtn" class="btn btn-sm" type="button">送审</button>
						<span class="input-icon">
							<input type="text" id="docNameTxt" placeholder="文档名">
							<i class="ace-icon fa fa-fighter-jet  blue"></i>
							</span>
						<button id="docSearchBtn" class="btn btn-purple btn-sm" type="button">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								查询
						</button>
					</div>
					<div>
						<table id="docTable" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>

<div id="modal-doc" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">        
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            	<span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">...</h4>
        	</div>
            <div class="modal-body">            
          		<form class="form-horizontal" role="form">
					<!-- #section:elements.form -->
					<input name="id" type="hidden" value="${id }">
					<input name="onlyRead" type="hidden" value="${onlyRead }">
					<form:form-group id="archiveName" label="文档标题" />
					<form:form-group id="archiveDesc" label="文档简介" />					
					<form:form-group id="archiveTag" label="标签" />
					<form:form-group id="archiveFile" label="上传文件" type="file" />
				</form>
          	</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
    			<button id="confirmBtn" type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>

<approval:approval modalID="approvalModealID" dataID="" dataTable="ARCHIVE" />
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
		<%-- 	<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />	 --%>
		
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/fileinput/fileinput.css" />
			<link href="<%=basePath%>ras/common/css/fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
		</plugin_css>
		<plugin_js>
			
			<!-- bootbox.js 提示框 -->
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			

			<!-- 文件上传 -->
			<script src="<%=basePath%>ras/common/js/fileinput/fileinput.js"></script>
			
			<%-- <script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script> --%>

			<script src="<%=basePath%>ras/common/js/jqueryvalidation/jquery.validate.js"></script>
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/messages_zh.js"></script>

		</plugin_js>
<script type="text/javascript">
			$(function(){
				$("#archiveFile").fileinput({
					uploadUrl : './uparchivefile', //上传的地址
					showUpload : false,
					showRemove : false,
					showPreview:false, //关闭预览
					uploadExtraData: function(previewId, index) {   //额外参数的关键点
	                    var obj = {};
	                    obj.overviewID = $(':hidden[name=overviewID]').val();
	                    obj.modelName=$(':hidden[name=modelName]').val();
	                    obj.archiveName=$('#archiveName').val();
	                    obj.archiveDesc=$('#archiveDesc').val();
	                    obj.tag=$('#archiveTag').val();
	                    return obj;
	                }
				})
				
				//文件上传完成
				$("#archiveFile").on("fileuploaded",function(){
					docTable.ajax.reload();
					$('#modal-doc').modal('hide');
					$('#modal-doc :text').val("");
					$("#archiveFile").fileinput("clear").fileinput('enable');
					
				});
				
				//对话框-确定按钮
				$("#confirmBtn").on('click',function(){
					$("#archiveFile").fileinput('upload');//上传文件
				})
				
				/////////////////////////////////////////////////////////////
				
				/**
				上传按钮
				*/
				$("#upDoc").on('click',function(){
					$('#modal-doc').modal();
					$('#modal-doc .modal-title').text("上传文件")
				})
				
				/**
				删除按钮
				*/
				$("#delDoc").on('click',function(){
					var arr=docTable.rows('.selected').data();
					if(arr.length==0){
						bootbox.alert("请选择数据");
					}else{
						bootbox.confirm("是否删除当前数行?",function(result){
							if(result){
								var archiveID=[]
								for(var i=0;i<arr.length;i++){
									archiveID.push(arr[i].archiveID);
								}
								$.ajax({
									url:'./delarchivefile',
									data:{
										archiveID : archiveID.toString()
									},
									success:function(){
										docTable.ajax.reload();
									}
								})
							}
						})
					}
					
				});
				
				/**
				查询按钮  
				*/
				$("#docSearchBtn").on('click',function(){
					docTable.settings()[0].ajax.data={
						photoCategory:$(this).val(),
						tag:$("#docNameTxt").val()
					};
					docTable.ajax.reload();
				})
				
				// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
				var docTable = $('#docTable')
				// .wrap("<div class='dataTables_borderWrap' />") 
				.DataTable({
					// "paging": false, //是否开启本地分页
					lengthChange: false, //是否隐藏每页显示数量框(css).true,默认不隐藏;false,隐藏.
					//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]], //定义每页显示数据数量
					"ordering" : false, // 是否允许Datatables开启排序
					// "info": false, //控制是否显示表格左下角的信息
					"searching" : false, // 开启、关闭Datatables的搜索功能
					bAutoWidth : false,
					//"destroy": true,
					// processing: true,//"<img src='./loading.gif' />"
					serverSide : true, // 开启服务器模式
					ajax : {
						url : "./findarchivegrid"
					},
					//deferLoading:0, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
					"columns" : [{
								class : "center",
								render : function(data, type, full, meta) {
									return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
								},
								//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
								sorting : false
							},{
								"title" : "文档标题", 
								data:'archiveName',
								render : function(data, type, row, meta) {
									//个人理解  --以及参数的应用场景
								    //data:当前cell的值  --主要是操作这个参数来做渲染
								    //type:数据类型,枚举类型dt内置定义的  --用处不大
								    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
								    //meta:它下面有三个参数
								    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
								    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
								    return '<a href="./downloadarchivefile?archiveID='+row.archiveID+'">'+data+'</a>'
								}
							}, {
								"title" : "文档简介", 
								data:'archiveDesc',
								sorting : false
							}, {
								"title" : "标签", 
								data:'tag',
								sorting : false
							}, {
								"title" : "权限级别", 
								data:'permissionLevel',
								render:function(data, type, row, meta){
									if(data==""||data=='1'){
										return "个人可视";
									}else if(data=='2'){
										return "部门可视";
									}else if(data=="3"){
										return "所内可视";
									}
								}
							}, {
								"title" : "编辑人", 
								data:'editor',
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
					} // 多语言配置
				});
				
				//2.单选
				$('#docTable tbody').on('click', 'tr', function () {
				    var curCheckbox=$(this).find(':checkbox')[0];
				    if(curCheckbox==null){
					 	return;
					 }
				    
				    //curCheckbox.checked=!curCheckbox.checked;
				    docTable.$(':checked').attr('checked',false);//清除所有的选中框.
				    if ($(this).hasClass('selected')) {
				        $(this).removeClass('selected');
				        curCheckbox.checked=false;
				     } else {
				    	 docTable.$('tr.selected').removeClass('selected');
				        $(this).addClass('selected');
				        curCheckbox.checked=true;
				        
				        curSelectData=docTable.rows(this).data()[0];
					    $("#docTool").children().removeAttr('disabled');
						//需要数据管理员权限才能修改,删除和送审
				    	if(!algz.curUser.isDataManager&&curSelectData.editor!=algz.curUser.username){
				    		//不是数据管理员
				    		$("#upDoc").attr('disabled',"true");
				    		$("#delDoc").attr('disabled',"true");
				    		$("#submitApprovalBtn").attr('disabled',"true");
				    	}
				     }
				    //$(this).toggleClass('selected');
				});
				
				
				/**
				 * 送审
				 */
				$("#submitApprovalBtn").on('click',function(){
					if(docTable.row('.selected').length==0){
						bootbox.alert("请选择一条数据!")
						return ;
					}else{
						bootbox.confirm("是否送审?",function(result){
							if(result){
								approvalModal.show({
									dataID:curSelectData.archiveID
								});
								approvalModal.complete=function(data){
									alert(data.responseText);
								}
							}
						});
					}

				})
				
			})

</script>

	</body>
</html>
