<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String o1=request.getParameter("pageTitle");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String ssss=o1;
%>

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="<%=basePath%>ras/common/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>ras/common/css/font-awesome.css" />

<!-- page specific plugin styles -->
${param.plugins_css }



<!-- text fonts -->
<link rel="stylesheet" href="<%=basePath%>ras/common/css/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="<%=basePath%>ras/common/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=basePath%>ras/common/css/ace-ie.css" />
		<![endif]-->

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="<%=basePath%>ras/common/css/ace.onpage-help.css" />
<link rel="stylesheet" href="<%=basePath%>ras/common/docs/assets/js/themes/sunburst.css" />

