
$(function(){

	
	//设置为中文
	bootbox.setDefaults("locale","zh_CN"); 
	
	//弹出窗保存
	$('#saveModelParamBtn').on('click',function(){
		var dataSources=$('#dataSources_add').val();
		var paramName=$("#param_name").val();
		var paramValue=$("#param_val").val();
		$.ajax({
			cache:false,
			url:'./searchsummarize/savemodelparam',
			data:{
				overviewID: $(':hidden[name=overviewID]').val(),
				dataSources: dataSources,
				paramName: paramName,
				paramValue: paramValue
			},
			success:function(data){
				if(data.msg!=""){
					alert(data.msg);
					return;
				}
				var tr="";
					tr+='<td >'+dataSources+'</td>'
					tr+='<td >'+paramValue+'</td>'
				tr='<tr class="hidden" name="'+paramName+'">'+tr+'</tr>'
				$('#affixTable tbody').append(tr);
				
				
				$('#modalparam-form').modal('hide');
			}
		})
	})
	
	//编辑机型参数
	var showModelParamEditBtn=function(){
		$('#modelParamForm div.form-group :input').siblings('span').addClass('hidden')
		$(this).siblings('span').removeClass('hidden')
	}
	
	//机型参数编辑按钮
	$('#modelParamForm :input~span button[name=modelparamEditBtn]').on('click',function(){
		//打开弹出窗口.
		var el=this;
	 	$('#modalparam-form').on('show.bs.modal',function(){
	 		$('#dataSources_add').val('')
	 		 
	 		$("#param_text").val($(el).closest("div").siblings("label").text());
			$("#param_name").val($(el).closest("span").siblings("input").attr('name'));
			$("#param_val").val("");
		}).modal();//先定义函数,再调用弹出函数,才能按顺序执行.
	})

	

	/**
	 *开启编辑功能
	*/
	$('#closeEditBtn').on('click',function(){
		if($(this).attr('checked')==null){
			//打开
			$(this).attr("checked","checked");
			$("#modelParamForm div.form-group :input").on('click',showModelParamEditBtn); //显示编辑按钮
		}else{
			//关闭
			$(this).removeAttr("checked");
			$('#modelParamForm div.form-group :input').siblings('span').addClass('hidden')
			$("#modelParamForm div.form-group :input").off('click',showModelParamEditBtn)
		}
	})
	
	
	//显示机型参数注释
	var showModelParam=function(){
		$('#affixTable tbody tr').addClass('hidden');
		$('#affixTable tbody tr[name='+this.name+']').removeClass('hidden');
	}
	
	/**
	* 开启注释
	*/
	$("#checkBtn").click(function(){
		//checked="checked"
		$('#affixTable').toggleClass('hidden');
		
		if($(this).attr("checked")==null){
			//打开
			$(this).attr("checked","checked");
			var s="";
			var els=$("#modelParamForm :text[name!=dataSources]");
			for(var i=0;i<els.length;i++){
				if(i!=0){
					s+=",";
				}
				s+=$(els[i]).attr("name");
			}
			$.ajax({
				cache:false,
				url:'./addnotefortaginput',
				data:{
					overviewID:$(":hidden[name=overviewID]").val(),
					inputName:s
				},
				success:function(data){
					var obj=data;//eval("("+data+")");
					$('#affixTable tbody').empty();
					for(var i=0;i<obj.inputs.length;i++){
						var input=obj.inputs[i];
						var tr="";
						for(var j=0;j<input.vals.length;j++){
							var td=""
								td+='<td >'+input.vals[j].dataSources+'</td>'
								td+='<td >'+input.vals[j].inputValue+'</td>'
							tr+='<tr class="hidden" name="'+input.name+'">'+td+'</tr>'
						}
						$('#affixTable tbody').append(tr);
						
						
						$("#modelParamForm div.form-group :input").on('click',showModelParam);
						
						//toolip
						var el=$(":text[name="+input.name+"]");
						el.offsetParent().offsetParent().attr("data-toggle","tooltip");
						el.after("<i class='ace-icon fa fa-info-circle'></i>");
						var s="<ul class='list-unstyled'>"
						for(var j=0;j<input.vals.length;j++){
							var val=input.vals[j]
							s+="<li>"
							s+=(j+1)+"."+val.inputValue+" <i class='ace-icon fa fa-external-link blue'/><span class='text-muted'>"+val.dataSources+"</span><p>";
							s+="</li>"
						}
						el.offsetParent().offsetParent().attr("title",s+"</ul>"); 
					}
					$('[data-toggle="tooltip"]').tooltip({
						html:true,
						placement:'right'
					});
				}
			})
		}else{
			//关闭
			$(this).removeAttr("checked")
			$('[data-toggle="tooltip"]').tooltip('destroy')
			$('[data-toggle="tooltip"]').attr("title","")
			$(":text~:not(span)").remove();
			
			$("#modelParamForm div.form-group :input").off('click',showModelParam);
		}
	})
	
	/**
	* 隐藏空行
	*/
	$("#closeSpaceBtn").on('click',function(){
		if($(this).attr("checked")==null){
			//打开
			$(this).attr("checked","checked");
			var els=$(".form-horizontal .form-group").has(':text[value=""]');
			els.addClass('hidden')
		}else{
			//关闭
			$(this).removeAttr("checked")
			$(".form-horizontal .form-group").removeClass("hidden");
		}
	})
	
	/**
	* 是否收藏
	*/
	$("#isFavoritesBtn").on('click',function(event){
		if($("#isFavoritesBtn").attr("checked")==null){
			//打开
			//$(this).attr("checked","checked");
			bootbox.prompt("输入收藏名称", function(result){
				var url="";
				if(result){
					//打开
					event.preventDefault();
					$.ajax({
						url: '../personal/favorites/addfavorites', //添加
						type:"POST",
						data:{
							url: window.location.href,
							favoritesName:result
						},
						success:function(data){
							if(data!=null&&data.success==true){
								$("#favoritesID").val(data.favoritesID);
								$("#isFavoritesBtn").attr("checked","checked");
							}else{
								$("#isFavoritesBtn").removeAttr("checked");
							}
							
						}
					})
				}else{
					$("#favoritesID").val("");
					$("#isFavoritesBtn").removeAttr("checked")
					
				}
			});
		}else{
			//关闭
				$.ajax({
					url: '../personal/favorites/delfavorites?d='+new Date(),
					type:"POST",
					data:{
						url: window.location.href//,
//						favoritesID:$("#favoritesID").val()
					},
					success:function(data){
						$("#favoritesID").val("");
						$("#isFavoritesBtn").removeAttr("checked")
						
					}
				})
//			if($("#favoritesID").val()!=""){
//				
//
//				$("#isFavoritesBtn").attr("checked","checked");
//			}else{
//				$("#isFavoritesBtn").removeAttr("checked")
//			}
		}
		
	})

	/**默认是否加载收藏
	 */
	$.ajax({
		url:'../personal/favorites/showfavorites',
		data:{
			url: window.location.href
		},
		success:function(data){
			if(data!=null&&data.success==true){
				$("#isFavoritesBtn").attr("checked","checked");
			}else{
				$("#isFavoritesBtn").removeAttr("checked");
			}
		}
	})
	
})