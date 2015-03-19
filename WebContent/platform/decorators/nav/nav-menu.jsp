<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"  %>
<%@ page import="algz.platform.core.shiro.authority.resourceManager.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="java.util.List" %>

<%!
//暂时只支持二层菜单,JS不支持多层
public String getMenus(List<Resource> list,int rootID){
	String s="";
	for(Resource menu:list){
		if(menu.getParentId().intValue()==rootID){
			Boolean f=true;
			for(Resource subMenu:list){
				if(subMenu.getParentId().equals(menu.getId())){
					f=false;
				}
			}
			if(!f){
				s+="<li class='dropdown'><a data-toggle='dropdown' href='#'>"+
						"<span aria-hidden='true' class='se7en-star'></span>"+menu.getName()+
						"<b class='caret'></b></a><ul class='dropdown-menu'>";
					s+=getMenus(list,menu.getId().intValue());
					s+="</ul></li>";
			}else{
				if(!menu.isRootNode()){
					//非根叶子结点
					
					if(menu.getIsNew()){
						s+="<li><a href='"+menu.getUrl()+"'>"+
							"<span class='notifications label label-warning'>新</span>"+menu.getName()+"</a></li>";
					}else{
						s+="<li><a href='"+menu.getUrl()+"'>"+menu.getName()+"</a></li>";
					}
				}else{
					//根叶子结点
					s+="<li><a class='current' href='"+menu.getUrl()+"'>"+
					"<span aria-hidden='true' class='"+menu.getIco()+"'></span>"+menu.getName()+"</a></li>";
				}
			}
		}
	}
	return s;
}%>
		<%
		ApplicationContext ac   =   WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

		//获取ApplicationContext.xml文件中定义的BEAN
		ResourceServiceImpl resourceService=(ResourceServiceImpl)ac.getBean("resourceServiceImpl");
		List<Resource> menuList=resourceService.findMenus(null);
		%>
<div class="container-fluid main-nav clearfix">
	<div class="nav-collapse">
		<ul class="nav">

		<%
		out.println(getMenus(menuList,0));;
		%>
			<li>
				<a class="current" href="index-2.html"> 
					<span aria-hidden="true" class="se7en-home"></span>控制台
				</a>
			</li>
			
			<li>
				<a href="social.html"> <span aria-hidden="true"
					class="se7en-feed"></span>社交 订阅
				</a>
			</li>
			<li class="dropdown">
				<a data-toggle="dropdown" href="#"> 
					<span aria-hidden="true" class="se7en-star"></span>特色<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="buttons.html">Buttons</a></li>
					<li><a href="fontawesome.html">Font Awesome Icons</a></li>
					<li><a href="glyphicons.html"> <span
							class="notifications label label-warning">新</span>
							<p>Glyphicons</p></a></li>
					<li><a href="components.html">Components</a></li>
					<li><a href="widgets.html">Widgets</a></li>
					<li><a href="typo.html">Typography</a></li>
					<li><a href="grid.html">Grid Layout</a></li>
				</ul>
			</li>
			<li class="dropdown"><a data-toggle="dropdown" href="#"> <span
					aria-hidden="true" class="se7en-forms"></span>表单<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="form-components.html"> <span
							class="notifications label label-warning">新</span>
							<p>Form Components</p></a></li>
					<li><a href="form-advanced.html">Advanced Forms</a></li>
				</ul></li>
			<li class="dropdown"><a data-toggle="dropdown" href="#"> <span
					aria-hidden="true" class="se7en-tables"></span>表格<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="tables.html">Basic tables</a></li>
					<li><a href="datatables.html">DataTables</a></li>
					<li><a href="datatables-editable.html">
							<div class="notifications label label-warning">新</div>
							<p>Editable DataTables</p>
					</a></li>
				</ul></li>
			<li><a href="charts.html"> <span aria-hidden="true"
					class="se7en-charts"></span>图表
			</a></li>
			<li class="dropdown"><a data-toggle="dropdown" href="#"> <span
					aria-hidden="true" class="se7en-pages"></span>页面<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="chat.html"> <span
							class="notifications label label-warning">新</span>
							<p>Chat</p></a></li>
					<li><a href="calendar.html">Calendar</a></li>
					<li><a href="timeline.html"> <span
							class="notifications label label-warning">新</span>
							<p>时间轴</p></a></li>
					<li><a href="login1.html"> <span
							class="notifications label label-warning">新</span>
							<p>Login 1</p></a></li>
					<li><a href="login2.html">Login 2</a></li>
					<li><a href="signup1.html"> <span
							class="notifications label label-warning">新</span>
							<p>Sign Up 1</p></a></li>
					<li><a href="signup2.html">Sign Up 2</a></li>
					<li><a href="invoice.html"> <span
							class="notifications label label-warning">新</span>
							<p>订单</p></a></li>
					<li><a href="faq.html"> <span
							class="notifications label label-warning">新</span>
							<p>FAQ</p></a></li>
					<li><a href="filters.html">筛选结果</a></li>
					<li><a href="404-page.html">404 Page</a></li>
				</ul></li>
			<li><a href="gallery.html"> <span aria-hidden="true"
					class="se7en-gallery"></span>相册
			</a></li>
		</ul>
	</div>
</div>