/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.analyze;

import java.io.IOException;
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
import com.ras.search.SearchTag;
import com.ras.search.SearchTagService;
import com.ras.tool.CommonTool;

import net.sf.json.JSONArray;

/*
 *  对比功能
 * 
 *  当@RequestMapping 标记在Controller 类上的时候，里面使用@RequestMapping 标记的方法的请求地址都是相对于类上的@RequestMapping 而言的；
 *  当Controller 类上没有标记@RequestMapping 注解时，方法上的@RequestMapping 都是绝对路径。
 *  这种绝对路径和相对路径所组合成的最终路径都是相对于根路径“/ ”而言的。
 */

@Controller   
@RequestMapping(value="/ras/analyze")
public class AnalyzeController {
	
	@Autowired
	private AnalyzeService service;
	
	@Autowired
	private SearchTagService searchTagService;
	

    @RequestMapping(value={"","/"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  AnalyzeIndex() {
    	Map<String, Object> map=new HashMap<String, Object>();
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("分析");
    	map.put("page", page);
    	
//    	List<SearchTag> list=searchTagService.findAllParent();
//    	for(SearchTag tag:list){
//    		if(!tag.getParent_id().equals("0")&&!tag.getUi_type().equals("number")&&!tag.getUi_type().equals("numberRegion")){
//    			list.remove(tag);
//    		}
//    	}
    	map.put("searchTags", searchTagService.findAllParent());
    	
        return new ModelAndView("ras/analyze/analyze",map);
    }
    
    @RequestMapping(value={"/findmodelgird"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findModelGird(AnalyzeVo vo,HttpServletRequest request,HttpServletResponse response) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	String modelName=request.getParameter("modelName");
    	vo.setData(service.findModelGird(modelName));
    	CommonTool.writeJSONToPage(response,vo );
    }
    
    
//    @RequestMapping(value={"/findcomparisondetailgrid"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
//    public void  findComparisonDetailGrid(AnalyzeVo vo,HttpServletRequest request,HttpServletResponse response) {
//    	Map<String, Object> map=new HashMap<String, Object>();
//    	String modelName=request.getParameter("modelName");
//    	if(modelName!=null){
//    		vo.setData(service.findComparisonDetailGrid(modelName.split(",")));
//    	}
//    	CommonTool.writeJSONToPage(response,vo );
//    }
    
    @RequestMapping(value={"/findmodelfortypeahead"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findModelForTypeahead(HttpServletRequest request,HttpServletResponse response) {
//    	Map<String, Object> map=new HashMap<String, Object>();
    	String modelName=request.getParameter("modelName");
    	try {
    		JSONArray ja=JSONArray.fromObject(service.findModelForTypeahead(modelName));
			response.getWriter().print(ja);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value={"/analyzechart"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  analyzeChart(HttpServletRequest request,HttpServletResponse response) {
    	String modelName=request.getParameter("modelName");
    	String[] axis=new String[2];
    	axis[0]=request.getParameter("xAxis");
    	axis[1]=request.getParameter("yAxis");
    	try {
    		JSONArray ja=service.analyzeChart(modelName.split(","), axis);
			response.getWriter().print(ja);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
