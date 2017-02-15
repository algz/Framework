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


    <!-- Flot -->
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/demo/peity-demo.min.js"></script>

    <!-- 自定义js -->
    <script src="js/content.min.js?v=1.0.0"></script>


    <!-- jQuery UI -->
    <script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- GITTER -->
    <script src="js/plugins/gritter/jquery.gritter.min.js"></script>

    <!-- EayPIE -->
    <script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>

    <!-- Sparkline -->
    <script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

    <!-- Sparkline demo data  -->
    <script src="js/demo/sparkline-demo.min.js"></script>