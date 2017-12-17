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

		<jsp:include  page="../common/common_css.jsp"/> 
		<jsp:include  page="../common/common_js.jsp"/> 
	</head>

	<body >


		
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->	
			
			
			<div class="widget-box">
				<div class="widget-header widget-header-flat widget-header-small">
					<div class="widget-title">查询条件:
					</div>
				</div>
				
				<div class="widget-body">
					<div class="widget-main">
									
						<div class="row">
							
							<div class="col-xs-12">
								<form>
									<div>
										<!-- <div class="btn-group">
											<button id="addProductSearchBtn" class="btn btn-purple btn-sm" type="button">+</button>
										</div> -->
										<select name="paramID" class="chosen-select"  >
										<c:forEach items="${productCombox}" var="product">
											<optgroup label="${product.productName }">
											<c:forEach items="${product.childrenProducts }" var="subProduct">
												<option value="${subProduct.productID }">${subProduct.productName }</option>
											</c:forEach>
											</optgroup>
										</c:forEach>
										</select>
										<label class="btn btn-sm  inline">
											<small class="muted smaller-90">=</small>
										</label>
										<div class="btn-group">
											<input name="paramValue" type="text" >
										</div>
										<div class="btn-group">
											<button id="productSearchBtn" class="btn btn-purple btn-sm" type="button">查询</button>
										</div>
									</div>
									<div style="display:none;">
										<div class="btn-group">
											<button id="addProductSearchBtn" class="btn btn-purple btn-sm" type="button">-</button>
										</div>
										<select class="chosen-select"  >
										
										</select>
										<label class="btn btn-sm  inline">
											<small class="muted smaller-90">=</small>
										</label>
										<input type="text" >
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">
					<!-- <div class="btn-group">
						<button id="comparisonModelparamBtn" class="btn btn-purple btn-sm" type="button">对比</button>
						<button id="analyzeModelparamBtn" class="btn btn-purple btn-sm" type="button">分析</button>
					</div> 
					<label class="btn btn-sm  inline">
						<small class="muted smaller-90">隐藏个人:</small>
						<input class="ace ace-switch ace-switch-5" id="showPersonDataBtn" type="checkbox" >
						<span class="lbl middle" ></span>
					</label>
					-->
					<div>
						<table id="searchTable" class="table table-striped table-bordered table-hover">
						</table>
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

		
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>

		<script type="text/javascript" src="productSearch.js"></script>		

	</body>
</html>
