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

		<jsp:include  page="../../common/common_css.jsp"/> 
		<jsp:include  page="../../common/common_js.jsp"/> 
	</head>

	<body >


		
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-xs-6">
					
					<div class="table-header">
						用户管理
					</div>

					<!-- <div class="table-responsive"> -->
					<div class="btn-group">
						<button id="addUserBtn" class="btn btn-sm" type="button">添加</button>
						<button id="modifyUserBtn" class="btn btn-sm" type="button">修改</button>
						<button id="delUserBtn" class="btn btn-sm" type="button">删除</button>
					</div>
					<!-- <div class="dataTables_borderWrap"> -->
					<div>
										
						<table id="table-user" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
				
				<div class="col-xs-6">
					
					<div class="table-header">
						角色信息
					</div>
					<label class="btn btn-sm  inline">
						<small class="muted smaller-90">隐藏系统用户:</small>
						<input class="ace ace-switch ace-switch-5" id="hidePublicResourceBtn" type="checkbox" >
						<span class="lbl middle" ></span>
					</label>
					<div>
										
						<table id="table-role" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
			
			<div id="modal-userform" class="modal fade" tabindex="-1">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true">&times;</span></button>
			        		<h4 class="modal-title">添加用户</h4>
			            </div>
			            <div class="modal-body">
			            	<form class="form-horizontal">
			            		<input id="userid" type="hidden">
				            	<form:form-group id="username" label="用户名称"/>
				            	<form:form-group id="password" label="用户密码" type="password"/>
				            	<form:form-group id="cname" label="中文名称"/>
				            	<form:form-group id="department" label="部门名称"/>
							</form>
			            
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			    			<button id="user-confirmBtn" type="button" class="btn btn-primary">确定</button>
			            </div>
			        </div>
			    </div>
			</div>
			
			
			
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		
		<plugin_js>
			<!-- 提示框 -->
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>
		<script type="text/javascript" src="ras_user.js"></script>		

	</body>
</html>
