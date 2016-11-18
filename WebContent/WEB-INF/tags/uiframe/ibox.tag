<%-- 
    * copyright    : ALGZ Co., Ltd
    * @version    : 1.0 
    * @created    : 2016/02/18
    * @team	    : 
    * @author      : algz
    
    导航菜单仅支持到第三层.
    example: 
    
    <%
String s="["+
"{'text':'选项1',url:'index.html'},"+
"{'text':'选项2',url:'index.html'}"+"+
"]" ;
JSONArray ja=JSONArray.fromObject(s);
request.setAttribute("title", "数据报告");
request.setAttribute("isclose", "true");
request.setAttribute("iscollapse","true");
request.setAttribute("setmenus", ja);
%>
--%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- 标签属性 -->
<%@attribute name="id"  rtexprvalue="true" required="false" description="控件ID,必填" %>
<%@attribute name="title"  rtexprvalue="true" required="false"  description="" %>
<%@attribute name="isclose"  rtexprvalue="true" required="false"  description="" %>
<%@attribute name="iscollapse"  rtexprvalue="true" required="false"   description="" %>
<%@attribute name="msg"  rtexprvalue="true" required="false"   description="" %>
<%@attribute name="setmenus"  rtexprvalue="true" required="false" type="net.sf.json.JSONArray"  description="" %>

                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>${title}</h5> 
                        <c:if test="${msg!=null }">
                        <span class="label label-primary">${msg }</span>
                        </c:if>
                        
                        <div class="ibox-tools">
                        	<%--折叠按钮 --%>
                        	<c:if test="${iscollapse!=null }">
                        	<a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        	</c:if>
                            
                            
                            <%--设置按钮菜单 --%>
                            <c:if test="${setmenus!=null }">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                            	<c:forEach var="setmenu" items="${setmenus }">
                            	<li><a href="${setmenu.url }#">${setmenu.text }</a>
                                </li>
                            	</c:forEach>
                            </ul>
                            </c:if>

                            <%--关闭按钮 --%>
                            <c:if test="${isclose!=null&&isclose!='false' }">
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                            </c:if>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <%--替换标签内容 --%>
						<jsp:doBody/>
                    </div>
                </div>
  