<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %>
<%@page import="net.sf.json.JSONObject" %>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<input type="hidden" id="favoritesID">
<label class="pull-right inline">
	<small class="smaller-90">是否收藏:</small>
	<input class="ace ace-switch ace-switch-5" id="isFavoritesBtn" type="checkbox" >
	<span class="lbl middle" ></span>
</label>

<div class="row">	

	<div class="col-xs-9">
<form id='modelParamForm' action="./savemodelparam" method="post">
	<input name="overviewID" type="hidden" value="<%=request.getParameter("overviewID") %>"/>
	<c:forEach var="dataParam" items="${paramMap }" varStatus="status">
	<c:if test="${fn:length(dataParam.dataMap)!=0 }">
	
	<div>
		<h3 class="header smaller lighter purple">
			${dataParam.caption}
			<c:if test="${status.index==0}">
			<label>
				<small class="smaller-90">开启注释:</small>
				<input class="ace ace-switch ace-switch-5" id="checkBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			<label>
				<small class="smaller-90">隐藏空行:</small>
				<input class="ace ace-switch ace-switch-5" id="closeSpaceBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			<c:if test="${permissionLevel=='1' }">
			<label>
				<small class="smaller-90">开启编辑:</small>
				<input class="ace ace-switch ace-switch-5" id="closeEditBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			</c:if>

			</c:if>
		</h3>
					
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="form-horizontal" role="form">
					<input name="basicID" type="hidden" value="<%=request.getParameter("basicID") %>"/>
					<!-- <input name="editor" type="hidden" value="${EDITOR}"/> -->
					<c:forEach var="basicItem" items="${dataParam.dataMap }">
						<c:choose>
						<c:when test="${basicItem.elType=='textArea' }">
							<form:form-group id="${basicItem.elID }" label="${basicItem.elLabel }" type="textArea" value="${basicItem.elValue}" readonly="${basicItem.readonly}"/>   
					    
						</c:when>
						<c:otherwise>
							<form:form-group id="${basicItem.elID }" label="${basicItem.elLabel }"  hidden="${basicItem.elID!='dataSources'?'false':'true'}" value="${basicItem.elValue}" readonly="${basicItem.readonly}" >
								<c:if test="${basicItem.readonly==null&&basicItem.elID!='dataSources'}">
								<span class="input-group-btn hidden">
									<button name='modelparamEditBtn' class="btn btn-xs btn-primary" type="button">
										编辑
									</button>
								</span>
								</c:if>

							</form:form-group> 

						</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div><!-- /.col -->
		</div>
	</div>
	
	</c:if>
</c:forEach>

	<div class="row">
		<div class="col-sm-offset-6">
			<div class="btn-group">
				<a class="btn btn-primary" href="javascript:window.close();history.back()">取消</a>
			</div>
		</div>
	</div>

	

</form>
</div>
	<div style="float:left; position: relative;">
			<div id='affixTable' class="hidden" style='position:fixed' data-offset-top="125" data-spy="affix" >
				
					<div class="table-header">
						机型参数来源注释
					</div>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center" style='width: 100px'>数据来源</th>
								<th class="center">数据值</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
			</div>
    </div>
</div>



<div id="modalparam-form" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">        
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            	<span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">机型参数编辑</h4>
        	</div>
            <div class="modal-body">
	            <form class="form-horizontal">
	            	<form:form-group id="dataSources_add" label="数据来源"/>
	            	<input id="param_name" type="hidden">
	            	<form:form-group id="param_text" label="参数名称" readonly="true"/>
	            	<form:form-group id="param_val" label="参数值"/>
				</form>
          	
          	</div>
            <div class="modal-footer">
           		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            	<button id="saveModelParamBtn" type="button" class="btn btn-primary">保存</button>
                
            </div>
        </div>
    </div>
</div>
<script src="./summarize/searchSummarize_param.js"></script>