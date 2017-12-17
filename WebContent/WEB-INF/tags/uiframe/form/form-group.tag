<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 

<%@attribute name="id" rtexprvalue="true" required="true" description="ID" %>
<%@attribute name="name" rtexprvalue="true" required="false" description="提交到表单action时的参数名,如果不指定,默认采用ID值(action提交需要name值)" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="标签名称" %>
<%@attribute name="readonly"  rtexprvalue="true" required="false" description="是否只读.true为只读,空可 编辑." %>
<%@attribute name="hidden" rtexprvalue="true" required="false" description="是否显示;默认为false." %>
<%@attribute name="type" rtexprvalue="true" required="false" description="类型,text(默认),password,checkbox(多选框),select(下拉框),textArea(文本区).(不支持hidden,JSP脚本%不能用在JSP标签值中)" %>
<%@attribute name="defaultText"  rtexprvalue="true" required="false" description="默认文本" %>
<%@attribute name="value" rtexprvalue="true" required="false" description="文本值" %>
<%@attribute name="formClass" rtexprvalue="true" required="false" description="样式类" %>
<%@attribute name="style" rtexprvalue="true" required="false" description="样式" %>
<%@attribute name="extData" rtexprvalue="true" required="false" description="扩展数据" %>
<%@attribute name="simpleValidate" rtexprvalue="true" required="false" description="验证类型: required number email url digits(整数)"%>
<%@attribute name="isMultiple" rtexprvalue="true" required="false" description="type=file,是否多文件上传.1支持,0或null(默认)不支持;type=select,是否支持多选.1多选"%>

<div id='form-${id }' class="form-group ${hidden!=null&&hidden!='false'?'hidden':''} ">
	<label class="col-sm-3 control-label no-padding-right" for="${id }"> ${label } </label>
	<div class="col-xs-10 col-sm-7">
		<span class="block input-icon input-icon-right">
		<%if(type==null||type.equals("")||type.equals("text")){ %>
			<input class="${formClass==null?'width-100':'' } ${simpleValidate==null?'':simpleValidate}" <%=style==null?"":"style='"+style+"'" %> id="${id }" name="${name==null?id:name }" ${readonly=='true'?'readonly':'' }   type="text" placeholder="${defaultText }" ${extData } <%=value==null?"":"value='"+value+"'"  %> >
			<jsp:doBody/>
		<%}else if(type.equals("password")){ %>
		    <input class="${formClass==null?'width-100':'' } ${simpleValidate==null?'':simpleValidate}" <%=style==null?"":"style='"+style+"'" %> id="${id }" name="${name==null?id:name }" ${readonly=='true'?'readonly':'' }   type="password" placeholder="${defaultText }" ${extData } <%=value==null?"":"value='"+value+"'"  %> >
			<jsp:doBody/>
		<%}else if(type.equals("select")){ %>
		    <form:form-select id="${id }" name="${name==null?id:name }" value="${value }" isMultiple="${isMultiple}" style="${style}"  readonly="${readonly}" defaultText="${defaultText}" extData="${extData }">
		    	<jsp:doBody/>
		    </form:form-select>

		<%}else if(type.equals("file")){%>
			<input id="${id }" name="${name==null?id:name }" type="file" ${isMultiple=="1"?"multiple":"" } >
		
		<%}else if(type.equals("checkbox")){%>
			<div class="checkbox ">
			    <%for(String checkboxData:extData.split(",")){ %>
				<label>
					<input name="${name==null?id:name }" type="checkbox" <%=value!=null&&value.contains(checkboxData)?"checked":""%> class="ace" value="<%=checkboxData %>">
					<span class="lbl"><%=checkboxData %></span>
				</label>
				<%} %>
			</div>
		
		<%}else if(type.equals("textArea")){%>
			<textarea id="${id }" name="${name==null?id:name }" placeholder="${defaultText }" class="${formClass==null?'width-100':'' } ${simpleValidate==null?'':simpleValidate}" style="height: 68px;${style==null?'':style}" ${readonly=='true'?'readonly':'' }   ${extData } >${value }</textarea>
			<jsp:doBody/>
		<%}%>
		</span>
	</div>
</div>
<%-- 
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
 --%>