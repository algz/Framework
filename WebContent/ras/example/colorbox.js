/**
 * css:
 * 			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox) -->
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/colorbox.css" />
 * js:
 * 			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox)  -->
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>
 * html:
 * <a>href属性==<img>src属性
 * <div class="ace-thumbnails">
 *   <a href="'+data+'" data-rel="colorbox">+
	   <img width="150" height="150" alt="" src="'+data+'" />
	 </a>
   </div>
 */
$('.ace-thumbnails [data-rel="colorbox"]').colorbox({
			//rel: 'colorbox',//群组必须属性,单图显示可不需要设置.
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
				//$overflow = document.body.style.overflow;
				document.body.style.overflow = 'hidden';
			},
			onClosed:function(){
				document.body.style.overflow = 'auto'; //必须设置,不然只能点击一次后就不弹窗
			},
			onComplete:function(){
				$.colorbox.resize();
			}/*,
		    onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
		    onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },*/
		});