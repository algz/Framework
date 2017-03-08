<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="header"  rtexprvalue="true" required="false" description="" %>
<%@attribute name="icon"  rtexprvalue="true" required="false" description="" %>
<%@attribute name="box-transparent"  rtexprvalue="true" required="false"  type="java.lang.Boolean" description="背景是否透明" %>
<%@attribute name="title-lighter"  rtexprvalue="true" required="false"  type="java.lang.Boolean" description="标题是否亮光细,一般对应于 box-transparent 属性为真" %>
<%@attribute name="title-toolbar"  rtexprvalue="true" required="false"  type="java.lang.Boolean" description="是否显示标题工具栏,一般对应于 box-transparent 属性为真" %>


<div class="widget-box ${box-transparent?'box-transparent':''}">
	<c:if test="${header!=null}">
	<div class="widget-header widget-header-flat widget-header-small">
		<h5 class="widget-title ${title-lighter?'lighter':''}">
			<i class="ace-icon fa ${icon!=null?icon:'fa-signal' }"></i>
			${header }
		</h5>
		
		<c:if test="${title-toolbar }">
		
		<div class="widget-toolbar no-border">
			<a href="#" data-action="settings">
				<i class="ace-icon fa fa-cog"></i>
			</a>

			<a href="#" data-action="reload">
				<i class="ace-icon fa fa-refresh"></i>
			</a>

			<a href="#" data-action="collapse">
				<i class="ace-icon fa fa-chevron-up"></i>
			</a>

			<a href="#" data-action="close">
				<i class="ace-icon fa fa-times"></i>
			</a>
		</div>
		
		
		</c:if>

	
	</div>
	</c:if>
	
	<div class="widget-body">
		<div class="widget-main">
			<jsp:doBody/>
		</div><!-- /.widget-main -->
	</div><!-- /.widget-body -->
</div><!-- /.widget-box -->