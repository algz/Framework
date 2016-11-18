<%@tag pageEncoding="UTF-8"%>

<%@attribute name="icon"  rtexprvalue="true" required="false" description="" %>
<%@attribute name="num"  rtexprvalue="true" required="false" description="" %>
<%@attribute name="header_txt"  rtexprvalue="true" required="false" description="" %>
<%@attribute name="footer_txt"  rtexprvalue="true" required="false"  description="" %>
<%@attribute name="color"  rtexprvalue="true" required="false" description=""  %>

<li class="${color==null?'transparent':color }">
	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
		<i class="ace-icon fa ${icon!=null?icon:'fa-tasks' }"></i>
		<span class="badge badge-grey">${num==null?0:num }</span>
	</a>

	<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
		<li class="dropdown-header">
			<i class="ace-icon fa fa-check"></i>
			${header_txt}
		</li>

		<li class="dropdown-content">
			<ul class="dropdown-menu dropdown-navbar">
			
			<jsp:doBody/>
			
			</ul>
		</li>

		<li class="dropdown-footer">
			<a href="#">
				${footer_txt}
				<i class="ace-icon fa fa-arrow-right"></i>
			</a>
		</li>
	</ul>
</li>