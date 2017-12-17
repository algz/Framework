<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %>
<%@page import="net.sf.json.JSONObject" %>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


			<div class="row">
				<div class="col-xs-12">
					<form class="form-horizontal" role="form" action="./saveproductinform" method="post">
		
						<c:forEach var="paramValue" items="${productParamValue}">
						
						<div>
							<h3 class="header smaller lighter purple">
								${paramValue.text}
							</h3>
							<div class="row">
								<div class="col-xs-12">
									<!-- PAGE CONTENT BEGINS -->
									<div class="form-horizontal" role="form">
										<c:forEach var="childParam" items="${paramValue.childParams}">
										<form:form-group id="pv${childParam.productID }" label="${childParam.text }" type="textArea" value="${childParam.val }"/>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						
						</c:forEach>
					</form>
	         
	
				</div>
			</div>



