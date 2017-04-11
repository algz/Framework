<%--

<div class="modal-body">
	<div class="row">
		<div class="col-xs-12 col-sm-5">
			<div class="space"></div>

			<input type="file" />
		</div>

		<div class="col-xs-12 col-sm-7">

			<div class="space-4"></div>

			<div class="form-group">
				<label for="form-field-username">Username</label>

				<div>
					<input type="text" id="form-field-username" placeholder="Username" value="alexdoe" />
				</div>
			</div>
			
		</div>
	</div>
</div>

 --%>


<%@tag pageEncoding="UTF-8"%>

<%@attribute name="id" rtexprvalue="true" required="false" description="ID" %>

<div class="modal-body">
	<jsp:body/>
</div>