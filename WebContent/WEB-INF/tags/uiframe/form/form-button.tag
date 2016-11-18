<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="true" description="ID" %>
<%@attribute name="label"  rtexprvalue="true" required="false" description="按钮名称" %>
<%@attribute name="icon" rtexprvalue="true" required="false" description="按钮图标:fa-check,fa-undo......详见:http://fontawesome.io/icons/ "  %>
<%@attribute name="buttonType"  rtexprvalue="true" required="false" description="按钮类型:button(默认),reset" %>
<%@attribute name="buttonClass"  rtexprvalue="true" required="false" description="按钮样式:btn-info(默认)" %>
<%@attribute name="url" rtexprvalue="true" required="false" description="URL" %>
<%if(url==null){ %>                        
<button <%=id==null?"":"id="+id %> class="btn ${buttonClass==null?'':buttonClass }" type="${buttonType=='reset'?'reset':'button' }">
	<%=icon==null?"":"<i class='ace-icon fa "+icon+" bigger-110'></i>"  %>
	${label }
</button>
<%}else{%>
<a <%=id==null?"":"id="+id %> class="btn ${buttonClass==null?'':buttonClass }" href="${url }">
	<%=icon==null?"":"<i class='ace-icon fa "+icon+" bigger-110'></i>"  %>
	${label }
</a>
<%}%>