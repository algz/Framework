<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray" %>
<%@page import="net.sf.json.JSONObject" %>

<%@taglib prefix="gallery" tagdir="/WEB-INF/tags/uiframe/gallery" %> 
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";	
%>

<gallery:gallery headerText="整体图" imgJSonArray="${integralGraph }" relGroup="c1" />
<gallery:gallery headerText="三面图" imgJSonArray="${threeGraph }" relGroup="c2" />
<gallery:gallery headerText="外观图" imgJSonArray="${surfaceGraph }" relGroup="c3" />
<gallery:gallery headerText="其它图" imgJSonArray="${surfaceGraph }" relGroup="c3" />


	
