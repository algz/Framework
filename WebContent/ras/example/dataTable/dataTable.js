/**
 * html:
 * <table id="table" class="table table-striped table-bordered table-hover"></table>

 <plugin_js>
 	<!-- dataTables -->
	<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.js"></script>
	<script src="<%=basePath%>ras/common/js/dataTables/jquery.dataTables.bootstrap.js"></script>
	<!-- 配置后,需设置 "language":dataTables_zh  -->
	<script src="<%=basePath%>ras/common/js/dataTables/i18n/dataTables_zh.lang"></script>
</plugin_js>
 */
// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
var dataTable = $('#table-dataTable')
		// .wrap("<div class='dataTables_borderWrap' />") 
		.DataTable({
			/*//初始化完成调用.
			initComplete:function(){
				//......
			},
			//重绘完成之后调用.Function that is called every time DataTables performs a draw.
			"drawCallback": function( settings ) {
			        //......
			},*/
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
				url : "./findtablemodelgrid"
			},
			//deferLoading:1, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
			"columns" : [{
					class : "center",
					title : "序号", 
					width:70,
					data:null,
					render: function(data, type, row, meta){
						    //个人理解  --以及参数的应用场景
						    //data:当前cell的值  --主要是操作这个参数来做渲染
						    //type:数据类型,枚举类型dt内置定义的  --用处不大
						    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
						    //meta:它下面有三个参数
						    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
						    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息

						　　var startIndex= meta.settings._iDisplayStart;//获取到本页开始的条数
							return startIndex+meta.row+1;
					}
				},{
						class : "center",
						render : function(data, type, full, meta) {
							return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
						},
						//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
						sorting : false
					},{
						"title" : "机型名", 
						data:'modelName',
						sorting : false
					}, {
						"title" : "中文名", 
						data:'modelCname',
						sorting : false
					}, {
						"title" : "英文名",
						data:'modelEname',
						sorting : false
					}],
			"language" : dataTables_zh // 多语言配置
			});