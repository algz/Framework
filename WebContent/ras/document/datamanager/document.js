$(function() {
	//设置为中文
	bootbox.setDefaults("locale", "zh_CN");

	//子机型创建--查找所有主机型
	$('#subModelSelectBtn').on("chosen:ready", function() {
		$.ajax({
					url : "./findaircraftall",
					success : function(data) {
						var arr = eval(data);
						$.each(arr, function(i, obj) {
									$('#subModelSelectBtn')
											.append("<option value='"
													+ obj.overviewID + "'>"
													+ obj.modelName
													+ "</option>");
									$("#subModelSelectBtn")
											.trigger("chosen:updated"); //必须设置,让chosen更新
								})
					}
				})
	}).chosen({
		allow_single_deselect : true,
		width : '90%',
		placeholder_text_multiple : '请选择一项'
			//disable_search:true //关闭搜索框,默认为false.
		})

	$('#subModel-confirmBtn').on('click', function() {
				$.ajax({
							url : "./savesubmodel",
							data : {
								modelName : $('#subModelName').val(),
								parentID : $('#subModelSelectBtn').val()
							},
							success : function() {
								tablemodel.ajax.reload();
								$('#modal-subModel').modal('hide');
							}
						})
			})

	/**创建子机型
	 */
	$('#addSubModelBtn').on('click', function() {
		//		if(tablemodel.row('.selected').length==0){
		//			bootbox.alert("请选择一条机型数据!")
		//			return ;
		//		}else{
		$('#subModelName').val("");
		var val = "";
		if (tablemodel.row('.selected').length != 0) {
			val = tablemodel.row('.selected').data().overviewID
		}
		$('#subModelSelectBtn').val(val);
		$("#subModelSelectBtn").trigger("chosen:updated"); //必须设置,让chosen更新
		$('#modal-subModel').modal({
					keyboard : false
				})
			//		}
	})

	$('#modelNameTxt').typeahead(null, {
		source : function(query, process, aysprocess) {
			var objs = $.get("../../analyze/findmodelfortypeahead", {
						modelName : query
					}, function(data) {
						//       		var data=['name1','name2'];
						aysprocess(eval(data))
						//process(eval(data))
				});//['name1','name2']
		}
	});

	/** 机型参数管理
	 */
	$("#modelParamManagerBtn").on('click', function() {
		if (tablemodel.row('.selected').length == 0) {
			bootbox.alert("请选择一条数据!")
			return;
		} else {
			$('#modal-modelParam').modal({
				keyboard : false
			})
		}
	});
	
	/* 机型成品管理 */
	$("#modelProductBtn").on('click', function() {
		if (tablemodel.row('.selected').length == 0) {
			bootbox.alert("请选择一条数据!")
			return;
		} else {
			var data = tablemodel.row(".selected").data()
			window.open("./productData?overviewID=" + data.overviewID
					+ "&modelName=" + data.modelName);
		}
	});
	
	/** 送审
	 */
	$("#submitApprovalBtn").on('click', function() {
		if (tablemodel.row('.selected').length == 0) {
			bootbox.alert("请选择一条数据!")
			return;
		} else {
			bootbox.confirm("是否送审?", function(result) {
						if (result) {
							approvalModal.show({
										dataID : selectModelRowData.overviewID
									});
							approvalModal.complete = function(data) {
								tablemodel.ajax.reload();
								bootbox.alert(data.responseText);
							}
						}

					});
		}
	});

	/** 查询按钮
	 */
	$("#modelSearch").on('click', function() {
				tablemodel.settings()[0].ajax.data = {
					modelName : $("#modelNameTxt").val()
				};
				tablemodel.ajax.reload();
			})

	/** 图片管理
	 */
	$('#pictureManager').on('click', function() {
		if (tablemodel.row(".selected").length != 1) {
			bootbox.alert("请选择一个机型!");
		} else {
			var data = tablemodel.row(".selected").data()
			window.open("picturemanager?overviewID=" + data.overviewID
					+ "&modelName=" + data.modelName);
		}
	})

	/** 文档管理
	 */
	$('#archiveManager').on('click', function() {
		if (tablemodel.row(".selected").length != 1) {
			bootbox.alert("请选择一个机型!");
		} else {
			var data = tablemodel.row(".selected").data()
			window.open("archivemanager?overviewID=" + data.overviewID
					+ "&modelName=" + data.modelName);
		}
	})

	/* 机型Grid */
	var tablemodel = $('#table-model')
			// .wrap("<div class='dataTables_borderWrap' />") 
			.DataTable({
				// "paging": false, //是否开启本地分页
				lengthChange : false, //是否隐藏每页显示数量框(css).true,默认不隐藏;false,隐藏.
				//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]], //定义每页显示数据数量
				"ordering" : false, // 是否允许Datatables开启排序
				// "info": false, //控制是否显示表格左下角的信息
				"searching" : false, // 开启、关闭Datatables的搜索功能
				bAutoWidth : false,
				//"destroy": true,
				// processing: true,//"<img src='./loading.gif' />"
				serverSide : true, // 开启服务器模式
				ajax : {
					method : "POST",
					url : "./findtablemodelgrid"
				},
				//deferLoading:1, //延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
				"columns" : [{
							class : "center",
							title : "序号",
							width : 70,
							//data:null,
							render : function(data, type, row, meta) {
								var startIndex = meta.settings._iDisplayStart;//获取到本页开始的条数
								return startIndex + meta.row + 1;
							}
						}, {
							class : "center",
							width : 100,
							width : 50,
							render : function(data, type, full, meta) {
								return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
							},
							//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
							sorting : false
						}, {
							"title" : "机型名",
							data : 'modelName',
							sorting : false
						}, {
							"title" : "中文名",
							data : 'modelCname',
							sorting : false
						}, {
							"title" : "英文名",
							data : 'modelEname',
							sorting : false
						}, {
							"title" : "主机型名",
							data : 'parentID',
							sorting : false
						}, {
							"title" : "创建人",
							data : 'editor',
							sorting : false
						}, {
							"title" : "创建日期",
							data : 'modifyDate',
							//									type:'date',
							width : 120,
							sorting : false,
							render : function(data, type, row, meta) {
								if (data == null) {
									return "";
								}
								var d = new Date(data.time); //new Date().setTime(data.time);
								return d.getFullYear() + "-"
										+ (d.getMonth() + 1) + "-"
										+ d.getDate();
							}
						}, {
							"title" : "权限级别",
							data : 'permissionLevel',
							width : 120,
							render : function(data, type, row, meta) {
								if (data == "" || data == '1') {
									return "个人可视";
								} else if (data == '2') {
									return "部门可视";
								} else if (data == "3") {
									return "所内可视";
								} else if (data == "4") {
									return '<button class="btn btn-xs btn-success">可视范围</button>';
								} else {
									return "未知";
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

	/* 机型参数Grid */
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
					type : "POST"
				},
				deferLoading : 0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
				"columns" : [{
					class : "center",
					render : function(data, type, full, meta) {
						return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
					},
					//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
					sorting : false
				}, {
					"title" : "数据来源",
					data : 'dataSources',
					sorting : false,
					render : function(data, type, row, meta) {
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
					data : 'modifyDate',
					//									type:'date',
					sorting : false,
					render : function(data, type, row, meta) {
						if (data == null) {
							return "";
						}
						var d = new Date(data.time); //new Date().setTime(data.time);
						return d.getFullYear() + "-" + d.getMonth() + "-"
								+ d.getDate();
					}
				}, {
					"title" : "编辑人",
					data : 'editor',
					sorting : false
				}, {
					"title" : "主要信息",
					data : 'mainInfo',
					//									type:'date',
					sorting : false,
					render : function(data, type, row, meta) {
						if (data == "1") {
							return "主要";
						} else if (data == "") {
							return "";
						}
					}
				}, {
					"title" : "权限级别",
					data : 'permissionLevel',
					render : function(data, type, row, meta) {
						if (data == "" || data == '1') {
							return "个人可视";
						} else if (data == '2') {
							return "部门可视";
						} else if (data == "3") {
							return "所内可视";
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

	/**可视范围-查看*/
	$('#table-model tbody').on('click', 'tr button', function() {
		var data = $('#table-model').DataTable().row($(this).parents('tr'))
				.data();
		//alert("查看修改："+data[1] +","+ data[2] );
		$.ajax({
					url : './findaircraftoverviewprivilidgeuser',
					data : {
						overviewID : data.overviewID
					},
					success : function(data) {
						//                		alert(data)
						data = eval(data);
						var s = "";
						for (var i = 0; i < data.length; i++) {
							if (s != "") {
								s += ",";
							}
							s += data[i].usercname;
						}
						if (s != "") {
							bootbox.alert(s)
						}

					}
				})
	})

	/**机型Grid行选
	 * 
	 */
	var selectModelRowData = null;
	$('#table-model tbody').on('click', 'tr', function() {
				var curCheckbox = $(this).find(':checkbox')[0];
				if (curCheckbox == null) {
					return;
				}

				/**
				 * dataTable 的多选通过<tr>标签 class="selected" 属性是否存在来判断
				 * row对象 : table.row('.selected').data();  
				 * 返回数组: table.rows('.selected').data();
				 */
				//					 $(this).toggleClass('selected');
				//				$("#modelTool").children().removeAttr('disabled');
				if (!curCheckbox.checked) {
					//选中
					tablemodel.$(':checked').attr('checked', false);//清除所有的选中框.
					$('#table-model tr').removeClass('selected'); //清除grid所有的选择项.
					$(this).addClass('selected');

					//					curCheckbox.checked=true;
					selectModelRowData = tablemodel.rows(this).data()[0];
					tablemodelparam.settings()[0].ajax.data = {
						overviewID : selectModelRowData.overviewID
					};
					tablemodelparam.ajax.reload();

					//					$("#modelTool").children().removeAttr('disabled');
					//					//需要数据管理员权限才能修改,删除和送审
					//		        	if(!algz.curUser.isDataManager&&selectModelRowData.editor!=algz.curUser.username){
					//		        		//不是数据管理员
					//		        		$("#modifyModel").attr('disabled',"true");
					//		        		$("#delModel").attr('disabled',"true");
					//		        	}

				} else {
					//取消
					$(this).removeClass('selected');

					////					row('.selected')
					//					tablemodelparam.rows('.selected').remove().draw( false );

					tablemodelparam.settings()[0].ajax.data = {
						overviewID : -1
					};
					tablemodelparam.ajax.reload();
					//		        	tablemodelparam.clear();
					//		        	tablemodelparam.rows(0).clear();
					//		        	tablemodelparam.rows(0).remove().draw(false);
					//					curCheckbox.checked=false
				}
				curCheckbox.checked = !curCheckbox.checked;
			});

	/** 修改机型
	 */
	$("#modifyModel").click(function() {
		if (tablemodel.row(".selected").length == 0) {
			bootbox.alert("请选择机型!");
		} else {
			var s = "addmodel?overviewID="
					+ tablemodel.row(".selected").data().overviewID;
			window.location.href = s
			//					bootbox.dialog({
			//						//onload="this.height =   document.frames["ifrname"].document.body.scrollHeight" id="ifrid"
			//						message:'<iframe src="'+s+'" frameborder=no height=100% width=100%></iframe>'
			//					})
		}
	})

	/** 删除机型
	 */
	$("#delModel").click(function() {
		if (tablemodel.row(".selected").length == 0) {
			bootbox.alert({
						message : "请选择机型后在删除!",
						buttons : {
							"ok" : {
								"label" : "确定",
								"className" : "btn-sm ",
								"callback" : function() {
									//Example.show("great success");
								}
							}
						}
					});
		} else {
			bootbox.confirm("将删除机型相关的所有图片、文档等数据,是否删除当前机型?", function(result) {
				if (result) {
					$.ajax({
						type : "POST",
						url : "./delmodel",
						data : {
							overviewID : tablemodel.row(".selected").data().overviewID
						},
						complete : function(msg) {
							bootbox.alert(msg.responseText);
							tablemodel.ajax.reload();
							selectModelParamRowData = null;
							tablemodelparam.ajax.reload();

						}
					})
				}
			})

			//window.location.href="addmodelparampage?overviewID="+selectModelRowData.overviewID+"&basicID="+selectModelParamRowData.basicID; 
		}
	})

	///////////////////机型参数管理/////////////////////////////////

	/** 机型参数Grid行选 单选
	 */
	var selectModelParamRowData = null;
	$('#table-modelparam tbody').on('click', 'tr', function() {
				var curCheckbox = $(this).find(":checkbox")[0];
				if (curCheckbox == null) {
					return;
				}

				//table.$(':checked').attr('checked',false); //清除所有的选中框.
				$('#table-modelparam tr').removeClass('selected'); //清除grid所有的选择项.
				//$(this).toggleClass('selected');

				if (curCheckbox.checked) {
					//取消
					curCheckbox.checked = false;
					$(this).removeClass('selected');

					$("#modelparamTool").children().removeAttr('disabled');
				} else {
					//选中
					//table.$('tr.selected').removeClass('selected');
					tablemodelparam.$(':checked').attr('checked', false);
					curCheckbox.checked = true;
					selectModelParamRowData = tablemodelparam.rows(this).data()[0];
					$(this).addClass('selected');

					if (selectModelParamRowData.permissionLevel != '1') {
						//需要数据管理员权限才能修改,删除和送审
						if (!algz.curUser.isDataManager) {
							//不是数据管理员
							$("#modifyModelparam").attr('disabled', "true");
							$("#delModelparam").attr('disabled', "true");
							$("#submitApprovalBtn").attr('disabled', "true");

						}
					} else {
						$("#modelparamTool").children().removeAttr('disabled');
					}
				}
			});

	/** 新增机型参数
	 */
	$("#addModelparam").click(function() {
		if (tablemodel.row(".selected").length == 0) {
			bootbox.alert("请选择机型后在添加参数!");
		} else {
			//					if(tablemodelparam.data().length>=1){
			//						bootbox.alert("系统暂时仅支持一条数源来源.");
			//						return ;
			//					}
			var url = "addmodelparampage?overviewID="
					+ selectModelRowData.overviewID + "&option=create"//+window.location.href; 
					//				window.location.href=url; //本窗口打开
			window.open(url); //新窗口打开
		}
	})

	/** 修改机型参数
	 */
	$("#modifyModelparam").click(function() {
		if (tablemodelparam.row('.selected').length == 0) {
			bootbox.alert("请选择机型参数后在修改参数!");
		} else {
			var url = "addmodelparampage?overviewID="
					+ tablemodel.row(".selected").data().overviewID
					+ "&basicID="
					+ tablemodelparam.row('.selected').data().basicID
					+ "&option=modify";
			//window.location.href=url
			window.open(url);
		}
	})

	/* 删除机型参数 */
	$("#delModelparam").click(function() {
		if (tablemodelparam.row('.selected').length == 0) {
			bootbox.alert({
						message : "请选择机型参数后在删除参数!",
						buttons : {
							"ok" : {
								"label" : "确定",
								"className" : "btn-sm ",
								"callback" : function() {
									//Example.show("great success");
								}
							}
						}
					});
		} else {
			bootbox.confirm("是否删除当前数行?", function(result) {
				if (result) {
					$.ajax({
						type : "POST",
						url : "./delmodelparam",
						data : {
							basicID : tablemodelparam.row('.selected').data().basicID,
							overviewID : tablemodel.row(".selected").data().overviewID
						},
						complete : function(msg) {
							bootbox.alert(msg.responseText);
							selectModelParamRowData = null;
							tablemodelparam.ajax.reload();

						}
					})
				}
			})

			//window.location.href="addmodelparampage?overviewID="+selectModelRowData.overviewID+"&basicID="+selectModelParamRowData.basicID; 
		}
	})

	/** 设置机型参数为主要
	 */
	$("#setMainModelparam").click(function() {
		if (tablemodelparam.row('.selected').length == 0) {
			bootbox.alert({
						message : "请选择机型参数!",
						buttons : {
							"ok" : {
								"label" : "确定",
								"className" : "btn-sm ",
								"callback" : function() {
									//Example.show("great success");
								}
							}
						}
					});
		} else {
			bootbox.confirm("是否设置当前机型参数为主要?", function(result) {
				if (result) {
					$.ajax({
						type : "POST",
						url : "./setMainModelparam",
						data : {
							overviewID : tablemodelparam.row('.selected')
									.data().overviewID,
							basicID : tablemodelparam.row('.selected').data().basicID
						},
						complete : function(msg) {
							bootbox.alert(msg.responseText);
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