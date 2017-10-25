$(function() {

	// 是否隐藏个人数据
	$("#showPersonDataBtn").on('click', function() {
				if ($(this).attr("checked") == null) {
					// 打开
					$(this).attr("checked", "checked");
					dataTable.settings()[0].ajax.data.showPerson = '0';

					/*
					 * dataTable.rows().data().each( function (data,index,meta) {
					 * var flag=true; for(var i=1;i<data.length;i++){
					 * if(data[i]!=null){ flag=false; break; } } if(flag){
					 * dataTable.row(index).child.hide();
					 * $(dataTable.row(index).node()).addClass("hidden"); } });
					 */
				} else {
					// 关闭
					$(this).removeAttr("checked");
					dataTable.settings()[0].ajax.data.showPerson = '1';

					/* dataTable.$('tr').removeClass("hidden"); */
				}
				dataTable.ajax.reload(); // 必须用
			})		
			
	function loadModelGridByNodes() {
		var m = {};
		var cNodes = $('#tree').treeview('getChecked');// 获取所有选中的结点
		for (var i = 0; i < cNodes.length; i++) {
			// 父结点不作为参数提交
			if (cNodes[i].nodes != undefined && cNodes[i].nodes.length != 0) {
				continue;
			}

			var pnode = $('#tree').treeview('getParent', cNodes[i]);
			if (m[pnode.tags] != null) {
				m[pnode.tags] += "," + cNodes[i].text;
			} else {
				m[pnode.tags] = cNodes[i].text;
			}
		}

		dataTable.settings()[0].ajax.data = {
			data : JSON.stringify(m)
		};
		dataTable.ajax.reload(); // 必须用
	}

	function getTree() {
		$.ajax({
			data : '',
			method : "POST",
			url : './getsearchtreeviewnode',
			success : function(data) {

				$('#tree').treeview({
							data : data,
							levels : 1,
							showIcon : false,
							showCheckbox : true,
							onNodeChecked : function(event, node) {
								var nIds = [];
								if (node.nodes !== undefined) {
									for (var i = 0; i < node.nodes.length; i++) {
										nIds.push(node.nodes[i].nodeId);
									}
								} else {
									nIds.push(node);
								}
								$('#tree').treeview("checkNode", [nIds, {
													silent : true
												}]);

								loadModelGridByNodes();
							},
							onNodeUnchecked : function(event, node) {
								var nIds = [];
								if (node.nodes !== undefined) {
									for (var i = 0; i < node.nodes.length; i++) {
										nIds.push(node.nodes[i].nodeId);
									}
								}
								$('#tree').treeview("uncheckNode", [nIds, {
													silent : true
												}]);

								loadModelGridByNodes();
							}
						});
			}
		})
	}

	getTree();
	// $('#tree').treeview({data: getTree()});

	function stringToJson(data) {
		var o = {};
		$.each(data, function(i, data) {
					if (o[this.name]) {
						/*
						 * 表单中可能有多个相同标签，比如有多个label，
						 * 那么你在json对象o中插入第一个label后，还要继续插入，
						 * 那么这时候o[label]在o中就已经存在，所以你要把o[label]做嵌套数组处理
						 */
						// 如果o[label]不是嵌套在数组中
						if (!o[this.name].push) {
							o[this.name] = [o[this.name]]; // 将o[label]初始为嵌套数组，如o={a,[a,b,c]}
						}
						o[this.name].push(this.value || ''); // 将值插入o[label]
					} else {
						o[this.name] = this.value || ''; // 第一次在o中插入o[label]
					}
				});
		return o;
	}

	$('#submitBtn').click(function() {
		var data = $('#tagForm').serializeArray();
		var o = stringToJson(data);
		// ///////////////
		var params = $('#tagForm').serialize();
		// params=decodeURIComponent(params,true);
		dataTable.settings()[0].ajax.data = {
			data : params
		};
		dataTable.ajax.reload(); // 必须用

			// $('#selectModelName').val("");
			// $('#selectOverviewID').val("");
		})

	$('#clearModelSelectBtn').on('click', function() {
				$('#selectModelName').val("");
				$('#selectOverviewID').val("");

				$("#searchTable :checked").removeAttr("checked")
			})

	// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
	var dataTable = $('#searchTable')
			// .wrap("<div class='dataTables_borderWrap' />")
			// if you are applying horizontal scrolling (sScrollX)
			.DataTable({
				// "paging": false, //是否开启本地分页
				// "lengthChange": false,
				// //是否隐藏每页显示数量框(css).true,默认不隐藏;false,隐藏.
				"lengthMenu" : [[10, 25, 50, 100, 200, -1],
						[10, 25, 50, 100, 200, "All"]], // 定义每页显示数据数量,与"lengthChange":
														// true 一起使用.
				"ordering" : false, // 是否允许Datatables开启排序
				// "info": false, //控制是否显示表格左下角的信息
				"searching" : false, // 开启、关闭Datatables的搜索功能
				bAutoWidth : false,
				// processing: true,//"<img src='./loading.gif' />"
				serverSide : true, // 开启服务器模式
				ajax : {
					url : "./simplesearchgird",
					type : 'POST'
				},
				deferLoading : 0, // 延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
				"columns" : [/*
								 * { data:null, class : "center", width:50, //
								 * "defaultContent": '', 
								 * render: function(data,type, row, meta){ 
								 * //icon expand-icon glyphicon glyphicon-minus - 
								 * //icon expand-icon glyphicon glyphicon-plus 
									return '<i class="icon expand-icon glyphicon glyphicon-plus "></i>'; 
								 * } },
								 */{
					class : "center",
					width : 50,
					render : function(data, type, full, meta) {
						return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
					},
					sorting : false
				}, {
					data : null,
					class : "center",
					width : 50,
					render : function(data, type, row, meta) {
						var startIndex = meta.settings._iDisplayStart;// 获取到本页开始的条数
						return startIndex + meta.row + 1;
					}
				}, {
					"title" : "机型名",
					data : 'modelName',
					sorting : false,
					render : function(data, type, row, meta) {
						// 个人理解 --以及参数的应用场景
						// data:当前cell的值 --主要是操作这个参数来做渲染
						// type:数据类型,枚举类型dt内置定义的 --用处不大
						// row:当前行的所有数据 --可以做来用级联(没办法更改其他cell的渲染)
						// meta:它下面有三个参数
						// row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
						// settings:dt的api实例,动态所有的参数信息都在里面
						// --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
						// return data;
						// "<a target='_blank'
						// href='./searchsummarize?overviewID="+row.overviewID+"&basicID="+row.basicID+"&option=load'>"+data+"</a>";
						var el="";
						if(row.subModelTotal!=0){
							el= '<i class="icon expand-icon glyphicon glyphicon-plus "></i>';
						}
						return el+ "<a target='_blank' href='./searchsummarize?overviewID="
								+ row.overviewID
								+ "&option=load'>"
								+ data
								+ "</a>";
					}
				}, {
					"title" : "中文名称",
					data : 'modelCname',
					sorting : false
				}, {
					"title" : "英文名称",
					data : 'modelEname',
					sorting : false
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
						} else if (data == "4") {
							return "范围可视";
						} else {
							return "未知";
						}
					}
				},{
					title:'子机型总数',
					data:'subModelTotal',
					class : "center",
					width : 100
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

	// 2.多选(必须放到 Dtable配置后下面);所有 table 有效,需放在所有 dataTable 后面
	$('#searchTable tbody').on('click', 'tr', function(event) {
		if (event.target.tagName == 'A'
		||event.target.tagName == "I"
		||$(event.target).children('.subTable').length==1
		||$(event.target).closest('.subTable').length==1) {
			return;
		}
		event.preventDefault(); // 阻止默认事件触发(即执行二次)

		/* 多选框的多选 */
		var curCheckbox = $(this).find(':checkbox')[0];
		if (curCheckbox == null) {
			return;
		}
		curCheckbox.checked = !curCheckbox.checked;
		$(this).toggleClass('selected', curCheckbox.checked);

		// var
		// modelName=$($(this).closest('.subTable').closest('tr').prev('tr').children('td')[2]).text();
		var org_text = $('#selectModelName').val();
		var org_val = $('#selectOverviewID').val()
		var text = dataTable.rows(this).data()[0].modelName;// $(this).closest('tr').find('a').text()+"("+modelName+")";
		var val = dataTable.rows(this).data()[0].overviewID;// $(this).val();
		if (curCheckbox.checked) {
			// 添加
			$('#selectModelName').val(org_text == ""
					? text
					: (org_text + "," + text));
			$('#selectOverviewID').val(org_text == "" ? val : org_val + ","
					+ val);
		} else {
			// 删除
			org_text = org_text.replace("," + text, "").replace(text, "")
					.replace(",,", ",");
			org_text = org_text.indexOf(",") == 0
					? org_text.substring(1)
					: org_text;
			$('#selectModelName').val(org_text);
			org_val = org_val.replace("," + val, "").replace(val, "").replace(
					",,", ",");
			org_val = org_val.indexOf(",") == 0
					? org_val.substring(1)
					: org_val;
			$('#selectOverviewID').val(org_val);
		}

	})

	 function template(data) {
		var template = '<table class="subTable table table-striped table-bordered table-hover" ><tbody>';
		for (var i = 0; i < data.length; i++) {
			var permissionLevel="";
			switch(data[i].permissionLevel){
				case "":
				case "1":
					permissionLevel	="个人可视"
					break;
				case "2":
					permissionLevel	="部门可视"
					break;
				case "3":
					permissionLevel	="所内可视"
					break;
				case "4":
					permissionLevel	="范围可视"
					break;
			}
			if (data == "" || data == '1') {
				return "个人可视";
			} else if (data == '2') {
				return "部门可视";
			} else if (data == "3") {
				return "所内可视";
			} else if (data == "4") {
				return "范围可视";
			}
			template += '<tr>'
					+ '<td width="50">'
					+ '<label class="pos-rel"><input class="ace" type="checkbox" value="'+data[i].overviewID+ '"><span class="lbl"></span></label>'
					+'</td>'
					+"<td width=50>"+(i+1)+"</td>"
					+ '<td>'
					+ "<a target='_blank' href='../simplesearch/searchsummarize?overviewID="+data[i].overviewID + "&option=load'>" + data[i].modelName + "</a>" 
					+ '</td>'
					+"<td>"+data[i].modelCname+"</td>"
					+"<td>"+data[i].modelEname+"</td>"+"<td width=100>"+permissionLevel+"</td>"
					+'</tr>';
		}
		template += '</tbody></table>';
		return template;
	}

	 $('#searchTable').on('click', '.subTable :checkbox', function() {
		var modelName = $($(this).closest('.subTable').closest('tr').prev('tr')
				.children('td')[2]).text();
		var org_text = $('#selectModelName').val();
		var org_val = $('#selectOverviewID').val()
		var text = $(this).closest('tr').find('a').text() + "(" + modelName
				+ ")";
		var val = $(this).val();
		if (this.checked) {
			// 添加
			$('#selectModelName').val(org_text == ""
					? text
					: (org_text + "," + text));
			$('#selectOverviewID').val(org_text == "" ? val : org_val + ","
					+ val);
		} else {
			// 删除
			org_text = org_text.replace("," + text, "").replace(text, "")
					.replace(",,", ",");
			org_text = org_text.indexOf(",") == 0
					? org_text.substring(1)
					: org_text;
			$('#selectModelName').val(org_text);
			org_val = org_val.replace("," + val, "").replace(val, "").replace(
					",,", ",");
			org_val = org_val.indexOf(",") == 0
					? org_val.substring(1)
					: org_val;
			$('#selectOverviewID').val(org_val);
		}
	})

	 /**子表格展开.
	 * 
	 */
	 $('#searchTable tbody').on('click', 'td .expand-icon', function(e) {
			var tr = $(this).closest('tr');
			var row = dataTable.row(tr);

			if (row.child.isShown()) { // This row is already open - close
										// it
				row.child.hide();
				$(this).removeClass('glyphicon-minus');

			} else { // Open this row
				if (row.child() == null) {
					$.ajax({
						method : 'Post',
						url : './searchsubmodelgrid',
						data : {
							overviewID : row.data().overviewID
						},
						success : function(data) {
							row.child(template(data.data)).show();
						}
					})
				} else {
					row.child.show();
				}

				$(this).addClass('glyphicon-minus');
			}
		});
	 

	// $('#searchTable tbody').on('click', 'tr', function () {
	// var curCheckbox=$(this).find(":checkbox")[0];
	// if(curCheckbox==null){
	// return;
	// }
	//				 
	// if (curCheckbox.checked ) {
	// //取消
	// curCheckbox.checked=false;
	// $(this).removeClass('selected');
	// }
	// else {
	// //选中
	// curCheckbox.checked=true;
	// //selectModelParamRowData=tablemodelparam.rows(this).data()[0];
	// $(this).addClass('selected');
	// }
	// });
	/**
	 * 对比
	 */
	$("#comparisonModelparamBtn").on('click', function() {
		var datas = dataTable.rows('.selected').data();
		if (datas.length == 0) {
			return;
		}

		var obj = new Object()
		obj.overviewID = $('#selectOverviewID').val();
		obj.modelName = $('#selectModelName').val();
		if (obj.basicID != "") {
			StandardPost("/algz/ras/comparison/comparisondetail", obj);
		}

			// var datas=dataTable.rows('.selected').data();
			// if(datas.length==0){
			// return ;
			// }
			// var arr=[];
			// for(var i=0;i<datas.length;i++){
			// arr.push(datas[i].modelName);
			// }
			// var obj=new Object()
			// obj.modelName=arr
			// StandardPost("/algz/ras/comparison/comparisondetail",obj)
			// //window.location='/algz/ras/comparison/comparisondetail';
		})

	function StandardPost(url, args) {
		var form = $("<form method='post' target='_blank'></form>"), input;
		form.attr({
					"action" : url
				});
		$.each(args, function(key, value) {
					input = $("<input type='hidden'>");
					input.attr({
								"name" : key
							});
					input.val(value);
					form.append(input);
				});
		form.appendTo(document.body);
		form.submit();
		document.body.removeChild(form[0]);
	}

	// //////////图表////////////////////////

	$(".chosen-select").chosen({
				allow_single_deselect : true,
				// disable_search:true,
				// disable_search_threshold:-1,
				// enable_split_word_search:false,
				width : '40%'
			});

	var chart = null;

	// 坐标轴
	$("#xAxisBtn").on('change', clickBtn);
	$("#yAxisBtn").on('change', clickBtn);
	$("#chartTypeBtn").on('change', clickBtn);
	function clickBtn() {
		var xAxis = $("#xAxisBtn").val();
		var yAxis = $("#yAxisBtn").val();
		var overviewID = [];
		if (xAxis == "" || yAxis == "") {
			return;
		}
		if (xAxis == yAxis) {
			alert("x轴与y轴不能相同!")
			$(this).val("");
			$(this).trigger("chosen:updated");
			if (chart != null) {
				chart.destroy();
			}

			return;
		}

		var datas = dataTable.rows('.selected').data();
		if (datas.length == 0) {
			return;
		} else {
			for (var i = 0; i < datas.length; i++) {
				overviewID.push(datas[i].overviewID);
			}
		}

		$.ajax({
					url : '../analyze/analyzechart',
					data : {
						// modelName : $("#modelName").val(),
						overviewID : overviewID,
						xAxis : xAxis,
						yAxis : yAxis
					},
					success : function(data) {
						var objs = eval(data);
						chart = Highcharts.chart("highchart", {
							// Highcharts 配置
							credits : {
								enabled : false
							},
							chart : {
								type : $('#chartTypeBtn').val()
								// 'column'// 'scatter'//,
								// zoomType: 'xy'
							},
							title : {
								text : "机型图表分析"// '我是标题'
							},
							subtitle : {
						// text: '我是副标题'
							},
							xAxis : {
								title : {
									text : $('#xAxisBtn option:selected')
											.text()
								}
							},
							yAxis : {
								title : {
									text : $('#yAxisBtn option:selected')
											.text()
								}
							},
							series : objs
								/*
								 * , series : [{ name : '', data :
								 * [1,3,5,7,9]//[[1, 4], [3, 5], [2, 6]] },{
								 * name : '', data : [2,4,6,8,10]//[[1, 4], [3,
								 * 5], [2, 6]] }]
								 */
							});
					}
				})
	}

	/**
	 * 分析
	 */
	$("#analyzeModelparamBtn").on('click', function() {
				var datas = dataTable.rows('.selected').data();
				if (datas.length == 0) {
					return;
				}
				//				
				// var arr=[];
				// $.each(datas,function(i,obj){
				// arr.push(obj.modelName);
				// })
				//				
				// $("#modelName").val(arr);
				$("#modelName").val($('#selectModelName').val());

				$('#modal-analyzeChart').on('show.bs.modal', function() {
							$("#xAxisBtn").val("");
							$("#xAxisBtn").trigger("chosen:updated");
							$("#yAxisBtn").val("");
							$("#yAxisBtn").trigger("chosen:updated");
							if (chart != null) {
								chart.destroy();
								chart = null;
								// chart.destroy();
							}
						}).modal();
			})

})