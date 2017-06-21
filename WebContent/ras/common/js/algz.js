

/**
 * 1.类级别扩展
 * 调用方式: var i = $.add(3,2); 
 * 
 */
$(function(){
	$.extend({
		add:function(a,b){return a+b;} , 
		algz:function(){
			
		}
	})
})

/**
 * 2.对象级别调用
 * 调用方式: $('input[type=checkbox]').check(); 
 * 
 */
$(function(){
	$.fn.extend({ 
		check:function(){ 
			return this.each({ 
				this.checked=true; 
			}); 
		}
	}); 
})

/**
 * 3.扩展
 * 
 */
$(function(){
	$.xy = { 
		add:function(a,b){return a+b;} , 
		minus:function(a,b){return a-b;}, 
		voidMethod:function(){ alert("void"); } 
	}; 
	var i = $.xy.add(3,2); 
	var m = $.xy.minus(3,2); 
	$.xy.voidMethod(); 
})

/**
 * 
 */
$(function(){
	var algz={};
	algz.option={
	
	}
	
	
	//////////page////////////

	/**
	* 	
	algz.page({
		render:'',
		num:10
	})
	*/
	algz.page=function(option){
		
		//每页显示的数目
        var show_per_page = option.num||10;
        //获取话题数据的数量
        var number_of_items = $('#datas').children().size();
        //计算页数
        var number_of_pages = Math.ceil(number_of_items/show_per_page);
        //设置隐藏域默认值
        $('#current_page').val(0);
        $('#show_per_page').val(show_per_page);

        //生成分页->上一页
        var page_info =// '<li><a class="previous_link" href="javascript:previous();">&laquo;</a></li>';
        page_info='<li class="disabled"><a href="#"><i class="ace-icon fa fa-angle-double-left"></i></a></li>'
       
        var current_link = 0;
        //生成分页->页数
        while(number_of_pages > current_link){
            if(current_link == 5){
                break;
            }
            //page_info += '<li><a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a></li>';
            page_info +='<li><a href="#">'+(current_link+1)+'</a></li>'
            current_link++;
        }
        
        //生成分页->下一页
        //page_info += '<li><a class="next_link" href="javascript:next();">&raquo;</a></li>';
        page_info+='<li><a href="#"><i class="ace-icon fa fa-angle-double-right"></i></a></li>'
        
        //加载分页
        //$('.pagination').html(page_info);
        var el=option.render||'.pagination'
       	$(el).html(page_info);

        //生成分页->左侧总数
        $("#data-total-page").html(show_per_page+"条/页，共"+number_of_pages+"页")

        //激活第一页，使得显示第一页
        $('#data-pagination li').eq(1).addClass('active');
        //隐藏该对象下面的所有子元素
        $('#datas').children().css('display', 'none');
        //显示第n（show_per_page）元素
        $('#datas').children().slice(0, show_per_page).css('display', 'inline-block');
	}
	
	//上一页
	page.previous=function(){
        //当前页-1
        new_page = parseInt($('#current_page').val()) - 1;
        go_to_page(new_page);
    }
	
  	//下一页
    page.next=function next(){
        //当前页+1
        new_page = parseInt($('#current_page').val()) + 1;
        go_to_page(new_page);
    }
  
    //跳转某一页
    function go_to_page(page_num){
        $('.page_link[longdesc=' + page_num +']').parent().addClass('active').siblings('.active').removeClass('active');
        //获取隐藏域中页数大小（每页多少条数据）
        var show_per_page = parseInt($('#show_per_page').val());
        //得到元素从哪里开始的片数（点击页 * 页大小）如点击第5页，5条/页。则开始为25
        start_from = page_num * show_per_page;
        //得到结束片的元素数量，如果开始为25，5条/页，则结束为30
        end_on = start_from + show_per_page;
        //隐藏所有子div元素的内容,显示具体片数据，如显示25~30
        $('#datas').children().css('display', 'none').slice(start_from, end_on).css('display', 'inline-block');

        //每页显示的数目
        var show_per_page = 10;
        //获取总数据的数量
        var number_of_items = $('#datas').children().size();
        //计算页数
        var number_of_pages = Math.ceil(number_of_items/show_per_page);
        //在页数区域内则做页偏移
        if( (page_num >= 2) && (page_num <= (number_of_pages - 3)) ){
            //生成分页->上一页
            var page_info = '<li><a class="previous_link" href="javascript:previous();">&laquo;</a></li>';

            var p = page_num;
            var i = page_num - 2;
            var j = page_num + 2;
            //生成分页->前2页
            while(page_num > i){
                page_info += '<li><a class="page_link" href="javascript:go_to_page(' + i +')" longdesc="' + i +'">'+ (i + 1) +'</a></li>';
                i++;
            }
            //生成分页->当前页
            page_info += '<li><a class="page_link" href="javascript:go_to_page(' + page_num +')" longdesc="' + page_num +'">'+ (page_num + 1) +'</a></li>';
            //生成分页->后2页
            while(p < j){
                if(p == number_of_pages){
                    break;
                }
                page_info += '<li><a class="page_link" href="javascript:go_to_page(' + (p + 1) +')" longdesc="' + (p + 1) +'">'+ (p + 2) +'</a></li>';
                p++;
            }
            //生成分页->下一页
            page_info += '<li><a class="next_link" href="javascript:next();">&raquo;</a></li>';

            //加载分页
            $('.pagination').html(page_info);
            $('.page_link[longdesc=' + page_num +']').parent().addClass('active').siblings('.active').removeClass('active');
        }
        else{ //否则不偏移
            //激活某一页，使得显示某一页
            $('.page_link[longdesc=' + page_num +']').parent().addClass('active').siblings('.active').removeClass('active');
        }

        //更新隐藏域中当前页
        $('#current_page').val(page_num);
    }
})




;(function($){
    $.fn.extend({
    "page":function(form){
    var $this=this;
    //定义分页结构体
    var pageinfo={
             url:$(this).attr("url"),
             currentPage : $(this).attr("currentPage")*1, // 当前页码
             pageCount : $(this).attr("pageCount")*1 // 总页码
             
    };
     
     
    if(pageinfo.pageCount<2)
        return false;
    //初始起始页数、结束页数
            var start=0,end=10;
    if(pageinfo.currentPage>=10)         
        start=pageinfo.currentPage-5;
 
      if(pageinfo.pageCount>pageinfo.currentPage+5)
            end=pageinfo.currentPage+5;
        else
            end=pageinfo.pageCount;
    var html=[];
    html.push("<ul>");
    if(pageinfo.currentPage!=1)
        //如果不是第一页则有前一页
        html.push("<li class='page_prev'><a>前一页</a></li>");
    if(pageinfo.pageCount>10&&pageinfo.currentPage>9)
        html.push("<li class='nomal'><a >1</a></li>");
    for(var i=start;i<end;i++){
        if((i+1)==pageinfo.currentPage)
            html.push("<li class='active'><a >"+(i+1)+"</a></li>");
        else
            html.push("<li class='nomal'><a >"+(i+1)+"</a></li>");
    }
     
    if(pageinfo.pageCount>10&&pageinfo.currentPage<pageinfo.pageCount-4)
        html.push("<li class='nomal'><a >"+pageinfo.pageCount+"</a></li>");
    if(pageinfo.currentPage!=pageinfo.pageCount)
        html.push("<li class='page_next'><a >后一页</a></li>");
    html.push("</ul>");
     
     
    $this.html(html.join("")); 
    //绑定数据处理函数
    $this.find(".nomal a").bind("click",function(){
                redirectTo($(this).html());
    });
    $this.find(".page_prev a").bind("click",function(){
                  redirectTo(pageinfo.currentPage-1);
    });
    $this.find(".page_next a").bind("click",function(){
         redirectTo(pageinfo.currentPage+1);
    });
     
 
 
    function redirectTo(page){
         
            var url=pageinfo.url;
            if(url.indexOf("?")==-1)
                url+="?";
            else
                url+="&";
            url+="page="+page;
            $("form[name='"+form+"']").attr("action",url);
            $("form[name='"+form+"']").submit();
             
             
    }
    return $this;
    }
    });
 
    })(jQuery);