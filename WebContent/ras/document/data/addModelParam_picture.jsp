<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div>
	<h3 class="header smaller lighter purple">
		整体图
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<input id="integral-graph" name="file" type="file" multiple >
		</div>
	</div>
</div>

<div>
	<h3 class="header smaller lighter purple">
		三面图
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<input id="three-graph" name="file" type="file" >
		</div>
	</div>
</div>

<div>
	<h3 class="header smaller lighter purple">
		外观
	</h3>
	<div class="row">
		<div class="col-xs-12">
			<input id="surface-graph" name="file" type="file" >
		</div>
	</div>
</div>

<script type="text/javascript">
jQuery(function($) {

	var imgData=eval('(${img})');
	
	var integralPhoto={};
	integralPhoto.uploadExtraData={};
	integralPhoto.uploadExtraData.photoCategory="整体图";
	integralPhoto.uploadExtraData.basicID=$(":hidden[name=basicID]").val();
	
	var threePhoto={};
	threePhoto.uploadExtraData={};
	threePhoto.uploadExtraData.photoCategory="三面图";
	threePhoto.uploadExtraData.basicID=$(":hidden[name=basicID]").val();
	
	var surfacePhoto={};
	surfacePhoto.uploadExtraData={};
	surfacePhoto.uploadExtraData.photoCategory="外观图";
	surfacePhoto.uploadExtraData.basicID=$(":hidden[name=basicID]").val();
	
	var initPhotoUrl=new Array();
	for(var i=0;i<imgData.length;i++){
		var img=imgData[i];
		switch (img.photoCategory){
		case '整体图':
			loadFileinputParams(img,integralPhoto);
			break;
		case '三观图':
			loadFileinputParams(img,threePhoto);
			break;
		case '外观图':
			loadFileinputParams(img,surfacePhoto);
			break;
		}
		
	}
	

		

	
	function loadFileinputParams(img,obj){
		if(obj.photoUrl==null){
			obj.photoUrl=new Array();
			obj.photoConfig=new Array();
		}
		obj.photoUrl.push(img.photoUrl);
		obj.photoConfig.push({
			caption:img.photoName,
			key:1,
			extra:{
				photoID:img.photoID
			}
		});
		//obj.uploadExtraData.photoCategory="整体图";
		//obj.uploadExtraData.basicID=$(":hidden[name=basicID]").val();
		//integralPhoto.uploadExtraData.photoID="";
	}
	
	createFileinput("#integral-graph",integralPhoto);//整体图
	createFileinput("#three-graph",threePhoto);//三观图
	createFileinput("#surface-graph",surfacePhoto);//外观图
	
	
	

	
	/**
	创建fileinput
	*/
	function createFileinput(id, photo) {
		
			$(id).fileinput({
				//'theme': 'explorer',
				uploadUrl : './upimagefile', //上传的地址
				//allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
				//browseClass: "btn btn-primary", //按钮样式  
				//showCaption: false,//是否显示标题(默认true)
				//showPreview: false,//是否显示预览(默认true)
				//showRemove:true,//是否(whether to)显示remove/clear button(默认true)
				//showUpload: true, //是否显示上传按钮. Defaults to true. This will default to a form submit button, unless the uploadUrl is specified.
				//showCancel:true,//boolean whether to display the file upload cancel button. Defaults to true. This will be only enabled and displayed when an AJAX upload is in process.
				//showClose:true,//boolean whether to display the close icon in the preview. Defaults to `true`. This will be only parsed when showPreview is true or when you are using the {close} tag in your preview templates.
				//showUploadedThumbs:true,//boolean whether to persist display of the uploaded file thumbnails in the preview window (for ajax uploads) until the remove/clear button is pressed. Defaults to true. When set to false, a next batch of files selected for upload will clear these thumbnails from preview.
				//showBrowse:true,//boolean whether to display the file browse button. Defaults to true.
				//browseOnZoneClick : true,//boolean whether to enable file browse/select on clicking of the preview zone. Defaults to false.
				//dropZoneEnabled : false,//是否显示拖拽区域(设置为false不能与initialPreview有值同时使用.).bug:此属性如果设置为false,同时initialPreview有值时(即预览状态),removel和upload按钮将自动显示,并且点击按钮将报错.bool whether to enable a drag and drop zone for dragging and dropping files to. This is available only for ajax based uploads. Defaults to true.
				//minImageWidth: 50, //图片的最小宽度
				//minImageHeight: 50,//图片的最小高度
				//maxImageWidth: 1000,//图片的最大宽度
				//maxImageHeight: 1000,//图片的最大高度
				//maxFileSize: 0,//单位为KB，如果为0表示不限制文件大小
				//minFileCount: 0,
				//maxFileCount: 10, //表示允许同时上传的最大文件个数
				//enctype: 'multipart/form-data',
				//validateInitialCount:true,
				//previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
				//msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
				deleteUrl : './delimagefile',//删除URL.string, the URL for deleting the image/content in the initial preview via AJAX post response. This will be overridden by the initialPreviewConfig['url'] property.
				overwriteInitial : false,//boolean, whether you wish to overwrite the initial preview content and caption setup. This defaults to true, whereby, any initialPreview content set will be overwritten, when new file is uploaded or when files are cleared. Setting it to false will help displaying a saved image or file from database always - useful especially when using the multiple file upload feature.
				initialCaption: "请选择...",
				//initialPreviewShowDelete:false,
				initialPreviewAsData : true,//boolean, whether the initial preview content set is to be parsed as data instead of raw markup. Defaults to false for backward compatibility (prior to v4.3.2).
				initialPreview : photo.photoUrl,
				/* [
					//'<img width="150" height="150" alt="150x150" src="/upload/photo/Desert.jpg" />'
				    '/upload/photo/Desert.jpg',
				    '/upload/photo/Chrysanthemum.jpg'
				], */
				initialPreviewConfig : photo.photoConfig,
				/*[ {
					caption : "nature-1.jpg",
					size : 329892,
					width : "520px",
					key : 1,
					extra : {
						id : 100
					}
				} ], */
				previewSettings : {
					image : {
						width : "150px",
						height : "150px"
					},
				},
				uploadExtraData : photo.uploadExtraData
			/*,
			             uploadExtraData: function() {
			                 var extraValue = null;
			                 var radios = document.getElementsByName('excelType');
			                 for(var i=0;i<radios.length;i++){
			                     if(radios[i].checked){
			                         extraValue = radios[i].value;
			                     }
			                 }
			                 return {"excelType": extraValue};
			             }*/
			});
			$(id).on("fileuploaded",
					function(event, data, previewId, index) {
						alert("上传成功!");
						/*$("#excelImport").modal("hide");
						//后台处理后返回的经纬度坐标json数组，
						var array = data.response;
						console.dir(array);
						//jquery循环取经纬度坐标
						$.each(array,function(index,latAndLon){
							var lon = latAndLon.lon;
							var lat = latAndLon.lat;
							var point =  new Point(lon, lat, map.spatialReference);
							var symbol = new esri.symbol.SimpleMarkerSymbol().setStyle(
								    SimpleMarkerSymbol.STYLE_CIRCLE).setColor(
								    new Color([255,255,0,0.5]));
							var attr = {"address": "addressName" };
							var infoTemplate = new esri.InfoTemplate("标题", "地址 :${address}");
							var graphic = new Graphic(point,symbol,attr,infoTemplate);
							map.graphics.add(graphic);
						});*/

					});
		}


<%--
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
	});*/--%>
})


	/* $('#textBtn').on('click',function(){
		$("#kv-explorer").fileinput('addToStack','<img width="150" height="150" alt="150x150" src="/upload/photo/Desert.jpg" />')
		return ;
		$.ajax({
	        url: './upfile',
	        type: 'POST',
	        dataType: 'json',
	        data: new FormData($( "#uploadForm" )[0]),
	        cache: false,
	        processData: false,
	        contentType: false,
	        beforeSend: function(){
	        	alert('beforeSend')
	        },
	        success:function(){
	        	alert('success')
	        },
	        complete:function(){
	        	alert('complete')
	        },
	        error:function(){
	        	alert('error')
	        }
	    })
	}) */
		</script> 
