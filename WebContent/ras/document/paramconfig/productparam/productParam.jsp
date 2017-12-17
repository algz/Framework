<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
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

		<jsp:include  page="../../../common/common_css.jsp"/> 
		<jsp:include  page="../../../common/common_js.jsp"/> 
	</head>

	<body >


		
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->	
			
	
			<div class="row">
				<div class="col-xs-4">
					<div class="btn-group">
						<button id="addNodeBtn" class="btn btn-purple btn-sm" type="button">添加</button>
						<button id="modifyNodeBtn" class="btn btn-purple btn-sm" type="button">修改</button>
						<button id="delNodeBtn" class="btn btn-purple btn-sm" type="button">删除</button>
					</div>
					<div id="tree"></div>
				</div>
				
				<div class="col-xs-8">
					<!-- <h3 class="header smaller lighter blue"></h3> -->
					<div class="table-header">
						
					</div>

					<!-- <div class="table-responsive"> -->

					<!-- <div class="dataTables_borderWrap"> -->
					<div class="btn-group">
						<button id="addParamBtn" class="btn btn-purple btn-sm" type="button">添加</button>
						<button id="modifyParamBtn" class="btn btn-purple btn-sm" type="button">修改</button>
						<button id="delParamBtn" class="btn btn-purple btn-sm" type="button">删除</button>
					</div>
					<div>
						<table id="productParamTable" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				

				</div>
			</div>

			<div id="modal-productParamNode" class="modal fade" tabindex="-1">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true">&times;</span></button>
			        		<h4 class="modal-title">管理参数结点</h4>
			            </div>
			            <div class="modal-body">
			            	<form class="form-horizontal" role="form">
			            		<input type="hidden" id="productID-node">
								<form:form-group id="productName-node" label="结点名称"/>
							</form>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			    			<button id="confirmBtn-node" type="button" class="btn btn-primary">确定</button>
			            </div>
			        </div>
			    </div>
			</div>

			<div id="modal-productParamGrid" class="modal fade" tabindex="-1">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true">&times;</span></button>
			        		<h4 class="modal-title">管理参数</h4>
			            </div>
			            <div class="modal-body">
			            	<form class="form-horizontal" role="form">
			            		<input type="hidden" id="productID">
								<form:form-group id="productName" label="中文参数名称"/>
								<form:form-group id="paramName" label="英文参数名称"/>
								<form:form-group id="ui_type" label="参数类型" type="select">
									<option value="text">文本框</option>
									<option value="checkbox" >多选框</option>
									<option value="number" >数值框</option>
									<option value="numberRegion" >数值区</option>
									<option value="textArea" >文本区</option>
								</form:form-group>
								<form:form-group id="sequence" label="序列值"/>
							</form>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			    			<button id="confirmBtn-grid" type="button" class="btn btn-primary">确定</button>
			            </div>
			        </div>
			    </div>
			</div>
			
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
		<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />
		
		</plugin_css>
		
		<plugin_js>
		
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		
			<!-- bootstrap select -->
			<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>

			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>
		
		<!-- bootstrap-treeview.js -->
		<script src="<%=basePath%>ras/common/js/treeview/bootstrap-treeview.js"></script>
		
		<script type="text/javascript" src="productParam.js"></script>		

	</body>
</html>
