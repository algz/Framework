package com.ras.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.search.SearchTagService;
import com.ras.search.searchCriteria.SearchCriteriaService;
import com.ras.tool.CommonTool;
import com.ras.tool.ReturnVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


//import algz.platform.util.excel.ExcelView;


/*
 *  当@RequestMapping 标记在Controller 类上的时候，里面使用@RequestMapping 标记的方法的请求地址都是相对于类上的@RequestMapping 而言的；
 *  当Controller 类上没有标记@RequestMapping 注解时，方法上的@RequestMapping 都是绝对路径。
 *  这种绝对路径和相对路径所组合成的最终路径都是相对于根路径“/ ”而言的。
 */

@Controller   
@RequestMapping(value="/ras/search")
public class SearchController {
	
	@Autowired
	private SearchTagService searchTagService;
	
	@Autowired
	private SearchCriteriaService searchCriteriaService;
	
//	@Autowired
//	private ExcelService excelService;
	

	/**
	 * 查询首页
	 * @return
	 */
    @RequestMapping(value={"","/"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  SearchIndex() {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("查询");
    	map.put("page", page);
    	
    	map.put("searchTags", searchTagService.findAllParent());
    	
        return new ModelAndView("ras/search/search",map);
    }
    
    /**
     * 查询关键字
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value={"","/searchcriteria"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  SearchCriteria(HttpServletRequest request, HttpServletResponse response) {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();

    	Page page=new Page();
    	page.setHeader_h1("查询");
    	page.setHeader_small("查询关键字");
    	map.put("page", page);
    	
    	String str=request.getParameter("ids");
    	String[] ids=str.split(",");
    	map.put("searchTags", searchTagService.findAllByIds(ids));
    	
        return new ModelAndView("ras/search/searchCriteria",map);
    }
    
    
    @RequestMapping(value={"","/searchcriteriagird"})
    public void SearchCriteriaGird(HttpServletRequest request,HttpServletResponse response){
    	ReturnVo rtvo=new ReturnVo();
    	rtvo.setDraw(Integer.parseInt(request.getParameter("draw")));
    	rtvo.setRecordsTotal(2);
//    	rtvo.setRecordsFiltered(2);
    	rtvo.setData(searchCriteriaService.SearchCriteriaGird());//((new String[][]{{"a1","b1"},{"a111","b111"}}));
//    	List list=new ArrayList();
//    	list.add(new String[]{"a1","b1","c1","d1"});
//    	rtvo.setData(list);
//    	
//    	    	String  sss="{draw:1,recordsTotal:2,recordsFiltered:2,"
//    			+ "'data': [['a1','b1'],['a111','b111']]}";
//    	JSONArray ja=JSONArray.fromObject(searchCriteriaService.SearchCriteriaGird());
//		JSONObject jObject = JSONObject.fromObject(rtvo, new JsonConfig().copy());
//    	    	rtvo.setData(list);
		CommonTool.writeJSONToPage(response, rtvo);
		
    }
    
    /**
     * 查询关键字
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value={"","/searchsummarize"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  SearchSummarize(HttpServletRequest request, HttpServletResponse response) {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();

    	Page page=new Page();
    	page.setHeader_h1("查询");
    	page.setHeader_small("F-35战斗机");
    	map.put("page", page);
    	
//    	String str=request.getParameter("ids");
//    	String[] ids=str.split(",");
//    	map.put("searchTags", searchTagService.findAllByIds(ids));
    	
        return new ModelAndView("ras/search/searchSummarize",map);
    }
}
