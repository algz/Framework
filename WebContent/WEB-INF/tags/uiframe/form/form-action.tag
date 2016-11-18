<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="标签名称" %>
<%@attribute name="readonly"  rtexprvalue="true" required="false" description="是否只读" %>
<%@attribute name="defaultText"  rtexprvalue="true" required="false" description="默认文本" %>
<%@attribute name="class1" rtexprvalue="true" required="false" description="样式" %>


<div class="clearfix form-actions">
	<div class="col-md-offset-3 col-md-9">
		<jsp:doBody/>
	</div>
</div>
