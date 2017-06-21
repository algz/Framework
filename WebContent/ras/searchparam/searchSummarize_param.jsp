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
<form id='modelParamForm' action="./savemodelparam" method="post">
	<input name="overviewID" type="hidden" value="<%=request.getParameter("overviewID") %>"/>
	<c:forEach var="dataParam" items="${paramMap }" varStatus="status">
	<c:if test="${fn:length(dataParam.dataMap)!=0 }">
	
	<div>
		<h3 class="header smaller lighter purple">
			${dataParam.caption}
			<c:if test="${status.index==0}">
			<label>
				<small class="smaller-90">开启注释:</small>
				<input class="ace ace-switch ace-switch-5" id="checkBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			<label>
				<small class="smaller-90">隐藏空行:</small>
				<input class="ace ace-switch ace-switch-5" id="closeSpaceBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			</c:if>
		</h3>
							
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="form-horizontal" role="form">
					<input name="basicID" type="hidden" value="<%=request.getParameter("basicID") %>"/>
					<!-- <input name="editor" type="hidden" value="${EDITOR}"/> -->
					<c:forEach var="basicItem" items="${dataParam.dataMap }">
						<form:form-group id="${basicItem.elID }" label="${basicItem.elLabel }" value="${basicItem.elValue}" readonly="${basicItem.readonly}"/>   
					</c:forEach>
				</div>
			</div><!-- /.col -->
		</div>
	</div>
	
	</c:if>
</c:forEach>

	<div class="row">
		<div class="col-sm-offset-6">
			<div class="btn-group">
				<a class="btn btn-primary" href="javascript:history.back()">取消</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
$(function(){
	/**
	* 开启注释
	*/
	$("#checkBtn").click(function(){
		//checked="checked"
		if($(this).attr("checked")==null){
			//打开
			$(this).attr("checked","checked");
			var s="";
			var els=$("#modelParamForm :text[name!=dataSources]");
			for(var i=0;i<els.length;i++){
				if(i!=0){
					s+=",";
				}
				s+=$(els[i]).attr("name");
			}
			$.ajax({
				url:'./addnotefortaginput',
				data:{
					overviewID:$(":hidden[name=overviewID]").val(),
					inputName:s
				},
				success:function(data){
					var obj=data;//eval("("+data+")");
					for(var i=0;i<obj.inputs.length;i++){
						var input=obj.inputs[i];
						var el=$(":text[name="+input.name+"]");
						el.offsetParent().offsetParent().attr("data-toggle","tooltip");
						el.after("<i class='ace-icon fa fa-info-circle'></i>");
						var s="<ul class='list-unstyled'>"
						for(var j=0;j<input.vals.length;j++){
							var val=input.vals[j]
							s+="<li>"
							s+=(j+1)+"."+val.inputValue+" <i class='ace-icon fa fa-external-link blue'/><span class='text-muted'>"+val.dataSources+"</span><p>";
							s+="</li>"
						}
						el.offsetParent().offsetParent().attr("title",s+"</ul>");
					}
					$('[data-toggle="tooltip"]').tooltip({
						html:true,
						placement:'right'
					});
				}
			})
		}else{
			//关闭
			$(this).removeAttr("checked")
			$('[data-toggle="tooltip"]').tooltip('destroy')
			$('[data-toggle="tooltip"]').attr("title","")
			$(":text~").remove();
		}
	})
	
	/**
	* 隐藏空行
	*/
	$("#closeSpaceBtn").on('click',function(){
		if($(this).attr("checked")==null){
			//打开
			$(this).attr("checked","checked");
			var els=$(".form-horizontal .form-group").has(':text[value=""]');
			els.addClass('hidden')
		}else{
			//关闭
			$(this).removeAttr("checked")
			$(".form-horizontal .form-group").removeClass("hidden");
		}
	})
	
})
</script>
</form>
