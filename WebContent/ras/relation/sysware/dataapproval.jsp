<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="algz.platform.core.shiro.authority.userManager.User" %>
<%@page import="algz.platform.core.shiro.authority.roleManager.Role" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<!-- <title><sitemesh:write property='title' /></title> -->
		<title>总体部飞机论证参照系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="description" content="top menu &amp; navigation" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />	
		
		<jsp:include  page="../../common/common_css.jsp"/>
		<jsp:include  page="../../common/common_js.jsp"/>
		
					
		<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>
	</head>
	<body class="no-skin">
		
		<div class="main-container" id="main-container">
			<div class="row">
				<div class="col-xs-9">
					<div class="modal-dialog">
				        <div class="modal-content">
							   <div class="modal-header">        
					        		<h4 class="modal-title">数据审批</h4>
					        	</div>
					            <div class="modal-body">            
					          		<form class="form-horizontal" role="form">
										<!-- #section:elements.form -->
										<input name="approval-approvalID" type="hidden" value="${approvalID }">
										<input name="approval-dataID" type="hidden" value="${dataID }">
										<form:form-group id="permission_level" label="数据权限级别" type="select">
											<option value="1">个人可视</option>
											<option value="2">部门可视</option>
											<option value="3">所内可视</option>
											<option value="4">范围可视</option>
										</form:form-group>
										<form:form-group id="permissionUserRange" type="text" readonly="true" value="${permissionUserRange }"/>
									</form>
					          	</div>
					          	<div class="modal-footer">
					    			<button id="approval-confirmBtn" type="button" class="btn btn-primary">保存</button>
					            </div>
				        </div>
				    </div>
				</div>
			</div>
<div class="row">	

	<div class="col-xs-9">
		<form id='modelParamForm' action="./savemodelparam" method="post">
		<input name="overviewID" type="hidden" value="<%=request.getParameter("overviewID") %>"/>
		<c:forEach var="dataParam" items="${paramMap }" varStatus="status">
		
		<div>
			<h3 class="header smaller lighter purple">
				${dataParam.caption}
			</h3>
						
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="form-horizontal" role="form">
						<input name="basicID" type="hidden" value="<%=request.getParameter("basicID") %>"/>
						<!-- <input name="editor" type="hidden" value="${EDITOR}"/> -->
						<c:forEach var="basicItem" items="${dataParam.dataMap }">
							<c:choose>
							<c:when test="${basicItem.elType=='textArea' }">
								<form:form-group id="${basicItem.elID }" label="${basicItem.elLabel }" type="textArea" value="${basicItem.elValue}" readonly="${basicItem.readonly}"/>   
						    
							</c:when>
							<c:otherwise>
								<form:form-group id="${basicItem.elID }" label="${basicItem.elLabel }"  hidden="${basicItem.elID!='dataSources'?'false':'true'}" value="${basicItem.elValue}" readonly="${basicItem.readonly}" >
									<c:if test="${basicItem.readonly==null&&basicItem.elID!='dataSources'}">
									<span class="input-group-btn hidden">
										<button name='modelparamEditBtn' class="btn btn-xs btn-primary" type="button">
											编辑
										</button>
									</span>
									</c:if>
	
								</form:form-group> 
	
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div><!-- /.col -->
			</div>
		</div>
	</c:forEach>
	
		
	
	</form>
	</div>

</div>
		</div><!-- /.main-container -->

<script type="text/javascript">
//全局变量
var basePath='<%=basePath%>';

var algz={};
algz.curUser={};
algz.curUser.username='${user.username}'
algz.curUser.roles=${user.getRoleIds()}
algz.curUser.isDataManager=${isDataManager}


$(function(){
	
	if(document.body.clientWidth<991){
		alert("请将页面宽度调整至991px以上或全屏.");
	}
	
	$('#permission_level').chosen({
		allow_single_deselect : true,
		width : '90%',
		placeholder_text_multiple:'请选择一项'
		//disable_search:true //关闭搜索框,默认为false.
	})
	
    //权限选择
    $('#permission_level').on('change',function(){
    	if(this.value==4){
    		$('#permissionUserRange').removeClass('hidden');
    	}else{
    		$('#permissionUserRange').addClass('hidden');
    	}
    })
	
	$("#permission_level").val("${permission_level}");
	$("#permission_level").trigger("chosen:updated");
	
	$('#approval-confirmBtn').on('click',function(){
		$.ajax({
			url:"./updateapproval",
			data:{
				approvalID:$(':hidden[name=approval-approvalID]').val(),
				permissionLevel:$("#permission_level").val()
			},
			success:function(){
				alert("保存成功!")
			}
		})
	})
})
</script>
	</body>
</html>
