<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="true" description="ID" %>
<%@attribute name="name" rtexprvalue="true" required="false" description="提交到表单action时的参数名,如果不指定,默认采用ID值(action提交需要name值)" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="标签名称" %>
<%@attribute name="readonly"  rtexprvalue="true" required="false" description="是否只读" %>
<%@attribute name="type" rtexprvalue="true" required="false" description="类型,text(默认).(不支持hidden,JSP脚本%不能用在JSP标签值中)" %>
<%@attribute name="defaultText"  rtexprvalue="true" required="false" description="默认文本" %>
<%@attribute name="value" rtexprvalue="true" required="false" description="文本值" %>
<%@attribute name="formClass" rtexprvalue="true" required="false" description="样式类" %>
<%@attribute name="style" rtexprvalue="true" required="false" description="样式" %>

<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="${id }"> ${label } </label>
	<div class="col-sm-9">
		<div class="clearfix">
		<% if(type!=null&&type.equals("select")){ %>
			<select class="chosen-select form-control" id="${id }" <%=style==null?"":"style='"+style+"'" %> name="${name==null?id:name }" ${readonly=='true'?'readonly':'' } data-placeholder="${defaultText==null?'请选择...':defaultText}">
				<jsp:doBody/>
			</select>
		<%}else{ %>
			<input class="${formClass==null?'col-xs-10 col-sm-5':'' }" <%=style==null?"":"style='"+style+"'" %> id="${id }" name="${name==null?id:name }" ${readonly=='true'?'readonly':'' }   type="${type==null?'text':type }" placeholder="${defaultText }" <%=value==null?"":"value='"+value+"'"  %> >
		<%} %>
		</div>
	</div>
</div>