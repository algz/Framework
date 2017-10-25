<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
fade: 动画效果

<div id="modal-form" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">......</div>
            <div class="modal-body">......</div>
            <div class="modal-footer">......</div>
        </div>
    </div>
</div>

js:
$('#modal-form').modal('show'); 或  $('#modal-form').modal();
$('#modal-form').modal('hide');
--%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

%>
<%@tag pageEncoding="UTF-8"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 

<%@attribute name="modalID" rtexprvalue="true" required="false" description="ID" %>
<%@attribute name="dataID" rtexprvalue="true" required="false" description="数据ID" %>
<%@attribute name="dataTable" rtexprvalue="true" required="false" description="数据ID" %>

<div id="${modalID==null?'approval-modal':modalID }" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
			   <div class="modal-header">        
	            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	            	<span aria-hidden="true">&times;</span></button>
	        		<h4 class="modal-title">数据审批</h4>
	        	</div>
	            <div class="modal-body">            
	          		<form class="form-horizontal" role="form">
						<!-- #section:elements.form -->
						<input name="approval-dataID" type="hidden" value="${dataID }">
						<input name="approval-dataTable" type="hidden" value="${dataTable }">
						<input name="onlyRead" type="hidden" value="${onlyRead }">
						<form:form-group id="permission_level" label="数据权限级别" type="select">
							<option value="1">个人可视</option>
							<option value="2">部门可视</option>
							<option value="3">所内可视</option>
							<option value="4">范围可视</option>
						</form:form-group>
						<form:form-group formClass="hidden" id="permissionUserRange" label="选择范围" type="select" hidden="true" isMultiple="1">
						</form:form-group>
					</form>
	          	</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	    			<button id="approval-confirmBtn" type="button" class="btn btn-primary">确定</button>
	            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

<!-- 

	//动态加载JS
	$.fn.chosen||document.write("<link rel='stylesheet' href='<%=basePath%>ras/common/css/chosen/chosen.css' />");
	$.fn.chosen||document.write("<script src='<%=basePath%>ras/common/js/chosen/chosen.jquery.js'></script>")

	$(function() {
		$('#permission_level,#permissionUserRange').chosen({
			allow_single_deselect : true,
			width : '90%',
			placeholder_text_multiple:'请选择一项'
			//disable_search:true //关闭搜索框,默认为false.
		})
		
		//加载所有用户
		$.ajax({
    		type:"POST",
    		url:'<%=basePath%>/ras/approval/getuserall',
   			complete :function(data){
   				data=data.responseJSON;
   				for(var i=0;i<data.length;i++){
					$('#permissionUserRange').append("<option value='"+data[i].userid+"'>"+data[i].cname+"</option>");
					$("#permissionUserRange").trigger("chosen:updated"); //必须设置,让chosen更新
   				}
   				
   			}
   		})
	})



	window.approvalModal={
		dataID:null
	};
	var _approvalModal=$('#${modalID==null?'approval-modal':modalID }');

    //显示
    approvalModal.show=function(config,callBack){
    	!config.dataID||$(':hidden[name=approval-dataID]').val(config.dataID);
    	!config.dataTable||$(':hidden[name=approval-dataTable]').val(config.dataTable);

		//初始化文本框
		$('#permission_level').val("");
		$("#permission_level").trigger("chosen:updated"); //必须设置,让chosen更新
		$('#permissionUserRange').val("");
		$("#permissionUserRange").trigger("chosen:updated"); //必须设置,让chosen更新
		$('#form-permissionUserRange').addClass('hidden');
		
    	_approvalModal.modal();
		if(callBack!=null){
			callBack();
		}
    };
    
    //隐藏
    approvalModal.hide=function(){
    	_approvalModal.modal('hide');
    };
    
    approvalModal.complete=null;
    
    //提交
    approvalModal.submitApproval=function(callBack){
    	var arr=$('#permissionUserRange option:selected');
    	var range="";
    	for(var i=0;i<arr.length;i++){
    		var val=arr[i].value;
    		if(val==""){
    			continue;
    		}
    		if(range!=""){
    			range+=",";
    		};
    		range+=val;
    	};
    	$.ajax({
    		type:"POST",
    		url:'<%=basePath%>/ras/approval/submitapproval',
    		data:{
	    			dataID:$(':hidden[name=approval-dataID]').val(),
	    			dataTable:$(':hidden[name=approval-dataTable]').val(),
	    			permissionLevel:$('#permission_level').val(),
	    			permissionUserRange:range
    			},
   			complete :function(data){
   				approvalModal.hide();
   				if(callBack!=null){
   					callBack(data);
   				}
   				
   			}
   		})
    };
    
    //提交
    $('#approval-confirmBtn').on('click',function(){
    	approvalModal.submitApproval(approvalModal.complete);
    })
    
    //权限选择
    $('#permission_level').on('change',function(){
    	if(this.value==4){
    		$('#form-permissionUserRange').removeClass('hidden');
    	}else{
    		$('#form-permissionUserRange').addClass('hidden');
    	}
    })
    

-->
</script>
