<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<table id="pictureTable" class="table table-striped table-bordered table-hover">
</table>
<script type="text/javascript">
$(function(){
	
	// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
	var pictureTable = $('#pictureTable')
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
			url : "./findtagsearchgird",
			data:{
				tag:function(){return $('#tag').val()},
				tagCategory:'picture'
			}
		},
		deferLoading:0, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
		"columns" : [{
					class : "center",
					title : "序号", 
					width:70,
					data:null,
					render: function(data, type, row, meta){
						　　var startIndex= meta.settings._iDisplayStart;//获取到本页开始的条数
							return startIndex+meta.row+1;
					}
				},{
					"title" : "机型名称", 
					data:'MODELNAME'
				},{
					"title" : "图片名称", 
					data:'PHOTO_NAME'
				}, {
					"title" : "图片简介", 
					data:'PHOTO_DESC'
				},{
					"title" : "图片类别", 
					data:'PHOTO_CATEGORY'
				}, {
					"title" : "标签", 
					data:'TAG'
				}, {
					"title" : "图片", 
					data:'PHOTO_URL',
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
				}],
		"language" : dataTables_zh // 多语言配置
		});
	
	pictureTable.on('init.dt', function () {
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
    })
	 
})
</script>