<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频详情</title>
<!-- 引入jquery.js 必须放在所有JS前 -->
<script type="text/javascript"
	src="<%=path%>/platform/core/lib/jqGrid/js/jquery-1.9.0.min.js"></script>
<!-- 引入Bootstrap.css -->
<link href="<%=path%>/platform/core/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- widget -->
<!-- 引入panel -->
<link href="<%=path%>/video/lib/css/widget/panel.css"
	rel="stylesheet">
<!-- 引入Image缩略图 js -->
<script type="text/javascript" src="<%=path%>/video/lib/holder.js"></script>

<style type="text/css">
.media ul {
	padding:  0 ;
	margin: 0;
	list-style: none;
}

.media ul li {
	text-align: left;
	line-height: 25px;
	height: 25px;
	padding-right: 8px;
}

.media ul li a {
	float: left;
}





</style>
</head>
<body>
<%@ include file="common/header.jsp" %>
	<div class="container">
		<div class="row-fluid">
			<div class="span9">
				<ul class="breadcrumb" style='margin:0;'>
					<li><a href="#">首页</a> <span class="divider">/</span></li>
					<li><a href="#">Library</a> <span class="divider">/</span></li>
					<li class="active">Data</li>
				</ul>
				<div class="media" style='margin-top:7px;' >
					<a class="pull-left" href="#"> <img class="media-object"
						data-src="holder.js/200x280">
					</a>
					<div class="media-body" style='height: 280px; position: relative;'>
						<h4 class="media-heading">电影名称</h4>
						<!-- Nested media object -->
						<div class="media"
							style='padding: 10px 0; border-top: 1px solid #e1e1e8; border-bottom: 1px solid #e1e1e8;'>
							<a class="pull-left" href="#"> <img class="media-object"
								data-src="holder.js/31x50">
							</a>
							<div class="media-body">
								<ul>
									<li>我的评分：12345</li>
									<li>评价：197人 订阅：188人 指数：3,194,577</li>
								</ul>
							</div>
						</div>
						<ul>
						<li>导演：XXX</li>
						<li>演员：XXX/XXX/XXX 更多></li>
						<li>类型：古装/经典/悬疑</li>
						<li>首映：年 月 日 中国</li>
						</ul>
						<div style='position: absolute; bottom: 0px;'>
							<div class="btn-group">
								<a class="btn" href='#'>立即观看</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>点评</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>转发</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>订阅</a>
							</div>
						</div>
					</div>
				</div>
				<div class="panel">
					<div class='title'>播放列表</div>
					<div class='content'>
						<div class="btn-toolbar">
							<div class="btn-group">
								<a class="btn" href='#'>第一集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第二集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第三集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第四集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第五集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第六集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第七集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第八集</a>
							</div>
						</div>
						<div class="btn-toolbar">
							<div class="btn-group">
								<a class="btn" href='#'>第一集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第二集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第三集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第四集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第五集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第六集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第七集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第八集</a>
							</div>
							<div class="btn-group">
								<a class="btn" href='#'>第九集</a>
							</div>
						</div>
					</div>
				</div>
				<div class='panel' >
					<h2>剧情介绍</h2>
					<div class='content'>故事将改编自乔治·R·R·马丁（George RR
						Martin）系列小说的第三卷《冰雨的风暴》。大卫·贝尼奥夫表示，《权力的游戏》第三季将继续做到最好，力争超越第二季的精彩。在原著中，有大量涉及儿童、法律和道义上敏感话题和色情内容，但他们都将这些内容一一过滤，并尽量让《权力的游戏》在不越线的同时，也能保持该作品的原汁原味。
					</div>
				</div>
				<div class='panel' >
				    <h2>影评(12345)</h2>
					<div class='content'>
						<form class='form-horizontal' action="">
							<div class="media">
								<a class="pull-left" href="#"> 
								<img class="media-object" data-src="holder.js/64x64">
								</a>
								<div class="media-body">
									<div class="control-group" >
										<textarea rows="3" style='width:97%;'></textarea>
									</div>
									<div class="btn-group pull-right"">
										<button class="btn">发布</button>
									</div>
								</div>
							</div>
						</form>
						<div class="media"
							style='padding: 7px 0; border-top: 1px solid #e1e1e8;'>
							<a class="pull-left" href="#"> 
							   <img class="media-object"
								data-src="holder.js/64x64">
							</a>
							<div class="media-body">
								<div>影评内容</div>
								<div style='margin-top:5px;'>
								  <p class="pull-left">XXXX年XX月XX日 来自:XXX</p>
							      <p class='pull-right'>有用 (0)</p>
							    </div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="span3">
				<div style='padding: 0 5px;border: 1px solid #e1e1e8;'>
					<span style='font: 14px/40px "微软雅黑"; font-weight: bold;'>
					   相关推荐
					</span>
					<div class='row-fluid'>
						<ul class="thumbnails">
							<li class="span6">
								<div class="thumbnail">
									<img data-src="holder.js/100x140" alt="" />
								</div>
								<p>电影名称</p>
							</li>
							<li class="span6">
								<div class="thumbnail">
									<img data-src="holder.js/100x140" alt="" />
								</div>
								<p>电影名称</p>
							</li>
						</ul>
					</div>
					<div class='row-fluid'>
						<ul class="thumbnails">
							<li class="span6">
								<div class="thumbnail">
									<img data-src="holder.js/100x140" alt="">
								</div>
							</li>
							<li class="span6">
								<div class="thumbnail">
									<img data-src="holder.js/100x140" alt="">
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class='panel' style='margin-top:7px;'>
				 <h2>热播剧</h2>
				 <div class='content'>
				 </div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="common/footer.jsp" %>
</body>
</html>