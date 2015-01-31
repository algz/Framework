<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频</title>
<!-- 引入jquery.js 必须放在所有JS前 -->
<script type="text/javascript"
	src="<%=path%>/platform/core/lib/jqGrid/js/jquery-1.9.0.min.js"></script>
<!-- 引入Bootstrap.css -->
<link href="<%=path%>/platform/core/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
	
<!-- 引入Bootstrap-tab 标签页 css -->
<script type="text/javascript"
	src="<%=path%>/platform/core/lib/jquery_plugins/bootstrap-tab.js"></script>

<!-- 引入Image缩略图  holder.js -->
<script type="text/javascript" src="<%=path%>/video/lib/holder.js"></script>

<style>
/*自定义tabs样式*/
.tabbable {
	border: 1px solid #ddd;
	border-top: 0;
}
/*自定义缩略图样式*/
.thumbnails {
margin-bottom:0;
}

/*列表框样式*/
#side {
	float: right;
	width: 225px;
	overflow: hidden;
	font-size: 12px;
}

#clside {
	float: right;
	width: 252px;
	overflow: hidden
}

.rside {
	border: 1px #D4D3D3 solid;
	/* border-top: 0; */
	background-color: #FFF;
	overflow: hidden;
	margin-bottom:10px;
}

.rside h2 {
	height: 37px;
	margin: 0;
	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#fff),
		to(#f2f2f2) );
	border-bottom: 1px solid #d4d4d4;
	padding: 0 20px;
	font: 14px/40px "微软雅黑";
	font-weight: bold;
}

.rside ul {
	padding: 6px 0 6px;
	margin: 0;
	list-style: none;
}

.rside ul li {
	background: url(<%=path%>/video/lib/images/icon.jpg) 5px 8px no-repeat;
	padding: 0 5px 0 23px;
	text-align: right;
	line-height: 25px;
	height: 25px;
	overflow: hidden;
	padding-right: 8px;
	border-bottom: 1px solid #f3f3f3;
}

.rside ul li a {
	float: left;
}

.rside p {
	text-align: right;
	padding: 0 15px 1px;
	font: 11px/17px Tahoma;
}

.rside .sj0 {
	margin: 15px 0 0 0;
	width: 30px;
	height: 25px;
	line-height: 25px;
	float: right;
	font-size: 12px;
	font-weight: normal;
	text-align: center;
	background: #FFF;
}

.rside .sj {
	margin: 15px 0 0 0;
	width: 30px;
	height: 25px;
	line-height: 25px;
	float: right;
	font-size: 12px;
	font-weight: normal;
	text-align: center;
	border: 0;
}
</style>
</head>
<body>
<%@ include file="common/header.jsp" %>
	<div class="container">
		<div class="row-fluid">
			<div class="span9">
				<div class="tabbable" >
					<ul class="nav nav-tabs">
						<li class="active" ><a href="#tab1" data-toggle="tab" style='border-left:0;' >热播电影</a></li>
						<li><a href="#tab1" data-toggle="tab">动作</a></li>
						<li><a href="#tab1" data-toggle="tab">喜剧</a></li>
						<li><a href="#tab1" data-toggle="tab">爱情</a></li>
						<li><a href="#tab1" data-toggle="tab">科幻</a></li>
						<li><a href="#tab1" data-toggle="tab">剧情</a></li>
						<li><a href="#tab1" data-toggle="tab">恐怖</a></li>
						<li><a href="#tab2" data-toggle="tab">战争</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
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
							<div class="row-fluid">
								<ul class="thumbnails" style='padding:0 10px 0;'>
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
						</div>
						<div class="tab-pane" id="tab2">
							<p>Howdy, I'm in Section 2.</p>
						</div>
					</div>
				</div>
			</div>
			<div class="span3">
				<div id="side">
<div class="rside">
	<h2>本站今日更新：[15] </h2>
	<ul >
	<li><a href="/?s=video/detail/id/27498.html" title="今日VIP" target="_blank">今日VIP[20130521]</a><span><font color="red"><font color="red">05-22</font></font></span></li>
	<li><a href="/?s=video/detail/id/27477.html" title="东张西望" target="_blank">东张西望[20130521]</a><span><font color="red"><font color="red">05-22</font></font></span></li><li><a href="/?s=video/detail/id/28919.html" title="张玉贞为爱而生" target="_blank">张玉贞为爱而生[14]</a><span><font color="red"><font color="red">05-22</font></font></span></li><li><a href="/?s=video/detail/id/28716.html" title="先声夺人/美国之声/美国好声音 第四季" target="_blank">先声夺人/美国之声/美国[19]</a><span><font color="red"><font color="red">05-22</font></font></span></li><li><a href="/?s=video/detail/id/29262.html" title="食平D" target="_blank">食平D[16]</a><span><font color="red"><font color="red">05-22</font></font></span></li><li><a href="/?s=video/detail/id/27445.html" title="国光帮帮忙" target="_blank">国光帮帮忙[20130521]</a><span><font color="red"><font color="red">05-22</font></font></span></li><li><a href="/?s=video/detail/id/28931.html" title="九家之书" target="_blank">九家之书[14]</a><span><font color="red"><font color="red">05-22</font></font></span></li><li><a href="/?s=video/detail/id/27489.html" title="大学生了没" target="_blank">大学生了没[20130521]</a><span><font color="red"><font color="red">05-22</font></font></span></li>	</ul>
	<p><a href="/index.php?s=video/search/p/1.html" target="_blank">更多&gt;&gt;</a></p>
</div>

	
<div class="rside">
	<h2>电影排行榜<div id="dy_3" class="sj" onmousemove="phbcl('dy',3,3)">总</div><div id="dy_2" class="sj0" onmousemove="phbcl('dy',2,3)">周</div><div id="dy_1" class="sj" onmousemove="phbcl('dy',1,3)">日</div></h2>
	<ul id="dyul_1" style="display:none" >
	<li><a href="/?s=video/detail/id/28735.html" title="楼/鬼楼" target="_blank">楼/鬼楼</a><span>05-18</span></li><li><a href="/?s=video/detail/id/27956.html" title="速度与激情6" target="_blank">速度与激情6</a><span>05-19</span></li><li><a href="/?s=video/detail/id/28953.html" title="19车/惊魂19车/玩命车手" target="_blank">19车/惊魂19车/玩命车手</a><span>05-14</span></li><li><a href="/?s=video/detail/id/29532.html" title="少林杀手：血钱" target="_blank">少林杀手：血钱</a><span>05-19</span></li><li><a href="/?s=video/detail/id/11406.html" title="携枪流浪汉/流浪汉的猎枪" target="_blank">携枪流浪汉/流浪汉的猎</a><span>05-20</span></li>	</ul>
	<ul id="dyul_2" style="display:block">
	<li><a href="/?s=video/detail/id/27956.html" title="速度与激情6" target="_blank">速度与激情6</a><span>05-19</span></li><li><a href="/?s=video/detail/id/27558.html" title="虎胆龙威5" target="_blank">虎胆龙威5</a><span>05-14</span></li><li><a href="/?s=video/detail/id/21547.html" title="钢铁侠3" target="_blank">钢铁侠3</a><span>05-06</span></li><li><a href="/?s=video/detail/id/29532.html" title="少林杀手：血钱" target="_blank">少林杀手：血钱</a><span>05-19</span></li><li><a href="/?s=video/detail/id/24083.html" title="致我们终将逝去的青春" target="_blank">致我们终将逝去的青春</a><span>05-05</span></li>	</ul>
	<ul id="dyul_3" style="display:none">
	<li><a href="/?s=video/detail/id/22894.html" title="人再囧途之泰囧" target="_blank">人再囧途之泰囧</a><span>04-27</span></li><li><a href="/?s=video/detail/id/24080.html" title="101次求婚(黄渤版)" target="_blank">101次求婚(黄渤版)</a><span>03-08</span></li><li><a href="/?s=video/detail/id/27231.html" title="古惑仔：江湖新秩序/新古惑仔" target="_blank">古惑仔：江湖新秩序/新</a><span>03-26</span></li><li><a href="/?s=video/detail/id/15297.html" title="金陵十三钗" target="_blank">金陵十三钗</a><span>12-16</span></li><li><a href="/?s=video/detail/id/21419.html" title="生肖传说/十二生肖" target="_blank">生肖传说/十二生肖</a><span>03-14</span></li>	</ul>
</div>
<div class="rside mtop">
	<h2>电视剧排行榜<div id="dsj_3" class="sj" onmousemove="phbcl('dsj',3,3)">总</div><div id="dsj_2" class="sj0" onmousemove="phbcl('dsj',2,3)">周</div><div id="dsj_1" class="sj" onmousemove="phbcl('dsj',1,3)">日</div></h2>
	<ul id="dsjul_1" style="display:none" class=''>
	<li><a href="/?s=video/detail/id/29326.html" title="陆贞传奇" target="_blank">陆贞传奇</a><span>05-21</span></li><li><a href="/?s=video/detail/id/29372.html" title="宝贝(电视剧)" target="_blank">宝贝(电视剧)</a><span>05-21</span></li><li><a href="/?s=video/detail/id/29330.html" title="天才碰麻瓜" target="_blank">天才碰麻瓜</a><span>05-20</span></li><li><a href="/?s=video/detail/id/29111.html" title="江湖正道" target="_blank">江湖正道</a><span>05-02</span></li><li><a href="/?s=video/detail/id/29297.html" title="风影" target="_blank">风影</a><span>05-15</span></li>	</ul>
	<ul id="dsjul_2" style="display:block">
	<li><a href="/?s=video/detail/id/29326.html" title="陆贞传奇" target="_blank">陆贞传奇</a><span>05-21</span></li><li><a href="/?s=video/detail/id/29372.html" title="宝贝(电视剧)" target="_blank">宝贝(电视剧)</a><span>05-21</span></li><li><a href="/?s=video/detail/id/29406.html" title="枪械师" target="_blank">枪械师</a><span>05-20</span></li><li><a href="/?s=video/detail/id/20753.html" title="火线三兄弟" target="_blank">火线三兄弟</a><span>05-07</span></li><li><a href="/?s=video/detail/id/28967.html" title="大宅门1912" target="_blank">大宅门1912</a><span>04-22</span></li>	</ul>
	<ul id="dsjul_3" style="display:none">
	<li><a href="/?s=video/detail/id/7564.html" title="飞虎神鹰" target="_blank">飞虎神鹰</a><span>11-20</span></li><li><a href="/?s=video/detail/id/14568.html" title="后宫甄嬛传" target="_blank">后宫甄嬛传</a><span>11-26</span></li><li><a href="/?s=video/detail/id/19114.html" title="轩辕剑之天之痕" target="_blank">轩辕剑之天之痕</a><span>09-01</span></li><li><a href="/?s=video/detail/id/27711.html" title="新笑傲江湖" target="_blank">新笑傲江湖</a><span>03-04</span></li><li><a href="/?s=video/detail/id/21367.html" title="我是特种兵之利刃出鞘" target="_blank">我是特种兵之利刃出鞘</a><span>10-14</span></li>	</ul>
</div>
<div class="rside mtop">
	<h2>动漫排行榜<div id="dm_3" class="sj" onmousemove="phbcl('dm',3,3)">总</div><div id="dm_2" class="sj0" onmousemove="phbcl('dm',2,3)">周</div><div id="dm_1" class="sj" onmousemove="phbcl('dm',1,3)">日</div></h2>
	<ul id="dmul_1" style="display:none">
	<li><a href="/?s=video/detail/id/28885.html" title="进击的巨人" target="_blank">进击的巨人</a><span>05-19</span></li><li><a href="/?s=video/detail/id/28882.html" title="人鱼又上钩" target="_blank">人鱼又上钩</a><span>05-19</span></li><li><a href="/?s=video/detail/id/25644.html" title="鬼马小精灵" target="_blank">鬼马小精灵</a><span>09-10</span></li><li><a href="/?s=video/detail/id/22196.html" title="樱花大战OVA" target="_blank">樱花大战OVA</a><span>04-23</span></li><li><a href="/?s=video/detail/id/11798.html" title="SA特优生" target="_blank">SA特优生</a><span>10-30</span></li>	</ul>
	<ul id="dmul_2" style="display:block">
	<li><a href="/?s=video/detail/id/6511.html" title="火影忍者" target="_blank">火影忍者</a><span>05-16</span></li><li><a href="/?s=video/detail/id/13491.html" title="海贼王" target="_blank">海贼王</a><span>05-19</span></li><li><a href="/?s=video/detail/id/28638.html" title="疯狂原始人/古鲁家族" target="_blank">疯狂原始人/古鲁家族</a><span>04-29</span></li><li><a href="/?s=video/detail/id/6670.html" title="全职猎人 2011" target="_blank">全职猎人 2011</a><span>05-19</span></li><li><a href="/?s=video/detail/id/28885.html" title="进击的巨人" target="_blank">进击的巨人</a><span>05-19</span></li>	</ul>
	<ul id="dmul_3" style="display:none">
	<li><a href="/?s=video/detail/id/6511.html" title="火影忍者" target="_blank">火影忍者</a><span>05-16</span></li><li><a href="/?s=video/detail/id/13491.html" title="海贼王" target="_blank">海贼王</a><span>05-19</span></li><li><a href="/?s=video/detail/id/18741.html" title="名侦探柯南" target="_blank">名侦探柯南</a><span>05-18</span></li><li><a href="/?s=video/detail/id/6598.html" title="妖精的尾巴" target="_blank">妖精的尾巴</a><span>03-30</span></li><li><a href="/?s=video/detail/id/19803.html" title="秦时明月之万里长城" target="_blank">秦时明月之万里长城</a><span>01-02</span></li>	</ul>
</div>
<div class="rside mtop">
	<h2>综艺排行榜<div id="zy_3" class="sj" onmousemove="phbcl('zy',3,3)">总</div><div id="zy_2" class="sj0" onmousemove="phbcl('zy',2,3)">周</div><div id="zy_1" class="sj" onmousemove="phbcl('zy',1,3)">日</div></h2>
	<ul id="zyul_1" style="display:none">	<li><a href="/?s=video/detail/id/15108.html" title="天天向上" target="_blank">天天向上</a><span>05-17</span></li><li><a href="/?s=video/detail/id/20164.html" title="陈晓东-倩女幽魂" target="_blank">陈晓东-倩女幽魂</a><span>03-25</span></li><li><a href="/?s=video/detail/id/19283.html" title="老鹰合唱团墨尔本告别巡回演唱会" target="_blank">老鹰合唱团墨尔本告别巡</a><span>03-14</span></li><li><a href="/?s=video/detail/id/8329.html" title="娱乐大风暴2011-2012" target="_blank">娱乐大风暴2011-2012</a><span>12-31</span></li><li><a href="/?s=video/detail/id/13624.html" title="大剧院零距离" target="_blank">大剧院零距离</a><span>05-09</span></li>	</ul>
	<ul id="zyul_2" style="display:block">	<li><a href="/?s=video/detail/id/16260.html" title="壹周立波秀" target="_blank">壹周立波秀</a><span>05-19</span></li><li><a href="/?s=video/detail/id/27561.html" title="非诚勿扰" target="_blank">非诚勿扰</a><span>05-19</span></li><li><a href="/?s=video/detail/id/27555.html" title="快乐大本营" target="_blank">快乐大本营</a><span>05-18</span></li><li><a href="/?s=video/detail/id/29138.html" title="NBA季后赛 2012-2013" target="_blank">NBA季后赛 2012-201</a><span>05-20</span></li><li><a href="/?s=video/detail/id/29114.html" title="中国最强音" target="_blank">中国最强音</a><span>05-19</span></li>	</ul>
	<ul id="zyul_3" style="display:none">	<li><a href="/?s=video/detail/id/13604.html" title="非诚勿扰2010-2012" target="_blank">非诚勿扰2010-2012</a><span>12-30</span></li><li><a href="/?s=video/detail/id/24967.html" title="中国好声音" target="_blank">中国好声音</a><span>10-10</span></li><li><a href="/?s=video/detail/id/16063.html" title="NBA常规赛 2011-2012" target="_blank">NBA常规赛 2011-201</a><span>06-23</span></li><li><a href="/?s=video/detail/id/15108.html" title="天天向上" target="_blank">天天向上</a><span>05-17</span></li><li><a href="/?s=video/detail/id/24929.html" title="百变大咖秀" target="_blank">百变大咖秀</a><span>05-17</span></li>	</ul>
</div>

</div>
			</div>
		</div>
	</div>
	<%@ include file="common/footer.jsp" %>
</body>
</html>