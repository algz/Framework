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


		<jsp:include  page="../common/common_css.jsp"  >
			<jsp:param name="pageTitle" value="首页"/>  
		</jsp:include>
		
		<jsp:include  page="../common/common_js.jsp"/> 
	</head>
	
	<body >
	
	<!-- 需加载到CSS的插件 -->
	<plugin_css><link rel="stylesheet" href="<%=basePath%>ras/common/css/ui.jqgrid.css " /></plugin_css>
	
		<page:page page="${page }">
		
			<!-- PAGE CONTENT BEGINS -->
			<form method="post" action="./comparisondetail">
			<input name="modelName" type="hidden"/>
			<div class="row">
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
			
			<p>
			
			<div class="row">
				<div class="col-sm-offset-6">
					<div class="btn-group">
						<input type="submit" class="btn btn-primary" value="提交">
					</div>
				</div>
			</div>
			</form>
			<!-- PAGE CONTENT ENDS -->
		
		
		
			<!-- PAGE CONTENT BEGINS -->
			
			
			

			
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		<plugin_js>
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
			<script src="<%=basePath%>ras/common/js/bootstrap-tag.js"></script>

		</plugin_js>
		<!-- inline scripts related to this page -->
		<script type="text/javascript" src="comparison.js"></script>

		
	</body>
</html>
