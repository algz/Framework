package com.ras.searchParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.ras.documnet.data.DataService;
import com.ras.documnet.data.DataVo;
import com.ras.index.Page;
import com.ras.searchParam.SearchParamService;
import com.ras.searchParam.searchCriteria.SearchCriteriaService;
import com.ras.searchParam.searchCriteria.SearchCriteriaVo;
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
@RequestMapping(value="/ras/searchparam")
public class SearchParamController {
	
	@Autowired
	private SearchParamService service;
	
	@Autowired
	private SearchCriteriaService searchCriteriaService;
	
	@Autowired
	private DataService dataService;
	
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
    	
    	map.put("searchTags", service.findAllParent());
    	
        return new ModelAndView("ras/searchparam/search",map);
    }
    
    @RequestMapping(value={"/addnotefortaginput"})
    public void addNoteForTagInput(HttpServletRequest request,HttpServletResponse response){
    	String overviewID=request.getParameter("overviewID");
    	String inputNames=request.getParameter("inputName");
    	if(overviewID==null||inputNames==null){
    		//return "{\"success\":false}";
    	}
    	JSONObject jo=service.addNoteForTagInput(overviewID, inputNames.split(","));
    	jo.put("\"success\"", true);
//    	String msg="{\"success\":true,"
//    			+ "inputs:[{name:'aircraftType',vals:[{dataSource:'飞机手册1',inputValue:'美国1'},{dataSource:'飞机手册2',inputValue:'美国2'}]}]}";
		CommonTool.writeJSONToPage(response, jo);
    	//return msg;//"{\"success\":true}";
    }
    
    
    
    
}
