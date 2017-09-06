package algz.platform.util.excel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import algz.platform.util.excel.ExcelService;
import algz.platform.util.excel.ExcelView;

public class ExcelTestController {

	@Autowired
	private ExcelService service;
	
	/** 
	    * 导出Excel 
	    * @param model 
	    * @param projectId 
	    * @param request 
	    * @return 
	    */  
	   @RequestMapping(value="/dcExcel",method=RequestMethod.GET)  
	    public ModelAndView toDcExcel(ModelMap model, HttpServletRequest request,HttpServletResponse response){  
	       List<String> list = new ArrayList<String>();  //测试数据没有用到  
//	       Map map = new HashMap();    
	       list.add("test1");    
	       list.add("test2");    
	       model.put("list", list);    
	       ExcelView viewExcel = new ExcelView();    
	       try {
	    	   viewExcel=service.createExcelView(model, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	       return new ModelAndView(viewExcel, model);   
	   }  
}
