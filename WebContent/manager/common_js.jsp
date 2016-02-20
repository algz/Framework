 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <!-- 全局js -->
    <script src="<%=basePath %>platform/common/js/jquery-2.1.1.min.js"></script>
    <script src="<%=basePath %>platform/common/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="<%=basePath %>platform/common/js/plugins/layer/layer.min.js"></script>
        
    <script src="<%=basePath %>platform/common/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=basePath %>platform/common/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    
        <!-- 自定义js -->
    <script src="<%=basePath %>platform/common/js/hplus.min.js?v=3.0.0"></script>