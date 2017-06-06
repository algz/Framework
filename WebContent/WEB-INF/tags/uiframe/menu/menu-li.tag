<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="menu" tagdir="/WEB-INF/tags/uiframe/menu" %> 
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%

	String path = request.getContextPath(); //项目名称  
%>

<!-- 标签属性 -->
<%@attribute name="hover"  rtexprvalue="true" required="false" description="是否浮动" %>
<%@attribute name="menu"  rtexprvalue="true" required="false" type="algz.platform.core.shiro.authority.resourceManager.Resource" description="菜单实体类" %>
<c:choose>
<c:when test="${menu.resources.size()==0 }">
	<li class="${menu.active=='1'?'active':''} ${hover} ">
		<a href="<%=path %>${fn:split(menu.url, ',')[0]}" >
			<i class="menu-icon fa ${menu.icon }"></i>
			<span class="menu-text"> ${menu.name }</span>
		</a>
		<b class="arrow"></b>

		<jsp:doBody/>
	</li>
</c:when>
<c:otherwise>
	<li class="${menu.active=='1'?'active':''} ${hover }">
		<a href="#" class="dropdown-toggle" >
			<i class="menu-icon fa ${menu.icon }"></i>
			<span class="menu-text"> ${menu.name} </span>
			<i class="ace-icon fa fa-angle-down bigger-110"></i>
		</a>
		<b class="arrow"></b>
		<menu:menu-ul menus="${menu.resources }" hover="${hover }"/>
		<jsp:doBody/>
	</li>
</c:otherwise>
</c:choose>

