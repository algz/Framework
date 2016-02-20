<%-- 
    * copyright    : ALGZ Co., Ltd
    * @version    : 1.0 
    * @created    : 2016/02/18
    * @team	    : 
    * @author      : algz
    
    example: {menus:[{text:'',icon:'',isheader:'true'}]}
--%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="algz" tagdir="/WEB-INF/tags/uiframe" %>
<%@tag import="net.sf.json.JSONObject" %>
<%@tag import="net.sf.json.JSONArray" %>

<!-- 标签属性 -->
<%@attribute name="id"  rtexprvalue="true" required="false" description="控件ID,必填" %>
<%@attribute name="menus"  rtexprvalue="true" required="false" type="net.sf.json.JSONArray" description="控件ID,必填" %>

<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
            
            <c:if test="${menus!=null}">
                <ul class="nav" id="side-menu">
				<c:forEach var="menu" items="${menus }">
					<c:choose>
					<%-- (1)header下拉菜单 --%>
					    <c:when test="${menu.isheader=='true'}">
					<!-- nav-header -->
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${menu.title}</strong></span>
                                <span class="text-muted text-xs block">${menu.text }<b class="caret"></b></span>
                                </span>
                            </a>
                            <c:if test="${menu.menus!=null }">
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <c:forEach var="submenu" items="${menu.menus }">
								<c:choose>
								    <c:when test="${submenu=='divider' }">
								 <li class="divider"></li>
								    </c:when>
								    <c:otherwise>
								<li>
                            		<a class="J_menuItem" href="<c:out value='${submenu.url}' default='#'/>">${submenu.text }</a>
                                </li>
								    </c:otherwise>
								</c:choose>
                            </c:forEach>
                            </ul>
                            </c:if>
                        </div>
                        <div class="logo-element">Algz+
                        </div>
                    </li>
					    </c:when>
					    <%-- (2)下拉框菜单--%>
					    <c:when test="${menu.isheader!='true'&&menu.menus!=null}">
					<li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label"><c:out value="${menu.text }" /></span>
                            <span class="fa arrow"></span>
                        </a>
                             <ul class="nav nav-second-level">
                             <c:forEach var="submenu"  items="${menu.menus}" varStatus='status'>
                             <c:choose>
                             <c:when test="${submenu.target=='_blank' }">
                                <li>
                                	<a href="<c:out value='${submenu.url }' default='#'/>" target="_blank">${submenu.text}</a>
                            	</li>
                             </c:when>
                             <c:otherwise>
                             	<li>
	                                <a class="J_menuItem"  href="${submenu.url==null?'#':submenu.url}" data-index="${status.index }"  >${submenu.text}</a>
	                            </li>
                             </c:otherwise>
                             </c:choose>
                             </c:forEach>
                        </ul>
                    </li>
					    </c:when>
					    <%-- (3)(直接)菜单 --%>
					    <c:otherwise>
					<li>
                        <a class="J_menuItem" href="${menu.url }"><i class="fa fa-columns"></i> <span class="nav-label">${menu.text }</span></a>
                    </li>
					    </c:otherwise>
					</c:choose>
				</c:forEach>
                </ul>
            </c:if>
            
            </div>
        </nav>
        <!--左侧导航结束-->