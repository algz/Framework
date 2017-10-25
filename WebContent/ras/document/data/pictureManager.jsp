<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
<%@taglib prefix="approval" tagdir="/WEB-INF/tags/customize/approval" %> 
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
				<div class="col-xs-12">
				<input type="hidden" name="modelName" value="${ modelName}">
				<input type="hidden" name="overviewID" value="${ overviewID}">
					<h3 class="header smaller lighter purple">
					全部图片
					</h3>
					<div class="table-header">
						
					</div>
			
					<!-- <div class="table-responsive"> -->
			
					<!-- <div class="dataTables_borderWrap"> -->
					<div class="control-group">
						<div class="radio">
							<label>
								<input name="pic-radio" type="radio" class="ace" checked value="整体图">
								<span class="lbl">整体图</span>
							</label>
			
							<label>
								<input name="pic-radio" type="radio" class="ace" value="三面图">
								<span class="lbl">三面图</span>
							</label>
			
							<label>
								<input name="pic-radio" type="radio" class="ace" value="外观图">
								<span class="lbl">外观图</span>
							</label>
							
							<label>
								<input name="pic-radio" type="radio" class="ace" value="其它">
								<span class="lbl">其它图</span>
							</label>
						</div>
					</div>
					<div id='pictureTool' class="btn-group">
						<button id="upPicture" class="btn btn-sm" type="button">上传</button>
						<button id="delPicture" class="btn btn-sm" type="button">删除</button>
						<button id="submitApprovalBtn" class="btn btn-sm" type="button">送审</button>
						<span class="input-icon">
							<input type="text" id="picTagTxt" placeholder="标签名">
							<i class="ace-icon fa fa-fighter-jet  blue"></i>
						</span>
						<button id="picSearchBtn" class="btn btn-purple btn-sm" type="button">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								查询
						</button>
					</div>
					<div>
						<table id="pictureTable" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
			
			<div id="modal-picture" class="modal fade" tabindex="-1">
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
								<form:form-group id="photoName" label="图片标题" />
								<form:form-group id="photoDesc" type="textArea" label="图片简介" />	
								<form:form-group id="photoCategory" label="类别" type="select">
									<option>整体图</option>
									<option>三面图</option>
									<option>外观图</option>
									<option>其它图</option>
								</form:form-group>
								<form:form-group id="photoTag" label="标签名称" />
								<form:form-group id="photoFile" label="上传文件" type="file" isMultiple="1" />
							</form>
			          	</div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			    			<button id="pic-confirmBtn" type="button" class="btn btn-primary">确定</button>
			            </div>
			        </div>
			    </div>
			</div>
			<div class="row">
				<div class="col-sm-offset-6">
					<div class="btn-group">
						<input class="btn btn-primary" onclick="history.go(-1)" type="button" value="返回">
					</div>
				</div>
			</div>
			<approval:approval modalID="approvalModealID" dataID="" dataTable="PICTURE" />
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<script src="pictureManager.js"></script>
		
		<plugin_css>
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />	
		
			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox) -->	
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/colorbox.css" />
		
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/fileinput/fileinput.css" />
			<link href="<%=basePath%>ras/common/css/fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
		</plugin_css>
		<plugin_js>
			
			<!-- bootbox.js 提示框 -->
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		
			<!-- 文本框扩展查询 -->
			<script src="<%=basePath%>ras/common/js/typeahead.jquery.js"></script>	
		
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			<!-- 配置后,需设置 "language":dataTables_zh  -->
			<script src="<%=basePath%>ras/common/js/dataTables/i18n/dataTables_zh.lang"></script>
			
			<!-- 图片放大查看 -->
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>
			
			<!-- JS编辑器 
			<script src="<%=basePath%>ras/common/js/jquery.hotkeys.js"></script>
			<script src="<%=basePath%>ras/common/js/bootstrap-wysiwyg.js"></script>-->

			<!-- 文件上传 -->
			<script src="<%=basePath%>ras/common/js/fileinput/fileinput.js"></script>
			
			<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>

			<script src="<%=basePath%>ras/common/js/jqueryvalidation/jquery.validate.js"></script>
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/messages_zh.js"></script>
		</plugin_js>			
	</body>
</html>
