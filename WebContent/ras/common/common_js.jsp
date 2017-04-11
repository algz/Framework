<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
		<!-- inline styles related to this page -->

		<!-- ace settings handler 
		<script src="<%=basePath%>ras/common/js/ace-extra.js"></script>-->

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=basePath%>ras/common/js/html5shiv.js"></script>
		<script src="<%=basePath%>ras/common/js/respond.js"></script>
		<![endif]-->
		
		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=basePath%>ras/common/js/jquery.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=basePath%>ras/common/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=basePath%>ras/common/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="<%=basePath%>ras/common/js/bootstrap.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="<%=basePath%>ras/common/js/excanvas.js"></script>
		<![endif]-->
		
		<!-- plugins -->
        ${param.plugins_js }
		
<%-- 	<script src="<%=basePath%>ras/common/js/jquery-ui.custom.js"></script>
		<script src="<%=basePath%>ras/common/js/jquery.ui.touch-punch.js"></script>
		<script src="<%=basePath%>ras/common/js/jquery.easypiechart.js"></script>
		<script src="<%=basePath%>ras/common/js/jquery.sparkline.js"></script>
		<script src="<%=basePath%>ras/common/js/flot/jquery.flot.js"></script>
		<script src="<%=basePath%>ras/common/js/flot/jquery.flot.pie.js"></script>
		<script src="<%=basePath%>ras/common/js/flot/jquery.flot.resize.js"></script>   --%>	



		<%--  ace scripts
		<script src="<%=basePath%>ras/common/js/ace/elements.scroller.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.colorpicker.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.fileinput.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.typeahead.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.wysiwyg.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.spinner.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.treeview.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.wizard.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/elements.aside.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.ajax-content.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.touch-drag.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.sidebar.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.submenu-hover.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.widget-box.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.settings.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.settings-rtl.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.settings-skin.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.widget-on-reload.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.searchbox-autocomplete.js"></script>
		
		<script src="<%=basePath%>ras/common/js/ace/elements.onpage-help.js"></script>
		<script src="<%=basePath%>ras/common/js/ace/ace.onpage-help.js"></script>
		<script src="<%=basePath%>ras/common/docs/assets/js/rainbow.js"></script>
		<script src="<%=basePath%>ras/common/docs/assets/js/language/generic.js"></script>
		<script src="<%=basePath%>ras/common/docs/assets/js/language/html.js"></script>
		<script src="<%=basePath%>ras/common/docs/assets/js/language/css.js"></script>
		<script src="<%=basePath%>ras/common/docs/assets/js/language/javascript.js"></script>
		 --%>

