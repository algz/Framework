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
	window.approvalModal={
		dataID:null
	};
	var _approvalModal=$('#${modalID==null?'approval-modal':modalID }');

    //显示
    approvalModal.show=function(config,callBack){
    	!config.dataID||$(':hidden[name=approval-dataID]').val(config.dataID);
    	!config.dataTable||$(':hidden[name=approval-dataTable]').val(config.dataTable);

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
    	$.ajax({
    		type:"POST",
    		url:'<%=basePath%>/ras/approval/submitapproval',
    		data:{
	    			dataID:$(':hidden[name=approval-dataID]').val(),
	    			dataTable:$(':hidden[name=approval-dataTable]').val(),
	    			permissionLevel:$('#permission_level').val()
    			},
   			complete :function(data){
   				approvalModal.hide();
   				if(callBack!=null){
   					callBack(data);
   				}
   				
   			}
   		})
    };
    
    $('#approval-confirmBtn').on('click',function(){
    	approvalModal.submitApproval(approvalModal.complete);
    })
-->
</script>
