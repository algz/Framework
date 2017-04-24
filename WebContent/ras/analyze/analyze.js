$(function(){

	$("#dt-list-1 dd").each(function(index,el){
		if($(el).find(".btn-group :input").length==0){
			$(el).prev().remove();
			$(el).next().remove();
			$(el).remove();
		}
	})
	
	var chart=null;

   $('#modelNameBtn').typeahead(null,{
       source:function(query,process,aysprocess) {
       		var objs=$.get("./findmodelfortypeahead",{modelName:query},function(data){
//       		var data=['name1','name2'];
       			aysprocess(eval(data))
       			//process(eval(data))
       		});//['name1','name2']
    	}
   });
$('#modelNameBtn').keydown(function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];           
             if(e && e.keyCode==13){ // enter 键
                var data=$('#modelNameBtn').typeahead('val');
				if(data==null){
					return;
				}
				var element=$(":hidden[name=modelName]");
				var s=true;
				element.val("");
				$("#modelNameContent span").each(function(i){
					var v=$(this).clone().children().remove().end().text();
					if(v==data){
						s=false;
						return ;
					}
					element.val(v+(element.val()==""?"":(","+element.val())))
				})
				 if(s){
				 	$("#modelNameContent").append('<span class="btn btn-xs ">'+data+'<button class="close" type="button">×</button></span> ');
				 }
				 element.val(data+(element.val()==""?"":",")+element.val())

				 e.preventDefault();
            }
})

	$("#modelNameContent").on('click','button.close',function(){
		$(this).parent().remove();
		var s=$(":hidden[name=modelName]");
		s.val("");
		$("modelNameContent span").each(function(i){
			var v=$(this).clone().children().remove().end().text();
			s.val(s.val()+(i!=0?",":"")+v)
		})
	})
   
	////////////////////////////////////
	
	$("#xAxisBtn").on('click',function(){
		$(".modal-body label").removeClass("active");
		$('#modal-form').modal();
		$('#modal-form .modal-title').text("x轴")
	})
   
	$("#yAxisBtn").on('click',function(){
		$(".modal-body label").removeClass("active");
		$('#modal-form').modal();
		$('#modal-form .modal-title').text("y轴")
	})
	
	$(".modal-body label").on('click',function(){
		$(".modal-body label").removeClass("active");
	})
	
	$("#confirmBtn").on('click',function(e){
		var title=$('#modal-form .modal-title').text();
		var val=$(".modal-body label.active input").attr("name");
		var txt=$(".modal-body label.active span").text();
		if(val==""||txt==""){
			return;
		}
		if(title=="x轴"){
			$("#xAxisTxt").val(txt)
			$(":hidden[name=xAxis]").val(val)
		}else if(title=="y轴"){
			$("#yAxisTxt").val(txt)
			$(":hidden[name=yAxis]").val(val)
		}
		$('#modal-form').modal("hide");
	})
	//////////////////////////////////
	
	$("#subbtn").click(function(){
//		    chart.addSeries({
//                data:[200]
//            });
//		return;
		var modelName=$(":input[name=modelName]").val(),
			xAxis=$(":input[name=xAxis]").val(),
			yAxis=$(":input[name=yAxis]").val();
		if(modelName==""||(xAxis==""&&yAxis=="")){
			alert("请输入数据!");
			return;
		}
		$.ajax({
			url:'./analyzechart',
			data:{
				modelName:modelName,
				xAxis:xAxis,
				yAxis:yAxis
			},
			success:function(data){
//				if(chart==null){
					chart=Highcharts.chart("highchart",{
				    	// Highcharts 配置
						credits: {
			            	enabled: false
			       	 	},
						chart: {
            				type: $('#chartType').val()//'column'// 'scatter'//,
            				//zoomType: 'xy'
	        				},
					    title: {
					        text: ""//'我是标题'
					    },
					    subtitle: {
					        //text: '我是副标题'
					    },
					    xAxis:{
					        title:{
					            text:'x轴标题'
					        }
					    },
					    yAxis:{
					        title:{
					            text:'y轴标题'
					        }
					    }/*,
					    series : [{
					        name : '',
					        data : [1,3,5,7,9]//[[1, 4], [3, 5], [2, 6]]
					    },{
					        name : '',
					        data : [2,4,6,8,10]//[[1, 4], [3, 5], [2, 6]]
					    }]*/
					});
//				}
				
				chart.xAxis[0].setTitle({
            		text: $('#xAxisTxt').val()
        		});
				chart.yAxis[0].setTitle({
            		text: $('#yAxisTxt').val()
            		//x: -20,
            		//rotation: 90
        		});
				chart.series=[];
				//data="[{names:'f-32',datas:[[100,100],2000]},{names:'f-35',datas:[200,2200]}]"
				var objs=eval(data);
				
				for(var i=0;i<objs.length;i++){
					chart.addSeries({
						name:objs[i].names,
    	            	data:objs[i].datas
	            	});
				}

			}
		})
	})
	
	
	
	
	
})


