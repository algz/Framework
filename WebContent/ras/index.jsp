<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@page import="net.sf.json.JSONArray" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<title>Dashboard - Ace Admin</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<jsp:include  page="common/common_css.jsp"/> 
		<jsp:include  page="common/common_js.jsp"/> 

	</head>

	<body class="no-skin">

		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			

			<div class="widget-main">
					<form id="searchForm" class="form-search" >
						<div class="row">
							<div class="col-xs-12 col-sm-8">
								<div class="input-group">
									<input name="modelName" class="form-control search-query" 
									type="text" placeholder="飞机名称" 
									value="${modelName }">
									<span class="input-group-btn">
										<button id="submitBtn" class="btn btn-purple btn-sm" type="button">
											查询一下
											<i class="icon-search icon-on-right bigger-110"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
					</form>
				</div>

			
			<div class="hr hr8 hr-dotted"></div>
<c:if test="${searchs!=null}">
   <c:forEach var="item"   items="${searchs}" >
			<div class="row">
				<div class="widget-box transparent col-xs-3">
	
						<div class="widget-body">
							<div class="widget-main padding-6 no-padding-left no-padding-right">
								<div class="clearfix ">
									<div class="grid2">
								    	<!-- 图片相框 -->
										<span class="profile-picture">
											<img class=" img-responsive " id="avatar" alt="Alex's Avatar" src="../picture/homeBackground.jpg">
										</span>
									</div>
	
									<div class="grid2">
									<p><p>
									<a href="../search/searchsummarize?overviewID=${item.OVERVIEWID }">${item.MODELNAME}</a><p>${item.AIRCRAFTTYPE}<p>${item.PRODUCER_COUNTRIES }<p>${item.MANUFACTURER }
									</div>
								</div>
								
								
							</div>
						</div>
						
				</div>
			</div>
</c:forEach>
</c:if>
			

					
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
<script type="text/javascript">
$('#submitBtn').click(function(){
	$('#searchForm').submit();
})
</script>


	</body>
</html>
