<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>
      登录页面2
    </title>
    <!--<link href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700" media="all" rel="stylesheet" type="text/css" />-->
	<link href="<%=basePath%>/platform/stylesheets/bootstrap.min.css" media="all" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/platform/stylesheets/font-awesome.css" media="all" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/platform/stylesheets/se7en-font.css" media="all" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/platform/stylesheets/style.css" media="all" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>/platform/javascripts/jquery.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>/platform/javascripts/jquery-ui.js" type="text/javascript"></script>
	<script src="<%=basePath%>/platform/javascripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>/platform/javascripts/modernizr.custom.js" type="text/javascript"></script>
	
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
  </head>
  <body class="login2">
    <!-- Login Screen -->
    <div class="login-wrapper">
      <a href="index.html"><img width="100" height="30" src="" /></a>
      <form action="login" method="post">
        <div class="form-group">
          <div class="input-group">
            <span class="input-group-addon"><i class="icon-envelope"></i></span>
            <input class="form-control" name="username" placeholder="用户名" type="text">
          </div>
        </div>
        <div class="form-group">
          <div class="input-group">
            <span class="input-group-addon"><i class="icon-lock"></i></span>
            <input class="form-control" name="password" placeholder="密码" type="text">
          </div>
          <a class="pull-right" href="#">忘记密码?</a>
        </div>
        
        <%if(request.getAttribute("error")!=null){ %>
        <div class="alert alert-danger">
                      <div class="badge pull-right">
                        ${error}
                      </div>
        </div>
        <%} %>
        <div class="text-left">
          <label class="checkbox"><input type="checkbox"><span>自动登陆</span></label>
        </div>

        <input class="btn btn-lg btn-primary btn-block" type="submit" value="登陆">

      </form>
      <a class="btn btn-default-outline btn-block" href="signup2.html">注册</a>
    </div>
    <!-- End Login Screen -->
  </body>

</html>