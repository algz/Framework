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
<%@attribute name="msg"  rtexprvalue="true" required="false"   description="" %>

                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>${title}</h5> 
                        <c:if test="${msg!=null }">
                        <div class="ibox-tools">
                            <span class="label label-warning-light">${msg }</span>
                        </div>
                        </c:if>
                        
                        <div class="ibox-tools">
                        	<%--折叠按钮 --%>
                        	<c:if test="${iscollapse!=null }">
                        	<a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        	</c:if>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div>
                            <div class="feed-activity-list">

                                <div class="feed-element">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="img/profile.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right">5分钟前</small>
                                        <strong>谨斯里</strong> 上传了一个文件
                                        <br>
                                        <small class="text-muted">2014.11.8 12:22</small>

                                    </div>
                                </div>

                                <div class="feed-element">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="img/a2.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right">2个月前</small>
                                        <strong>田亮</strong> 参加了<strong>《粑粑去哪儿》</strong>
                                        <br>
                                        <small class="text-muted">2014.11.8 12:22</small>
                                    </div>
                                </div>
                                <div class="feed-element">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="img/a3.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right">2小时前</small>
                                        <strong>林依晨Ariel</strong> 刚刚起床
                                        <br>
                                        <small class="text-muted">2014.11.8 12:22</small>
                                    </div>
                                </div>
                                <div class="feed-element">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="img/a5.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right">32分钟前</small>
                                        <strong>颜文字君</strong> 评论了
                                        <br>
                                        <small class="text-muted">2014.11.8 12:22</small>
                                        <div class="well">
                                            【九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来推荐的9部同类题材并同样出色的的电影，绝对不可错过哦~
                                        </div>
                                        <div class="pull-right">
                                            <a class="btn btn-xs btn-white"><i class="fa fa-thumbs-up"></i> 喜欢 </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <button class="btn btn-primary btn-block m-t"><i class="fa fa-arrow-down"></i> 加载更多</button>

                        </div>

                    </div>
                </div>
  