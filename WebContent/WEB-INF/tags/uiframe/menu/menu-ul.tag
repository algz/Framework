<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="menu" tagdir="/WEB-INF/tags/uiframe/menu" %> 

<!-- 标签属性 -->
<%@attribute name="hover"  rtexprvalue="true" required="false" description="是否浮动" %>
<%@attribute name="menus"  rtexprvalue="true" required="false" type="java.util.Set" description="菜单实体类" %>

<ul class="submenu">
<c:forEach items="${menus}" var="menu"> 
<menu:menu-li menu="${menu }" hover="${hover }"/>
</c:forEach>
</ul>

	