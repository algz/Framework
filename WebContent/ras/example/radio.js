/**
<input name="pic-radio" type="radio" class="ace" checked value="整体图">
<input name="pic-radio" type="radio" class="ace" value="三面图">
<input name="pic-radio" type="radio" class="ace" value="外观图">
 */
$(function(){
	//获取radio的值,也是用checked.
	alert($(':checked[name="pic-radio"]').val())
})
