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
			<form action="searchcriteria" method="post">
			
			<div class="widget-box">
				<div class="widget-header widget-header-flat widget-header-small">
				<div class="widget-title">筛选：
				<span class="label"></span>
			
				
				</div>
				</div>
				
				<div class="widget-body">
					<div class="widget-main">
					
						<dl class="dl-horizontal " id="dt-list-1">
						<c:forEach items="${searchTags }" var="searchTag">
						<c:if test="${searchTag.searchTags.size()!=0 }">
						<dt><span class="label" data_id="${searchTag.id }">${searchTag.name}</span></dt>
							<dd>
								<div class="btn-group" data-toggle="buttons">
								<c:forEach items="${searchTag.searchTags }" var="ctag">
									<label class="btn btn-sm btn-white btn-info">
										<input type="checkbox" name="${ctag.enname }" value="${ctag.id }" >${ctag.name }
									</label>
								</c:forEach>
								</div>
							</dd>
							<p/>
							</c:if>
						</c:forEach>
						
						</dl>
					</div>
				</div>
			
			</div>
			
			
			<div class="row">
				<div class="col-sm-offset-6">
					<div class="btn-group">
						<input type="hidden" id="ids" name="ids" value="">
						<input type="submit" class="btn btn-primary" value="提交">
					</div>
				</div>
			</div>
			</form>
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		
		<script src="search.js"></script>
		<plugin_js>

			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		</plugin_js>
		
		
		
	</body>
</html>
