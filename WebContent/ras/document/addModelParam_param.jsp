<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 

<form action="./savemodelparam" method="post">
	<input name="overviewID" type="hidden" value="<%=request.getParameter("overviewID") %>"/>
	
	<div>
		<h3 class="header smaller lighter purple">
			基本信息
		</h3>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="form-horizontal" role="form">
					<input name="basicID" type="hidden" value="${BASICID}"/>
					<input name="editor" type="hidden" value="${EDITOR}"/>
					<form:form-group id="modelName" label="型号" value="${ MODELNAME}" readonly="true"/>
					<form:form-group id="modelCname" label="中文名称" value="${ MODELCNAME}" readonly="true"/>
					<form:form-group id="modelEname" label="英文名称" value="${ MODELENAME}" readonly="true"/>
					<form:form-group id="dataSources" label="数据来源" value="${DATASOURCES }"/>
					<form:form-group id="producerCountries" label="研发国别" value="${PRODUCERCOUNTRIES }"/>
					<form:form-group id="manufacturer" label="研发厂商" value="${MANUFACTURER}" />
					<form:form-group id="usingCountries" label="使用国别" value="${USINGCOUNTRIES }"/>
					<form:form-group id="aircraftType" label="飞机类型" value="${AIRCRAFTTYPE }"/>
					<form:form-group id="firstFlightYear" label="首飞年份" value="${FIRSTFLIGHTYEAR }"/>
					<form:form-group id="serviceYear" label="服役年份" value="${SERVICEYEAR }"/>
					<form:form-group id="crewNumber" label="机组人员数量" value="${CREWNUMBER }"/>
					<form:form-group id="passengerCapacity" label="最大载客量" value="${PASSENGERCAPACITY }"/>
					<form:form-group id="price" label="单价" value="${PRICE }"/>
				</div>
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
				<div class="form-horizontal" role="form">
					<input name="weightID" type="hidden" value="${WEIGHTID}"/>
					<form:form-group id="emptyWeight" label="使用空重" value="${EMPTYWEIGHT }"/>
					<form:form-group id="normalTakeoffWeight" label="正常起飞重量" value="${NORMALTAKEOFFWEIGHT }"/>
					<form:form-group id="maxTakeoffWeight" label="最大起飞重量" value="${ MAXTAKEOFFWEIGHT}"/>
					<form:form-group id="designLandWeight" label="设计着陆重量" value="${DESIGNLANDWEIGHT }"/>
					<form:form-group id="fuelCapacity" label="机内油量" value="${FUELCAPACITY }"/>
					<form:form-group id="maxLoadWeight" label="最大装载重量" value="${MAXLOADWEIGHT }"/>
					<form:form-group id="takeWeightRatio" label="起飞推重比" value="${TAKEWEIGHTRATIO }"/>
				</div>
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
					<input name="capabilityID" type="hidden" value="${CAPABILITYID}"/>
					<form:form-group id="takeoffSpeed" label="起飞速度" value="${TAKEOFFSPEED }"/>
					<form:form-group id="landingSpeed" label="着陆速度" value="${LANDINGSPEED }"/>
					<form:form-group id="takeoffDistance" label="起飞滑跑距离" value="${TAKEOFFDISTANCE }"/>
					<form:form-group id="landingDistance" label="着陆滑跑距离" value="${LANDINGDISTANCE }"/>
					<form:form-group id="maxClimbRate" label="最大爬升率" value="${MAXCLIMBRATE }"/>
					<form:form-group id="maxSCAV" label="最大稳定盘旋角速度" value="${MAXSCAV }"/>
					<form:form-group id="maxICAV" label="最大瞬时盘旋角速度" value="${maxICAV }"/>
					<form:form-group id="maxOverloadUpLimit" label="最大过载上限" value="${MAXOVERLOADUPLIMIT }"/>
					<form:form-group id="maxOverloadDownLimt" label="最大过载下限" value="${MAXOVERLOADDOWNLIMT }"/>
					<form:form-group id="practicalCeiling" label="实用升限" value="${PRACTICALCEILING }"/>
					<form:form-group id="theoreticalCeiling" label="理论升限" value="${THEORETICALCEILING }"/>
					<form:form-group id="maxLevelSpeed" label="最大平飞速度" value="${MAXLEVELSPEED }"/>
					<form:form-group id="maxEndurance" label="最大平飞马赫数" value="${MAXENDURANCE }"/>
					<form:form-group id="maxRangeEngineOil" label="最大航程（机内油）" value="${MAXRANGEENGINEOIL }"/>
					<form:form-group id="maxRangeFuelTank" label="最大航程（带副油箱）" value="${MAXRANGEFUELTANK }"/>
					<form:form-group id="fightRadiusAir" label="作战半径（对空）" value="${FIGHTRADIUSAIR }"/>
					<form:form-group id="fightRadiusSurface" label="作战半径（对地）" value="${FIGHTRADIUSSURFACE }"/>
					<form:form-group id="maxEndurance" label="最大续航时间（机内油）" value="${MAXENDURANCE }"/>
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
					<form:form-group id="engineModel" label="发动机型号" value="${ engineModel}"/>
					<form:form-group id="engineType" label="发动机类型" value="${ENGINETYPE }"/>
					<form:form-group id="engineNumber" label="发动机数量" value="${ENGINENUMBER }"/>
					<form:form-group id="engineMaxThrust" label="发动机单台最大推力" value="${ENGINEMAXTHRUST }"/>
					<form:form-group id="engineMaxPower" label="发动机单台最大功率" value="${ENGINEMAXPOWER }"/>
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
					<form:form-group id="radarModel" label="雷达型号" value="${RADARMODEL }"/>
					<form:form-group id="radarRange" label="雷达作用距离" value="${RADARRANGE }"/>
					<form:form-group id="flightControlSystem" label="飞控系统" value="${FLIGHTCONTROLSYSTEM }"/>
					<form:form-group id="maxMountPoint" label="最大挂点数目" value="${MAXMOUNTPOINT }"/>
					<form:form-group id="optionalWeaponsMount" label="可挂载武器" value="${OPTIONALWEAPONSMOUNT }"/>
					<form:form-group id="weaponsMount" label="武器挂载方式" value="${WEAPONSMOUNT }"/>
					<form:form-group id="MachineGunModel" label="机炮型号" value="${MACHINEGUNMODEL }"/>
					<form:form-group id="avionicsSystem" label="航电系统特点" value="${AVIONICSSYSTEM }"/>
				</div>
			</div>
		</div>
	</div>
	<%if(request.getParameter("isModify")!=null&&request.getParameter("isModify").equals("true")){ %>
	<div class="row">
		<div class="col-sm-offset-6">
			<div class="btn-group">
				<input type="submit" id="addModelparam" class="btn btn-primary" value="保存"/>
			</div>
			<div class="btn-group">
				<a class="btn btn-primary" href="javascript:history.back()">取消</a>
			</div>
		</div>
	</div>
	<%} %>
</form>