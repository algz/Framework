<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>
    <sitemesh:write property='title' /> 
</title>
<%@ include file="common_css.html" %>
<%@ include file="common_js.html" %>
<sitemesh:write property='head' />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
    <sitemesh:write property='body' />
</body>
</html>