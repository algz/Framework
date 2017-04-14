package com.ras.search.searchCriteria;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.search.SearchTagService;

@RequestMapping("/ras/search/searchcriteria")
@Controller  
public class SearchCriteriaController {

	
	@Autowired
	private SearchTagService searchTagService;
	
	   /**
     * 查询关键字
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value={"","/"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  SearchCriteria(HttpServletRequest request, HttpServletResponse response) {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();

    	Page page=new Page();
    	page.setHeader_h1("查询");
    	page.setHeader_small("查询关键字");
    	map.put("page", page);
    	
    	String str=request.getParameter("ids");
    	if(str!=null){
        	String[] ids=str.split(",");
        	map.put("searchTags", searchTagService.findAllByIds(ids));
    	}
    	return new ModelAndView("ras/search/searchCriteria",map);
    }
}
