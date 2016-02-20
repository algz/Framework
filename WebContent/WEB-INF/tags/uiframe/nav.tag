<%-- 
    * copyright    : ALGZ Co., Ltd
    * @version    : 1.0 
    * @created    : 2016/02/18
    * @team	    : 
    * @author      : algz
    
    导航菜单仅支持到第三层.
    example: 
    
    <%
String s="[{'header':'Beaut-zihan','text':'超级管理员',img:'img/profile_small.jpg','menus':["+
"{'text':'修改头像',url:'form_avatar.html'},"+
"{'text':'个人资料',url:'profile.html'},"+
"{'text':'联系我们',url:'contacts.html'},"+
"{'text':'信箱',url:'mailbox.html'},"+
"'divider',"+
"{'text':'安全退出',url:'login.html'}"+
"]},"+

"{'text':'主页','menus':["+
"{'text':'主页示例一','url':'index_v1.html'},"+
"{'text':'主页示例二','url':'index_v2.html'},"+
"{'text':'主页示例三','url':'index_v3.html'},"+
"{'text':'主页示例四','url':'index_v4.html'},"+
"{'text':'主页示例五','url':'index_v5.html','target':'_blank'}"+
"]},"+

"{'text':'布局',url:'layouts.html',msg:'12'},"+

"{'text':'表单','menus':["+
"{'text':'基本表单','url':'form_basic.html'},"+
"{'text':'表单验证','url':'form_validate.html'},"+
"{'text':'高级插件','url':'form_advanced.html'},"+
"{'text':'表单向导','url':'form_wizard.html'},"+
"{'text':'文件上传','url':'#','menus':["+
	"{'text':'百度WebUploader','url':'form_webuploader.html'},"+
	"{'text':'DropzoneJS','url':'form_file_upload.html'},"+
	"{'text':'头像裁剪上传','url':'form_avatar.html',msg:0}"+
"]}"+
"]}"+
"]" ;
JSONArray ja=JSONArray.fromObject(s);
request.setAttribute("menus", ja);

%>
--%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- 标签属性 -->
<%@attribute name="id"  rtexprvalue="true" required="false" description="控件ID,必填" %>
<%@attribute name="menus"  rtexprvalue="true" required="false" type="net.sf.json.JSONArray" description="菜单集合" %>
<%@attribute name="headermenu" rtexprvalue="true" required="false" type="net.sf.json.JSONObject" description="header菜单" %>

<!--左侧导航开始-->
		
		<nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
            
                <ul class="nav" id="side-menu">
                <c:if test="${header!=null}">
                	<%-- (1)header下拉菜单 --%>
					<!-- nav-header -->
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="${headermenu.img }" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${headermenu.title}</strong></span>
                                <span class="text-muted text-xs block">${headermenu.text }<b class="caret"></b></span>
                                </span>
                            </a>
                            <c:if test="${headermenu.menus!=null }">
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <c:forEach var="submenu" items="${headermenu.menus }">
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
                </c:if>
                
                <c:if test="${menus!=null}">
				<c:forEach var="menu" items="${menus }">
					<c:choose>
					    <%-- (2)下拉框菜单--%>
					    <c:when test="${menu.menus!=null}">
					<li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label"><c:out value="${menu.text }" /></span>
                            <span class="fa arrow"></span>
                            <c:if test="${menu.msg!=null}">
                            <span class="label label-warning pull-right">${menu.msg }</span>
                            </c:if>
                        </a>
                             <ul class="nav nav-second-level">
                             <c:forEach var="secondmenu"  items="${menu.menus}" varStatus='status'>
                             <c:choose>
                             <c:when test="${secondmenu.target=='_blank' }">
                                <li>
                                	<a href="<c:out value='${secondmenu.url }' default='#'/>" target="_blank">${secondmenu.text}</a>
                            	</li>
                             </c:when>
                             <c:when test="${secondmenu.menus!=null}">
	                            <li>
	                                <a href="#">${secondmenu.text }<span class="fa arrow"></span>
			                        <c:if test="${secondmenu.msg!=null}">
		                            	<span class="label label-warning pull-right">${secondmenu.msg }</span>
		                            </c:if>
	                                </a>
	                                <ul class="nav nav-third-level">
	                                <c:forEach var="thirdmenu" items="${ secondmenu.menus}">
	                                	<li>
	                                		<a class="J_menuItem" href="${thirdmenu.url==null?'#':thirdmenu.url }">${thirdmenu.text }
			                                <c:if test="${thirdmenu.msg!=null}">
				                            	<span class="label label-warning pull-right">${thirdmenu.msg }</span>
				                            </c:if>
	                                		</a>
	                                    </li>
                             		</c:forEach>
	                                </ul>
	                            </li>
                             </c:when>
                             <c:otherwise>
                             	<li>
	                                <a class="J_menuItem"  href="${secondmenu.url==null?'#':secondmenu.url}" data-index="${status.index }"  >${secondmenu.text}</a>
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
                        <a class="J_menuItem" href="${menu.url }"><i class="fa fa-columns"></i> 
                        	<span class="nav-label">${menu.text }</span>
                        <c:if test="${menu.msg!=null}">
                            <span class="label label-warning pull-right">${menu.msg }</span>
                        </c:if>
                        </a>
                    </li>
					    </c:otherwise>
					</c:choose>
				</c:forEach>
				</c:if>
                </ul>
           
            
            </div>
        </nav>
         
        <!--左侧导航结束-->