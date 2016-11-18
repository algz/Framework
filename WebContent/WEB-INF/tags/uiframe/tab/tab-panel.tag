<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>
<%@attribute name="active"  rtexprvalue="true" required="false" description="active" %>
<%@attribute name="panelClass" rtexprvalue="true" required="false" description="样式.如 fade" %>

<div id="${id }" class="tab-pane ${panelClass==null?'':panelClass } ${active==null?'':'in active' }">
	<jsp:doBody/>
</div>



