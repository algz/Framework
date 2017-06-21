<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="radio" tagdir="/WEB-INF/tags/uiframe/form/radio" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="name" rtexprvalue="true" required="false" description="提交到表单action时的参数名,如果不指定,默认采用ID值(action提交需要name值)" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="标签名称" %>
<%@attribute name="disabled"  rtexprvalue="true" required="false" description="是否不可用:disabled" %>
<%@attribute name="value" rtexprvalue="true" required="false" description="radio值" %>
<%--@attribute name="extData" rtexprvalue="true" required="false" description="扩展数据" --%>
<%@attribute name="simpleValidate" rtexprvalue="true" required="false" description="验证类型: required number email url digits(整数)"%>

<div class="control-group" ${disabled }>
	<!-- <label class="control-label bolder blue">Radio</label> -->
	<div class="radio">
	<c:forEach items="${fn:split(value, ',') }" varStatus="status" >
		<label>
			<input name="${name }" type="radio" class="ace">
			<span class="lbl">${(fn:split(label, ','))[status.index] }</span>
		</label>
   
	</c:forEach>  
	</div>
</div>
