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
	<plugin_css>
	
	</plugin_css>
	
		<page:page page="${page }">
		
			<!-- PAGE CONTENT BEGINS -->
			<input name="modelName" type="hidden"/>
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">
						参数：
					</div>
					<div class="row">
						<div class="col-xs-12">
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
								</thead>
								<tbody>
									<tr>
										<td width="150">
											<a href="#">机型</a>
										</td>
										<td class="hidden1-480">
											<input name="modelName" type="hidden">
											<input id="modelNameBtn" class="typeahead scrollable" type="text" placeholder="机型名称" />
											<span id="modelNameContent"></span>
										</td>
									</tr>

									<tr>
										<td>
											<a href="#">X轴</a>
										</td>
										<td class="hidden1-480">
											<input name="xAxis" type="hidden">
											<input id="xAxisTxt" disabled="disabled" type="text" placeholder="" />
											<button id="xAxisBtn" class="btn">选择</button>
										</td>
									</tr>

									<tr>
										<td>
											<a href="#">Y轴</a>
										</td>
										<td class="hidden1-480">
											<input name="yAxis" type="hidden">
											<input id="yAxisTxt" disabled="disabled" type="text" placeholder="" />
											<button id="yAxisBtn" class="btn">选择</button>
											<!-- <span class="label label-sm label-warning">Expiring</span> -->
										</td>
									</tr>

								</tbody>
							</table>
						</div><!-- /.span -->
					</div><!-- /.row -->
				</div>
			</div>
			
			<p>
			
			<div class="row">
				<div class="col-sm-offset-6">
					<div class="btn-group">
						<button id="subbtn" class="btn btn-primary" >提交</button>
					</div>
				</div>
			</div>
			<div class="hr hr8 hr-dotted"></div>
			<select id="chartType">
				<option value="scatter">散点图</option>
				<option value="column">矩形图</option>
			</select>
			<div id="highchart" ></div>

<div id="modal-form" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">        
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            	<span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">...</h4>
        	</div>
            <div class="modal-body">            
          		<dl class="dl-horizontal " id="dt-list-1">
					<c:forEach items="${searchTags }" var="searchTag">
					<c:if test="${searchTag.searchTags.size()!=0 }">
					<dt><span class="label" data_id="${searchTag.id }">${searchTag.name}</span></dt>
						<dd>
							<div class="btn-group" data-toggle="buttons">
							<c:forEach items="${searchTag.searchTags }" var="ctag">
							<c:if test="${ctag.ui_type=='number'||ctag.ui_type=='numberRegion'}">
								<label class="btn btn-sm btn-white btn-info">
									<input type="checkbox" name="${ctag.enname }" value="${ctag.id }" >
									<span>${ctag.name }</span>
								</label>
							</c:if>
							</c:forEach>
							</div>
						</dd>
						<p/>
						</c:if>
					</c:forEach>
				</dl>
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
		<plugin_js>
			<!-- dataTable.js 表格-->
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
			<!-- highcharts.js 图表-->
			<script src="<%=basePath%>ras/common/js/highcharts/highcharts.js"></script>

			<!-- 文本框扩展查询 -->
			<script src="<%=basePath%>ras/common/js/typeahead.jquery.js"></script>
			
		</plugin_js>
		<!-- inline scripts related to this page -->
		<script type="text/javascript" src="analyze.js"></script>

	</body>
</html>
