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
							<%--
							<li>
								<a data-toggle="tab" href="#discuss">
									评论
									<span class="badge badge-danger">4</span>
								</a>
							</li>
							--%>
						</ul>
	
						<div class="tab-content">
						
							<div id="param" class="tab-pane fade in active">
								<jsp:include page="./searchSummarize_param.jsp"></jsp:include>
							</div>
							<div id="picture" class="tab-pane fade">
								<jsp:include page="./searchSummarize_picture.jsp"></jsp:include>
							</div>
							
							<div id="docment" class="tab-pane fade">
								<jsp:include page="./searchSummarize_document.jsp"></jsp:include>
							</div>
							<%--
							<div id="discuss" class="tab-pane fade">
								<jsp:include page="./searchSummarize_discuss.jsp"></jsp:include>
							</div>  --%>
						</div>
					</div>
	
					<!-- /section:elements.tab -->
				</div><!-- /.col -->	
			</div>
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox) -->
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/colorbox.css" />
		</plugin_css>
		<plugin_js>
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			
			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox)  -->
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>
			
			<!-- JS编辑器 
			<script src="<%=basePath%>ras/common/js/jquery.hotkeys.js"></script>
			<script src="<%=basePath%>ras/common/js/bootstrap-wysiwyg.js"></script>
-->
		</plugin_js>	
<script type="text/javascript">
$(function(){
	$('#myTab li:eq(${tab==null?"0":tab}) a').tab('show')
})
</script>
	</body>
</html>
