<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="menu" tagdir="/WEB-INF/tags/uiframe/menu" %> 

<%

	String path = request.getContextPath(); //项目名称  
%>

<!-- 标签属性 -->
<%@attribute name="hover"  rtexprvalue="true" required="false" description="是否浮动" %>
<%@attribute name="menu"  rtexprvalue="true" required="false" type="algz.platform.system.menu.Menu" description="菜单实体类" %>
<c:choose>
<c:when test="${menu.menus.size()==0 }">
	<li class="${menu.active=='1'?'active':''} ${hover} ">
		<a href="<%=path %>${menu.url }" >
			<i class="menu-icon fa ${menu.icon }"></i>
			<span class="menu-text"> ${menu.text} </span>
		</a>
		<b class="arrow"></b>

		<jsp:doBody/>
	</li>
</c:when>
<c:otherwise>
	<li class="${menu.active=='1'?'active':''} ${hover }">
		<a href="#" class="dropdown-toggle" >
			<i class="menu-icon fa ${menu.icon }"></i>
			<span class="menu-text"> ${menu.text} </span>
			<b class="arrow fa fa-angle-down"></b>
		</a>
		<b class="arrow"></b>
		<menu:menu-ul menus="${menu.menus }" hover="${hover }"/>
		<jsp:doBody/>
	</li>
</c:otherwise>
</c:choose>

