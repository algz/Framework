$(function() {

	//设置为中文
	bootbox.setDefaults("locale","zh_CN"); 
	
	//添加角色
	$('#addRoleBtn').on('click',function(){
		$('#modal-Role form input').val('')
		$('#modal-Role').modal();
	})
	
	//修改角色
	$('#modifyRoleBtn').on('click',function(){
		var datas=roleTable.rows('.selected').data();
		if(datas.length>0){
			$('#roleid').val(datas[0].roleid); 
			$('#rolename').val(datas[0].rolename); 
			$('#rolecname').val(datas[0].rolecname);
			$('#description').val(datas[0].description);
			$('#modal-Role').modal();
		}
		
	})
	
	//删除角色
	$('#delRoleBtn').on('click',function(){
		var datas=roleTable.rows('.selected').data();
		if(datas.length>0){
			bootbox.confirm("是否删除当前数行?",function(result){
				if(result){
					$.ajax({
						url:'../role/delrole',
						data:{
							roleid:datas[0].roleid
						},
						success:function(){
							roleTable.ajax.reload();
							bootbox.alert("删除成功!");
						}
					})
				}
			})
			
		}
	})
	
	//角色管理弹窗-确定按钮
	$('#role-confirmBtn').on('click',function(){
		var rolename=$('#rolename').val();
		if(rolename!=""){
			$.ajax({
				url:'../role/saverole',
				data:{
					roleid:$('#roleid').val(),
					rolename:rolename, 
					rolecname:$('#rolecname').val(), 
					description:$('#description').val()
				},
				success:function(){
					roleTable.ajax.reload();
					$('#modal-Role').modal('hide');
					bootbox.alert("保存成功!");
				}
			})
		}
	})
	

	/** 角色管理
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
							"title" : "角色名称", 
							data:'rolename', 
							sorting : false
						},{
							"title" : "角色中文名称", 
							data:'rolecname', 
							sorting : false
						},{
							"title" : "角色描述",
							data:'description', 
							width:160,
							sorting : false
						},{
							"title" : "是否系统",
							data:'rolecategory', 
							width:160,
							render:function(data, type, row, meta){
							    //个人理解  --以及参数的应用场景
							    //data:当前cell的值  --主要是操作这个参数来做渲染
							    //type:数据类型,枚举类型dt内置定义的  --用处不大
							    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
							    //meta:它下面有三个参数
							    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
							    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
							    if(data=='0'){
							    	return '否';
							    }else if(data=='1'){
							    	return '是';
							    }else{
							    	return data;
							    }
							    
							}
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
	roleTable.on('click', 'tbody tr', function () {
		$(this).siblings(".selected").find(":checked").removeAttr("checked"); //.siblings() 筛选所有同辈元素
		$(this).siblings(".selected").removeClass('selected');
		
		var curCheckbox=$(this).find(':checkbox')[0];
	    if(curCheckbox==null){
		 	return;
		}
	    curCheckbox.checked=!curCheckbox.checked;
	    $(this).toggleClass('selected',curCheckbox.checked);
	    
	    var roleid=null;
	    if(curCheckbox.checked){
	    	roleid= roleTable.row(this).data().roleid;
//					resourceTable.$(':checkbox').removeAttr("disabled");
	    }
	    resourceTable.settings()[0].ajax.data={"roleid":roleid};
		resourceTable.ajax.reload();
	})
	
//////////////////////////////////////////////

//			$('#submitBtn').click(function() {
//						resourceTable.api().ajax.reload(); //必须用   
////						resourceTable.api().ajax.reload(function(json) {
////							// 这里的json返回的是服务器的数据
////							alert(json)
////								// $('#myInput').val( json.lastInput );
////							});
//					})

	// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
	var resourceTable = $('#table-resource')
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
					url : "./findresourcegrid",
					type : 'POST'
				},
//						deferLoading:0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
				"columns" : [{
							class:'center',
							data:'checkbox',
							width:50,
							render:function(data, type, row, meta){
								if(row.ispublic!="1"){
									return '<label class="position-relative"><input type="checkbox" '+(data=="1"?"checked":"")+' class="ace" /><span class="lbl"></span></label>'
								}
								return "";
							}
						},{
							"title" : "资源名称", 
							data:'name', 
							sorting : false,
							render:function(data, type, row, meta){
								return data==null?"":"<a href='#"+row.id+"'>"+data+"</a>";
							}
						},{
							"title" : "ICON", 
							data:'icon', 
							sorting : false,
							render:function(data, type, row, meta){
							    //个人理解  --以及参数的应用场景
							    //data:当前cell的值  --主要是操作这个参数来做渲染
							    //type:数据类型,枚举类型dt内置定义的  --用处不大
							    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
							    //meta:它下面有三个参数
							    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
							    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
							    return data==null?"":"<i class='menu-icon fa "+data+"'/> "+data+"";
							}
						}, {
							"title" : "URL",
							data:'url', 
							sorting : false,
							render:function(data, type, row, meta){
								return data==null?"":"<a href='../../.."+data+"' target='_blank'>"+data+"</a>";
							}
						}, {
							"title" : "是否公共",
							data:'ispublic', 
							class:'center',
							sorting : false,
							render:function(data, type, row, meta){
								return data=="1"?"是":"否";
							}
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
	//resourceTable
	$('#table-resource tbody').on('click', 'tr', function () {
		if(roleTable.rows('.selected').data().length==0){
			resourceTable.$(':checkbox').attr("disabled","disabled");
			return;
		}
		
		var curCheckbox=$(this).find(':checkbox')[0];
	    if(curCheckbox==null){
		 	return;
		}
	    curCheckbox.checked=!curCheckbox.checked;
	    $(this).toggleClass('selected',curCheckbox.checked);
	    
	    var operate;
	    var resourceid=resourceTable.row(this).data().id;
	    var roleid=resourceTable.settings()[0].ajax.data.roleid
	    if(curCheckbox.checked){
	    	operate="add";
	    }else{
	    	operate="del";
	    }
		$.ajax({
		   type: "POST",
		   url: "./saveroleresource",
		   data: {
		   	 resourceid:resourceid,
		     roleid:roleid,
		     operate:operate
		   },
		   success: function(msg){
		     resourceTable.ajax.reload();
		   }
		})
	})

	$('#table-resource tbody').on('click', 'tr a', function () {
		var data = $('#table-resource').DataTable().row($(this).parents('tr')).data();
		alert("查看修改："+data.id +","+ data.id );
		
		if(roleTable.rows('.selected').data().length==0){
			resourceTable.$(':checkbox').attr("disabled","disabled");
			return;
		}
		
		var curCheckbox=$(this).find(':checkbox')[0];
	    if(curCheckbox==null){
		 	return;
		}
	    curCheckbox.checked=!curCheckbox.checked;
	    $(this).toggleClass('selected',curCheckbox.checked);
	    
	    var operate;
	    var resourceid=resourceTable.row(this).data().id;
	    var roleid=resourceTable.settings()[0].ajax.data.roleid
	    if(curCheckbox.checked){
	    	operate="add";
	    }else{
	    	operate="del";
	    }
		$.ajax({
		   type: "POST",
		   url: "./saveroleresource",
		   data: {
		   	 resourceid:resourceid,
		     roleid:roleid,
		     operate:operate
		   },
		   success: function(msg){
		     resourceTable.ajax.reload();
		   }
		})
	})

	
	/** 隐藏公共资源
	 */
	$("#hidePublicResourceBtn").click(function(){
		//checked="checked"
		if($(this).attr("checked")==null){
			$(this).attr("checked","checked")
			resourceTable.settings()[0].ajax.data={'isPublic':'0'}
		}else{
			$(this).removeAttr("checked")
			resourceTable.settings()[0].ajax.data={'isPublic':'1'}
		}
		resourceTable.ajax.reload();
	})
	
	$('#addResourceBtn').on('click',function(){
		$('#modal-Resource').modal();
	})
	
	
})