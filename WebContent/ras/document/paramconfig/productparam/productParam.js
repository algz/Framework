$(function() {
	
	//设置为中文
	bootbox.setDefaults("locale","zh_CN"); 
	
	$(".chosen-select").chosen({
			allow_single_deselect : true,
			// disable_search:true,
			// disable_search_threshold:-1,
			// enable_split_word_search:false,
			width : '40%'
	});
			
	//删除结点
	$('#delNodeBtn').on('click',function(){
		var nodes=$('#tree').treeview('getSelected');
		if(nodes.length!=0){
			bootbox.confirm("是否删除当前结点?",function(result){
				if(result){
					$.ajax({
						url : './delproductparam',
						data : {
							productID : nodes[0].productID
						},
						success : function() {
							$('#tree').treeview('removeNode',[nodes,{ silent: true } ] );
						}
					})
				}
			})
		}
	})
	
	//修改结点
	$('#modifyNodeBtn').on('click',function(){
		
		var nodes=$('#tree').treeview('getSelected');
		if(nodes.length!=0){
			$('#modal-productParamNode').on('show.bs.modal',function(){
				$('#productName-node').val(nodes[0].text);
				$('#productID-node').val(nodes[0].productID);
			}).modal();
		}
	})
	
	//添加结点
	$('#addNodeBtn').on('click',function(){
		$('#modal-productParamNode').on('show.bs.modal',function(){
			$('#productName-node').val('');
			$('#productID-node').val('');
		}).modal();
	})
	
	//弹出窗-保存结点
	$('#confirmBtn-node').on('click',function(){
		var nodes=$('#tree').treeview('getSelected');
		var productID=$('#productID-node').val();
		var productName=$('#productName-node').val();
		if(productName!=""){
			$.ajax({
				url:'./saveproductparam',
				data:{
					isLeaf:'0',
					productID:productID,
					//如果 productID!='',则更新当前结点;否则添加当前结点.
					parentID:productID!=''?'':(nodes.length==0?"0":nodes[0].productID),
					productName:productName
				},
				success:function(data){
					if(productID!=''){
						var node=$.extend({},nodes[0]);
						node.$el=null;
						node.text=productName;
						$("#tree").treeview("updateNode", [nodes,node ]);  
						
					}else{
						var node={
							productID:data.productID,
							isLeaf:'0',
							text: productName
						}
						$("#tree").treeview("addNode", [node]);  
					}
					$('#modal-productParamNode').modal('hide');
				}
			})
		}
	})

	//自动加载Tree.
	!(function() {
		$.ajax({
			data : '',
			method : "POST",
			url : './getsearchtreeviewnode',
			success : function(data) {
				$('#tree').treeview({
							data : data,
							levels : 1,
							showIcon : false,
//							showCheckbox : true,
							onNodeSelected:function(event, node) {
									dataTable.settings()[0].ajax.data = {
										productID : node.productID
									};
									dataTable.ajax.reload(); // 必须用
							}/*,
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
							}*/
						});
			}
		})
	})();


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

		////////////表格////////////////
		
		//删除表格行
		$('#delParamBtn').on('click',function(){
			var data=dataTable.rows('.selected').data();
			if(data.length!=0){
				bootbox.confirm("是否删除当前结点?",function(result){
					if(result){
						$.ajax({
							url : './delproductparam',
							data : {
								productID : data[0].productID
							},
							success : function() {
								dataTable.ajax.reload(); 
							}
						})
					}
				})
			}
		})
		
		//修改结点
		$('#modifyParamBtn').on('click',function(){
			
			var datas=dataTable.rows('.selected').data()
			if(datas.length!=0){
				$('#modal-productParamGrid').on('show.bs.modal',function(){
					$('#productID').val(datas[0].productID);
					$('#productName').val(datas[0].productName);
					$('#paramName').val(datas[0].paramName);
					$('#ui_type').val(datas[0].ui_type);
					$("#ui_type").trigger("chosen:updated");
					$('#sequence').val(datas[0].sequence);
				}).modal();
			}

		})
		
		//添加 Grid 参数
		$('#addParamBtn').on('click', function() {
			var nodes=$('#tree').treeview('getSelected');
			if(nodes.length!=1){
				bootbox.alert("请选择一条参数!");
			}else{
				$('#modal-productParamGrid').on('show.bs.modal',function(){
					$('#productID').val('');
					$('#productName').val('');
					$('#paramName').val('');
					$('#sequence').val('');
				}).modal();
			}
		})
		
		//表格弹出窗-保存按钮
		$('#confirmBtn-grid').on('click',function(){
			var nodes=$('#tree').treeview('getSelected');
			var productName=$('#productName').val();
			var parentID=nodes[0].productID;
			if(parentID!=""&&productName!=""){
				$.ajax({
					url:'./saveproductparam',
					data:{
						productID:$('#productID').val(),
						isLeaf:'1',
						parentID:parentID,
						productName:productName,
						paramName:$('#paramName').val(),
						ui_type:$('#ui_type').val(),
						sequence:$('#sequence').val()
					},
					success:function(data){
						$('#modal-productParamGrid').modal('hide');
						dataTable.ajax.reload(); 
					}
				})
			}
		})
		

	// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
	var dataTable = $('#productParamTable')
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
					"title" : "参数名称",
					data : 'productName',
					sorting : false
				}, {
					"title" : "参数类型",
					data : 'ui_type',
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
						switch(data){
							case 'text':
								return '文本';
							case 'checkbox':
								return '多选框';
							case 'number':
								return '数值框';
							case 'numberRegion':
								return '数值区';
							case 'textArea': 
								return '文本框';
							default:
								return data;
						}
					}
				},{
					title:'序列',
					data:'sequence',
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
	$('#productParamTable tbody').on('click', 'tr', function(event) {
		event.preventDefault(); // 阻止默认事件触发(即执行二次)

		$(this).closest('table').find(':checkbox').attr('checked',false); //清除所有的选中框
		$(this).siblings(".selected").removeClass('selected'); //.siblings() 筛选所有同辈元素
		
		/* 多选框的多选 */
		var curCheckbox = $(this).find(':checkbox')[0];
		if (curCheckbox == null) {
			return;
		}
		curCheckbox.checked = !curCheckbox.checked;
		$(this).toggleClass('selected', curCheckbox.checked);

	})


})