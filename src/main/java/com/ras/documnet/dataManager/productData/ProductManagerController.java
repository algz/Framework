package com.ras.documnet.dataManager.productData;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.aircraftPicture.AircraftPicture;
import com.ras.index.Page;
import com.ras.tool.CommonTool;

@Controller
@RequestMapping("/ras/document/datamanager/productData")
public class ProductManagerController {
	
	@Autowired
	private ProductManagerService service;
	
	@RequestMapping({"","/"})
	public ModelAndView productManagerIndex(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		String modelName=request.getParameter("modelName");
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("("+modelName+")成品查询参数");
    	map.put("page", page);
    	
    	map.putAll(request.getParameterMap());
    	String overviewID=request.getParameter("overviewID");
    	
    	if(overviewID!=null){
    		map.put("productParamValue", service.findProductInform(overviewID));
    	}

        return new ModelAndView("ras/document/datamanager/productData/productManager",map);
	}
	
	@RequestMapping({"/saveproductinform"})
	public void saveProductInform(HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        Map<String,String[]> m=request.getParameterMap();
        service.saveProductInform(m);

      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,msg ); 
	}
}
