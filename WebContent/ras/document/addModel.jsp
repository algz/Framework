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
			<div>
				<h3 class="header smaller lighter purple">
					新增机型
				</h3>
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="./savemodel" method="post">
							<form:form-group id="form-model" label="机型名"/>
							<form:form-group id="form-cname" label="中文名称"/>
							<form:form-group id="form-ename" label="英文名称"/>
							<form:form-action >
								<form:form-button buttonType="submit" id="submit" label="提交" icon="fa-check" buttonClass="btn-info"/>
								<form:form-button id="canle" label="取消" url="./"/>
							</form:form-action>
						</form>
					</div><!-- /.col -->
				</div>
			</div>
			

			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<script type="text/javascript" src="addModel.js"></script>		

	</body>
</html>
