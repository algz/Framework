       $(function () {
       	


       	
       	    //(1)提交表单数据
            $('#btnverify').bind('click',function () {
            	var url="http://jkbb.zgwhfe.com/Tools/VotingUpload.ashx?mycheck=" 
        			+ $("#sys_check").val() + "&myid=14285" 
        			+ "&myip=" + $('#myip').val() + "&" + new Date();
        		
            	//$('#iframe').attr('src','http://jkbb.zgwhfe.com/BabyDetail.aspx?BabyId=14285')
            	$('#framePage').attr('src',url)
            	$("#sys_check").val('');
            	$(".VerifyCode img").attr("src", "");
            	$(".VerifyCode img").attr("alt", "表单提交中...");
            	return ;
        	});
       	
        	
//        	var iframe = document.getElementById("framePage");
////iframe.src = "http://www.planabc.net";
//if (iframe.attachEvent){    
//    iframe.attachEvent("onload", function(){        
//        alert("Local reg iframe is now loaded.");    
//    });
//} else {    
//    iframe.onload = function(){        
//        alert("Local iframe is now loaded.");    
//    };
//}
        	//(2)内嵌页面加载完成
            $("#framePage").bind("load", function () {
//                $("#loadIP").trigger('click');
            	$(".VerifyCode img").attr("alt", "验证码接收中...");
            	$(".VerifyCode").trigger('click');
            });
        	
       	    //(3)验证码切换
            $(".VerifyCode").bind("click", function () {
                $(".VerifyCode img").attr("src", "http://jkbb.zgwhfe.com/Tools/VerifyCodeImage.ashx?time=" + Math.random());
            });
            
            //验证码输入窗口验证
			$("#sys_check").bind("keypress", function(e) {
				var keynum
				var keychar

				if (window.event) // IE
				{
					keynum = e.keyCode
				} else if (e.which) // Netscape/Firefox/Opera
				{
					keynum = e.which
				}
				if (keynum == 13) {
					$('#btnverify').trigger('click');
				}
					// keychar = String.fromCharCode(keynum)
					//alert(keychar)
				});
            
            //重加载页面获取Cookies
            $("#loadiframe").bind("click", function () {
            	$('#framePage').attr('src','http://jkbb.zgwhfe.com/BabyDetail.aspx?BabyId=14285');
            });
                        //重加载页面获取Cookies
            
            $("#loadIP").bind("click", function() {
						$.ajax({
									url : basePath + "/getIP",
									contentType : 'application/json',
									complete : function(data) {
										$('#myip').attr('value',data.responseText)
										//alert(data.responseText)
									}
								});
					});

            

        });
//
//
////判断浏览器ie6创建的div样式和非ie6创建的div样式
////创建div
//function showid_zzjs_net(idname, myid, myip) {
//	var isIe = (document.all) ? true : false;
//	var isIe6 = false;
//	var newbox = document.getElementById(idname);
//	newbox.style.zIndex = "9999";
//	newbox.style.display = "block";
//	newbox.style.position = !isIe6 ? "fixed" : "absolute";
//	newbox.style.top = newbox.style.left = "50%";
//	newbox.style.marginTop = -newbox.offsetHeight / 2 + "px";
//	newbox.style.marginLeft = -newbox.offsetWidth / 2 + "px";
//	var layer = document.createElement("div");
//	layer.id = "layer";
//	layer.style.width = layer.style.height = "100%";
//	layer.style.position = !isIe6 ? "fixed" : "absolute";
//	layer.style.top = layer.style.left = 0;
//	layer.style.backgroundColor = "#000";
//	layer.style.zIndex = "9998";
//	layer.style.opacity = "0.6";
//	document.body.appendChild(layer);
//	var sel = document.getElementsByTagName("select");
//	for (var i = 0; i < sel.length; i++) {
//		sel[i].style.visibility = "hidden";
//	}
//
//	function layer_iestyle() {
//		layer.style.width = Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth)
//+ "px";
//		layer.style.height = Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight) +
//"px";
//	}
//	function newbox_iestyle() {
//		newbox.style.marginTop = document.documentElement.scrollTop - newbox.offsetHeight / 2 + "px";
//		newbox.style.marginLeft = document.documentElement.scrollLeft - newbox.offsetWidth / 2 + "px";
//	}
//	if (isIe) { layer.style.filter = "alpha(opacity=60)"; }
//	if (isIe6) {
//		layer_iestyle();
//		newbox_iestyle();
//		window.attachEvent("onscroll", function () {
//			newbox_iestyle();
//		});
//		window.attachEvent("onresize", layer_iestyle);
//	}
//	var btnverify = document.getElementById("btnverify");
//
//	var btnverifyout = document.getElementById("btnverifyout");
//
//	btnverifyout.onclick = function () {
//		$(".VerifyCode img").attr("src", "http://jkbb.zgwhfe.com/Tools/VerifyCodeImage.ashx?time=" + new Date());
//		newbox.style.display = "none";
//		layer.style.display = "none";
//		for (var i = 0; i < sel.length; i++) {
//			sel[i].style.visibility = "visible";
//		}
//	};
//
//	$('#btnverify').onclick = function () {
//		return
//		$.ajax({
//			url: "http://jkbb.zgwhfe.com/Tools/VotingUpload.ashx?mycheck=" + $("#sys_check").val() + "&myid=" + myid + "&myip=" + myip + "&" + new Date(),
//			type: "post",
//			async: false,
//			success: function (result) {
//				var data = eval('(' + result + ')');
//				switch (data.msg) {
//					case 'exist':
//						alert("您已经投过票，请勿重新投票！");
//						break;
//					case 'success':
//						alert("感谢您的投票！");
//						$("#voting" + myid + "").empty();
//						$("#voting" + myid + "").append("票数：" + data.votenum);
//						break;
//					case "error":
//						alert("请正确输入验证码！");
//						break;
//					default:
//						alert(data.msg);
//						break;
//				}
//				// 关闭
//				newbox.style.display = "none";
//				layer.style.display = "none";
//				for (var i = 0; i < sel.length; i++) {
//					sel[i].style.visibility = "visible";
//				}
//			}
//		});
//	};
//
//	//layer.onclick=function(){newbox.style.display="none";layer.style.display="none";for(var i=0;i<sel.length;i++){
//	//        sel[i].style.visibility = "visible";
//	//    } 
//	//}
//}
//
//function GetVoting(idname, $ID, $IP) {
//	$.ajax({
//		url: "http://jkbb.zgwhfe.com/Tools/getDateTime.ashx",
//		type: "get",
//		dataType:'jsonp',
//		beforeSend :function(){
//alert('beforeSend')
//},
//error:function(data){
//alert('加载失败'+data);
//},
//
//complete: function(data){
//alert('complete'+data)
//},
//		success: function (data) {
//			alert(data)
//			var myjson = eval(data);
//			if (true == myjson[0].msgCTime) {
//				alert("投票已经结束！");
//				return;
//			}
//			// 更新验证码
//			$("#sys_check").val("");
//			$(".VerifyCode img").attr("src", "http://jkbb.zgwhfe.com/Tools/VerifyCodeImage.ashx?time=" + Math.random());
//			//显示层
//			showid_zzjs_net(idname, $ID, $IP);
//		}
//	});
//
//}


