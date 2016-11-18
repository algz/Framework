<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>
<%@attribute name="header"  rtexprvalue="true" required="false" description="标题" %>
<%@attribute name="action"  rtexprvalue="true" required="false" description="提交地址" %>

<div>
	<h3 class="header smaller lighter purple">
		${header==null?'':header }
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<form class="form-horizontal" ${action==null?'':'action="'+action+'"' } role="form">
			<jsp:doBody/>
			</form>
		</div><!-- /.col -->
	</div>
</div>
