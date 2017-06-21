<%@tag pageEncoding="UTF-8"%>


<%@attribute name="name" rtexprvalue="true" required="false" description="提交到表单action时的参数名,如果不指定,默认采用ID值(action提交需要name值)" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="标签名称" %>
<%@attribute name="disabled"  rtexprvalue="true" required="false" description="是否不可用:disabled" %>
<%@attribute name="value" rtexprvalue="true" required="false" description="radio值" %>
<%--@attribute name="extData" rtexprvalue="true" required="false" description="扩展数据" --%>
<%@attribute name="simpleValidate" rtexprvalue="true" required="false" description="验证类型: required number email url digits(整数)"%>


<label>
	<input name="${name }" type="radio" class="ace ${simpleValidate==null?'':simpleValidate}" ${disabled} value="${value }"  >
	<span class="lbl">${label }</span>
</label>

<%--
		<div class="control-group">
			<label class="control-label bolder blue">Radio</label>

			<div class="radio">
				<label>
					<input name="form-field-radio" type="radio" class="ace">
					<span class="lbl"> radio option 1</span>
				</label>

				<label>
					<input name="form-field-radio" type="radio" class="ace">
					<span class="lbl"> radio option 2</span>
				</label>

				<label>
					<input name="form-field-radio" type="radio" class="ace">
					<span class="lbl"> radio option 3</span>
				</label>
			</div>
		</div>

 --%>