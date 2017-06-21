/**
 * html:
 * <form:form-group id="upfile-image" label="上传文件" type="file" />
 * 
 			<!-- fileinput.css文件上传 -->
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/fileinput/fileinput.css" />
			<link href="<%=basePath%>ras/common/css/fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
			
			<!-- fileinput.js文件上传 -->
			<script src="<%=basePath%>ras/common/js/fileinput/fileinput.js"></script>
 * 
 */


$('#upfile-image').fileinput({
			//'theme': 'explorer',
			uploadUrl : './upmodelimagefile', //上传的地址
			allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
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
			deleteUrl : './delmodelimagefile',//删除URL.string, the URL for deleting the image/content in the initial preview via AJAX post response. This will be overridden by the initialPreviewConfig['url'] property.
			overwriteInitial : false,//boolean, whether you wish to overwrite the initial preview content and caption setup. This defaults to true, whereby, any initialPreview content set will be overwritten, when new file is uploaded or when files are cleared. Setting it to false will help displaying a saved image or file from database always - useful especially when using the multiple file upload feature.
			initialCaption: "请选择...",
			//initialPreviewShowDelete:false,
			initialPreviewAsData : true,//boolean, whether the initial preview content set is to be parsed as data instead of raw markup. Defaults to false for backward compatibility (prior to v4.3.2).
			initialPreview : photo.photoUrl,
			initialPreviewConfig : photo.photoConfig,
			uploadExtraData : {
				overviewID:overviewID,
				modelName:modelName
			},
			previewSettings : {
				image : {
					width : "150px",
					height : "150px"
				}
			}
		})
//		.on("fileuploaded",function(event, data, previewId, index) {
//			//{"basicID":"5DEBD7F5C1F54CAD8BF6E1C234DC8720","photoCategory":"整体图","photoDesc":"","photoFile":null,"photoID":"FEE09A0FD6E7452C9C0731137D7B10BD","photoName":"Tulips.jpg","photoUrl":"/upload/photo/1492351809778.jpeg"}
//			photo.photoUrl=[];
//    		photo.photoUrl.push(data.response.photoUrl);
//			$('#upfile-image').fileinput('refresh',{
//				initialPreview : photo.photoUrl,
//				initialPreviewConfig : photo.photoConfig
//			});
//			$('#upfile-image').fileinput('enable');
//			alert("上传成功!");
//		})

	//页面提交
	$("#pic-confirmBtn").on('click',function(){
		//提交上传图片
		$("#upfile-image").fileinput('upload');
		//上传成功后,返回函数
		$("#upfile-image").on("fileuploaded",function(){
			tablemodel.ajax.reload();
			$('#modal-picture').modal('hide');
		});
	})
	