/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.documnet.paramConfig.productParam;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.aircraftProductParam.AircraftProductParam;
import com.ras.index.Page;
import com.ras.searchParam.SearchParam;
import com.ras.searchParam.SearchParamVo;
import com.ras.tool.CommonTool;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/ras/document/paramconfig/productparam")
public class ProductParamController{
	
	@Autowired
	private ProductParamService service;
	
	@RequestMapping({"","/"})
	public ModelAndView productParamIndex(){
		Map<String, Object> map=new HashMap<String, Object>();
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("成品参数配置");
    	map.put("page", page);
    	
        return new ModelAndView("ras/document/paramconfig/productparam/productParam",map);
	}
	
    /**
     * 查询搜索标签树
     * @param vo
     * @param request
     * @param response
     */
    @RequestMapping(value={"/getsearchtreeviewnode"})
    public void getProductTreeNode(HttpServletRequest request,HttpServletResponse response){
    	//String s="[{text:'Parent 1',nodes: [{text: 'Child 1'}]}]";
		CommonTool.writeJSONToPage(response, service.getProductTreeNode(null));
    }
	
	/**
	 * 查询标签表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/findproductgrid"})
    public void findProductParamGrid(ProductParamVo<AircraftProductParam> vo,HttpServletResponse response){
    	service.findProductParamGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
    
    @RequestMapping(value={"/saveproductparam"})
    public void saveProductParam(ProductParamVo<AircraftProductParam> vo,HttpServletResponse response){
    	service.saveProductParam(vo);
    	JSONObject jo=new JSONObject();
    	jo.put("success", true);
    	jo.put("productID", vo.getProductID());
    	jo.put("parentID", vo.getParentID());
		CommonTool.writeJSONToPage(response, jo);
    }
    
    @RequestMapping(value={"/delproductparam"})
    public void delProductParam(ProductParamVo<AircraftProductParam> vo,HttpServletResponse response){
    	service.delProductParam(vo.getProductID());
		CommonTool.writeJSONToPage(response, "{\"success\":\"true\"}");
    }
    
}