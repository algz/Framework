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
				<div class="col-sm-12">
					<input id="modelName" type="hidden" value="${modelName }" />
					<table id="comparisonDetail-table" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center" width="130">
								</th>
							<c:forEach items="${models}" var="model">
								<th  class="center">
									<label>
										<span class="lbl">${model }</span>
									</label>
								</th>
							</c:forEach>
							</tr>
						</thead>
					</table>
				</div><!-- /.col -->	
			</div>
			<div class="row">
				<div class="col-sm-offset-11">
					<div class="btn-group" >
						<a class="btn btn-primary" href="javascript:history.go(-1);">返回</a>
					</div>
				</div>
			</div>
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		
		<plugin_js>
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
			
			<!-- 图片放大查看 -->
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>


		</plugin_js>
		<script type="text/javascript" src="comparisonDetail.js"></script>		

	</body>
</html>
