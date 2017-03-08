$(function(){
	$("#subbtn").click(function(){
		$("#highchart").highcharts({
		    // Highcharts 配置
		    title: {
		        text: '我是标题'
		    },
		    subtitle: {
		        text: '我是副标题'
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
		    },
		    series : [{
		        name : '',
		        data : [1,3,5,7,9]//[[1, 4], [3, 5], [2, 6]]
		    },{
		        name : '',
		        data : [2,4,6,8,10]//[[1, 4], [3, 5], [2, 6]]
		    }]
		});
	})
	
	
	
	
})


