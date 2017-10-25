<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
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
						<!-- 审批控件 -->

			<div class="row">
				<div class="col-xs-12">
					
					<div class="table-header">
						机型数据列表
					</div>

					<!-- <div class="table-responsive"> -->
					<div id="modelTool" class="btn-group">
						<button data-toggle="dropdown" class="btn btn-sm dropdown-toggle" aria-expanded="false">
							新增机型
							<span class="ace-icon fa fa-caret-down icon-on-right"></span>
						</button>
						<ul class="dropdown-menu dropdown-default">
							<li>
								<a href="./addmodel">创建主机型</a>
							</li>

							<li>
								<a id="addSubModelBtn" href="#">创建子机型</a>
							</li>
						</ul>
						<button id="modifyModel" class="btn btn-sm" type="button">修改机型</button>
						<button id="delModel" class="btn btn-sm" type="button">删除机型</button>
						<button id="modelParamManagerBtn" class="btn btn-sm btn-primary" type="button">参数管理</button>
						<button id="pictureManager" class="btn btn-sm btn-primary" type="button">图片管理</button>
						<button id="archiveManager" class="btn btn-sm btn-primary" type="button">文档管理</button>
						<button id="submitApprovalBtn" class="btn btn-sm btn-purple" type="button">送审</button>
						<span class="input-icon">
							<input type="text" id="modelNameTxt" placeholder="机型名">
						</span>
						<button id="modelSearch" class="btn btn-purple btn-sm" type="button">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								查询
						</button>
					</div>
					
					<!-- <div class="dataTables_borderWrap"> -->
					<div>
						<table id="table-model" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
			
			<approval:approval modalID="approvalModealID" dataID="" dataTable="overview" />
			

			<div id="modal-modelParam" class="modal fade" tabindex="-1">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            				<span aria-hidden="true">&times;</span></button>
            				<h4 class="modal-title">机型参数管理</h4>
			            </div>
			            <div class="modal-body">
				
									<!-- <div class="table-responsive"> -->
									<div id="modelparamTool" class="btn-group">
										<button id="addModelparam" class="btn btn-sm" type="button">新增</button>
										<button id="modifyModelparam" class="btn btn-sm" type="button">修改</button>
										<button id="delModelparam" class="btn btn-sm" type="button">删除</button>
										<button id="setMainModelparam" class="btn btn-sm btn-info" type="button">设置为主要</button>
										<button id="mergeMainModelparam" class="btn btn-sm hidden" type="button">合并</button>
									</div>
									<!-- <div class="dataTables_borderWrap"> -->
									<div>
										<table id="table-modelparam" class="table table-striped table-bordered table-hover">
										</table>
							</div>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    					</div>
			        </div>
			    </div>
			</div>
			
			<div id="modal-subModel" class="modal fade" tabindex="-1">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            				<span aria-hidden="true">&times;</span></button>
            				<h4 class="modal-title">子机型创建</h4>
			            </div>
			            <div class="modal-body">
							<form class="form-horizontal" role="form" action="./savemodel" method="post">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" >子机型名称</label>
									<div class="col-xs-10 col-sm-5">
										<span class="block input-icon input-icon-right">
											<input id="subModelName" type="text" >
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" >主机型选择</label>
									<div class="col-xs-10 col-sm-5">
										<span class="block input-icon input-icon-right">
											<select id="subModelSelectBtn" class="chosen-select" >
											<c:forEach items="${aircraftAll}" var="ao">
												<option value="${ao.overviewID }">${ao.modelName}</option>
											</c:forEach>
											</select>
										</span>
									</div>
								</div>
							</form>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			    			<button id="subModel-confirmBtn" type="button" class="btn btn-primary">确定</button>
			            </div>
			        </div>
			    </div>
			</div>
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />	
		</plugin_css>
		<plugin_js>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			
			<!-- 文本框扩展查询 -->
			<script src="<%=basePath%>ras/common/js/typeahead.jquery.js"></script>
			
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
			
			<script src='<%=basePath%>ras/common/js/chosen/chosen.jquery.js'></script>
			
		</plugin_js>
		<script type="text/javascript" src="document.js"></script>	
	</body>
</html>
