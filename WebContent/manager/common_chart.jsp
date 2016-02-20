 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <!-- Flot -->
    <script src="<%=basePath %>platform/common/js/plugins/flot/jquery.flot.js"></script>
    <script src="<%=basePath %>platform/common/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="<%=basePath %>platform/common/js/plugins/flot/jquery.flot.resize.js"></script>

    <!-- ChartJS-->
    <script src="<%=basePath %>platform/common/js/plugins/chartJs/Chart.min.js"></script>

    <!-- Peity -->
    <script src="<%=basePath %>platform/common/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- Peity demo -->
    <script src="<%=basePath %>platform/common/js/demo/peity-demo.min.js"></script>
