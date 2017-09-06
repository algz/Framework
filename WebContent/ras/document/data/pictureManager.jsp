<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/uiframe/page" %> 
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 
<%@taglib prefix="approval" tagdir="/WEB-INF/tags/customize/approval" %> 
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
			<div class="row">
				<div class="col-xs-12">
				<input type="hidden" name="modelName" value="${ modelName}">
				<input type="hidden" name="overviewID" value="${ overviewID}">
					<h3 class="header smaller lighter purple">
					全部图片
					</h3>
					<div class="table-header">
						
					</div>
			
					<!-- <div class="table-responsive"> -->
			
					<!-- <div class="dataTables_borderWrap"> -->
					<div class="control-group">
						<div class="radio">
							<label>
								<input name="pic-radio" type="radio" class="ace" checked value="整体图">
								<span class="lbl">整体图</span>
							</label>
			
							<label>
								<input name="pic-radio" type="radio" class="ace" value="三面图">
								<span class="lbl">三面图</span>
							</label>
			
							<label>
								<input name="pic-radio" type="radio" class="ace" value="外观图">
								<span class="lbl">外观图</span>
							</label>
							
							<label>
								<input name="pic-radio" type="radio" class="ace" value="其它">
								<span class="lbl">其它图</span>
							</label>
						</div>
					</div>
					<div id='pictureTool' class="btn-group">
						<button id="upPicture" class="btn btn-sm" type="button">上传</button>
						<button id="delPicture" class="btn btn-sm" type="button">删除</button>
						<button id="submitApprovalBtn" class="btn btn-sm" type="button">送审</button>
						<span class="input-icon">
							<input type="text" id="picTagTxt" placeholder="标签名">
							<i class="ace-icon fa fa-fighter-jet  blue"></i>
						</span>
						<button id="picSearchBtn" class="btn btn-purple btn-sm" type="button">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								查询
						</button>
					</div>
					<div>
						<table id="pictureTable" class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
			
			<div id="modal-picture" class="modal fade" tabindex="-1">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">        
			            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true">&times;</span></button>
			        		<h4 class="modal-title">...</h4>
			        	</div>
			            <div class="modal-body">            
			          		<form class="form-horizontal" role="form">
								<!-- #section:elements.form -->
								<input name="id" type="hidden" value="${id }">
								<input name="onlyRead" type="hidden" value="${onlyRead }">
								<form:form-group id="photoName" label="图片标题" />
								<form:form-group id="photoDesc" type="textArea" label="图片简介" />	
								<form:form-group id="photoCategory" label="类别" type="select">
									<option>整体图</option>
									<option>三面图</option>
									<option>外观图</option>
									<option>其它图</option>
								</form:form-group>				
								<form:form-group id="photoTag" label="标签" />
								<form:form-group id="photoFile" label="上传文件" type="file" isMultiple="1" />
							</form>
			          	</div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			    			<button id="pic-confirmBtn" type="button" class="btn btn-primary">确定</button>
			            </div>
			        </div>
			    </div>
			</div>
			<div class="row">
				<div class="col-sm-offset-6">
					<div class="btn-group">
						<input class="btn btn-primary" onclick="history.go(-1)" type="button" value="返回">
					</div>
				</div>
			</div>
			<approval:approval modalID="approvalModealID" dataID="" dataTable="PICTURE" />
			<!-- PAGE CONTENT ENDS -->
		</page:page>
		
		<plugin_css>
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />	
		
			<!-- 图片放大查看(jquery弹出层插件jquery.ColorBox) -->	
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/colorbox.css" />
		
			<link rel="stylesheet" href="<%=basePath%>ras/common/css/fileinput/fileinput.css" />
			<link href="<%=basePath%>ras/common/css/fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
		</plugin_css>
		<plugin_js>
			
			<!-- bootbox.js 提示框 -->
			<script src="<%=basePath%>ras/common/js/bootbox.js"></script>
		
			<!-- dataTable.js -->
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
			<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
			<!-- 配置后,需设置 "language":dataTables_zh  -->
			<script src="<%=basePath%>ras/common/js/dataTables/i18n/dataTables_zh.lang"></script>
			
			<!-- 图片放大查看 -->
			<script src="<%=basePath%>ras/common/js/jquery.colorbox.js"></script>
			
			<!-- JS编辑器 
			<script src="<%=basePath%>ras/common/js/jquery.hotkeys.js"></script>
			<script src="<%=basePath%>ras/common/js/bootstrap-wysiwyg.js"></script>-->

			<!-- 文件上传 -->
			<script src="<%=basePath%>ras/common/js/fileinput/fileinput.js"></script>
			
			<script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>

			<script src="<%=basePath%>ras/common/js/jqueryvalidation/jquery.validate.js"></script>
			<script src="<%=basePath%>ras/common/js/jqueryvalidation/messages_zh.js"></script>

		</plugin_js>
<script type="text/javascript">
$(function(){
	/**
	美化select控件
	*/
	$('.chosen-select').chosen({
		allow_single_deselect : true,
		width : '100%',
		disable_search:true //关闭搜索框,默认为false.
	}); 
	
	/**
	上传控件
	*/
	$("#photoFile").fileinput({
		uploadUrl : './uppicturefile', //上传的地址
 		showUpload : false,
		showRemove : false, 
		showCancel:false,
		showPreview:false,
		previewTemplates:"",
		//hideThumbnailContent: true,
		//uploadAsync: true,
		browseClass:'btn btn-sm btn-primary',
		allowedFileExtensions: ["jpg", "gif", "png", "tif","bmp"],
		uploadExtraData: function(previewId, index) {   //额外参数的关键点
                  var obj = {};
                  obj.overviewID = $(':hidden[name=overviewID]').val();
                  obj.modelName=$(':hidden[name=modelName]').val();
                  obj.photoCategory=$('#photoCategory').val();
                  obj.photoName=$('#photoName').val();
                  obj.photoDesc=$('#photoDesc').val();
                  obj.tag=$('#photoTag').val();
                  return obj;
              }
	});
	
	$("#photoFile").on("fileuploaded",function(){
		tablemodel.ajax.reload();
		$('#modal-picture').modal('hide');
		$('#modal-picture :text').val("");
		$("#photoFile").fileinput("clear").fileinput('enable');
		
	});
	
	$("#pic-confirmBtn").on('click',function(){
		$("#photoFile").fileinput('upload');
	});
	
	
	/////////////////////////////////////////////////////////////
	
	/**
	整体图,外观图,三面图按钮
	*/
	$(":radio[name='pic-radio']").on('click',function(){
		//tablemodel.settings()[0].ajax.data={photoCategory:$(this).val()};
		tablemodel.ajax.reload();
	})
	
	/**
	上传按钮
	*/
	$("#upPicture").on('click',function(){
		$('#modal-picture').modal();
		$('#modal-picture .modal-title').text("上传图片");
		$('#photoCategory').val($(':checked[name=pic-radio]').val()).trigger("chosen:updated");
	})
	
	
	/**
	删除按钮
	*/
	$("#delPicture").on('click',function(){
		var arr=tablemodel.rows('.selected').data();
		if(arr.length==0){
			bootbox.alert("请选择数据");
		}else{
			bootbox.confirm("是否删除当前数行?",function(result){
				if(result){
					var photoID=[]
					for(var i=0;i<arr.length;i++){
						photoID.push(arr[i].photoID);
					}
					$.ajax({
						url:'./delpicturefile',
						data:{
							photoID : photoID.toString(),
							modelName:$(':hidden[name=modelName]').val()
						},
						success:function(){
							tablemodel.ajax.reload();
						}
					})
				}
			})
		}
		
	});
	
	/**
	查询按钮
	*/
	$("#picSearchBtn").on('click',function(){
		tablemodel.ajax.reload();
	})
	
	// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
	var tablemodel = $('#pictureTable')
	// .wrap("<div class='dataTables_borderWrap' />") 
	.DataTable({
		// "paging": false, //是否开启本地分页
		lengthChange: false, //是否隐藏每页显示数量框(css).true,默认不隐藏;false,隐藏.
		//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]], //定义每页显示数据数量
		"ordering" : false, // 是否允许Datatables开启排序
		// "info": false, //控制是否显示表格左下角的信息
		"searching" : false, // 开启、关闭Datatables的搜索功能
		bAutoWidth : false,
		//"destroy": true,
		// processing: true,//"<img src='./loading.gif' />"
		serverSide : true, // 开启服务器模式
		ajax : {
			url : "./findpicturegrid",
			data:{
				overviewID:function(){return $(":hidden[name=overviewID]").val()},
				photoCategory:function(){return $(':radio[name="pic-radio"]:checked').val()},
				modelName:function(){return $(':hidden[name=modelName]').val()},
				tag:function(){return $("#picTagTxt").val()}
			}
		},
		//deferLoading:0, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
		"columns" : [{
					class : "center",
					width:70,
					render : function(data, type, full, meta) {
						return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
					}
					//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
					
				},{
					"title" : "图片名称", 
					data:'photoName',
					render : function(data, type, row, meta) {
						//个人理解  --以及参数的应用场景
					    //data:当前cell的值  --主要是操作这个参数来做渲染
					    //type:数据类型,枚举类型dt内置定义的  --用处不大
					    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
					    //meta:它下面有三个参数
					    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
					    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
					    //return '<a href="./downloadarchivefile?archiveID='+row.photoID+'">'+data+'</a>'
						return data;
					}
				}, {
					"title" : "图片简介", 
					data:'photoDesc'
				},{
					"title" : "类别", 
					data:'photoCategory'
				}, {
					"title" : "标签", 
					data:'tag'
				}, {
					"title" : "图片", 
					data:'photoUrl',
					width:200,
					render : function(data, type, row, meta) {
						//个人理解  --以及参数的应用场景
					    //data:当前cell的值  --主要是操作这个参数来做渲染
					    //type:数据类型,枚举类型dt内置定义的  --用处不大
					    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
					    //meta:它下面有三个参数
					    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
					    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
					    return '<div class="ace-thumbnails"><a href="'+data+'" data-rel="colorbox">'+
							'<img width="150" height="150" alt="" src="'+data+'" /></a></div>'
					    //return '<a href="./downloadarchivefile?archiveID='+row.photoID+'">'+data+'</a>'
					}
				}, {
					"title" : "权限级别", 
					data:'permissionLevel',
					width:160,
					render:function(data, type, row, meta){
						if(data==""||data=='1'){
							return "个人可视";
						}else if(data=='2'){
							return "部门可视";
						}else if(data=="3"){
							return "所内可视";
						}
					}
				}, {
					"title" : "编辑人", 
					data:'editor',
					width:160
				}],
			"language" : dataTables_zh, // 多语言配置
			//重绘完成之后调用.Function that is called every time DataTables performs a draw.
			 "drawCallback": function( settings ) {
			        $('.ace-thumbnails [data-rel="colorbox"]').colorbox({
						//rel: 'colorbox',//群组必须属性.
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
							document.body.style.overflow = 'auto';//auto
						},
						onComplete:function(){
							$.colorbox.resize();
						}/*,
					    onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
					    onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },*/
					});
			    }
		})
	
	//2.多选
	var curSelectData={};
	$('#pictureTable tbody').on('click', 'tr', function () {
	    var curCheckbox=$(this).find(':checkbox')[0];
	    if(curCheckbox==null){
		 	return;
		 }
	    
	    //curCheckbox.checked=!curCheckbox.checked;
	    tablemodel.$(':checked').attr('checked',false);//清除所有的选中框.
	    if ($(this).hasClass('selected')) {
	        $(this).removeClass('selected');
	        curCheckbox.checked=false;
	     } else {
	    	tablemodel.$('tr.selected').removeClass('selected');
	        $(this).addClass('selected');
	        curCheckbox.checked=true;
	        
	        curSelectData=tablemodel.rows(this).data()[0]
		    $("#pictureTool").children().removeAttr('disabled');
			//需要数据管理员权限才能修改,删除和送审
	    	if(!algz.curUser.isDataManager&&curSelectData.editor!=algz.curUser.username){
	    		//不是数据管理员
	    		$("#upPicture").attr('disabled',"true");
	    		$("#delPicture").attr('disabled',"true");
	    		$("#submitApprovalBtn").attr('disabled',"true");
	    	}
	        
	     }
	    //$(this).toggleClass('selected',curCheckbox.checked);
	    
	    
	});
	
	/**
	 * 送审
	 */
	$("#submitApprovalBtn").on('click',function(){
		if(tablemodel.row('.selected').length==0){
			bootbox.alert("请选择一条数据!")
			return ;
		}else{
			bootbox.confirm("是否送审?",function(result){
				if(result){
					approvalModal.show({
						dataID:curSelectData.photoID
					});
					approvalModal.complete=function(data){
						alert(data.responseText);
					}
				}
			
			});
		}

	})
	
})

</script>			

	</body>
</html>
