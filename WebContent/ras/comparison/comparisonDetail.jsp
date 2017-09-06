<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

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
					<input id="basicID" type="hidden" value="${basicID }" />
					<label>
						<small class="smaller-90">隐藏空数据:</small>
						<input class="ace ace-switch ace-switch-5" id="closeSpaceBtn" type="checkbox">
						<span class="lbl middle"></span>
					</label>
					<div class="btn-group">
						<button id="selectModelParamBtn" class="btn btn-sm btn-primary" type="button">筛选</button>

						<button id="createReportBtn" class="btn btn-sm btn-primary" type="button">生成报告</button>
					</div>
					<table id="comparisonDetail-table" width="100%" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center" width="130">
								</th>
							<c:forEach items="${models}" var="model" varStatus="status" >
								<th  class="center" >
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
			<p>
			<div class="row">
				<div class="col-sm-offset-11">
					<div class="btn-group" >
						<a class="btn btn-primary" href="javascript:history.go(-1);">返回</a>
					</div>
				</div>
			</div>
			
<div id="modal-form" class="modal fade" tabindex="-1">
    <div class="modal-dialog width-100">
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
								<label class="btn btn-sm btn-white btn-info">
									<input type="checkbox" name="${ctag.enname }" value="${ctag.id }" >
									<span>${ctag.name }</span>
								</label>
							
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

<div id="createReport-form" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">        
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            	<span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">生成报告</h4>
        	</div>
            <div class="modal-body">
	            <form class="form-horizontal">
	            	<form:form-group id="reportName" label="报告名称" type="textArea" />
	            	<form:form-group id="reportDes" label="报告结论" type="textArea" />
				</form>
          	
          	</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveReport" >保存</button>
            </div>
        </div>
    </div>
</div>
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		
		<plugin_js>
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			
			<!-- 图片放大查看 
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>-->


		</plugin_js>
		<script type="text/javascript" src="comparisonDetail.js"></script>		

	</body>
</html>
