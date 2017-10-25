/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.personal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ras.comparison.ComparisonVo;
import com.ras.index.Page;
import com.ras.personal.faverites.FavoritesVo;
import com.ras.tool.CommonTool;

import algz.platform.util.excel.ExcelService;
import algz.platform.util.excel.ExcelView;

@Controller
@RequestMapping("/ras/personal")
public class PersonalController{
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private PersonalService service;
	
	@RequestMapping({"","/"})
	public ModelAndView PersonalIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("个人设置");
    	map.put("page", page);
		return new ModelAndView("ras/personal/personal",map);
	}

    
//	/** 
//	    * 导出Excel 
//	    * @param model 
//	    * @param projectId 
//	    * @param request 
//	    * @return 
//	    */  
//	@RequestMapping(value = "/exportreporttoexcel")
//	public ModelAndView exportReportToExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
//		//reportID
//		
//		HSSFWorkbook wb=service.exportReportToExcel(model);
//		ExcelView view=new ExcelView();
//		view.buildExcelDocument(model, wb/*generateWorkbook(model)*/, request, response);
////		return view;
//		
//		ExcelView viewExcel = null;
//		try {
//			viewExcel = excelService.createExcelView(model, request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(view);
//	}
}