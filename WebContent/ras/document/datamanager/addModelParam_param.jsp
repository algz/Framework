<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<%@taglib prefix="form" tagdir="/WEB-INF/tags/uiframe/form" %>
<%@page import="net.sf.json.JSONObject" %>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<form id='modelParamForm' action="./savemodelparam" method="post">
	<input name="overviewID" type="hidden" value="<%=request.getParameter("overviewID") %>"/>
	<input name="basicID" type="hidden" value="<%=request.getParameter("basicID") %>"/>
	
	
	<!-- <input name="editor" type="hidden" value="${EDITOR}"/> -->
	<c:forEach var="dataParam" items="${paramMap }" varStatus="status">
	<c:if test="${fn:length(dataParam.dataMap)!=0 }">
	
	<div>
		<h3 class="header smaller lighter purple">
			${dataParam.caption}
		</h3>
							
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="form-horizontal" role="form">
								
				
					<c:forEach var="basicItem" items="${dataParam.dataMap }">
					<%
					String validateType=null; 
					String extDataValue=null;
					//新建/修改状态验证
					if(request.getAttribute("isModify")!=null&&request.getAttribute("isModify").equals("true")){
						//JSONObject paramMap=(JSONObject)pageContext.findAttribute("paramMap");
						JSONObject jo=(JSONObject)pageContext.findAttribute("basicItem");
						validateType=jo.get("validate")==null?"":jo.get("validate").toString();
						if(jo.get("elType")!=null){
							switch (jo.get("elType").toString()){
							case "number":
							case "numberRegion":
								validateType+=" number ";
								jo.put("elType", "text");
								break;
							case "checkbox":
								extDataValue=jo.getString("checkboxVal");
								jo.put("elType", "select");
								//jo.put("elValue", "中国");
								//System.out.println(extDataValue);
								break;
							}
						}
					}
					%>
						<form:form-group id="${basicItem.elID }" isMultiple="1" type="${basicItem.elType}" label="${basicItem.elLabel }" value="${basicItem.elValue}" readonly="${basicItem.readonly}" extData="<%=extDataValue %>" simpleValidate="<%=validateType %>"/>   
					</c:forEach>
				</div>
			</div><!-- /.col -->
		</div>
	</div>
	
	</c:if>
</c:forEach>

	<div class="row">
		<div class="col-sm-offset-6">
			<div class="btn-group">
				<input type="submit" id="addModelparam" class="btn btn-primary" value="保存"/>
			</div>
			<div class="btn-group">
				<a class="btn btn-primary" href="javascript:history.back()">取消</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">

	
	$(function(){
		
		
		$("#modelParamForm").submit(function(){
			alert("提交成功!");
			return "";
		    //window.close();  
		}); 
		
		$('.chosen-select').chosen({
			allow_single_deselect : true,
			width : '100%',
			disable_search:true //关闭搜索框,默认为false.
		}).on('chosen:no_results', function(e, params) {
			var v=params.chosen.search_field.val(); 
			//alert(1);
		});
		
		
		
		/*$("select[extCheckbox]").each(function(index,element){
			$.ajax({
				url:'./findtablesql',
				data:{
					tableName:$(this).attr('extCheckbox')
				},
				success:function(data){
					element.options.add(new Option());
					$(element).attr('multiple',"")
					for(var i=0;i<data.vals.length;i++){
						element.options.add(new Option(data.vals[i],data.vals[i])); //这个兼容IE与firefox 
						//$(this).append('<option>'+data.vals[i]+'</option>')
					}
					  $(element).chosen({
							allow_single_deselect : true,
							disable_search:true, //关闭搜索框,默认为false.
						    width: '100%'
						}); 
				}
			})
		}) */

		
		 /**
         * 表单验证
         */
        var validator = $('#modelParamForm').validate({
        		//debug:true,//true.即不提交表单,默认为false.
        		//url:'',//=form-action的值.
        		/**
        		 * errorElement：类型 String，默认 "label"。指定使用什么标签标记错误。
        		 */
				errorElement : 'div', 
				//wrapper：类型 String，指定使用什么标签再把上边的 errorELement 包起来。
				//wrapper:"li"
				/**
				 * errorClass：类型 String，默认 "error"。指定错误提示的 css 类名。
				 */
				errorClass : 'help-block', 
				/**
				 * focusInvalid：类型 Boolean，默认 true。提交表单后，未通过验证的表单（第一个或提交之前获得焦点的未通过验证的表单）会获得焦点。
				 */
				//focusInvalid : false,
				/**
				 * //ignore：忽略某些元素不验证. ignore: ".ignore"
				 */
				//在使用jquery的chosen下拉列表的插件时，碰到了使用jquery.validate.js的冲突，不能进行空值校验,chosen插件本身自动隐藏了空值，所以校验不到.
				ignore: ":not(:text,select)" , //忽略:不包括text和select控件. 必须设置这值,此UI框架使的插件都以hidden隐藏原控件.
				/**
				 * rules：自定义规则，key:value 的形式，key 是要验证的元素(name属性)，value 可以是字符串或对象。
				 */
				rules : {
					name : "required",
					enname: "required",
					'ui_type':'required',
					parent_id:'required',
					sequence:{
						digits:true,
						max:99
					}
				},
				/**
				 * messages：自定义的提示信息，key:value 的形式，key 是要验证的元素(name属性)，value 可以是字符串或函数。
				 */
				messages : {
					//name : '请输入名称!',
					//enname : '请输入名称!',
					//ui_type : '请选择选项!',
					//parent_id : '请选择选项!'
				},
				/**
				 * element出错时触发
				 * (highlight：可以给未通过验证的元素加效果、闪烁等。)
				 */
				highlight: function (e) {
					$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
				},
				/**
				 * element通过验证时触发
				 */
				unhighlight: function(element) {
            		$(element).closest('.form-group').removeClass('has-error');
        		},
				//submitHandler:function(form){
				// alert("提交事件!");
				// form.submit();
				// },
				/**
				 * success：要验证的元素通过验证后的动作，如果跟一个字符串，会当作一个 css 类，也可跟一个函数。
				 */
				success: function (e) {
						$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
						$(e).remove();
				},
				/**
				 * 跟一个函数，可以自定义错误放到哪里。
				 */
				errorPlacement: function (error, element) {
						if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
							var controls = element.closest('div[class*="col-"]');
							if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
							else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
						}
						else if(element.is('.select2')) {
							error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
						}
						else if(element.is('.chosen-select')) {
							error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
						}
						else error.insertAfter(element.parent());
					}
			});
	})
	</script>
</form>
