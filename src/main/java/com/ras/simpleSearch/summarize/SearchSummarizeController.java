package com.ras.simpleSearch.summarize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.documnet.dataManager.DataService;
import com.ras.documnet.dataManager.DataVo;
import com.ras.documnet.dataManager.productData.ProductManagerService;
import com.ras.index.Page;
import com.ras.searchParam.SearchParamService;
import com.ras.searchParam.searchCriteria.SearchCriteriaService;
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
@RequestMapping(value="/ras/simplesearch/searchsummarize")
public class SearchSummarizeController {
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private ProductManagerService productManagerService;
  
    /**
     * 查询关键字
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value={"","/"})  //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  searchSummarize(HttpServletRequest request, HttpServletResponse response) {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
    	
    	map.put("tab", request.getParameter("tab"));
    	
    	DataVo<?> vo=new DataVo();
    	vo.setOverviewID(request.getParameter("overviewID"));
    	vo.setBasicID(request.getParameter("basicID"));
    	vo.setOption("load");
    	JSONObject jo=dataService.addModelParamPage(vo);
    	if(jo!=null){
    		map.putAll(jo);
        	
    		Page page=new Page();
        	page.setHeader_h1("查询");
        	page.setHeader_small(jo.getString("modelName")+"("+jo.getString("dataSource")+")");
        	map.put("page", page);
        	
        	//成品库
        	map.put("productParamValue", productManagerService.findProductInform(vo.getOverviewID()));

        	
//        	String test="[{url:'/upload/photo/Chrysanthemum.jpg',title:'整体图1'},{url:'/upload/photo/Desert.jpg'},{url:'/upload/photo/Hydrangeas.jpg'}]";
        	map.put("integralGraph",dataService.findModelImageParam("整体图",vo.getOverviewID()));
        	map.put("threeGraph",dataService.findModelImageParam("三面图",vo.getOverviewID()));
        	map.put("surfaceGraph",dataService.findModelImageParam("外观图",vo.getOverviewID()));
        	map.put("surfaceGraph",dataService.findModelImageParam("其它图",vo.getOverviewID()));
    	}
    	
        return new ModelAndView("ras/simplesearch/summarize/searchSummarize",map);
    }
    
    @RequestMapping("/savemodelparam")
    public void saveModelParam(HttpServletRequest request,HttpServletResponse response){
    	String dataSource=request.getParameter("dataSources");
    	String paramName=request.getParameter("paramName");
    	String paramValue=request.getParameter("paramValue");
    	String overviewID=request.getParameter("overviewID");
    	String msg="";
    	CaseInsensitiveMap map=new CaseInsensitiveMap();
    	map.put("OVERVIEWID", overviewID);
    	map.put("MAININFO", null);
//    	map.put("basicID", "");
    	map.put("datasources", dataSource);
    	map.put(paramName, paramValue);
    	try {
			dataService.saveModelParam(map);
		} catch (Exception e) {
			msg=e.getLocalizedMessage();
		}
		CommonTool.writeJSONToPage(response," {\"success\":true,\"msg\":\""+msg+"\"}");
    }
    
}
