<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 引入modal.js -->
<script type="text/javascript"
	src="<%=request.getContextPath() %>/platform/core/lib/jquery_plugins/bootstrap-modal.js"></script>


<script type="text/javascript">
<!--
function goUrl(x){
	location.href=x;
}  
//-->
</script>
<div style='height:48px;'></div>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<ul class="nav">
				<li class="active"><a href="javascript:goUrl('index.jsp')">首页</a></li>
				<li><a href="javascript:goUrl('videoGallery.jsp')">电影</a></li>
				<li><a href="javascript:goUrl('videoGallery.jsp')">电视剧</a></li>
				<li><a href="javascript:goUrl('videoGallery.jsp')">综艺</a></li>
				<li><a href="javascript:goUrl('videoGallery.jsp')">动漫</a></li>
				<li><a href="javascript:goUrl('videoGallery.jsp')">娱乐</a></li>
			</ul>
			<ul class="nav pull-right">
				<li><a href="#" data-toggle='modal' data-target='#createUser_Modal'>注册</a></li>
				<li><a href="#" data-toggle='modal' data-target='#loginUser_Modal'>登陆</a></li>
			</ul>
		</div>
	</div>
</div>

<!-- 新建用户  Modal -->
<div id="createUser_Modal" class="modal hide fade">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3 id="myModalLabel">新建用户</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="inputRolename"
					style="float: left; text-align: right;">邮箱</label>
				<div class="controls">
					<input type="text" id="inputRolename" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRoleDesc"
					style="float: left; text-align: right;">昵称</label>
				<div class="controls">
					<input type="text" id="inputRoleDesc" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRoleDesc"
					style="float: left; text-align: right;">密码</label>
				<div class="controls">
					<input type="text" id="inputRoleDesc" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRoleDesc"
					style="float: left; text-align: right;">确认</label>
				<div class="controls">
					<input type="text" id="inputRoleDesc" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button class="btn btn-primary">同意以下协议,提交</button>
				</div>
				<div class="controls">
					<a href='#'><small>《网络服务使用协议》</small></a>
				</div>
			</div>
		</form>
	</div>
</div>
    <!-- 用户登陆  Modal -->
	<div id="loginUser_Modal" class="modal hide fade">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>用户登陆</h3>
		</div>
		<div class="modal-body">
					<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="loginUser"
									style="float: left; text-align: right;">邮箱/用户名</label>
								<div class="controls">
									<input type="text" id="loginUser" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputRoleDesc1"
									style="float: left; text-align: right;">密码</label>
								<div class="controls">
									<input type="text" id="inputRoleDesc1" />
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button class="btn btn-primary">提交</button>
								</div>
							</div>
					</form>
		</div>
	</div>