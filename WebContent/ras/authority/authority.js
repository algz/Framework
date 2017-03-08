$(function() {

			$('#submitBtn').click(function() {
						dataTable.api().ajax.reload(); //必须用   
//						dataTable.api().ajax.reload(function(json) {
//							// 这里的json返回的是服务器的数据
//							alert(json)
//								// $('#myInput').val( json.lastInput );
//							});
					})

			// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
			var dataTable = $('#table-authority')
					// .wrap("<div class='dataTables_borderWrap' />") 
					// if you are applying horizontal scrolling (sScrollX)
					.dataTable({
						//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]], //定义每页显示数据数量
						// "paging": false, //是否开启本地分页
						// "lengthChange": false,
						"ordering" : false, // 是否允许Datatables开启排序
						// "info": false, //控制是否显示表格左下角的信息
						"searching" : false, // 开启、关闭Datatables的搜索功能
						bAutoWidth : false,
						// processing: true,//"<img src='./loading.gif' />"
						serverSide : true, // 开启服务器模式
						ajax : {
							url : "./SearchCriteriaGird",
							type : 'POST'
						},
						deferLoading:0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
						"columns" : [{
									//"title" : "用户名",/* data:'model', */
									sorting : false
								},{
									"title" : "用户名",/* data:'model', */
									sorting : false
								},{
									"title" : "密码",/* data:'name', */
									sorting : false,
									render:function(data, type, row, meta){
									    //个人理解  --以及参数的应用场景
									    //data:当前cell的值  --主要是操作这个参数来做渲染
									    //type:数据类型,枚举类型dt内置定义的  --用处不大
									    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
									    //meta:它下面有三个参数
									    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
									    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
									    return "<a href='./searchSummarize'>"+data+"</a>";
									}
								}, {
									"title" : "部门",/* data:'model', */
									sorting : false
								}, {
									"title" : "单位",/* data:'model', */
									sorting : false
								}, {
									"title" : "权限",/* data:'model', */
									sorting : false
								}],
						"language" : {
							"lengthMenu" : "每页显示 _MENU_ 条记录",
							"zeroRecords" : "对不起，查询不到任何相关数据。",
							"info" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
							"infoEmpty" : "找不到相关数据",
							"infoFiltered" : "(从 _MAX_ 条数据中检索)",
							"processing" : "正在加载中...",
							"search" : "搜索",
							"url" : "", // 多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
							"paginate" : {
								"first" : "第一页",
								"previous" : " 上一页 ",
								"next" : " 下一页 ",
								"last" : " 最后一页 "
							}
						} // 多语言配置

							// ,
							// "sScrollY": "200px",
							// "bPaginate": false,

							// "sScrollX": "100%",
							// "sScrollXInner": "120%",
							// "bScrollCollapse": true,
							// Note: if you are applying horizontal scrolling
							// (sScrollX) on a ".table-bordered"
							// you may want to wrap the table inside a
							// "div.dataTables_borderWrap" element

							// "iDisplayLength": 50
						});

		})