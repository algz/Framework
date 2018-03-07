/**
 * <link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />
 * 
 * <script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>
 * 
 * <select class="chosen-select">
   </select>
 * 
 * JSP TAG 示例: 
 * <%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %> 						
 * <form:form-group id="parent_id" label="父节点" value="${sequence}" type="select">
		<option value="1" >基本</option>
		<option value="2" >重量</option>
		<option value="3" >布局</option>
		<option value="4" >性能</option>
		<option value="5" >动力</option>
		<option value="6" >系统</option>
	</form:form-group>
	
	JS:
	$('.chosen-select').chosen({
		allow_single_deselect : true,
		width : '40%',
		disable_search:true //关闭搜索框,默认为false.
	}); 
 * 
 * 本地select,美化UI.
 * 
 * @param {} select
 * @return {}
 */

//1.获取选中的值
//单选
$(".chosen-select").val();
//多选取值
var els=$(".chosen-select option:selected");
var val="";
for(var i=0;i<els.length;i++){
	if(val!=""){
		val+=",";
	}
	val+=els[i].value; //els[i] 为html元素.
}

//2.获取选中的文本(仅单选)，多选时请参照以上多选取值
$(".chosen-select option:selected").text();

//3.清除选中
$(".chosen-select").val("");
$(".chosen-select").trigger("chosen:updated");

//4.设置选中的值
$(".chosen-select").val("选中的值");
$(".chosen-select").trigger("chosen:updated");

//5.设置选中的文本
$(".chosen-select").text("选中的值");
$(".chosen-select").trigger("chosen:updated");
//每次设置时都要触发更新这个选择事件。
//*监听的选择改变事件：
$(".chosen-select").chosen().change(function(){
    //do something...
});

//6.设置多选
$(".chosen-select").attr("multiple",true); 

//7.设置未找到时的显示文字
$(".chosen-select").chosen({ 
        no_results_text : "未找到此选项!", 
        width:"70%" 
});

//8.增加option
//var sel= document.getElementById(".chosen-select"); 
//sel.options.add(new Option("请选择","")); //dom方法增加,但也必须调用$(".chosen-select").trigger("chosen:updated");
$(".chosen-select").append("<option value='"+1+"'>"+2+"</option>"); //jquery方法增加. 
$(".chosen-select").trigger("chosen:updated"); //必须设置,让chosen更新

//9.Chosen 实例化完成时触发(必须放到.chosen()前,进行绑定)
$('.chosen-select').on('chosen:ready',function(e, params){
	$.ajax({
		url:'../analyze/getallaircraftforselect',
		data:'',
		success:function(data){
			var obj=JSON.parse(data);
			//.......
		}
	})
}).chosen({
	allow_single_deselect : true,
	width : '40%',
	placeholder_text_multiple:'请选择一项'
	//disable_search:true //关闭搜索框,默认为false.
})

//11.获得当前文本输入框(text)的数据=chosen.search_field.val()
$('.chosen-select').on('chosen:no_results', function(e, params) { //params参数下chosen属性指向当前对象
	var v=params.chosen.search_field.val(); 
})
