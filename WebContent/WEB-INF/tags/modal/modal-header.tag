<%--

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="blue bigger">Please fill the following form fields</h4>
</div>

 --%>
<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<jsp:body/>
</div>