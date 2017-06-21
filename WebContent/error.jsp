<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>404 Error Page</title>

		<meta name="description" content="404 Error Page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="/algz/ras/common/css/bootstrap.css" />
		<link rel="stylesheet" href="/algz/ras/common/css/font-awesome.css" />

		<!-- page specific plugin styles -->

		<!-- text fonts -->
		<link rel="stylesheet" href="/algz/ras/common/css/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="/algz/ras/common/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="../common/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/algz/ras/common/css/ace-ie.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="/algz/ras/common/js/ace-extra.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="/algz/ras/common/js/html5shiv.js"></script>
		<script src="/algz/ras/common/js/respond.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">

			<div class="main-content">
				<div class="main-content-inner">

					<!-- /section:basics/content.breadcrumbs -->
					<div class="page-content">

						<!-- /section:settings.box -->
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<!-- #section:pages/error -->
								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-sitemap"></i>
												404
											</span>
											Page Not Found
										</h1>

										<hr />
										
										<h3 class="lighter smaller">
										<%=request.getParameter("msg") %>
										 ${msg }<%-- 重定向页面. EL表达式取不到值--%>
										</h3>

										<hr />
										<div class="space"></div>

										<div class="center">
											<a href="javascript:history.back()" class="btn btn-grey">
												<i class="ace-icon fa fa-arrow-left"></i>
												返回
											</a>

											<a href="/algz/ras/" class="btn btn-primary">
												<i class="ace-icon fa fa-tachometer"></i>
												主页
											</a>
										</div>
									</div>
								</div>

								<!-- /section:pages/error -->

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<!-- #section:basics/footer -->
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

						&nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
					</div>

					<!-- /section:basics/footer -->
				</div>
			</div>

		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='../common/js/jquery.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='../common/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../common/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="../common/js/bootstrap.js"></script>

		<!-- page specific plugin scripts -->

		

	</body>
</html>
