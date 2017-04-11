<!-- 
采用twitter 0.11.1, http://twitter.github.io/typeahead.js/

class:
 必选typeahead; scrollable 可选.

ace:
input.typeahead.scrollable ~ .tt-dropdown-menu,tt-menu {
  max-height: 200px;
  overflow-y: auto;  
}

js: 
source:数组或函数.
函数时有三个参 数:
query值是输入的值;
process函值是如果本地调用,则调用process();
asyprocess函数是远程(AJAX)调用返回结果时,调用asyprocess().
   $('#modelNameBtn').typeahead(null,{
       source:function(query,process,asyprocess) {
       			var objs=['name1','name2'];
       			process(objs)
//       		var objs=$.get("./findmodelfortypeahead",{modelName:query},function(data){
//       			var objs=['name1','name2'];
//       			aysprocess(objs);
//       		});//['name1','name2']
    	}
   });
  -->
<input id="modelNameBtn" class="typeahead scrollable" type="text" placeholder="机型名称" />
