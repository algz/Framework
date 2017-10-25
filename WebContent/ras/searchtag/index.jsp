<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray" %>

<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tab" tagdir="/WEB-INF/tags/uiframe/tab" %> 
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

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<jsp:include  page="../common/common_css.jsp"/> 
		<jsp:include  page="../common/common_js.jsp"/> 

	</head>

	<body class="no-skin">

		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			

			<div class="widget-main">
						<div class="row">
							<div class="col-xs-12 col-sm-8">
								<div class="input-group">
									<input id="tag" class="form-control search-query" 
									type="text" placeholder="标签名称" 
									value="${tag }">
									<input type='hidden' name='start' value='${start ==null?0:start}'>
									<input type='hidden' name='length' value='${length==null?10:length }'>
									<span class="input-group-btn">
										<button id="submitBtn" class="btn btn-purple btn-sm" type="button">
											查询一下
											<i class="icon-search icon-on-right bigger-110"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
				</div>

			
			<div class="hr hr8 hr-dotted"></div>
			<div id="tab_row" class="row hidden1">
				<div class="col-sm-12">
					<tab:tab titles="参数,图片,文档" paths="param.jsp,picture.jsp,archive.jsp"/>
				</div><!-- /.col -->	
			</div>
			
<%-- <c:if test="${searchs!=null}">
   <c:forEach var="item"   items="${searchs}" >
			<div class="row">
				<div class="widget-box transparent col-xs-3">
	
						<div class="widget-body">
							<div class="widget-main padding-6 no-padding-left no-padding-right">
								<div class="clearfix ">
									<div class="grid2">
								    	<!-- 图片相框 -->
										<span class="profile-picture">
											<a href="../search/searchsummarize?tab=1&overviewID=${item.OVERVIEWID }">
											<img class=" img-responsive "  alt="机型图片" src="${item.PHOTOURL==null?'holder.js/209x122':item.PHOTOURL }">
											</a>
										</span>
									</div>
	
									<div class="grid2">
									<p><p>
									<a href="../search/searchsummarize?overviewID=${item.OVERVIEWID }">${item.MODELNAME}</a>
									<p>${item.AIRCRAFTTYPE}<p>${item.PRODUCERCOUNTRIES }
									<p>${item.MANUFACTURER }
									</div>
								</div>
								
								
							</div>
						</div>
						
				</div>
			</div>
</c:forEach>
			<div class="row">
			<div class=" col-xs-12">
				<ul class="pagination">
					<li id='previousPage' class='${curPage==1?"disabled":""}'>
						<span>
							<i class="ace-icon fa fa-angle-double-left"></i>
						</span>
					</li>

					<c:forEach begin="1" end="${pageCount }" var="i">
					<li class='${i==curPage?"active":"" }' >
						<span>${i}</span>
					</li>
					</c:forEach>
					
					
					<li id='nextPage' class='${curPage==pageCount?"disabled":""}'>
						<span><i class="ace-icon fa fa-angle-double-right"></i></span>
					</li>
				</ul>
				</div>
			</div>	
</c:if> --%>
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox) -->
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/colorbox.css" />
		</plugin_css>
		<plugin_js>
		 	<!-- dataTables -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			<!-- 配置后,需设置 "language":dataTables_zh  -->
			<script src="<%=basePath%>ras/common/js/dataTables/i18n/dataTables_zh.lang"></script>
			
			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox)  -->
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>
			

		
		</plugin_js>
<script type="text/javascript">
$(function(){
	//查询按钮
	$('#submitBtn').click(function(){
		var tagName=$('#tag').val()
		if(tagName!=""){
			$("#tab_row").removeClass("hidden")
			$('#paramTable').DataTable().ajax.reload();
			$('#pictureTable').DataTable().ajax.reload();
			$('#archiveTable').DataTable().ajax.reload();
		}
	});
	
	/* $('.pagination li').on('click',function(e){
		var el=$(e.target).closest('li');
		var num=0;//$(el).text();
		if(!el.hasClass('disabled')){
			
			var start=$(':hidden[name=start]').val();
			var length=$(':hidden[name=length]').val();
			if(el[0].id=='previousPage'){
				num=$('.pagination li.active span').text();
				num--;
			}else if(el[0].id=='nextPage'){
				num=$('.pagination li.active span').text();
				num++;
			}else{
				num=$(el).text();
			}
			$(':hidden[name=start]').val((num-1)*(parseInt(length)))
			$('#searchForm').submit();
		}

	}) */
    
})

</script>


	</body>
</html>
