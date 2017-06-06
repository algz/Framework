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
				<div class="col-xs-12">
					<div class="table-header">
						标签数据列表
					</div>

					<!-- <div class="table-responsive"> -->
					<div class="btn-group">
						<button id="addTag" class="btn btn-sm" type="button" >新增</button>
						<button id="modifyTag" class="btn btn-sm" type="button">修改</button>
						<button id="delTag" class="btn btn-sm" type="button">删除</button>
					</div>
					<label class="btn btn-sm  inline">
						<small class="muted smaller-90">隐藏系统标签:</small>
						<input class="ace ace-switch ace-switch-5" id="id-button-borders" type="checkbox" checked="checked">
						<span class="lbl middle" ></span>
					</label>
					<!-- <div class="dataTables_borderWrap"> -->
					<div>
						<table id="table-tag" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
			
			<div id="modal-form" class="modal fade" tabindex="-1">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			            	<button type="button" class="close" data-dismiss="modal">&times;</button>
			            	<h4 class="blue bigger">...</h4>
			            </div>
			            <div class="modal-body">
							<form class="form-horizontal" role="form">
								<!-- #section:elements.form -->
								<input name="id" type="hidden" value="${id }">
								<input name="onlyRead" type="hidden" value="${onlyRead }">
								<form:form-group id="name" label="名称" value="${name}"/>
								<form:form-group id="enname" label="英文名" value="${enname}"/>
								<form:form-group id="ui_type" label="类型" type='select' >
									<option value="text">文本框</option>
									<option value="checkbox" >多选框</option>
									<option value="number" >数值框</option>
									<option value="numberRegion" >数值区</option>
								</form:form-group>						
								<form:form-group id="ui_value" label="类型值" value="${ui_value}"/>
								<form:form-group id="parent_id" label="父节点" value="${sequence}" type="select">
									<option value="1" >基本</option>
									<option value="2" >重量</option>
									<option value="3" >布局</option>
									<option value="4" >性能</option>
									<option value="5" >动力</option>
									<option value="6" >系统</option>
								</form:form-group>
							
								<form:form-group id="sequence" label="序列" value="${sequence}"/>
							</form>
			            </div>
			            <div class="modal-footer">
			            	<button class="btn btn-sm" data-dismiss="modal">
								<i class="ace-icon fa fa-times"></i>
								取消
							</button>
						
							<button id="saveModalBtn" class="btn btn-sm btn-primary">
								<i class="ace-icon fa fa-check"></i>
								保存
							</button>
							
			            </div>
			        </div>
			    </div>
			</div>

			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
		<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />
		
		<link rel="stylesheet" href="<%=basePath%>ras/common/css/gritter/jquery.gritter.css" />
		</plugin_css>
		<plugin_js>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
			
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/jquery.validate.js"></script>
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/messages_zh.js"></script>
			
			<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>
			
			<script src="<%=basePath%>ras/common/js/gritter/jquery.gritter.js"></script>
			
		</plugin_js>
		<script type="text/javascript" src="tagedit.js"></script>	
	</body>
</html>
