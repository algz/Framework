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
		<title>飞机论证参照模块</title>

		<meta name="description" content="top menu &amp; navigation" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<jsp:include  page="../common/common_css.jsp"/> 
		<jsp:include  page="../common/common_js.jsp"/> 
	</head>

	<body >


		
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			
			<div class="row">
				<div class="col-xs-12">
					
					<div class="table-header">
						机型数据列表
					</div>

					<!-- <div class="table-responsive"> -->
					<div class="btn-group">
						<a class="btn btn-sm" type="button" href="./addmodel">新增</a>
						<button id="modifyModel" class="btn btn-sm" type="button">修改</button>
						<button id="delModel" class="btn btn-sm" type="button">删除</button>
					</div>
					<!-- <div class="dataTables_borderWrap"> -->
					<div>
						<table id="table-model" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">
					<h3 class="header smaller lighter blue"></h3>
					<div class="table-header">
						机型参数数据列表
					</div>

					<!-- <div class="table-responsive"> -->
					<div class="btn-group">
						<button id="addModelparam" class="btn btn-sm" type="button">新增</button>
						<button id="modifyModelparam" class="btn btn-sm" type="button">修改</button>
						<button id="delModelparam" class="btn btn-sm" type="button">删除</button>
					</div>
					<!-- <div class="dataTables_borderWrap"> -->
					<div>
						<table id="table-modelparam" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>

			<!-- PAGE CONTENT ENDS -->
		</page:page>
		

		<plugin_js>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
			
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		</plugin_js>
		<script type="text/javascript" src="document.js"></script>	
	</body>
</html>
