$(function() {

	//保存
	$('#saveProductInformBtn').on('click',function(){
		var data=$('form').serializeArray();
		$.ajax({
			url : './saveproductinform',
		    type:'post',
			data : data,
			success : function(data) {
//				bootbox.alert("保存成功!");
				alert("提交成功!");
		    	window.close();  
			}
		})
	})
		
})