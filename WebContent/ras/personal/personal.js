$(function() {
	
	bootbox.setDefaults("locale","zh_CN");
	
	$('#modifyReport').on('click',function(){
	
	})
					
					
	$('#delReport').on('click',function(){
			bootbox.confirm("是否删除当前报告?",function(result){
				if(result){
					$.ajax({
						url:'',
						data:{
							
						},
						success:function(data){
							
						}
					})
				}
			})
	})
	
	var reportTable = $('#report-table')
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
				url : "./report/findpersonalreportgrid"
			},
			//deferLoading:0, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
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
						width:70,
						render : function(data, type, full, meta) {
							return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
						}
					},{
						"title" : "报告名称", 
						data:'reportName',
						sorting : false,
						render: function(data, type, row, meta){
						   	return "<a target='_blank' href='./report/?reportID="+row.reportID+"'>"+data+"</a>";
					}
					}, {
						"title" : "创建日期", 
						data:'modifyDate',
						sorting : false,
						render:function(data, type, row, meta){
									    if(data==null){
									    	return "";
									    }
									    var d=new Date(data.time); //new Date().setTime(data.time);
									    return d.getFullYear()+"-"+d.getMonth()+"-"+d.getDate();
									}
					}],
			"language" : dataTables_zh // 多语言配置
			});
	
			$('#submitBtn').click(function() {
						dataTable.api().ajax.reload(); //必须用   
//						dataTable.api().ajax.reload(function(json) {
//							// 这里的json返回的是服务器的数据
//							alert(json)
//								// $('#myInput').val( json.lastInput );
//							});
					})

		////////////////////////////////
	
	$('#modifyFavorites').on('click',function(){
		$.ajax({
			url:'',
			data:{
				
			},
			success:function(data){
				
			}
		})
	})
					
					
	$('#delFavorites').on('click',function(){
		
//			bootbox.alert("请选择筛选条件!");
			
			bootbox.confirm("是否删除当前收藏夹内容?",function(result){
				if(result){
					$.ajax({
						url:'./favorites/delfavorites?d='+new Date(),
						data:{
							url:decodeURIComponent(favoritesTable.rows(this).data()[0].url)
						},
						success:function(data){
							if(data.success){
								favoritesTable.ajax.reload();
							}
						}
					})
				}
			})
	})
	
					
	var favoritesTable = $('#favoretes-table')
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
				url : "./favorites/findfavoritesgrid"
			},
			//deferLoading:0, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
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
						width:70,
						render : function(data, type, full, meta) {
							return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
						},
						//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
						sorting : false
					},{
						"title" : "名称", 
						data:'favoritesName',
						sorting : false
					},{
						"title" : "url", 
						data:'url',
						sorting : false,
						render: function(data, type, row, meta){
							var url=decodeURIComponent(data);
						   	return "<a target='_blank' href='"+url+"'>"+url+"</a>";
						}
					}, {
						"title" : "创建日期", 
						data:'modifyDate',
						sorting : false,
						render:function(data, type, row, meta){
									    if(data==null){
									    	return "";
									    }
									    var d=new Date(data.time); //new Date().setTime(data.time);
									    return d.getFullYear()+"-"+d.getMonth()+"-"+d.getDate();
									}
					}],
			"language" : dataTables_zh // 多语言配置
			});
			
			
	////////////////////////////////////
			
//1.单选.首先获取datatable对象(注意大小写`DataTable()`)：所有 table 有效,需放在所有 dataTable 后面
$('table tbody').on('click', 'tr', function () {
	/* if ($(this).hasClass('selected') ) {
       $(this).removeClass('selected');
    } else {
       $(this).siblings(".selected").removeClass('selected');
       $(this).addClass('selected');
    } */
	
	var curCheckbox=$(this).find(':checkbox')[0];
	var curCheckVal=curCheckbox.checked
	$(this).closest('table').find(':checkbox').attr('checked',false); //清除所有的选中框
    if(curCheckbox==null){
	 	return;
	 }
    curCheckbox.checked=!curCheckVal;
    
	$(this).siblings(".selected").removeClass('selected'); //.siblings() 筛选所有同辈元素
	$(this).toggleClass('selected');
})
			
		})