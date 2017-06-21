/**
<div>
	<ul class="pagination">
		<li class="disabled">
			<a href="#">
				<i class="ace-icon fa fa-angle-double-left"></i>
			</a>
		</li>
		<li class="active"><a href="#">1</a></li>
		<li><a href="#">2</a></li>
        ......
		<li><a href="#">5</a></li>
		<li>
			<a href="#">
				<i class="ace-icon fa fa-angle-double-right"></i>
			</a>
		</li>
	</ul>
</div>
*/
algz.page({
render:''
})
function page(option){
	if(option.render==null){
		return alert('必须设置render');
	};
	var defaultOption={
			render:option.render,
			// 每页显示的数目
			show_per_page:10,
			// 获取话题数据的数量
			number_of_items : $('#datas').children().size(),
			// 计算页数
			number_of_pages : Math.ceil(number_of_items / show_per_page)
	};

	init(defaultOption);
}

/**
 * @param {} option
 */
function init(option) {
	// 生成分页->上一页
	var page_info = 
	page_info += '<li class="disabled"><a href="#"><i class="ace-icon fa fa-angle-double-left"></i></a></li>'

	var current_link = 0;
	// 生成分页->页数
	while (option.number_of_pages > current_link) {
		if (current_link == 5) {
			break;
		}
		page_info += '<li><a href="#">' + (current_link + 1) + '</a></li>'
		current_link++;
	}

	// 生成分页->下一页
	page_info += '<li><a href="#"><i class="ace-icon fa fa-angle-double-right"></i></a></li>'

	// 设置隐藏域默认值
//	$('#current_page').val(0);
//	$('#show_per_page').val(show_per_page);
	
	// 加载分页
	// $('.pagination').html(page_info);
	var el = option.render || '.pagination'
	$(el).html(page_info);

	// 生成分页->左侧总数
	//$("#data-total-page").html(show_per_page + "条/页，共" + number_of_pages + "页")

	// 激活第一页，使得显示第一页
	//$('#data-pagination li').eq(1).addClass('active');
	// 隐藏该对象下面的所有子元素
	//$('#datas').children().css('display', 'none');
	// 显示第n（show_per_page）元素
	//$('#datas').children().slice(0, show_per_page).css('display','inline-block');
}

	
	// 上一页
	function previous(){
        // 当前页-1
        new_page = parseInt($('#current_page').val()) - 1;
        go_to_page(new_page);
    }
	
  	// 下一页
    function next(){
        // 当前页+1
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
}