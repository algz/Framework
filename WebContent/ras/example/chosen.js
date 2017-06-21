/**
 * <link rel="stylesheet" href="<%=basePath%>ras/common/css/chosen/chosen.css" />
 * 
 * <script src="<%=basePath%>ras/common/js/chosen/chosen.jquery.js"></script>
 * 
 * <select class="chosen-select">
 * </select>
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

//1.select value获取  
$(".chosen-select").val();

//2.select text获取，多选时请注意  
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