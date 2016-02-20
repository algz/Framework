<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>H+ 后台主题UI框架 - 主页</title>

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

	<jsp:include  page="common_css.jsp"/> 
</head>

<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">

        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
           <div class="footer">
                <div class="pull-right">&copy; 2014-2015 <a href="/" target="_blank">zihan's blog</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->

    </div>

<jsp:include  page="common_js.jsp"/> 


    <!-- 第三方插件 -->
    <script src="<%=basePath %>platform/common/js/plugins/pace/pace.min.js"></script>
</body>

</html>