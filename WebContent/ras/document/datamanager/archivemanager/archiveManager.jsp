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
				<input type="hidden" name="modelName" value="${modelName}">
				<input type="hidden" name="overviewID" value="${overviewID}">
					<h3 class="header smaller lighter purple">
					全部文档
					</h3>
					<div class="table-header">
						
					</div>

					<!-- <div class="table-responsive"> -->

					<!-- <div class="dataTables_borderWrap"> -->
					<div id="docTool" class="btn-group">
						<button id="upDoc" class="btn btn-sm" type="button">上传</button>
						<button id="delDoc" class="btn btn-sm" type="button">删除</button>
						<!-- <button id="submitApprovalBtn" class="btn btn-sm btn-primary" type="button">送审</button> -->
						<span class="input-icon">
							<input type="text" id="docTagTxt" placeholder="标签名称">
							<i class="ace-icon fa fa-fighter-jet  blue"></i>
							</span>
						<button id="docSearchBtn" class="btn btn-purple btn-sm" type="button">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								查询
						</button>
					</div>
					<div>
						<table id="docTable" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>

<div id="modal-doc" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">        
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            	<span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">...</h4>
        	</div>
            <div class="modal-body">            
          		<form class="form-horizontal" role="form">
					<!-- #section:elements.form -->
					<input name="id" type="hidden" value="${id }">
					<input name="onlyRead" type="hidden" value="${onlyRead }">
					<form:form-group id="archiveName" label="文档标题" />
					<form:form-group id="archiveDesc" label="文档简介" />					
					<form:form-group id="archiveTag" label="标签名称" />
					<form:form-group id="archiveFile" label="上传文件" type="file" />
				</form>
          	</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
    			<button id="confirmBtn" type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>

			<!-- PAGE CONTENT ENDS -->
			
		</page:page>
		
		
		<script type="text/javascript" src="archiveManager.js"></script>
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
