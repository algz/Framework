<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 


<div>
	<h3 class="header smaller lighter purple">
		基本信息
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<form class="form-horizontal" role="form">
				<form:form-group id="form-model" label="数据来源"/>
				<form:form-group id="form-model" label="型号"/>
				<form:form-group id="form-cname" label="中文名称"/>
				<form:form-group id="form-cname" label="英文名称"/>
				<form:form-group id="form-cname" label="研发国别"/>
				<form:form-group id="form-cname" label="研发厂商"/>
				<form:form-group id="form-cname" label="使用国别"/>
				<form:form-group id="form-cname" label="飞机类型"/>
				<form:form-group id="form-cname" label="首飞年份"/>
				<form:form-group id="form-cname" label="服役年份"/>
				<form:form-group id="form-cname" label="机组人员数量"/>
				<form:form-group id="form-cname" label="最大载客量"/>
				<form:form-group id="form-cname" label="单价"/>
				<form:form-group id="form-cname" label="中文名称"/>
			</form>
		</div><!-- /.col -->
	</div>
</div>
<div>
	<h3 class="header smaller lighter purple">
		重量
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<form class="form-horizontal" role="form">
				<form:form-group id="form-model" label="使用空重"/>
				<form:form-group id="form-cname" label="正常起飞重量"/>
				<form:form-group id="form-cname" label="最大起飞重量"/>
				<form:form-group id="form-cname" label="设计着陆重量"/>
				<form:form-group id="form-cname" label="机内油量"/>
				<form:form-group id="form-cname" label="最大装载重量"/>
				<form:form-group id="form-cname" label="起飞推重比"/>
			</form>
		</div><!-- /.col -->
	</div>
</div>
<div>
	<h3 class="header smaller lighter purple">
		布局
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="form-horizontal" >
				<form:form-group id="form-model" label="机翼布局"/>
				<form:form-group id="form-cname" label="尾翼布局"/>
				<form:form-group id="form-cname" label="机身布局"/>
				<form:form-group id="form-cname" label="进排气布局"/>
				<form:form-group id="form-cname" label="起落架布局"/>
				<form:form-group id="form-cname" label="特殊整流"/>
				<form:form-group id="form-cname" label="增升装置"/>
				<form:form-group id="form-cname" label="机长"/>
				<form:form-group id="form-cname" label="机高"/>
				<form:form-group id="form-cname" label="翼展"/>
				<form:form-group id="form-cname" label="机翼面积"/>
				<form:form-group id="form-cname" label="机翼展弦比"/>
				<form:form-group id="form-cname" label="起飞翼载"/>
			</div>
		</div>
	</div>
</div>
<div>
	<h3 class="header smaller lighter purple">
		性能
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="form-horizontal" >
				<form:form-group id="form-model" label="起飞速度"/>
				<form:form-group id="form-cname" label="着陆速度"/>
				<form:form-group id="form-cname" label="起飞滑跑距离"/>
				<form:form-group id="form-cname" label="着陆滑跑距离"/>
				<form:form-group id="form-cname" label="最大爬升率"/>
				<form:form-group id="form-cname" label="最大稳定盘旋角速度"/>
				<form:form-group id="form-cname" label="最大瞬时盘旋角速度"/>
				<form:form-group id="form-cname" label="最大过载上限"/>
				<form:form-group id="form-cname" label="最大过载下限"/>
				<form:form-group id="form-cname" label="实用升限"/>
				<form:form-group id="form-cname" label="理论升限"/>
				<form:form-group id="form-cname" label="最大平飞速度"/>
				<form:form-group id="form-cname" label="最大平飞马赫数"/>
				<form:form-group id="form-cname" label="最大航程（机内油）"/>
				<form:form-group id="form-cname" label="最大航程（带副油箱）"/>
				<form:form-group id="form-cname" label="作战半径（对空）"/>
				<form:form-group id="form-cname" label="作战半径（对地）"/>
				<form:form-group id="form-cname" label="最大续航时间（机内油）"/>
			</div>
		</div>
	</div>
</div>
<div>
	<h3 class="header smaller lighter purple">
		动力
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="form-horizontal" >
				<form:form-group id="form-model" label="发动机型号"/>
				<form:form-group id="form-cname" label="发动机类型"/>
				<form:form-group id="form-cname" label="发动机数量"/>
				<form:form-group id="form-cname" label="发动机单台最大推力"/>
				<form:form-group id="form-cname" label="发动机单台最大功率"/>
			</div>
		</div>
	</div>
</div>
<div>
	<h3 class="header smaller lighter purple">
		系统
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="form-horizontal" >
				<form:form-group id="form-model" label="雷达型号"/>
				<form:form-group id="form-cname" label="雷达作用距离"/>
				<form:form-group id="form-cname" label="飞控系统"/>
				<form:form-group id="form-cname" label="最大挂点数目"/>
				<form:form-group id="form-cname" label="可挂载武器"/>
				<form:form-group id="form-cname" label="武器挂载方式"/>
				<form:form-group id="form-cname" label="机炮型号"/>
				<form:form-group id="form-cname" label="航电系统特点"/>
			</div>
		</div>
	</div>
</div>

