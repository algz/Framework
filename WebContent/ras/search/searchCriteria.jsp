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
							    <c:when test="${searchTag.ui_type=='text'}">
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
										<a class="btn btn-primary" href="javascript:history.go(-1);">返回</a>
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
					<div>
						<table id="sample-table-2" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				
				</div>
			</div>

			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		
		<plugin_js>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>
		<script type="text/javascript" src="searchCriteria.js"></script>		

	</body>
</html>
