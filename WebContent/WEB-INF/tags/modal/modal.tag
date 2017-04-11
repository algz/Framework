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
<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>


<div id="modal-form" class="modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
			<jsp:doBody/>
        </div>
    </div>
</div>

