$(function() {		
			$('.chosen-select').chosen({
				allow_single_deselect : true,
				width : '40%',
				disable_search:true //关闭搜索框,默认为false.
			}); 
			
			/**
			 * 消息提醒 全局设置
			 */
			$.gritter.options={
//				title:'提示',
				time: 2000//,
				//position:'center'//,//(bottom-left, top-left, top-right, bottom-right)
				//class_name: 'gritter-light'
			}
 
			//$('#form-field-select-3').select2({allowClear:true});
	
			// 没有采用官方jquery.dataTables.css 文件,CSS封装到ace.css中.
			var table = $('#table-tag')
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
							url : "./findtaggrid",
							type: "POST",
							data:{'onlyRead':$('#id-button-borders').attr("checked")==null?'1':'0'}
						},
						//deferLoading:0, //延迟加载(值为0,即默认不加载)，它的参数为整型,默认值为null,值为要加载条目的数目，通常与bServerSide，sAjaxSource等配合使用
						"columns" : [{
									class : "center",
									width:70,
									render : function(data, type, row, meta) {
										if(row.onlyRead!="1"){
											return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
										}
										return "";
									},
									//title : '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>',//"数据来源",/* data:'model', */
									sorting : false
								},{
									"title" : "名称",
									data:'name', 
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
									"title" : "英文名",
									data:'enname',
//									type:'date',
									sorting : false
								}, {
									"title" : "类型", 
									data:'ui_type', 
									sorting : false,
									render:function(data, type, row, meta){
										switch (data){
											case 'text':
												return '文本框';
											case 'checkbox':
												return '多选框';
											case 'numberRegion':
												return '数值区';
											default:
												return data;
										}
									}
								}, {
									"title" : "类型值", 
									data:'ui_value', 
									sorting : false
								}, {
									"title" : "单位值", 
									data:'unit', 
									width:100,
									sorting : false
								},  {
									"title" : "父节点", 
									data:'parent_id', 
									render:function(data, type, row, meta){
										switch (data){
											case '1':
												return '基本';
											case '2':
												return '重量';
											case '3':
												return '布局';
											case '4':
												return '性能';
											case '5':
												return '动力';
											case '6':
												return '系统';
											default:
												return data;
										}
									}
								}, {
									"title" : "序列", 
									data:'sequence', 
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
		
			$("#addTag").on('click',function(){
				$("#modal-form").modal('toggle',$(this));	
			})
		    
			/**
			 * 修改标签
			 */
			$("#modifyTag").on('click',function(){
				var data=table.row('.selected').data();
				if(data==null){
//					bootbox.alert('请选择一条数据!');
					$.gritter.add('请选择一条数据!');
					return;
				}
				//传参数,事件函数(event.relatedTarget返回 $(this))
				$("#modal-form").modal('toggle',$(this));
			})
			
			/**
			 * 删除标签
			 */
			$("#delTag").click(function(){
				var data=table.row('.selected').data();
				if(data==null){
//					bootbox.alert('请选择一条数据!');
					$.gritter.add('请选择一条数据!');
					return;
				}
				bootbox.confirm("是否删除当前数行?",function(result){
					if(result){
						$.ajax({
							type:"POST",
							url:"./deltag",
							data:{
								id:data.id
							},
							complete :function(msg){
								//bootbox.alert(msg);
								$.gritter.add(data);
								table.ajax.reload();
							}
						})
					}
				})
					//window.location.href="addmodelparampage?overviewID="+selectModelRowData.overviewID+"&basicID="+selectModelParamRowData.basicID; 
			})
			
			/**
			 * modal.show 方法调用之后立即触发该事件
			 */
			$('#modal-form').on('show.bs.modal', function (event) {
				var jq=event.relatedTarget
				validator.resetForm(); //重置表单的所有验证状态.
//				$(this).find("div").removeClass('has-error');
				$('.form-horizontal :input').val(""); //表单所有元素都置空
				if(jq[0].id=="addTag"){
					$("#modal-form .modal-header h4").text('新增');
				}else if(jq[0].id=="modifyTag"){
					var data=table.row('.selected').data();
					$("#modal-form .modal-header h4").text('修改');
					$('.form-horizontal :input[name=id]').val(data.id);
					$('#name').val(data.name);
					$('#enname').val(data.enname);
					$('#ui_type').val(data.ui_type);
					$('#ui_value').val(data.ui_value);
					$('#sequence').val(data.sequence);
					$('#parent_id').val(data.parent_id);
					$('#unit').val(data.unit);
					$('.form-horizontal :input[name=onlyRead]').val(data.onlyRead);
				}
				
				//$("#parent_id").chosen("destroy"); //还原为原版select控件
//				$('#parent_id')[0].options.length=0; //清空options
//			 	$('#parent_id')[0].options.add(new Option("text","value")); //DOM option 赋值
//			  	$('#parent_id')[0].options.add(new Option("text1","value1")); //DOM option 赋值
//			  	$('#parent_id').val(''); //val(''):不设默认值(因为更新列表时,自动设置第一个option为默认值.
			  	//更新chosen列表(增删改)或重新赋值(val(..),都必须调用.
			  	$('.chosen-select').trigger('chosen:updated'); //parent_id,ui_type.
			})
			
			/**
			 * modal 保存按钮
			 */
			$("#saveModalBtn").on('click',function(e){
				//var data=table.row('.selected').data();
				if(!$('.form-horizontal').valid()){
					return;
				}

				var param = $(".form-horizontal").serializeJson();
				var title=$("#modal-form .modal-header h4").text();
				var url=title=="新增"?'./addtag':"./modifytag";
				$.ajax({
					url:url,
					type:'POST',
					data:param,
					success:function(data){
						if(data.success){
							$("#modal-form").modal('hide');
							table.ajax.reload();
						}
						$.gritter.add(data.msg);
					}
				})
			})
			
			/**
			 * grid 的checkbox.
			 */
			$("#id-button-borders").click(function(){
				//checked="checked"
				if($(this).attr("checked")==null){
					$("#id-button-borders").attr("checked","checked")
					table.settings()[0].ajax.data={'onlyRead':'0'}
				}else{
					$("#id-button-borders").removeAttr("checked")
					table.settings()[0].ajax.data={'onlyRead':'1'}
				}
				table.ajax.reload();
			})
			
			/**
		     * Grid行选 单选
		     */
			$('#table-tag tbody').on('click', 'tr', function () {
				 var curCheckbox=$(this).find(":checkbox")[0];
				 if(curCheckbox==null){
				 	return;
				 }
				 
				 table.$(':checked').attr('checked',false); //清除所有的选中框.
				 $('#table-tag tr').removeClass('selected'); //清除grid所有的选择项.
				 
				/**
				 * dataTable 的多选通过<tr>标签 class="selected" 属性是否存在来判断
				 * row对象 : table.row('.selected').data();  
				 * 返回数组: table.rows('.selected').data();
				 */
				 $(this).toggleClass('selected');
				 
				 curCheckbox.checked=!curCheckbox.checked;
				//table.$(':checked').attr('checked',false);
				//selectModelParamRowData=table.rows(this).data()[0];
		    });
	
	   
	
        /**
         * 表单验证
         */
        var validator = $('.form-horizontal').validate({
        		/**
        		 * errorElement：类型 String，默认 "label"。指定使用什么标签标记错误。
        		 */
				errorElement : 'div', 
				//wrapper：类型 String，指定使用什么标签再把上边的 errorELement 包起来。
				//wrapper:"li"
				/**
				 * errorClass：类型 String，默认 "error"。指定错误提示的 css 类名。
				 */
				errorClass : 'help-block', 
				/**
				 * focusInvalid：类型 Boolean，默认 true。提交表单后，未通过验证的表单（第一个或提交之前获得焦点的未通过验证的表单）会获得焦点。
				 */
				//focusInvalid : false,
				/**
				 * //ignore：忽略某些元素不验证. ignore: ".ignore"
				 */
				//在使用jquery的chosen下拉列表的插件时，碰到了使用jquery.validate.js的冲突，不能进行空值校验,chosen插件本身自动隐藏了空值，所以校验不到.
				ignore: ":not(:text,select)" , //忽略:不包括text和select控件. 必须设置这值,此UI框架使的插件都以hidden隐藏原控件.
				// submitHandler:function(form){
				// alert("提交事件!");
				// form.submit();
				// },
				/**
				 * rules：自定义规则，key:value 的形式，key 是要验证的元素(name属性)，value 可以是字符串或对象。
				 */
				rules : {
					name : "required",
					enname: "required",
					'ui_type':'required',
					parent_id:'required',
					ui_value:{
						digits:true,
						max:300
					},
					sequence:{
						digits:true,
						max:99
					}
					
				},
				/**
				 * messages：自定义的提示信息，key:value 的形式，key 是要验证的元素(name属性)，value 可以是字符串或函数。
				 */
				messages : {
					//name : '请输入名称!',
					//enname : '请输入名称!',
					//ui_type : '请选择选项!',
					//parent_id : '请选择选项!'
				},
				/**
				 * element出错时触发
				 * (highlight：可以给未通过验证的元素加效果、闪烁等。)
				 */
				highlight: function (e) {
					$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
				},
				/**
				 * element通过验证时触发
				 */
				unhighlight: function(element) {
            		$(element).closest('.form-group').removeClass('has-error');
        		},
				/**
				 * success：要验证的元素通过验证后的动作，如果跟一个字符串，会当作一个 css 类，也可跟一个函数。
				 */
				success: function (e) {
						$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
						$(e).remove();
				},
				/**
				 * 跟一个函数，可以自定义错误放到哪里。
				 */
				errorPlacement: function (error, element) {
						if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
							var controls = element.closest('div[class*="col-"]');
							if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
							else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
						}
						else if(element.is('.select2')) {
							error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
						}
						else if(element.is('.chosen-select')) {
							error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
						}
						else error.insertAfter(element.parent());
					}
			});
			
	 	/**
	     * 序列化表单元素为 json 格式
	     * var param = $("#userForm").serializeJson();
	     */
		$.fn.serializeJson=function(){
            var serializeObj={};
            var array=this.serializeArray();
            var str=this.serialize();
            $(array).each(function(){
                if(serializeObj[this.name]){
                    if($.isArray(serializeObj[this.name])){
                        serializeObj[this.name].push(this.value);
                    }else{
                        serializeObj[this.name]=[serializeObj[this.name],this.value];
                    }
                }else{
                    serializeObj[this.name]=this.value; 
                }
            });
            return serializeObj;
        };
        
    /**
     * json 字符串加载到表单元素中
     */
	$.fn.loadJsonData=function(jsonStr){
		var obj = eval("("+jsonStr+")");
		var key,value,tagName,type,arr;
		for(x in obj){
			key = x;
			value = obj[x];
			
			$("[name='"+key+"'],[name='"+key+"[]']").each(function(){
				tagName = $(this)[0].tagName;
				type = $(this).attr('type');
				if(tagName=='INPUT'){
					if(type=='radio'){
						$(this).attr('checked',$(this).val()==value);
					}else if(type=='checkbox'){
						arr = value.split(',');
						for(var i =0;i<arr.length;i++){
							if($(this).val()==arr[i]){
								$(this).attr('checked',true);
								break;
							}
						}
					}else{
						$(this).val(value);
					}
				}else if(tagName=='SELECT' || tagName=='TEXTAREA'){
					$(this).val(value);
				}
				
			});
		}
	}
			
			
		})