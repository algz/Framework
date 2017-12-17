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
			
			
			
			
			




			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
		<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />
		
		</plugin_css>
		
		<plugin_js>
			<!-- bootstrap select -->
			<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>
			
			<!-- highcharts.js 图表-->
			<script src="<%=basePath%>ras/common/js/highcharts/highcharts.js"></script>
			<script src="<%=basePath%>ras/common/js/highcharts/modules/exporting.js"></script>
			<script src="<%=basePath%>ras/common/js/highcharts/plugins/highcharts-zh_CN.js"></script>
		

		
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>
		
		<script type="text/javascript" src="tree.js"></script>	
		
		<!-- bootstrap-treeview.js -->
		<script src="<%=basePath%>ras/common/js/treeview/bootstrap-treeview.js"></script>
		
		<script type="text/javascript" src="simpleSearch.js"></script>		

	</body>
</html>
