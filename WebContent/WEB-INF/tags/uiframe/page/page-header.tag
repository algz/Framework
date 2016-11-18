<%-- 
    * copyright    : ALGZ Co., Ltd
    * @version    : 1.0 
    * @created    : 2016/02/18
    * @team	    : 
    * @author      : algz
    
    导航菜单仅支持到第三层.
    example: 
    


%>
--%>
<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 标签属性 -->
<%@attribute name="header_h1"  rtexprvalue="true" required="true" description="主标题" %>
<%@attribute name="header_small"  rtexprvalue="true" required="true" description="子标题" %>

<div class="page-header">
	<h1>
		${header_h1 }
		<small>
			<i class="ace-icon fa fa-angle-double-right"></i>
			${header_small }
		</small>
	</h1>
</div><!-- /.page-header -->