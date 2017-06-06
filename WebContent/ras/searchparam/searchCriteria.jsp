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
					</div>
				</div>
				
				<div class="widget-body">
					<div class="widget-main">
					<form id="tagForm">
						<dl class="dl-horizontal " id="dt-list-1">
						<c:forEach items="${searchTags }" var="searchTag">
						<dt><span class="label">${searchTag.name}</span></dt>
							<dd>							
							<c:choose>
							    <c:when test="${searchTag.ui_type=='checkbox'}">
							    <!-- 复选框 -->
							    <div class="btn-group" data-toggle="buttons">
									<c:forEach items="${searchTag.searchTags }" var="ctag" varStatus="status">
									<label id="${searchTag.enname }${status.index}" class="btn btn-sm btn-white btn-info">
										<input type="checkbox" value="${ctag.name }">${ctag.name }
									</label>
									</c:forEach>
									<input name="${searchTag.enname }" type="hidden" value=""/>
									<script type="text/javascript">
									$("label[id^='${searchTag.enname}']").on('click ',function(){
										var jq=$(':hidden[name="${searchTag.enname }"]')
										var val=jq.val();
										if(this.className.indexOf('active')>0){
											jq.val(val.replace(this.innerText.trim(),""));
											if(jq.val().indexOf(",")==0){
												jq.val(jq.val().substr(1));
											}else if(jq.val().lastIndexOf(",")==jq.val().length-1){
												jq.val(jq.val().substr(0,jq.val().length-1))
											}
										}else{
											jq.val(val+(val!=""?",":"")+this.innerText.trim());
										}
										jq.val(jq.val().replace(",,",","));
									})
										
									</script>
								</div>
							    </c:when>
							    <c:when test="${searchTag.ui_type=='text'||searchTag.ui_type=='number'}">
							    <!-- 文本框 -->
							    <div class="btn-group" data-toggle="buttons">
									<div >
										<input name="${searchTag.enname }" class="col-xs-5 input-sm" type="text" >
									</div>
								</div>
							    </c:when>
							    <c:when test="${searchTag.ui_type=='numberRegion' }">
							    <!-- 区域框 -->
							    <div class="form-inline">
									<input id="${searchTag.enname }start" class="input-small" type="text" >
									- <input id="${searchTag.enname }end" class="input-small" type="text" >
									<input name="${searchTag.enname }" type="hidden"/>
									<script type="text/javascript">
										$("input[id^='${searchTag.enname}']").on('change',function(){
											var val=$("#${searchTag.enname }start").val()+"-"+$("#${searchTag.enname }end").val()
											$(':hidden[name="${searchTag.enname }"]').val(val);
										})
										
									</script>
								</div>
							    </c:when>
							  
							    <c:otherwise>
							        
							    </c:otherwise>
							</c:choose>
							</dd>
							<p>
						</c:forEach>
						    <dt><span class="label">${searchTag.name}</span></dt>
							<dd>
								<div class="col-sm-offset-10">
									<div class="btn-group" data-toggle="buttons">
										<button id='submitBtn' class="btn btn-primary">提交</button>
									</div>
									<div class="btn-group" data-toggle="buttons">
										<input type="button" class="btn btn-primary" value="返回" onclick="history.go(-1)">
									</div>
								</div>
							</dd>
						</dl>
						</form>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">
					<h3 class="header smaller lighter blue"></h3>
					<div class="table-header">
						
					</div>

					<!-- <div class="table-responsive"> -->

					<!-- <div class="dataTables_borderWrap"> -->
					<div class="btn-group">
						<button id="comparisonModelparamBtn" class="btn btn-purple btn-sm" type="button">对比</button>
						<button id="analyzeModelparamBtn" class="btn btn-purple btn-sm" type="button">分析</button>
						
						<span class="input-icon" style="width:500px;">
							<input type="text" class="width-100" id="selectModelName" placeholder="选择的机型"  disabled>
							<input type="hidden" id="selectBasicID">
							<i class="ace-icon fa fa-fighter-jet  blue"></i>
						</span>
					</div>
					<div>
						<table id="searchTable" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				
				</div>
			</div>



<div id="modal-form" class="modal fade" tabindex="-1">
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
							<c:forEach items="${tagSelect }" var="tag">
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
							<c:forEach items="${tagSelect }" var="tag">
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
		

		
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>
		
		<script type="text/javascript" src="tree.js"></script>	
		
		<script type="text/javascript" src="searchCriteria.js"></script>		

	</body>
</html>
