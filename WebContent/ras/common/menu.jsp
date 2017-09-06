<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="algz.platform.core.shiro.authority.resourceManager.*" %>
<%@page import="algz.platform.util.Common" %>
<%@page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="menu" tagdir="/WEB-INF/tags/uiframe/menu" %> 
<%

HttpServletRequest httpRequest=(HttpServletRequest)request;  
String strBackUrl = "http://" + request.getServerName() //服务器地址  
+ ":"   
+ request.getServerPort()           //端口号  
+ httpRequest.getContextPath()      //项目名称  
+ httpRequest.getServletPath()      //请求页面或其他地址  
+ "?" + (httpRequest.getQueryString()); //参数  
String ss=strBackUrl;
strBackUrl=httpRequest.getAttribute("javax.servlet.include.request_uri")+"111";

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String pathUrl=request.getAttribute("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping")+"";

	ServletContext   servletContext   =   request.getSession().getServletContext();           
	ApplicationContext   ac   =   WebApplicationContextUtils.getWebApplicationContext(servletContext);

	//获取ApplicationContext.xml文件中定义的BEAN
	ResourceService resourceService=(ResourceService)ac.getBean("resourceServiceImpl");
	List<Resource> list=resourceService.findAllByUser(Common.getLoginUser(),pathUrl);
	request.setAttribute("menus", list);
	

%>

			<!-- #section:basics/sidebar.horizontal -->
			<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
<%-- 
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				    <!-- 屏幕大窗口状态 -->
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>

						<!-- #section:basics/sidebar.layout.shortcuts -->
						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>

						<!-- /section:basics/sidebar.layout.shortcuts -->
					</div>
					<!-- 屏幕小窗口状态 -->
					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->
--%>
				<menu:menu menus="${menus }" hover="hover" />

				<!-- #section:basics/sidebar.layout.minimize -->

				<!-- /section:basics/sidebar.layout.minimize -->
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>

			<!-- /section:basics/sidebar.horizontal -->