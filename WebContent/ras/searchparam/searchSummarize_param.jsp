<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %>
<%@page import="net.sf.json.JSONObject" %>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="row">	
<div class="col-xs-9">
<form id='modelParamForm' action="./savemodelparam" method="post">
	<input name="overviewID" type="hidden" value="<%=request.getParameter("overviewID") %>"/>
	<c:forEach var="dataParam" items="${paramMap }" varStatus="status">
	<c:if test="${fn:length(dataParam.dataMap)!=0 }">
	
	<div>
		<h3 class="header smaller lighter purple">
			${dataParam.caption}
			<c:if test="${status.index==0}">
			<label>
				<small class="smaller-90">开启注释:</small>
				<input class="ace ace-switch ace-switch-5" id="checkBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			<label>
				<small class="smaller-90">隐藏空行:</small>
				<input class="ace ace-switch ace-switch-5" id="closeSpaceBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			<label>
				<small class="smaller-90">开启编辑:</small>
				<input class="ace ace-switch ace-switch-5" id="closeEditBtn" type="checkbox" >
				<span class="lbl middle" ></span>
			</label>
			</c:if>
		</h3>
							
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="form-horizontal" role="form">
					<input name="basicID" type="hidden" value="<%=request.getParameter("basicID") %>"/>
					<!-- <input name="editor" type="hidden" value="${EDITOR}"/> -->
					<c:forEach var="basicItem" items="${dataParam.dataMap }">
						<c:choose>
						<c:when test="${basicItem.elType=='textArea' }">
							<form:form-group id="${basicItem.elID }" label="${basicItem.elLabel }" type="textArea" value="${basicItem.elValue}" readonly="${basicItem.readonly}"/>   
					    
						</c:when>
						<c:otherwise>
							<form:form-group id="${basicItem.elID }" label="${basicItem.elLabel }"  hidden="${basicItem.elID!='dataSources'?'false':'true'}" value="${basicItem.elValue}" readonly="${basicItem.readonly}" >
								<c:if test="${basicItem.readonly==null&&basicItem.elID!='dataSources'}">
								<span class="input-group-btn hidden">
									<button name='modelparamEditBtn' class="btn btn-xs btn-primary" type="button">
										编辑
									</button>
								</span>
								</c:if>

							</form:form-group> 

						</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div><!-- /.col -->
		</div>
	</div>
	
	</c:if>
</c:forEach>

	<div class="row">
		<div class="col-sm-offset-6">
			<div class="btn-group">
				<a class="btn btn-primary" href="javascript:window.close();history.back()">取消</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
$(function(){

	//弹出窗保存
	$('#saveModelParamBtn').on('click',function(){
		var dataSources=$('#dataSources_add').val();
		var paramName=$("#param_name").val();
		var paramValue=$("#param_val").val();
		$.ajax({
			cache:false,
			url:'./searchsummarize/savemodelparam',
			data:{
				overviewID: $(':hidden[name=overviewID]').val(),
				dataSources: dataSources,
				paramName: paramName,
				paramValue: paramValue
			},
			success:function(data){
				if(data.msg!=""){
					alert(data.msg);
					return;
				}
				var tr="";
					tr+='<td >'+dataSources+'</td>'
					tr+='<td >'+paramValue+'</td>'
				tr='<tr class="hidden" name="'+paramName+'">'+tr+'</tr>'
				$('#affixTable tbody').append(tr);
				
				
				$('#modalparam-form').modal('hide');
			}
		})
	})
	
	//编辑机型参数
	var showModelParamEditBtn=function(){
		$('#modelParamForm div.form-group :input').siblings('span').addClass('hidden')
		$(this).siblings('span').removeClass('hidden')
	}
	
	//机型参数编辑按钮
	$('#modelParamForm :input~span button[name=modelparamEditBtn]').on('click',function(){
		//打开弹出窗口.
		var el=this;
	 	$('#modalparam-form').on('show.bs.modal',function(){
	 		$('#dataSources_add').val('')
	 		 
	 		$("#param_text").val($(el).closest("div").siblings("label").text());
			$("#param_name").val($(el).closest("span").siblings("input").attr('name'));
			$("#param_val").val("");
		}).modal();//先定义函数,再调用弹出函数,才能按顺序执行.
	})

	

	/**
	 *开启编辑功能
	*/
	$('#closeEditBtn').on('click',function(){
		if($(this).attr('checked')==null){
			//打开
			$(this).attr("checked","checked");
			$("#modelParamForm div.form-group :input").on('click',showModelParamEditBtn); //显示编辑按钮
		}else{
			//关闭
			$(this).removeAttr("checked");
			$('#modelParamForm div.form-group :input').siblings('span').addClass('hidden')
			$("#modelParamForm div.form-group :input").off('click',showModelParamEditBtn)
		}
	})
	
	
	//显示机型参数注释
	var showModelParam=function(){
		$('#affixTable tbody tr').addClass('hidden');
		$('#affixTable tbody tr[name='+this.name+']').removeClass('hidden');
	}
	
	/**
	* 开启注释
	*/
	$("#checkBtn").click(function(){
		//checked="checked"
		$('#affixTable').toggleClass('hidden');
		
		if($(this).attr("checked")==null){
			//打开
			$(this).attr("checked","checked");
			var s="";
			var els=$("#modelParamForm :text[name!=dataSources]");
			for(var i=0;i<els.length;i++){
				if(i!=0){
					s+=",";
				}
				s+=$(els[i]).attr("name");
			}
			$.ajax({
				cache:false,
				url:'./addnotefortaginput',
				data:{
					overviewID:$(":hidden[name=overviewID]").val(),
					inputName:s
				},
				success:function(data){
					var obj=data;//eval("("+data+")");
					$('#affixTable tbody').empty();
					for(var i=0;i<obj.inputs.length;i++){
						var input=obj.inputs[i];
						var tr="";
						for(var j=0;j<input.vals.length;j++){
							var td=""
								td+='<td >'+input.vals[j].dataSources+'</td>'
								td+='<td >'+input.vals[j].inputValue+'</td>'
							tr+='<tr class="hidden" name="'+input.name+'">'+td+'</tr>'
						}
						$('#affixTable tbody').append(tr);
						
						
						$("#modelParamForm div.form-group :input").on('click',showModelParam);
						
						//toolip
						var el=$(":text[name="+input.name+"]");
						el.offsetParent().offsetParent().attr("data-toggle","tooltip");
						el.after("<i class='ace-icon fa fa-info-circle'></i>");
						var s="<ul class='list-unstyled'>"
						for(var j=0;j<input.vals.length;j++){
							var val=input.vals[j]
							s+="<li>"
							s+=(j+1)+"."+val.inputValue+" <i class='ace-icon fa fa-external-link blue'/><span class='text-muted'>"+val.dataSources+"</span><p>";
							s+="</li>"
						}
						el.offsetParent().offsetParent().attr("title",s+"</ul>"); 
					}
					$('[data-toggle="tooltip"]').tooltip({
						html:true,
						placement:'right'
					});
				}
			})
		}else{
			//关闭
			$(this).removeAttr("checked")
			$('[data-toggle="tooltip"]').tooltip('destroy')
			$('[data-toggle="tooltip"]').attr("title","")
			$(":text~:not(span)").remove();
			
			$("#modelParamForm div.form-group :input").off('click',showModelParam);
		}
	})
	
	/**
	* 隐藏空行
	*/
	$("#closeSpaceBtn").on('click',function(){
		if($(this).attr("checked")==null){
			//打开
			$(this).attr("checked","checked");
			var els=$(".form-horizontal .form-group").has(':text[value=""]');
			els.addClass('hidden')
		}else{
			//关闭
			$(this).removeAttr("checked")
			$(".form-horizontal .form-group").removeClass("hidden");
		}
	})

})
</script>

</form>
</div>
	<div style="float:left; position: relative;">
			<div id='affixTable' class="hidden" style='position:fixed' data-offset-top="125" data-spy="affix" >
				
					<div class="table-header">
						机型参数来源注释
					</div>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center" style='width: 100px'>数据来源</th>
								<th class="center">数据值</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
			</div>
    </div>
</div>



<div id="modalparam-form" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">        
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            	<span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">机型参数编辑</h4>
        	</div>
            <div class="modal-body">
	            <form class="form-horizontal">
	            	<form:form-group id="dataSources_add" label="数据来源"/>
	            	<input id="param_name" type="hidden">
	            	<form:form-group id="param_text" label="参数名称" readonly="true"/>
	            	<form:form-group id="param_val" label="参数值"/>
				</form>
          	
          	</div>
            <div class="modal-footer">
            	<button id="saveModelParamBtn" type="button" class="btn btn-default">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>