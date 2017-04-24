<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//JSONArray ja=(JSONArray)request.getAttribute("menus");
%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>飞机论证参照模块</title>

		<meta name="description" content="top menu &amp; navigation" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<jsp:include  page="../../common/common_css.jsp"/> 
		<jsp:include  page="../../common/common_js.jsp"/> 
	</head>

	<body >


		
		<page:page page="${page }">
			<!-- PAGE CONTENT BEGINS -->
			<div>
				<h3 class="header smaller lighter purple">
					新增机型
				</h3>
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="./savemodel" method="post">
							<input name="overviewID" type="hidden" value="${model.overviewID }"/>
							<form:form-group id="modelName" label="机型名" value="${model.modelName }" simpleValidate="required"/>
							<form:form-group id="modelCname" label="中文名称" value="${model.modelCname }" />
							<form:form-group id="modelEname" label="英文名称" value="${model.modelEname }"/>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" > 上传图片 </label>
								<div class="col-xs-10 col-sm-5">
									<span class="block input-icon input-icon-right">
										<input id="upfile-image" name='file' type="file" >
									</span>
								</div>
							</div>
							
							<form:form-action >
								<form:form-button buttonType="submit" id="submit" label="提交" icon="fa-check" buttonClass="btn-info"/>
								<form:form-button id="canle" label="取消" url="./"/>
							</form:form-action>
						</form>
					</div><!-- /.col -->
				</div>
			</div>

			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		
		<plugin_css>
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/fileinput/fileinput.css" />
		</plugin_css>
		<plugin_js>
		
		<!-- 文件上传 -->
		<script src="<%=basePath%>ras/common/js/fileinput/fileinput.js"></script>
		
		<script src="<%=basePath%>ras/common/js/jqueryvalidation/jquery.validate.js"></script>
		<script src="<%=basePath%>ras/common/js/jqueryvalidation/messages_zh.js"></script>
		</plugin_js>
		
<script type="text/javascript">
	$(function(){
		 /**
         * 表单验证
         */
        var validator = $('.form-horizontal').validate({
        		//debug:true,//true.即不提交表单,默认为false.
        		//url:'',//=form-action的值.
        		/**
        		 * errorElement：类型 String，默认 "label"。指定使用什么标签标记错误。
        		 */
				errorElement : 'div', 
				/**
				 * errorClass：类型 String，默认 "error"。指定错误提示的 css 类名。
				 */
				errorClass : 'help-block', 
				//在使用jquery的chosen下拉列表的插件时，碰到了使用jquery.validate.js的冲突，不能进行空值校验,chosen插件本身自动隐藏了空值，所以校验不到.
				ignore: ":not(:text,select)" , //忽略:不包括text和select控件. 必须设置这值,此UI框架使的插件都以hidden隐藏原控件.
				/**
				 * element出错时触发
				 * (highlight：可以给未通过验证的元素加效果、闪烁等。)
				 */
				highlight: function (e) {
					$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
				},
				/**
				 * element通过验证时触发
				 */
				unhighlight: function(element) {
            		$(element).closest('.form-group').removeClass('has-error');
        		}
			});
		 
        

    	var overviewID=$(":hidden[name=overviewID]").val();
    	var modelName=$(':text[name=modelName]').val();
    	var photoUrl='${model.photoUrl}';
    	var photo={};
    	photo.photoUrl= []
    	photo.photoConfig=[];
    	if(photoUrl!=""){
    		photo.photoUrl.push(photoUrl);
        	photo.photoConfig.push({
    			//caption:img.photoName,
    			key:1,
    			extra:{
    				overviewID:overviewID
    			}
    		});
        	
    	}

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
				},
			}
		}).on("fileuploaded",function(event, data, previewId, index) {
			//{"basicID":"5DEBD7F5C1F54CAD8BF6E1C234DC8720","photoCategory":"整体图","photoDesc":"","photoFile":null,"photoID":"FEE09A0FD6E7452C9C0731137D7B10BD","photoName":"Tulips.jpg","photoUrl":"/upload/photo/1492351809778.jpeg"}
			//loadFileinputParams(data.response,photo);
			photo.photoUrl=[];
    		photo.photoUrl.push(data.response.photoUrl);
			$('#upfile-image').fileinput('refresh',{
				initialPreview : photo.photoUrl,
				initialPreviewConfig : photo.photoConfig
			});
			$('#upfile-image').fileinput('enable');
			alert("上传成功!");
	})
	})
	</script>
	</body>
</html>
