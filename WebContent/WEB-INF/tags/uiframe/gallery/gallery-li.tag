<%--

<div>
	<h3 class="header smaller lighter purple">
		整体图
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div>
				<ul class="ace-thumbnails clearfix">
					<!-- #section:pages/gallery -->
					<li>
						<a href="ras/picture/homeBackground.jpg" title="Photo Title" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="ras/picture/homeBackground.jpg" />
						</a>

						<div class="tags">
							<span class="label-holder">
								<span class="label label-info">标签名</span>
							</span>
						</div>

						<div class="tools">
							<a href="#">
								<i class="ace-icon fa fa-link"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-paperclip"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-pencil"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-times red"></i>
							</a>
						</div>
					</li>

					<li>
												<a href="<%=basePath%>ras/picture/homeBackground.jpg" title="Photo Title" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="<%=basePath%>ras/picture/homeBackground.jpg" />
						</a>
					</li>

					<!-- /section:pages/gallery -->

					<!-- #section:pages/gallery.caption -->
					<li>
						<a href="../assets/images/gallery/image-3.jpg" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="../assets/images/gallery/thumb-3.jpg" />
							<div class="text">
								<div class="inner">Sample Caption on Hover</div>
							</div>
						</a>

						<div class="tools tools-bottom">
							<a href="#">
								<i class="ace-icon fa fa-link"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-paperclip"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-pencil"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-times red"></i>
							</a>
						</div>
					</li>

					<!-- /section:pages/gallery.caption -->
					<li>
						<a href="../assets/images/gallery/image-4.jpg" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="../assets/images/gallery/thumb-4.jpg" />
						</a>

						<div class="tools tools-top">
							<a href="#">
								<i class="ace-icon fa fa-link"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-paperclip"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-pencil"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-times red"></i>
							</a>
						</div>
					</li>

					<li>
						<div>
							<img width="150" height="150" alt="150x150" src="../assets/images/gallery/thumb-5.jpg" />
							<div class="text">
								<div class="inner">
									<span>Some Title!</span>

									<br />
									<a href="../assets/images/gallery/image-5.jpg" data-rel="colorbox">
										<i class="ace-icon fa fa-search-plus"></i>
									</a>

									<a href="#">
										<i class="ace-icon fa fa-user"></i>
									</a>

									<a href="#">
										<i class="ace-icon fa fa-share"></i>
									</a>
								</div>
							</div>
						</div>
					</li>

					<li>
						<a href="../assets/images/gallery/image-6.jpg" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="../assets/images/gallery/thumb-6.jpg" />
						</a>

						<div class="tools tools-right">
							<a href="#">
								<i class="ace-icon fa fa-link"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-paperclip"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-pencil"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-times red"></i>
							</a>
						</div>
					</li>

					<li>
						<a href="../assets/images/gallery/image-1.jpg" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="../assets/images/gallery/thumb-1.jpg" />
						</a>

						<div class="tools">
							<a href="#">
								<i class="ace-icon fa fa-link"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-paperclip"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-pencil"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-times red"></i>
							</a>
						</div>
					</li>

					<li>
						<a href="../assets/images/gallery/image-2.jpg" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="../assets/images/gallery/thumb-2.jpg" />
						</a>

						<!-- #section:pages/gallery.tools -->
						<div class="tools tools-top in">
							<a href="#">
								<i class="ace-icon fa fa-link"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-paperclip"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-pencil"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-times red"></i>
							</a>
						</div>

						<!-- /section:pages/gallery.tools -->
					</li>
				</ul>
			</div><!-- PAGE CONTENT ENDS -->
							
		</div><!-- /.col -->
	</div>
</div>


 --%>


<%@tag pageEncoding="UTF-8"%>

<%@attribute name="title" rtexprvalue="true" required="false" description="标题名" %>
<%@attribute name="url" rtexprvalue="true" required="false" description="图片url" %>
<%@attribute name="relGroup" rtexprvalue="true" required="false" description="rel群组(必选)" %>
<%@attribute name="labels" rtexprvalue="true" required="false" description="标签名组" %>
<%@attribute name="tools" rtexprvalue="true" required="false" description="工具名组" %>

<li>
	<a href="${url }" title="${title }"  data-rel="${relGroup} ">
		<img width="150" height="150" alt="150x150" src="${url }" />
	</a>
	<%if(labels!=null){ %>
	<div class="tags">
		<span class="label-holder">
			<span class="label label-info">${labels }</span>
		</span>
	</div>
	<%} %>
	<%if(tools!=null){ %>
	<div class="tools">
		<a href="#">
			<i class="ace-icon fa fa-link"></i>
		</a>

		<a href="#">
			<i class="ace-icon fa fa-paperclip"></i>
		</a>

		<a href="#">
			<i class="ace-icon fa fa-pencil"></i>
		</a>

		<a href="#">
			<i class="ace-icon fa fa-times red"></i>
		</a>
	</div>
	<%} %>
</li>