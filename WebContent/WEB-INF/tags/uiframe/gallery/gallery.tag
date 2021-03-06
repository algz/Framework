<%-- 
html:
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

js:
jQuery(function($) {
	var $overflow = '';
	var colorbox_params = {
		rel: 'colorbox',//群组必须属性.
		reposition:true,
		scalePhotos:true,//如果是true且maxWidth, maxHeight, innerWidth, innerHeight, width, 或者 height 被设置，Colorbox会缩放图片以使用边框
		maxWidth:'100%',
		maxHeight:'100%',
		scrolling:false,//如果是false,Colorbox不会为了溢出元素设置滚动条
		previous:'<i class="ace-icon fa fa-arrow-left"></i>',//“上一个”按钮的文本
		next:'<i class="ace-icon fa fa-arrow-right"></i>',//“下一个”按钮的文本
		close:'&times;',//“关闭”按钮的文本
		current:'{current} of {total}',//文本内容：现在正在显示的元素序号
		onOpen:function(){
			$overflow = document.body.style.overflow;
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = $overflow;
		},
		onComplete:function(){
			$.colorbox.resize();
		}/*,
	    onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
	    onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },*/
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
	
	
	$(document).one('ajaxloadstart.page', function(e) {
		$('#colorbox, #cboxOverlay').remove();
   });
})
--%>
<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="gallery" tagdir="/WEB-INF/tags/uiframe/gallery" %> 

<%@attribute name="headerText" rtexprvalue="true" required="false" description="标题" %>
<%@attribute name="relGroup" rtexprvalue="true" required="true" description="rel群组(必选)" %>
<%@attribute name="imgJSonArray"  rtexprvalue="true" required="true" type="net.sf.json.JSONArray" description="图片集([{url:.,title:..}])" %>
<%@attribute name="imgWidth"  rtexprvalue="true" required="false"  description="图片宽度" %>
<%@attribute name="imgHeight"  rtexprvalue="true" required="false" description="图片高度" %>
<%@attribute name="imgLabel" rtexprvalue="true" required="false" description="标签名组" %>
<%@attribute name="imgTool" rtexprvalue="true" required="false" description="工具名组" %>
 
<div>
	<h3 class="header smaller lighter purple">
		${headerText }
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<div>
				<ul class="ace-thumbnails clearfix">

					<c:forEach var="item" items="${imgJSonArray }">
					<li>
						<a href="${item.photoUrl }" title="${item.photoName==null?'Photo Title': item.photoName}" data-rel="${relGroup }">
							<img width="${imgWidth==null?'150':imgWidth }" height="${imgHeight==null?'150':imgHeight }" alt="${imgWidth==null?'150':imgWidth }x${imgHeight==null?'150':imgHeight }" src="${item.photoUrl }" />
						</a>
						<%if(imgLabel!=null){ %>
						<div class="tags">
							<span class="label-holder">
								<span class="label label-info">${imgLabel }</span>
							</span>
						</div>
						<%} %>
						<%if(imgTool!=null){ %>
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
					</c:forEach>
				</ul>
			</div>
							
		</div><!-- /.col -->
	</div>
</div>
<script type="text/javascript">
jQuery(function($) {
	$('.ace-thumbnails [data-rel="${relGroup}"]').colorbox({
		rel: '${relGroup}',//群组必须属性.
		reposition:true,
		scalePhotos:true,//如果是true且maxWidth, maxHeight, innerWidth, innerHeight, width, 或者 height 被设置，Colorbox会缩放图片以使用边框
		maxWidth:'100%',
		maxHeight:'100%',
		scrolling:false,//如果是false,Colorbox不会为了溢出元素设置滚动条
		previous:'<i class="ace-icon fa fa-arrow-left"></i>',//“上一个”按钮的文本
		next:'<i class="ace-icon fa fa-arrow-right"></i>',//“下一个”按钮的文本
		close:'&times;',//“关闭”按钮的文本
		current:'{current} of {total}',//文本内容：现在正在显示的元素序号
		onOpen:function(){
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = 'auto';//auto
		},
		onComplete:function(){
			$.colorbox.resize();
		}/*,
	    onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
	    onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },*/
	});
	/*$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
	
	
	$(document).one('ajaxloadstart.page', function(e) {
		$('#colorbox, #cboxOverlay').remove();
   });*/
})
		</script>