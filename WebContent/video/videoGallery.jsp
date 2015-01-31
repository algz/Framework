<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>视频集</title>
<!-- 引入jquery.js 必须放在所有JS前 -->
<script type="text/javascript"
	src="<%=path%>/platform/core/lib/jqGrid/js/jquery-1.9.0.min.js"></script>
<!-- 引入Bootstrap.css -->
<link href="<%=path%>/platform/core/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet">

<!-- 引入panel.css -->
<link href="<%=path%>/video/lib/css/widget/panel.css" rel="stylesheet">

<!-- 引入Image缩略图  holder.js -->
<script type="text/javascript" src="<%=path%>/video/lib/holder.js"></script>


<style type="text/css">
/**/
.retrieve li
</style>

</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class='container'>
		<div class='row-fluid'>
			<div class='span9'>
				<div class="panel" style='margin-top: 7px;'>
					<div class='content'>
						<ul class="nav nav-pills">
							<li><span style='padding-top: 8px;
padding-bottom: 8px;
margin-top: 2px;
margin-bottom: 2px;padding-right: 12px;
padding-left: 12px;
margin-right: 2px;
line-height: 14px;display: block;'>按地区：</span></li>
							<li><a href="#">全部</a></li>
							<li><a href="#">韩剧</a></li>
						</ul>
					</div>
				</div>
<div class='panel'>
<div class='content'>
<div class="btn-group">
  <button class="btn">最近更新</button>
  <button class="btn">最受欢迎</button>
  <button class="btn">评分最高</button>
  <button class="btn">最新上映</button>
</div>

<div class="row-fluid">
								<ul class="thumbnails" style='padding:0 10px 0;'>
									<li class="span3">
										<div class="thumbnail">
											<a href='videoDetails.jsp'><img data-src="holder.js/160x120" alt=""></a>
											<span><a href='#'>电影名称</a></span>
											<div>评论数</div>
											<p>简介</p>
										</div>
									</li>
									<li class="span3">
										<div class="thumbnail">
											<img data-src="holder.js/160x120" alt="">
											<span><a href='#'>电影名称</a></span>
											<div>评论数</div>
											<p>简介</p>
										</div>
									</li>
									<li class="span3">
										<div class="thumbnail">
											<img data-src="holder.js/160x120" alt="">
											<span><a href='#'>电影名称</a></span>
											<div>评论数</div>
											<p>简介</p>
										</div>
									</li>
									<li class="span3">
										<div class="thumbnail">
											<img data-src="holder.js/160x120" alt="">
											<span><a href='#'>电影名称</a></span>
											<div>评论数</div>
											<p>简介</p>
										</div>
									</li>
								</ul>
							</div>
							<div class="pagination">
  <ul>
    <li><a href="#">Prev</a></li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li><a href="#">Next</a></li>
  </ul>
</div>
</div>
</div>
			</div>
			<div class='span3'></div>
		</div>
	</div>
	<%@ include file="common/footer.jsp"%>
</body>
</html>