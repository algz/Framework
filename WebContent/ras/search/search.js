$(function(){
	
  $("form").submit(function(){
  	var s=$("label.active :checkbox");
  	var ids="";
  	for(var i=0;i<s.length;i++){
  		if(i!=0){
  			ids+=",";
  		}
  		ids+=$(s[i]).attr("value");
  	}
  	if(ids!=""){
  		$("#ids").attr("value",ids)
  	}else{
  		bootbox.alert("请选择筛选条件!");
  		return false;
  	}
  	
  	
//    alert("提交");
  });
});