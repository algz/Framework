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

		<jsp:include  page="../../common/common_css.jsp"/> 
		<jsp:include  page="../../common/common_js.jsp"/> 
	</head>

	<body >

		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-sm-12">
					<!-- #section:elements.tab -->
					<div class="tabbable">
						<ul class="nav nav-tabs" id="myTab">
							<li class="active">
								<a data-toggle="tab" href="#param">
									<i class="green ace-icon fa fa-home bigger-120"></i>
									参数
								</a>
							</li>
							<li >
								<a data-toggle="tab" href="#picture">
									<i class="green ace-icon fa fa-home bigger-120"></i>
									图片
								</a>
							</li>
							<li >
								<a data-toggle="tab" href="#docment">
									<i class="green ace-icon fa fa-home bigger-120"></i>
									文档
								</a>
							</li>
	
						</ul>
	
						<div class="tab-content">
						
							<div id="param" class="tab-pane fade in active">
								<jsp:include page="addModelParam_param.jsp"></jsp:include>
							</div>
							<div id="picture" class="tab-pane fade">
								<jsp:include page="addModelParam_picture.jsp"></jsp:include>
							</div>
							<div id="docment" class="tab-pane fade">
								<jsp:include page="addModelParam_document.jsp"></jsp:include>
							</div>
						</div>
					</div>
	
					<!-- /section:elements.tab -->
				</div><!-- /.col -->	
			</div>
			<p>
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/fileinput/fileinput.css" />
			<link href="<%=basePath%>ras/common/css/fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
		</plugin_css>
		<plugin_js>
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
			
			<!-- 图片放大查看 
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>-->
			
			<!-- JS编辑器 
			<script src="<%=basePath%>ras/common/js/jquery.hotkeys.js"></script>
			<script src="<%=basePath%>ras/common/js/bootstrap-wysiwyg.js"></script>-->

			<!-- 文件上传 -->
			<script src="<%=basePath%>ras/common/js/fileinput/fileinput.js"></script>
			<script src="<%=basePath%>ras/common/js/fileinput/themes1/explorer/theme.js"></script>

		</plugin_js>
			

	</body>
</html>