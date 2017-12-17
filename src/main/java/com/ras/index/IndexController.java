package com.ras.index;

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

import com.ras.documnet.dataManager.DataService;
import com.ras.documnet.dataManager.DataVo;
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
@RequestMapping(value={"","/ras/"})
public class IndexController {

	

	/**
	 * 查询首页
	 * @return
	 */
    @RequestMapping(value={"","/","/index"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  SearchIndex() {
    	//跳转请求给另一个controller
        return new ModelAndView("redirect:/ras/searchtag/");
    }
    
    
    
    
    
}
