/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.productSearch;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.documnet.dataManager.DataVo;
import com.ras.index.Page;
import com.ras.tool.CommonTool;

@Controller   
@RequestMapping(value="/ras/productsearch")
public class ProductSearchControl{
	
	@Autowired
	private ProductSearchService service;
	
	@RequestMapping(value={"","/"})
	public ModelAndView indexPage(){
    	Map<String, Object> map=new HashMap<String, Object>();
//    	Iterator it=SecurityUtils.getSubject().getSession().getAttributeKeys().iterator();
//    	while(it.hasNext()){
//    		Object key=it.next();
//    		Object val=SecurityUtils.getSubject().getSession().getAttribute(key);
//    		System.out.println(key+"="+val);
//    	}
    	
    	Page page=new Page();
    	page.setHeader_h1("成品查询");
    	page.setHeader_small("查询");
    	map.put("page", page);
    	map.put("productCombox", service.findProductCombox());
        return new ModelAndView("ras/productsearch/productSearch",map);
	}
	
	/**
	 * 查询机型表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/findproductgrid"})
    public void findProductGrid(ProductSearchVo<AircraftOverview> vo,HttpServletResponse response){
    	service.findProductGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
    
}