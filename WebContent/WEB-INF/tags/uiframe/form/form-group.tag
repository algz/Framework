<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="true" description="ID" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="标签名称" %>
<%@attribute name="readonly"  rtexprvalue="true" required="false" description="是否只读" %>
<%@attribute name="defaultText"  rtexprvalue="true" required="false" description="默认文本" %>
<%@attribute name="formClass" rtexprvalue="true" required="false" description="样式" %>


<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="${id }"> ${label } </label>

	<div class="col-sm-9">
		<input class="${formClass==null?'col-xs-10 col-sm-5':'' }" id="${id }" ${readonly=='true'?'readonly':'' } type="text" placeholder="${defaultText }">
	</div>
</div>
