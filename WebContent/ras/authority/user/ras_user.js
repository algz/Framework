$(function() {

	
			
			$('#submitBtn').click(function() {
						userTable.api().ajax.reload(); //必须用   
//						dataTable.api().ajax.reload(function(json) {
//							// 这里的json返回的是服务器的数据
//							alert(json)
//								// $('#myInput').val( json.lastInput );
//							});
					})

			// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
			var userTable = $('#table-user')
					// .wrap("<div class='dataTables_borderWrap' />") 
					// if you are applying horizontal scrolling (sScrollX)
					.DataTable({
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
							url : "./findusergrid",
							type : 'POST'
						},
//						deferLoading:0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
						"columns" : [{
//									"title" : "用户名",
									width:50,
									class : "center",
									render:function(data, type, row, meta){
									    //个人理解  --以及参数的应用场景
									    //data:当前cell的值  --主要是操作这个参数来做渲染
									    //type:数据类型,枚举类型dt内置定义的  --用处不大
									    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
									    //meta:它下面有三个参数
									    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
									    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
									    return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
									}
								},{
									"title" : "用户名", 
									data:'username', 
									sorting : false
								},{
									"title" : "密码", 
									data:'password', 
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
								},{
									"title" : "中文名称", 
									data:'cname', 
									sorting : false
								}, {
									"title" : "部门",
									data:'department'
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
						}
					});

			//1.单选.首先获取datatable对象(注意大小写`DataTable()`)：
			userTable.on('click', 'tbody tr', function () {
				$(this).siblings(".selected").find(":checked").removeAttr("checked"); //.siblings() 筛选所有同辈元素
				$(this).siblings(".selected").removeClass('selected');
				
				var curCheckbox=$(this).find(':checkbox')[0];
			    if(curCheckbox==null){
				 	return;
				}
			    curCheckbox.checked=!curCheckbox.checked;
			    $(this).toggleClass('selected',curCheckbox.checked);
			    
			    var userid="-1";
			    if(curCheckbox.checked){
			    	userid= userTable.row(this).data().userid;
			    }
			    roleTable.settings()[0].ajax.data={"userid":userid};
				roleTable.ajax.reload();
			})

			////////////////////////////////////
						
			/**
			 * 角色管理
			 */
			var roleTable = $('#table-role')
					// .wrap("<div class='dataTables_borderWrap' />") 
					// if you are applying horizontal scrolling (sScrollX)
					.DataTable({
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
							url : "../role/findrolegrid",
							type : 'POST'
						},
//						deferLoading:0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
						"columns" : [{
//									"title" : "用户名",
									width:50,
									data:'checkbox',
									class : "center",
									render:function(data, type, row, meta){
									    //个人理解  --以及参数的应用场景
									    //data:当前cell的值  --主要是操作这个参数来做渲染
									    //type:数据类型,枚举类型dt内置定义的  --用处不大
									    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
									    //meta:它下面有三个参数
									    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
									    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
									    return '<label class="position-relative"><input type="checkbox" '+(data=="1"?"checked":"")+' class="ace" /><span class="lbl"></span></label>'
									}
								},{
									"title" : "角色名称", 
									data:'rolename', 
									sorting : false
								}, {
									"title" : "角色描述",
									data:'description', 
									width:160,
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
						} 
					});
					
					
			//1.多选.首先获取datatable对象(注意大小写`DataTable()`)：
			roleTable.on('click', 'tbody tr', function () {
				//$(this).siblings(".selected").find(":checked").removeAttr("checked"); //.siblings() 筛选所有同辈元素
				//$(this).siblings(".selected").removeClass('selected');
				
				if(userTable.rows('.selected').data().length==0){
					roleTable.$(':checkbox').attr("disabled","disabled");
					return;
				}
				
				var curCheckbox=$(this).find(':checkbox')[0];
			    if(curCheckbox==null){
				 	return;
				}
			    curCheckbox.checked=!curCheckbox.checked;
			    $(this).toggleClass('selected',curCheckbox.checked);
			    
			    var operate;
			    var roleid=roleTable.row(this).data().roleid;
			    var userid=roleTable.settings()[0].ajax.data.userid
			    if(curCheckbox.checked){
			    	operate="add";
			    }else{
			    	operate="del";
			    }
				$.ajax({
				   type: "POST",
				   url: "./saveuserrole",
				   data: {
				   	 userid:userid,
				     roleid:roleid,
				     operate:operate
				   },
				   success: function(msg){
				     roleTable.ajax.reload();
				   }
				})
			})
					
			
		})
		
		
