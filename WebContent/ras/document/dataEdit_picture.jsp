<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//JSONArray ja=(JSONArray)request.getAttribute("menus");
%>

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
						<a href="<%=basePath%>ras/picture/homeBackground.jpg" title="Photo Title" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="<%=basePath%>ras/picture/homeBackground.jpg" />
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
						<a href="../assets/images/gallery/image-2.jpg" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="../assets/images/gallery/thumb-2.jpg" />
							<div class="text">
								<div class="inner">Sample Caption on Hover</div>
							</div>
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

<div>
	<h3 class="header smaller lighter purple">
		三面图
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div>
				<ul class="ace-thumbnails clearfix">
					<!-- #section:pages/gallery -->
					<li>
						<a href="../assets/images/gallery/image-1.jpg" title="Photo Title" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="<%=basePath%>ras/common/js/holder.js/100%x200" />
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
							<div class="text">
								<div class="inner">Sample Caption on Hover</div>
							</div>
						</a>
					</li>

					<!-- /section:pages/gallery -->


				</ul>
			</div><!-- PAGE CONTENT ENDS -->
							
		</div><!-- /.col -->
	</div>
</div>

<div>
	<h3 class="header smaller lighter purple">
		外观
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div>
				<ul class="ace-thumbnails clearfix">
					<!-- #section:pages/gallery -->
					<li>
						<a href="../assets/images/gallery/image-1.jpg" title="Photo Title" data-rel="colorbox">
							<img width="150" height="150" alt="150x150" src="<%=basePath%>ras/common/js/holder.js/100%x200" />
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
							<div class="text">
								<div class="inner">Sample Caption on Hover</div>
							</div>
						</a>
					</li>

					<!-- /section:pages/gallery -->


				</ul>
			</div><!-- PAGE CONTENT ENDS -->
							
		</div><!-- /.col -->
	</div>
</div>

<script type="text/javascript">
jQuery(function($) {
	var colorbox_params = {
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="icon-arrow-left"></i>',
		next:'<i class="icon-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = 'auto';
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").append("<i class='icon-spinner orange'></i>");//let's add a custom loading icon

	/**$(window).on('resize.colorbox', function() {
		try {
			//this function has been changed in recent versions of colorbox, so it won't work
			$.fn.colorbox.load();//to redraw the current frame
		} catch(e){}
	});*/
})
		</script>
