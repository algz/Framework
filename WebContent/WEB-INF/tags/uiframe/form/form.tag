<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>
<%@attribute name="header"  rtexprvalue="true" required="false" description="标题" %>
<%@attribute name="action"  rtexprvalue="true" required="false" description="提交地址" %>
<%@attribute name="submitType"  rtexprvalue="true" required="false" description="提交方式:post,get"%>

<form class="form-horizontal" role="form" <%=action==null?"":"action='"+action+"'"%> method="post">
	<jsp:doBody/>
</form>
<%-- 
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

html:
	<div class="row">
		<div class="col-xs-12">
			<form class="form-horizontal" role="form">
				<!-- #section:elements.form -->
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Text Field </label>
					<div class="col-sm-9">
						<input type="text" id="form-field-1" placeholder="Username" class="col-xs-10 col-sm-5" />
					</div>
				</div>
			</form>
		</div>
	</div>
--%>