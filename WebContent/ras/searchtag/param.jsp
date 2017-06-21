<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<table id="paramTable" class="table table-striped table-bordered table-hover">
</table>
<script type="text/javascript">
$(function(){
	// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
	var paramTable = $('#paramTable')
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
				tagCategory:'param'
			}
		},
		deferLoading:0, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
		"columns" : [{
					class : "center",
					title : "序号", 
					width:70,
					//data:null,
					render: function(data, type, row, meta){
						　　var startIndex= meta.settings._iDisplayStart;//获取到本页开始的条数
							return startIndex+meta.row+1;
					}
				},{
					"title" : "机型名", 
					data:'modelName'
				}, {
					"title" : "中文名", 
					data:'modelCname'
				}, {
					"title" : "英文名",
					data:'modelEname'
				}, {
					"title" : "数据来源",
					data:'dataSources',
					render:function(data,type,row,meta){
						return "<a href='../searchparam/searchsummarize?overviewID="+row.overviewID+"&basicID="+row.basicID+"&option=load'>"+data+"</a>";   
					}
				},{
					"title" : "标签",
					data:'tag'
				}],
		"language" : dataTables_zh // 多语言配置
		});
})
</script>