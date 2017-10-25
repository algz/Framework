<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="tab" tagdir="/WEB-INF/tags/uiframe/tab" %> 
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//User user=(User)org.apache.shiro.SecurityUtils.getSubject().getSession().getAttribute("LoginUser");
	
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
			
			<div class="row">
				<div class="col-xs-12 col-sm-3 center">
					<div class="space space-4"></div>
					<span class="profile-picture">
						<img class="editable img-responsive" alt="Alex's Avatar" id="avatar2" src="../common/avatars/profile-pic.jpg" />
					</span>

				</div><!-- /.col -->

				<div class="col-xs-12 col-sm-9">
					<h4 class="blue">
						<span class="middle">${LoginUser.cname}</span>

						<span class="label label-purple arrowed-in-right">
							<i class="ace-icon fa fa-circle smaller-80 align-middle"></i>
							在线
						</span>
					</h4>

					<div class="profile-user-info">
						<div class="profile-info-row">
							<div class="profile-info-name"> 用户名 </div>

							<div class="profile-info-value">
								<span>${LoginUser.username}</span>
							</div>
						</div>
						
						<!-- <div class="profile-info-row">
							<div class="profile-info-name"> 工号 </div>

							<div class="profile-info-value">
								<span>0001</span>
							</div>
						</div> -->
						
						<div class="profile-info-row">
							<div class="profile-info-name"> 单位 </div>

							<div class="profile-info-value">
								<i class="fa fa-map-marker light-orange bigger-110"></i>
								<span>650所</span>
								<span>总体室</span>
							</div>
						</div>

						<!-- <div class="profile-info-row">
							<div class="profile-info-name"> 年龄 </div>

							<div class="profile-info-value">
								<span>38</span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name"> 入职日期 </div>

							<div class="profile-info-value">
								<span>2010/06/20</span>
							</div>
						</div> 

						<div class="profile-info-row">
							<div class="profile-info-name"> 最后在线时间 </div>

							<div class="profile-info-value">
								<span>3小时前</span>
							</div>
						</div>-->
					</div>

					<div class="hr hr-8 dotted"></div>

					
				</div><!-- /.col -->
			</div><!-- /.row -->
			
			<div class="row">
				<div id="user-profile-2" class="user-profile">
				
					<div class="tabbable">
						<ul class="nav nav-tabs padding-18">
							<tab:tab-nav id="report" active="active" label="报告"/>
							<tab:tab-nav id="favorites"  label="收藏记录"/>
						</ul>
					
						<div class="tab-content no-border padding-24">
							<tab:tab-panel id="report" active="active">
								<div class="row">
										<div class="col-xs-12 col-sm-12">
											<div class="btn-group">
												<button id="modifyReport" class="btn btn-purple btn-sm" type="button">修改</button>
												<button id="delReport" class="btn btn-purple btn-sm" type="button">删除</button>
											</div>
											<table id="report-table" class="table table-striped table-bordered table-hover"></table>
										</div>
								</div>
							</tab:tab-panel>
							<tab:tab-panel id="favorites" >
								<div class="row">
										<div class="col-xs-12 col-sm-12">
											<div class="btn-group">
												<button id="modifyFavorites" class="btn btn-purple btn-sm" type="button">修改</button>
												<button id="delFavorites" class="btn btn-purple btn-sm" type="button">删除</button>
											</div>
											<table id="favoretes-table" class="table table-striped table-bordered table-hover"></table>
										</div>
								</div>
							</tab:tab-panel>
							<tab:tab-panel id="picture" >
							</tab:tab-panel>
						</div>
					</div>
				
				</div>
			</div>
			
			
			
			<div class="row hide">
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
			<!-- 配置后,需设置 "language":dataTables_zh  -->
			<script src="<%=basePath%>ras/common/js/dataTables/i18n/dataTables_zh.lang"></script>
			
			<!-- 提示框 -->
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
			
		</plugin_js>
		<script type="text/javascript" src="personal.js"></script>		

	</body>
</html>
