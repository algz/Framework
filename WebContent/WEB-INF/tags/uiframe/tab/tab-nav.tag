<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="标题" %>
<%@attribute name="active"  rtexprvalue="true" required="false" description="提交地址" %>

<li ${active=null?"":"class='active'" }>
	<a data-toggle="tab" href="#${id }">
		<i class="green ace-icon fa fa-user bigger-120"></i>
		${label}
	</a>
</li>
