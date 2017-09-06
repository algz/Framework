package com.ras.comparison;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.searchParam.SearchParam;
import com.ras.searchParam.SearchParamService;
import com.ras.tool.CommonTool;
import com.ras.tool.ReturnVo;

import net.sf.json.JSONArray;



/*
 *  对比功能
 * 
 *  当@RequestMapping 标记在Controller 类上的时候，里面使用@RequestMapping 标记的方法的请求地址都是相对于类上的@RequestMapping 而言的；
 *  当Controller 类上没有标记@RequestMapping 注解时，方法上的@RequestMapping 都是绝对路径。
 *  这种绝对路径和相对路径所组合成的最终路径都是相对于根路径“/ ”而言的。
 */

@Controller   
@RequestMapping(value="/ras/comparison")
public class ComparisonController {
	
	@Autowired
	private ComparisonService service;
	
	@Autowired
	private SearchParamService searchParamService;
	
//	@Autowired
//	private ExcelService excelService;
	

    @RequestMapping(value={"","/"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  ComparisonIndex() {
    	Map<String, Object> map=new HashMap<String, Object>();
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("对比");
    	map.put("page", page);
    	
    	

    	
        return new ModelAndView("ras/comparison/comparison",map);
    }
    
    @RequestMapping(value={"/findmodelgird"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findModelGird(ComparisonVo vo,HttpServletRequest request,HttpServletResponse response) {
    	//Map<String, Object> map=new HashMap<String, Object>();
    	String modelName=request.getParameter("modelName");
    	service.findModelGird(vo);
    	CommonTool.writeJSONToPage(response,vo );
    	
    }
    
    
    @RequestMapping(value={"","/comparisondetail"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  comparisonDetail(HttpServletRequest request,HttpServletResponse response) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	String modelName=request.getParameter("modelName");
    	String basicID=request.getParameter("basicID");
    	map.put("modelName", modelName);
    	map.put("basicID", basicID);
    	Page page=new Page();
    	page.setHeader_h1("对比");
    	page.setHeader_small(modelName);
    	map.put("page", page);
    	
    	map.put("models", modelName.split(","));
    	map.put("searchTags", searchParamService.findAllParent());
    	
        return new ModelAndView("ras/comparison/comparisonDetail",map);
    }
    
    @RequestMapping(value={"/findcomparisondetailgrid"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findComparisonDetailGrid(ComparisonVo vo,HttpServletRequest request,HttpServletResponse response) {
    	String modelName=request.getParameter("modelName");
    	String basicID=request.getParameter("basicID");
    	if(modelName!=null){
    		vo.setData(service.findComparisonDetailGrid(modelName.split(","),basicID.split(",")));
    	}
    	CommonTool.writeJSONToPage(response,vo );
    }
    
    /**
     * 生成报告
     * @param request
     * @param response
     */
    @RequestMapping(value={"/savereport"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  saveReport(HttpServletRequest request,HttpServletResponse response) {
    	String reportName=request.getParameter("reportName");
    	String reportDes=request.getParameter("reportDes");
    	String[] reportContent=request.getParameterValues("reportContent[]");
    	service.saveReport(reportName, reportDes, reportContent);

    	CommonTool.writeJSONToPage(response," {\"success\":true}" );
    }
    
}
