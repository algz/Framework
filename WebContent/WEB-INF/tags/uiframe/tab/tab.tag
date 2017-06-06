<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="titles" rtexprvalue="true" required="true" description="标题名称.(以逗号分隔的字符串)" %>
<%-- 
path文件头必须包含:
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
不设置,报异常:
严重: Servlet.service() for servlet jsp threw exception
java.lang.NoClassDefFoundError: org/apache/jsp/ras/tagsearch/archive_jsp (wrong name: org/apache/jsp/ras/tagSearch/archive_jsp)
--%>
<%@attribute name="paths" rtexprvalue="true" required="true" description="JSP文件路径.(以逗号分隔的字符串.文件头必须包含 'page标签',否则报500错误,提示找不到JSP类)" %>



<!-- #section:elements.tab -->
<div class="tabbable">
	<ul class="nav nav-tabs">
	<c:forEach items="${fn:split(titles, ',') }" varStatus="status" >   
		<li ${status.first?"class='active'":"" }>
			<a data-toggle="tab" href="#param${status.index }">
				<i class="green ace-icon fa fa-home bigger-120"></i>
				${status.current }
			</a>
		</li>   
	</c:forEach>  
	</ul>

	<div class="tab-content">
		<c:forEach var="path" items="${fn:split(paths, ',') }" varStatus="status">
		<div id="param${status.index }" class="tab-pane fade in ${status.index==0?'active':'' }">
		<jsp:include page="${path }"/>
		</div>
		</c:forEach>
	</div>
</div>

<!-- /section:elements.tab -->

			