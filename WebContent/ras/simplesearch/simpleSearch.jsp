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
			
			
			<div class="widget-box">
				<div class="widget-header widget-header-flat widget-header-small">
					<div class="widget-title">筛选：
						<span class="label"></span>
						<input type="hidden" class="width-100" id="selectModelName" placeholder="选择的机型"  disabled>
						<input type="hidden" id="selectOverviewID">
					</div>
				</div>
				
				<div class="widget-body">
					<div class="widget-main">
									
						<div class="row">
							<div class="col-xs-4">
								<div id="tree"></div>
							</div>
							
							<div class="col-xs-8">
								<!-- <h3 class="header smaller lighter blue"></h3> -->
								<div class="table-header">
								</div>
			
								<div class="btn-group">
									<button id="comparisonModelparamBtn" class="btn btn-purple btn-sm" type="button">对比</button>
									<button id="analyzeModelparamBtn" class="btn btn-purple btn-sm" type="button">分析</button>
								</div>
								<label class="btn btn-sm  inline">
									<small class="muted smaller-90">隐藏个人:</small>
									<input class="ace ace-switch ace-switch-5" id="showPersonDataBtn" type="checkbox" >
									<span class="lbl middle" ></span>
								</label>
								<div>
									<table id="searchTable" class="table table-striped table-bordered table-hover">
									</table>
								</div>
							
			
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">
					<h3 class="header smaller lighter blue"></h3>
					<div class="table-header">
						
					</div>

					<!-- <div class="table-responsive"> -->


					<div>
						<table id="searchTable1" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				
				</div>
			</div>



<div id="modal-analyzeChart" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">        
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            	<span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">图表分析</h4>
        	</div>
            <div class="modal-body">
	            <form class="form-horizontal">
	            	<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 分析对象 </label>
	
						<div class="col-sm-9">
							<input class="col-xs-10 col-sm-5 width-100" disabled id="modelName" type="text" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> x轴 </label>
	
						<div class="col-sm-9">
							<select class="chosen-select form-control" id="xAxisBtn" data-placeholder="请选择x轴...">
							<option></option>
							<c:forEach items="${searchTags }" var="tag">
							<c:if test="${tag.searchTags.size()!=0 }">
								<optgroup label="${tag.name }">
									<c:forEach items="${tag.searchTags }" var="ctag">
									<c:if test="${ctag.ui_type=='number'||ctag.ui_type=='numberRegion'}">
									<option value="${ctag.enname }">${ctag.name }</option>
									</c:if>
									</c:forEach>
								</optgroup>
								</c:if>
							</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> y轴 </label>
	
						<div class="col-sm-9">
							<select class="chosen-select form-control" id="yAxisBtn" data-placeholder="请选择y轴...">
							<option></option>
							<c:forEach items="${searchTags }" var="tag">
							<c:if test="${tag.searchTags.size()!=0 }">
								<optgroup label="${tag.name }">
									<c:forEach items="${tag.searchTags }" var="ctag">
									<c:if test="${ctag.ui_type=='number'||ctag.ui_type=='numberRegion'}">
									<option value="${ctag.enname }">${ctag.name }</option>
									</c:if>
									</c:forEach>
								</optgroup>
								</c:if>
							</c:forEach>
							</select>
						</div>
					</div>
	            	<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 图表类型 </label>
	
						<div class="col-sm-9">
							<select class="chosen-select form-control" id="chartTypeBtn" data-placeholder="请选择y轴...">
								<option value="scatter">散点图</option>
								<option value="column">矩形图</option>
							</select>
						</div>
					</div>
	            </form>         

          		<div id="highchart" ></div>
          	
          	</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
			<!-- bootstrap select -->
			<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>
			
			<!-- highcharts.js 图表-->
			<script src="<%=basePath%>ras/common/js/highcharts/highcharts.js"></script>
			<script src="<%=basePath%>ras/common/js/highcharts/modules/exporting.js"></script>
			<script src="<%=basePath%>ras/common/js/highcharts/plugins/highcharts-zh_CN.js"></script>
		

		
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>
		
		<!-- bootstrap-treeview.js -->
		<script src="<%=basePath%>ras/common/js/treeview/bootstrap-treeview.js"></script>
		
		<script type="text/javascript" src="simpleSearch.js"></script>		

	</body>
</html>

