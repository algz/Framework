 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <!--[if lt IE 8]>
    <script>
        alert('H+已不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
    </script>
    <![endif]-->
    <link href="<%=basePath %>platform/common/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="<%=basePath %>platform/common/css/font-awesome.min.css?v=4.3.0" rel="stylesheet">
    <link href="<%=basePath %>platform/common/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath %>platform/common/css/style.min.css?v=3.0.0" rel="stylesheet">