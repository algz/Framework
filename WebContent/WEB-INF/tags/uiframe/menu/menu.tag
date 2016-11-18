<%-- 
    * copyright    : ALGZ Co., Ltd
    * @version    : 1.0 
    * @created    : 2016/02/18
    * @team	    : 
    * @author      : algz
    
    导航菜单仅支持到第三层.
    example: 
    
--%>
<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="menu" tagdir="/WEB-INF/tags/uiframe/menu" %> 

<!-- 标签属性 -->
<%@attribute name="hover"  rtexprvalue="true" required="false" description="是否浮动" %>

<%@attribute name="menus"  rtexprvalue="true" required="false" type="java.util.List" description="菜单实体类" %>

<ul class="nav nav-list">
	<c:forEach items="${menus}" var="menu"> 
	
	 <menu:menu-li menu="${menu}" hover="${hover}"/>
		
	</c:forEach>
</ul>
