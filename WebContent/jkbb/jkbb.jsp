<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.net.*" %>
<%@ page language="java" import="java.io.*" %>

        <%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>健康宝宝投票</title>
	
	<script src="<%=basePath %>/platform/core/lib/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>/jkbb/voting.js" type="text/javascript"></script>
</head>

<body>
<script>
var basePath="<%=basePath %>";
</script>
<div  >
        投票者代理IP:<input type="text" id='myip'  />
        <input type="button" id="loadIP" value="加载IP" />
        <div >
            验证码：
            <input name="sys_check" id="sys_check" class="input-text" style="width: 50px;" type="text"
                maxlength="5" />
            <a href="javascript:void(0);" class="VerifyCode">
                <img src="" width="100" height="24" style="vertical-align: middle;"
                    alt="点击验证码" /></a>&nbsp;&nbsp;<spn id='msg'>点击左边图片切换</spn><br />
            <br />
            <input type="button" id="btnverify" value="确 定" />&nbsp;&nbsp;
            <input type="button" id="btnverifyout" value="取 消" />
         </div>
         <br />
         <div>
         <input type="button" id="loadiframe" value="重加载页面获取Cookies" />
         </div>
         <br/>
<iframe id='framePage' name='framePage' width='300' height='50' src='#'></iframe>
</div>	
</body>
</html>
