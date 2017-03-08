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


		<jsp:include  page="../common/common_css.jsp"  >
			<jsp:param name="pageTitle" value="首页"/>  
		</jsp:include>
		
		<jsp:include  page="../common/common_js.jsp"/> 
	</head>
	
	<body >
	
	<!-- 需加载到CSS的插件 -->
	<plugin_css><link rel="stylesheet" href="<%=basePath%>ras/common/css/ui.jqgrid.css " /></plugin_css>
	
		<page:page page="${page }">
		
			<!-- PAGE CONTENT BEGINS -->
			<input name="modelName" type="hidden"/>
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">
						参数：
					</div>
					<div>

					<div class="row">
						<div class="col-xs-12">
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
								</thead>
								<tbody>
									<tr>
										<td>
											<a href="#">机型</a>
										</td>
										<td class="hidden-480">
											<span id="modelName" class="label label-sm label-warning">Expiring</span>
										</td>

										<td>
											<div class="hidden-sm hidden-xs btn-group">

												<button class="btn btn-xs btn-info">
													<i class="ace-icon fa fa-pencil bigger-120"></i>
												</button>

											</div>

										</td>
									</tr>

									<tr>

										<td>
											<a href="#">X轴</a>
										</td>

										<td class="hidden-480">
											<span id="xAxis" class="label label-sm label-success">Registered</span>
										</td>

										<td>
											<div class="hidden-sm hidden-xs btn-group">

												<button class="btn btn-xs btn-info">
													<i class="ace-icon fa fa-pencil bigger-120"></i>
												</button>

											</div>

										</td>
									</tr>

									<tr>

										<td>
											<a href="#">Y轴</a>
										</td>

										<td class="hidden-480">
											<span id="yAxis" class="label label-sm label-warning">Expiring</span>
										</td>

										<td>
											<div class="hidden-sm hidden-xs btn-group">
												<button class="btn btn-xs btn-info">
													<i class="ace-icon fa fa-pencil bigger-120"></i>
												</button>
											</div>

										</td>
									</tr>

								</tbody>
							</table>
						</div><!-- /.span -->
					</div><!-- /.row -->

					</div>
				</div>
			</div>
			
			<p>
			
			<div class="row">
				<div class="col-sm-offset-6">
					<div class="btn-group">
						<button id="subbtn" class="btn btn-primary" >提交</button>
					</div>
				</div>
			</div>
<div class="hr hr8 hr-dotted"></div>
			<div id="highchart" ></div>

<div id="modal-table" class="modal fade" tabindex="-1">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header no-padding">
												<div class="table-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														<span class="white">&times;</span>
													</button>
													Results for "Latest Registered Domains
												</div>
											</div>

											<div class="modal-body no-padding">
												<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
													<thead>
														<tr>
															<th>Domain</th>
															<th>Price</th>
															<th>Clicks</th>

															<th>
																<i class="ace-icon fa fa-clock-o bigger-110"></i>
																Update
															</th>
														</tr>
													</thead>

													<tbody>
														<tr>
															<td>
																<a href="#">ace.com</a>
															</td>
															<td>$45</td>
															<td>3,330</td>
															<td>Feb 12</td>
														</tr>

														<tr>
															<td>
																<a href="#">base.com</a>
															</td>
															<td>$35</td>
															<td>2,595</td>
															<td>Feb 18</td>
														</tr>

														<tr>
															<td>
																<a href="#">max.com</a>
															</td>
															<td>$60</td>
															<td>4,400</td>
															<td>Mar 11</td>
														</tr>

														<tr>
															<td>
																<a href="#">best.com</a>
															</td>
															<td>$75</td>
															<td>6,500</td>
															<td>Apr 03</td>
														</tr>

														<tr>
															<td>
																<a href="#">pro.com</a>
															</td>
															<td>$55</td>
															<td>4,250</td>
															<td>Jan 21</td>
														</tr>
													</tbody>
												</table>
											</div>

											<div class="modal-footer no-margin-top">
												<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
													<i class="ace-icon fa fa-times"></i>
													Close
												</button>

												<ul class="pagination pull-right no-margin">
													<li class="prev disabled">
														<a href="#">
															<i class="ace-icon fa fa-angle-double-left"></i>
														</a>
													</li>

													<li class="active">
														<a href="#">1</a>
													</li>

													<li>
														<a href="#">2</a>
													</li>

													<li>
														<a href="#">3</a>
													</li>

													<li class="next">
														<a href="#">
															<i class="ace-icon fa fa-angle-double-right"></i>
														</a>
													</li>
												</ul>
											</div>
										</div><!-- /.modal-content -->
									</div><!-- /.modal-dialog -->
								</div><!-- PAGE CONTENT ENDS -->
							
			
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		<plugin_js>
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/jquery.dataTables.bootstrap.js"></script>
			<script src="<%=basePath%>ras/common/js/highcharts/highcharts.js"></script>

		</plugin_js>
		<!-- inline scripts related to this page -->
		<script type="text/javascript" src="analyze.js"></script>

		
	</body>
</html>
