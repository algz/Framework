<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="algz.platform.core.shiro.authority.userManager.User" %>
<%@page import="algz.platform.core.shiro.authority.roleManager.Role" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	User user=(User)org.apache.shiro.SecurityUtils.getSubject().getSession().getAttribute("LoginUser");
	request.setAttribute("user", user);
	//session.setAttribute("user", user);
	boolean isDataManager=false;
	for(String roleID:user.getRoleIds()){
		if(roleID.equals("2")){
			isDataManager=true;
		}
	}
	request.setAttribute("isDataManager", isDataManager);

	
%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<!-- <title><sitemesh:write property='title' /></title> -->
		<title>总体部飞机论证参照系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		

		<meta name="description" content="top menu &amp; navigation" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<jsp:include  page="common/common_css.jsp">
			<jsp:param value="<sitemesh:write property='plugin_css' />" name="plugins_css"/>
		</jsp:include> 
		<jsp:include  page="common/common_js.jsp">
			<jsp:param value="<sitemesh:write property='plugin_js' />" name="plugins_js"/>
		</jsp:include> 
	</head>
	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<jsp:include  page="common/navbar.jsp"/> 
		<!-- /section:basics/navbar.layout -->
		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<jsp:include  page="common/menu.jsp"/> 

			<sitemesh:write property='body' />

			<jsp:include  page="common/footer.jsp"/> 
		</div><!-- /.main-container -->

<script type="text/javascript">
//全局变量
var basePath='<%=basePath%>';

var algz={};
algz.curUser={};
algz.curUser.username='${user.username}'
algz.curUser.roles=${user.getRoleIds()}
algz.curUser.isDataManager=${isDataManager}


$(function(){
	
	if(document.body.clientWidth<991){
		alert("请将页面宽度调整至991px以上或全屏.");
	}
})
</script>
		

		<!-- inline scripts related to this page 
		<script type="text/javascript">
			jQuery(function($) {
			 var $sidebar = $('.sidebar').eq(0);
			 if( !$sidebar.hasClass('h-sidebar') ) return;
			
			 $(document).on('settings.ace.top_menu' , function(ev, event_name, fixed) {
				if( event_name !== 'sidebar_fixed' ) return;
			
				var sidebar = $sidebar.get(0);
				var $window = $(window);
			
				//return if sidebar is not fixed or in mobile view mode
				var sidebar_vars = $sidebar.ace_sidebar('vars');
				if( !fixed || ( sidebar_vars['mobile_view'] || sidebar_vars['collapsible'] ) ) {
					$sidebar.removeClass('lower-highlight');
					//restore original, default marginTop
					ace.helper.removeStyle(sidebar , 'margin-top')
			
					$window.off('scroll.ace.top_menu')
					return;
				}
			
			
				 var done = false;
				 $window.on('scroll.ace.top_menu', function(e) {
			
					var scroll = $window.scrollTop();
					scroll = parseInt(scroll / 4);//move the menu up 1px for every 4px of document scrolling
					if (scroll > 17) scroll = 17;
			
			
					if (scroll > 16) {			
						if(!done) {
							$sidebar.addClass('lower-highlight');
							done = true;
						}
					}
					else {
						if(done) {
							$sidebar.removeClass('lower-highlight');
							done = false;
						}
					}
			
					sidebar.style['marginTop'] = (17-scroll)+'px';
				 }).triggerHandler('scroll.ace.top_menu');
			
			 }).triggerHandler('settings.ace.top_menu', ['sidebar_fixed' , $sidebar.hasClass('sidebar-fixed')]);
			
			 $(window).on('resize.ace.top_menu', function() {
				$(document).triggerHandler('settings.ace.top_menu', ['sidebar_fixed' , $sidebar.hasClass('sidebar-fixed')]);
			 });
			
			
			});
		</script>
		-->
	</body>
</html>
