$(function() {

	$('.chosen-select').chosen({
		allow_single_deselect : true,
		width : '20%',
		disable_search:true //关闭搜索框,默认为false.
	}); 
	
	/* 添加查询条件  */
	$('#addProductSearchBtn').on('click',function(){
		
	})
	
	/* 查询按钮 */
	$('#productSearchBtn').on('click',function(){
		var arr=$('form').serializeArray();
		dataTable.settings()[0].ajax.data={
			paramID:arr[0].value,
			paramValue:arr[1].value
		};
		dataTable.ajax.reload(); 

	})
	
	
//	function stringToJson(data) {
//		var o = {};
//		$.each(data, function(i, data) {
//					if (o[this.name]) {
//						/*
//						 * 表单中可能有多个相同标签，比如有多个label，
//						 * 那么你在json对象o中插入第一个label后，还要继续插入，
//						 * 那么这时候o[label]在o中就已经存在，所以你要把o[label]做嵌套数组处理
//						 */
//						// 如果o[label]不是嵌套在数组中
//						if (!o[this.name].push) {
//							o[this.name] = [o[this.name]]; // 将o[label]初始为嵌套数组，如o={a,[a,b,c]}
//						}
//						o[this.name].push(this.value || ''); // 将值插入o[label]
//					} else {
//						o[this.name] = this.value || ''; // 第一次在o中插入o[label]
//					}
//				});
//		return o;
//	}

//	$('#submitBtn').click(function() {
//		var data = $('#tagForm').serializeArray();
//		var o = stringToJson(data);
//		// ///////////////
//		var params = $('#tagForm').serialize();
//		// params=decodeURIComponent(params,true);
//		dataTable.settings()[0].ajax.data = {
//			data : params
//		};
//		dataTable.ajax.reload(); // 必须用
//
//			// $('#selectModelName').val("");
//			// $('#selectOverviewID').val("");
//		})

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
					url : "./findproductgrid",
					type : 'POST'
				},
				deferLoading : 0, // 延迟加载(值为0,即不加载;不设置为默认自动加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
				"columns" : [/*
								 * { data:null, class : "center", width:50, //
								 * "defaultContent": '', render:
								 * function(data,type, row, meta){ //icon
								 * expand-icon glyphicon glyphicon-minus -
								 * //icon expand-icon glyphicon glyphicon-plus
								 * return '<i class="icon expand-icon glyphicon
								 * glyphicon-plus "></i>'; } },
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
						var el = "";
						if (row.subModelTotal != 0) {
							el = '<i class="icon expand-icon glyphicon glyphicon-plus "></i>';
						}
						return el
								+ "<a target='_blank' href='../simplesearch/searchsummarize?overviewID="
								+ row.overviewID + "&option=load'>" + data
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
		if (event.target.tagName == 'A' || event.target.tagName == "I"
				|| $(event.target).children('.subTable').length == 1
				|| $(event.target).closest('.subTable').length == 1) {
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
			var permissionLevel = "";
			switch (data[i].permissionLevel) {
				case "" :
				case "1" :
					permissionLevel = "个人可视"
					break;
				case "2" :
					permissionLevel = "部门可视"
					break;
				case "3" :
					permissionLevel = "所内可视"
					break;
				case "4" :
					permissionLevel = "范围可视"
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
					+ '<label class="pos-rel"><input class="ace" type="checkbox" value="'
					+ data[i].overviewID
					+ '"><span class="lbl"></span></label>'
					+ '</td>'
					+ "<td width=50>"
					+ (i + 1)
					+ "</td>"
					+ '<td>'
					+ "<a target='_blank' href='../simplesearch/searchsummarize?overviewID="
					+ data[i].overviewID + "&option=load'>" + data[i].modelName
					+ "</a>" + '</td>' + "<td>" + data[i].modelCname + "</td>"
					+ "<td>" + data[i].modelEname + "</td>" + "<td width=100>"
					+ permissionLevel + "</td>" + '</tr>';
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

	/** 子表格展开.
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


	/** 动态创建表单进行提交
	 */
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

})