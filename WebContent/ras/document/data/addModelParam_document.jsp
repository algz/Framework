<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//JSONArray ja=(JSONArray)request.getAttribute("menus");
%>


			<div class="row">
				<div class="col-xs-12">
					<h3 class="header smaller lighter purple">
					全部文档
					</h3>
					<div class="table-header">
						
					</div>

					<!-- <div class="table-responsive"> -->

					<!-- <div class="dataTables_borderWrap"> -->
					<div class="btn-group">
						<button id="addDoc" class="btn btn-sm" type="button">新增</button>
						<button id="modifyDoc" class="btn btn-sm" type="button">修改</button>
						<button id="delDoc" class="btn btn-sm" type="button">删除</button>
						<span class="input-icon">
							<input type="text" id="modelNameTxt" placeholder="机型名">
							<i class="ace-icon fa fa-fighter-jet  blue"></i>
							</span>
						<button id="modelSearch" class="btn btn-purple btn-sm" type="button">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								查询
						</button>
					</div>
					<div>
						<table id="doc-Table" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>

			<script type="text/javascript">
			$(function(){
				
				$("#addDoc").on('click',function(){
					//$(".modal-body label").removeClass("active");
					$('#modal-form').modal();
					$('#modal-form .modal-title').text("y轴")
				})
				
				// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
				var tablemodel = $('#doc-Table')
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
						url : "./finddoctablegrid"
					},
					//deferLoading:1, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
					"columns" : [{
								class : "center",
								render : function(data, type, full, meta) {
									return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
								},
								//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
								sorting : false
							},{
								"title" : "文档标题", 
								data:'modelName',
								sorting : false
							}, {
								"title" : "文档简介", 
								data:'modelCname',
								sorting : false
							}, {
								"title" : "发表日期",
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
					} // 多语言配置
					});
			})

</script>
