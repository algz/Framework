/**
 * DataTable  checkbox
 * 
 * "columns" : [{"title" : "用户名",
					width:50,
					class : "center",
					render:function(data, type, row, meta){
					    //个人理解  --以及参数的应用场景
					    //data:当前cell的值  --主要是操作这个参数来做渲染
					    //type:数据类型,枚举类型dt内置定义的  --用处不大
					    //row:当前行的所有数据  --可以做来用级联(没办法更改其他cell的渲染)
					    //meta:它下面有三个参数
					    //row,col 是当前cell的横纵坐标(相对于左上角) --可以结合上个参数row做更加复杂的级联
					    //settings:dt的api实例,动态所有的参数信息都在里面  --这个很强大,获取参数信息就好,新手不要随便更改里面的参数信息
					    return '<label class="position-relative"><input type="checkbox" class="ace" /><span class="lbl"></span></label>'
					}
				}......
//									
 */
//1.checkbox 全选 [所有table]
$('table th input:checkbox').on('click' , function(){
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
			
});

//2.checkbox 单选 [所有table]
$('table tbody').on('click', 'tr', function () {
	$(this).siblings(".selected").find(":checked").removeAttr("checked"); //.siblings() 筛选所有同辈元素
	$(this).siblings(".selected").removeClass('selected');
	
	var curCheckbox=$(this).find(':checkbox')[0];
    if(curCheckbox==null){
	 	return;
	}
    curCheckbox.checked=!curCheckbox.checked;
    $(this).toggleClass('selected',curCheckbox.checked);
})

//3.checkbox 多选 [所有table]
$('table tbody').on('click', 'tr', function () {
	var curCheckbox=$(this).find(':checkbox')[0];
    if(curCheckbox==null){
	 	return;
	}
    curCheckbox.checked=!curCheckbox.checked;
    $(this).toggleClass('selected',curCheckbox.checked);
})