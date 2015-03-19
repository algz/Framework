<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>
    <sitemesh:write property='title' /> 
</title>
<%@ include file="common_css.jsp" %>
<%@ include file="common_js.jsp" %>
<sitemesh:write property='head' />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="modal-shiftfix">
		<!-- Navigation -->
		<div class="navbar navbar-fixed-top scroll-hide">
			<%@ include file="nav/nav-top.jsp" %>
			<%@ include file="nav/nav-menu.jsp" %>
		</div>
      	<!-- End Navigation -->

		<sitemesh:write property='body' />
	</div>
</body>
</html>