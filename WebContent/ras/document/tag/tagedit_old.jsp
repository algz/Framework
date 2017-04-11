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

		<jsp:include  page="../../common/common_css.jsp"/> 
		<jsp:include  page="../../common/common_js.jsp"/> 
	</head>

	<body >


		
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			    <div class=row>
					<div  class="modal" style="width:600px;height:450px; " >
							<div class="col1-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form class="form-horizontal" >
									<!-- #section:elements.form -->
									
									<div class="row">

										<div class="col-xs-12 col-sm-4">


														<!-- #section:plugins/input.chosen -->
														<div>
															<label for="form-field-select-3">Chosen</label>

															<br />
															<select class="chosen-select form-control" id="form-field-select-3" data-placeholder="Choose a State...">
																<option value="">  </option>
																<option value="AL">Alabama</option>
																<option value="AK">Alaska</option>
																<option value="AZ">Arizona</option>
																</select>
														</div>

														<!-- /section:plugins/input.chosen -->

										</div><!-- /.span -->
									</div><!-- /.row -->
								</form>
</div><!-- /.col --></div>
						</div><!-- /.row -->
					
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
		<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />
		</plugin_css>
		<plugin_js>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
			
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
			
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/jquery.validate.js"></script>
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/messages_zh.js"></script>
			
			<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>
			
		</plugin_js>
		<script type="text/javascript" src="tagedit.js"></script>	
	</body>
</html>
