<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ras.index.Navbar" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="navbar" tagdir="/WEB-INF/tags/uiframe/navbar" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	

	
	Navbar navbar=new Navbar(); 
	navbar.setNavbarbrand("总体部飞机论证参照系统");
	navbar.setIsTask("1"); //任务
	navbar.setIsNotice("1"); //消息
	navbar.setIsMessage("0"); //信息
	request.setAttribute("navbar", navbar);
	//map.put("navbar", navbar);
%>
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default    navbar-collapse       h-navbar">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<!-- #section:basics/navbar.layout.brand -->
			<a href="#" class="navbar-brand">
				<small>
					<i class="fa fa-leaf"></i>
					${navbar.navbarbrand }
				</small>
			</a>
		</div>

		<!-- #section:basics/navbar.dropdown -->
		<div class="navbar-buttons navbar-header pull-right  collapse navbar-collapse" role="navigation">
			<ul class="nav ace-nav">
			
						<navbar:navbar-menu-ul header_txt="任务标题" footer_txt="footer" num="5" color="transparent">
							<navbar:navbar-menu-li>
								<div class="clearfix">
									<span class="pull-left">text-left</span>
									<span class="pull-right">text-right</span>
								</div>
		
								<div class="progress progress-mini">
									<div style="width:65%" class="progress-bar"></div>
								</div>
							</navbar:navbar-menu-li>
						</navbar:navbar-menu-ul>
			
				<li class="transparent">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-bell icon-animated-bell"></i>
					</a>

					<div class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab" href="#navbar-tasks">
										Tasks
										<span class="badge badge-danger">4</span>
									</a>
								</li>

								<li>
									<a data-toggle="tab" href="#navbar-messages">
										Messages
										<span class="badge badge-danger">5</span>
									</a>
								</li>
							</ul><!-- .nav-tabs -->

							<div class="tab-content">
								<div id="navbar-tasks" class="tab-pane in active">
									<ul class="dropdown-menu-right dropdown-navbar dropdown-menu">
										<li class="dropdown-content">
											<ul class="dropdown-menu dropdown-navbar">
												<li>
													<a href="#">
														<div class="clearfix">
															<span class="pull-left">Software Update</span>
															<span class="pull-right">65%</span>
														</div>

														<div class="progress progress-mini">
															<div style="width:65%" class="progress-bar"></div>
														</div>
													</a>
												</li>

												<li>
													<a href="#">
														<div class="clearfix">
															<span class="pull-left">Hardware Upgrade</span>
															<span class="pull-right">35%</span>
														</div>

														<div class="progress progress-mini">
															<div style="width:35%" class="progress-bar progress-bar-danger"></div>
														</div>
													</a>
												</li>

												<li>
													<a href="#">
														<div class="clearfix">
															<span class="pull-left">Unit Testing</span>
															<span class="pull-right">15%</span>
														</div>

														<div class="progress progress-mini">
															<div style="width:15%" class="progress-bar progress-bar-warning"></div>
														</div>
													</a>
												</li>

												<li>
													<a href="#">
														<div class="clearfix">
															<span class="pull-left">Bug Fixes</span>
															<span class="pull-right">90%</span>
														</div>

														<div class="progress progress-mini progress-striped active">
															<div style="width:90%" class="progress-bar progress-bar-success"></div>
														</div>
													</a>
												</li>
											</ul>
										</li>

										<li class="dropdown-footer">
											<a href="#">
												See tasks with details
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</li>
									</ul>
								</div><!-- /.tab-pane -->

								<div id="navbar-messages" class="tab-pane">
									<ul class="dropdown-menu-right dropdown-navbar dropdown-menu">
										<li class="dropdown-content">
											<ul class="dropdown-menu dropdown-navbar">
												<li>
													<a href="#">
														<img src="../assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
														<span class="msg-body">
															<span class="msg-title">
																<span class="blue">Alex:</span>
																Ciao sociis natoque penatibus et auctor ...
															</span>

															<span class="msg-time">
																<i class="ace-icon fa fa-clock-o"></i>
																<span>a moment ago</span>
															</span>
														</span>
													</a>
												</li>

												<li>
													<a href="#">
														<img src="../assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
														<span class="msg-body">
															<span class="msg-title">
																<span class="blue">Susan:</span>
																Vestibulum id ligula porta felis euismod ...
															</span>

															<span class="msg-time">
																<i class="ace-icon fa fa-clock-o"></i>
																<span>20 minutes ago</span>
															</span>
														</span>
													</a>
												</li>

												<li>
													<a href="#">
														<img src="../assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
														<span class="msg-body">
															<span class="msg-title">
																<span class="blue">Bob:</span>
																Nullam quis risus eget urna mollis ornare ...
															</span>

															<span class="msg-time">
																<i class="ace-icon fa fa-clock-o"></i>
																<span>3:15 pm</span>
															</span>
														</span>
													</a>
												</li>

												<li>
													<a href="#">
														<img src="../assets/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" />
														<span class="msg-body">
															<span class="msg-title">
																<span class="blue">Kate:</span>
																Ciao sociis natoque eget urna mollis ornare ...
															</span>

															<span class="msg-time">
																<i class="ace-icon fa fa-clock-o"></i>
																<span>1:33 pm</span>
															</span>
														</span>
													</a>
												</li>

												<li>
													<a href="#">
														<img src="../assets/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" />
														<span class="msg-body">
															<span class="msg-title">
																<span class="blue">Fred:</span>
																Vestibulum id penatibus et auctor  ...
															</span>

															<span class="msg-time">
																<i class="ace-icon fa fa-clock-o"></i>
																<span>10:09 am</span>
															</span>
														</span>
													</a>
												</li>
											</ul>
										</li>

										<li class="dropdown-footer">
											<a href="inbox.html">
												See all messages
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</li>
									</ul>
								</div><!-- /.tab-pane -->
							</div><!-- /.tab-content -->
						</div><!-- /.tabbable -->
					</div><!-- /.dropdown-menu -->
				</li>

				<!-- #section:basics/navbar.user_menu -->
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<c:if test="${user.photo!=null }">
						<img class="nav-user-photo" width="36" height="36" src="${user.photo }" alt="${user.username}'s Photo" />
						</c:if>
						
						<span class="user-info">
							<small>欢迎,</small>
							${user.username}
						</span>

						<i class="ace-icon fa fa-caret-down"></i>
					</a>

					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="#">
								<i class="ace-icon fa fa-cog"></i>
								设置
							</a>
						</li>

						<li>
							<a href="profile.html">
								<i class="ace-icon fa fa-user"></i>
								配置
							</a>
						</li>

						<li class="divider"></li>

						<li>
							<a href="<%=basePath%>logout">
								<i class="ace-icon fa fa-power-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>

				<!-- /section:basics/navbar.user_menu -->
			</ul>
		</div>

		<!-- /section:basics/navbar.dropdown -->
		<nav role="navigation" class="navbar-menu pull-left collapse navbar-collapse">
			<!-- #section:basics/navbar.nav -->
			<ul class="nav navbar-nav">
				<li>
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						概述&nbsp;
						<i class="ace-icon fa fa-angle-down bigger-110"></i>
					</a>

					<ul class="dropdown-menu dropdown-light-blue dropdown-caret">
						<li>
							<a href="#">
								<i class="ace-icon fa fa-book bigger-110 blue"></i>
								说明
							</a>
						</li>
					</ul>
				</li>
				<c:if test="${navbar.isNotice=='1'}">
				<li>
					<a href="#">
						<i class="ace-icon fa fa-envelope"></i>
						消息
						<span class="badge badge-warning">5</span>
					</a>
				</li>
				</c:if>

			</ul>

			<!-- /section:basics/navbar.nav -->

			<!-- #section:basics/navbar.form -->
			<form class="navbar-form navbar-left form-search" role="search">
				<div class="form-group">
					<input type="text" placeholder="查询" />
				</div>

				<button type="button" class="btn btn-xs btn-info2">
					<i class="ace-icon fa fa-search icon-only bigger-110"></i>
				</button>
			</form>

			<!-- /section:basics/navbar.form -->
		</nav>
	</div><!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->

