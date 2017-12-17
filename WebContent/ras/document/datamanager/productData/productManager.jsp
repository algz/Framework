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
				<div class="col-xs-12">
					<form class="form-horizontal" role="form" action="./saveproductinform" method="post">
						<input type="hidden" name="modelName" value="${modelName[0]}">
						<input type="hidden" name="overviewID" value="${overviewID[0]}">
						<c:forEach var="paramValue" items="${productParamValue}">
						
						<div>
							<h3 class="header smaller lighter purple">
								${paramValue.text}
							</h3>
							<div class="row">
								<div class="col-xs-12">
									<!-- PAGE CONTENT BEGINS -->
									<div class="form-horizontal" role="form">
										<c:forEach var="childParam" items="${paramValue.childParams}">
										<form:form-group id="pv${childParam.productID }" label="${childParam.text }" type="textArea" value="${childParam.val }"/>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						
						</c:forEach>
					</form>
	         
	
				</div>
			</div>
			<div class="row">
				<div class="col-sm-offset-6">
					<div class="btn-group">
						<input type="submit" id="saveProductInformBtn" class="btn btn-primary" value="保存"/>
					</div>
					<div class="btn-group">
						<a class="btn btn-primary" href="javascript:history.back()">取消</a>
					</div>
				</div>
			</div>


			<!-- PAGE CONTENT ENDS -->
			
		</page:page>
		
		
		<script type="text/javascript" src="productManager.js"></script>
		<plugin_css>
		<%-- 	<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />	 --%>
		
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/fileinput/fileinput.css" />
			<%-- <link href="<%=basePath%>ras/common/css/fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/> --%>
		</plugin_css>
		<plugin_js>
			
			<!-- bootbox.js 提示框 -->
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			
			<!-- 文本框扩展查询 -->
			<script src="<%=basePath%>ras/common/js/typeahead.jquery.js"></script>	

			<!-- 文件上传 -->
			<script src="<%=basePath%>ras/common/js/fileinput/fileinput.js"></script>
			
			<%-- <script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script> --%>

			<script src="<%=basePath%>ras/common/js/jqueryvalidation/jquery.validate.js"></script>
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/messages_zh.js"></script>

		</plugin_js>
		
	</body>
</html>
