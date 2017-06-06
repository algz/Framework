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

		<jsp:include  page="../../common/common_css.jsp"/> 
		<jsp:include  page="../../common/common_js.jsp"/> 
	</head>

	<body >


		
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			ras_user.jsp
			authentication  [ɔːˌθentɪ'keɪʃn] n. 证明；鉴定
authorization [ˌɔːθərə'zeɪʃn] n. 授权(书)；批准
authority  [ə'θɔːrəti] n. 权力；官方；当局；职权；权威
			<div class="row">
				<div class="col-xs-12">
					
					<div class="table-header">
						用户权限
					</div>

					<!-- <div class="table-responsive"> -->
					<div class="btn-group">
						<a class="btn btn-sm" type="button" href="./addmodel">授权</a>
						<!-- <button class="btn btn-sm" type="button">修改</button>
						<button class="btn btn-sm" type="button">删除</button> -->
					</div>
					<!-- <div class="dataTables_borderWrap"> -->
					<div>
										
						<table id="table-authority" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
			

			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		
		<plugin_js>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
		</plugin_js>
		<script type="text/javascript" src="ras_user.js"></script>		

	</body>
</html>
