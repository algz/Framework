$(function() {
	$("#modelSearch").on('click',function(){
		var modelName=$("#modelNameTxt").val();
		if(modelName){
			tablemodel.settings()[0].ajax.data={modelName:modelName};
			tablemodel.ajax.reload();
		}
	})
	
			// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
			var tablemodel = $('#table-model')
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
							url : "./findtablemodelgrid"
						},
						//deferLoading:1, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
						"columns" : [{
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
						});

						
						// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
			var tablemodelparam = $('#table-modelparam')
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
						//processing: true,//"<img src='./loading.gif' />"
						serverSide : true, // 开启服务器模式
						//"destroy": true,
						ajax : {
							url : "./findtablemodelparamgrid",
							type: "POST"
						},
						deferLoading:0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
						"columns" : [{
									class : "center",
									render : function(data, type, full, meta) {
										return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
									},
									//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
									sorting : false
								},{
									"title" : "数据来源",
									data:'dataSources', 
									sorting : false,
									render:function(data, type, row, meta){
									    //个人理解  --以及参数的应用场景
									    //data:当前cell的值  --主要是操作这个参数来做渲染
									    //type:数据类型,枚举类型dt内置定义的  --用处不大
									    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
									    //meta:它下面有三个参数
									    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
									    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
									    return data;
									}
								}, {
									"title" : "编辑日期",
									data:'modifyDate',
//									type:'date',
									sorting : false,
									render:function(data, type, row, meta){
									    if(data==null){
									    	return "";
									    }
									    var d=new Date(data.time); //new Date().setTime(data.time);
									    return d.getFullYear()+"-"+d.getMonth()+"-"+d.getDate();
									}
								}, {
									"title" : "编辑人", 
									data:'editor', 
									sorting : false
								}, {
									"title" : "主要信息",
									data:'mainInfo',
//									type:'date',
									sorting : false,
									render:function(data, type, row, meta){
									    if(data=="1"){
									    	return "主要";
									    }else if(data==""){
									    	return "";
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
						} // 多语言配置
						});
		
			/**
			 * 机型Grid行选
			 */
			var selectModelRowData=null;
			$('#table-model tbody').on('click', 'tr', function () {
				var curCheckbox=$(this).find(':checkbox')[0];
				if(curCheckbox==null){
				 	return;
				 }
				 
				 	/**
					 * dataTable 的多选通过<tr>标签 class="selected" 属性是否存在来判断
					 * row对象 : table.row('.selected').data();  
					 * 返回数组: table.rows('.selected').data();
					 */
//					 $(this).toggleClass('selected');
				 
				if(!curCheckbox.checked){
					//选中
					tablemodel.$(':checked').attr('checked',false);//清除所有的选中框.
					$('#table-model tr').removeClass('selected'); //清除grid所有的选择项.
					$(this).addClass('selected');
					
//					curCheckbox.checked=true;
					selectModelRowData=tablemodel.rows(this).data()[0];
					tablemodelparam.settings()[0].ajax.data={overviewID:selectModelRowData.overviewID};
					tablemodelparam.ajax.reload();
				}else{
					//取消
					$(this).removeClass('selected');
					tablemodelparam.settings()[0].ajax.data={overviewID:-1};
					tablemodelparam.ajax.reload();
//		        	tablemodelparam.clear();
//		        	tablemodelparam.rows(0).clear();
	//		        	tablemodelparam.rows(0).remove().draw(false);
//					curCheckbox.checked=false
				}
				curCheckbox.checked=!curCheckbox.checked;
		    });
		    
		    /**
			 * 修改机型
			 */
			$("#modifyModel").click(function(){
				if(tablemodel.row(".selected").length==0){
					bootbox.alert("请选择机型!");
				}else{
					var s="addmodel?overviewID="+tablemodel.row(".selected").data().overviewID; 
					window.location.href=s
//					bootbox.dialog({
//						//onload="this.height =   document.frames["ifrname"].document.body.scrollHeight" id="ifrid"
//						message:'<iframe src="'+s+'" frameborder=no height=100% width=100%></iframe>'
//					})
				}
			})
			
			/**
			 * 删除机型
			 */
			$("#delModel").click(function(){
				if(tablemodel.row(".selected").length==0){
					bootbox.alert({
						message:"请选择机型后在删除!",
						buttons: 			
						{
							"ok" :
							 {
								"label" : "确定",
								"className" : "btn-sm ",
								"callback": function() {
									//Example.show("great success");
								}
							}
						}
						});
				}else{
					bootbox.confirm("是否删除当前数行?",function(result){
						if(result){
							$.ajax({
								type:"POST",
								url:"./delmodel",
								data:{
									overviewID:tablemodel.row(".selected").data().overviewID
								},
								success:function(msg){
									bootbox.alert(msg);
									tablemodel.ajax.reload();
									selectModelParamRowData=null;
									tablemodelparam.ajax.reload();
									
								}
							})
						}
					})

					//window.location.href="addmodelparampage?overviewID="+selectModelRowData.overviewID+"&basicID="+selectModelParamRowData.basicID; 
				}
			})
			
			
		    
			////////////////////////////////////////////////////
			
			/**
		     * 机型参数Grid行选 单选
		     */
		    var selectModelParamRowData=null;
			$('#table-modelparam tbody').on('click', 'tr', function () {
				 var curCheckbox=$(this).find(":checkbox")[0];
				 if(curCheckbox==null){
				 	return;
				 }
				 
				 //table.$(':checked').attr('checked',false); //清除所有的选中框.
				 $('#table-modelparam tr').removeClass('selected'); //清除grid所有的选择项.
				 //$(this).toggleClass('selected');
				 
		        if (curCheckbox.checked ) {
		        	//取消
		        	curCheckbox.checked=false;
		        	$(this).removeClass('selected');
		        }
		        else {
		        	//选中
		            //table.$('tr.selected').removeClass('selected');
		        	tablemodelparam.$(':checked').attr('checked',false);
		        	curCheckbox.checked=true;
		        	selectModelParamRowData=tablemodelparam.rows(this).data()[0];
		        	$(this).addClass('selected');
		        }
		    });
		    
			
			/**
			 * 新增机型参数
			 */
			$("#addModelparam").click(function(){
				if(tablemodel.row(".selected").length==0){
					bootbox.alert("请选择机型后在添加参数!");
				}else{
//					if(tablemodelparam.data().length>=1){
//						bootbox.alert("系统暂时仅支持一条数源来源.");
//						return ;
//					}
					window.location.href="addmodelparampage?overviewID="+selectModelRowData.overviewID+"&option=create"//+window.location.href; 
				}
			})
			
			/**
			 * 修改机型参数
			 */
			$("#modifyModelparam").click(function(){
				if(tablemodelparam.row('.selected').length==0){
					bootbox.alert("请选择机型参数后在修改参数!");
				}else{
					window.location.href="addmodelparampage?overviewID="+tablemodel.row(".selected").data().overviewID+
					"&basicID="+tablemodelparam.row('.selected').data().basicID+"&option=modify"; 
				}
			})
			
			$("#delModelparam").click(function(){
				if(tablemodelparam.row('.selected').length==0){
					bootbox.alert({
						message:"请选择机型参数后在删除参数!",
						buttons: 			
						{
							"ok" :
							 {
								"label" : "确定",
								"className" : "btn-sm ",
								"callback": function() {
									//Example.show("great success");
								}
							}
						}
						});
				}else{
					bootbox.confirm("是否删除当前数行?",function(result){
						if(result){
							$.ajax({
								type:"POST",
								url:"./delmodelparam",
								data:{
									basicID:tablemodelparam.row('.selected').data().basicID,
									overviewID:tablemodel.row(".selected").data().overviewID
								},
								success:function(msg){
									bootbox.alert(msg);
									selectModelParamRowData=null;
									tablemodelparam.ajax.reload();
									
								}
							})
						}
					})

					//window.location.href="addmodelparampage?overviewID="+selectModelRowData.overviewID+"&basicID="+selectModelParamRowData.basicID; 
				}
			})
			
			/**
			 * 设置机型参数为主要
			 */
			$("#setMainModelparam").click(function(){
				if(tablemodelparam.row('.selected').length==0){
					bootbox.alert({
						message:"请选择机型参数!",
						buttons: 			
						{
							"ok" :
							 {
								"label" : "确定",
								"className" : "btn-sm ",
								"callback": function() {
									//Example.show("great success");
								}
							}
						}
						});
				}else{
					bootbox.confirm("是否设置当前机型参数为主要?",function(result){
						if(result){
							$.ajax({
								type:"POST",
								url:"./setMainModelparam",
								data:{
									overviewID:tablemodelparam.row('.selected').data().overviewID,
									basicID:tablemodelparam.row('.selected').data().basicID
								},
								success:function(msg){
									bootbox.alert(msg);
									tablemodelparam.ajax.reload();
								}
							})
						}
					})

					//window.location.href="addmodelparampage?overviewID="+selectModelRowData.overviewID+"&basicID="+selectModelParamRowData.basicID; 
				}
			})
			
			////////////////////////////////////////////
			
			//DataTable  checkbox 全选
//		    $('table th input:checkbox').on('click' , function(){
//					var that = this;
//					$(this).closest('table').find('tr > td:first-child input:checkbox')
//					.each(function(){
//						this.checked = that.checked;
//						$(this).closest('tr').toggleClass('selected');
//					});
//						
//			});
			
			
		})