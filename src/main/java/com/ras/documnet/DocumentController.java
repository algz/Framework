/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.documnet;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.index.Page;
import com.ras.search.SearchTag;
import com.ras.tool.CommonTool;
import com.ras.tool.ReturnVo;

import algz.platform.core.shiro.authority.userManager.User;

@Controller
@RequestMapping("/ras/document")
public class DocumentController{
	
	@Autowired
	private DocumentService service;
	
	
	@RequestMapping({"","/"})
	public ModelAndView DocumentIndex(){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("资料编辑");
    	map.put("page", page);
    	
//    	Navbar navbar=new Navbar();
//    	navbar.setNavbarbrand("飞机论证参照模块");
//    	navbar.setIsTask("1");
//    	navbar.setIsNotice("1");
//    	navbar.setIsMessage("1");
//    	map.put("navbar", navbar);
    	
    	User user=new User();
    	user.setUsername("algz");
    	map.put("user", user);
    	
    	
    	Map<String, Object> searchParam=new HashMap<String, Object>();
//    	List<SearchTag> l=searchTagService.findAllParent();
//    	map.put("searchTags", searchTagService.findAllParent());
		return new ModelAndView("/ras/document/document",map);
	}

	/**
	 * 新增机型页面
	 * @return
	 */
	@RequestMapping({"/addmodel"})
	public ModelAndView AddModelPage(HttpServletRequest request){
		
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();

    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("机型编辑");
    	map.put("page", page);
    	
    	String overviewID=request.getParameter("overviewID");
    	if(overviewID!=null){
    		map.put("model", service.findModel(overviewID));
    	}
    	
    	
    	return new ModelAndView("/ras/document/addModel",map);
	}

	/**
	 * 新增机型参数页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({"/addmodelparampage"})
	public ModelAndView AddModelParamPage(DocumentVo vo,HttpServletRequest request,HttpServletResponse response){
    	Map<String, Object> map=new HashMap<String, Object>();
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("机型参数编辑");
    	map.put("page", page);
    	
    	map.put("isModify", "true");
    	
    	Map<String,String> dataMap=service.addModelParamPage(vo);
    	if(dataMap!=null){
    		map.putAll(dataMap);
    	}else{
    		//map.put("overviewID", vo.getOverviewID());
    	}
    	

    	
    	return new ModelAndView("/ras/document/addModelParam",map);
	}
	

	
	/**
	 * 查询机型表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/findtablemodelgrid"})
    public void findTableModelGrid(DocumentVo<AircraftOverview> vo,HttpServletResponse response){
    	service.findTableModelGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
    
    /**
     * 查询机型参数表格数据
     * @param vo
     * @param response
     */
    @RequestMapping(value={"findtablemodelparamgrid"})
    public void findTableModelParamGrid(DocumentVo<Map<String,String>> vo,HttpServletResponse response){
    	service.findTableModelParamGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
    
	/**
	 * 保存机型
	 */
	@RequestMapping({"/savemodel"})
	public ModelAndView saveModel(HttpServletRequest request,HttpServletResponse response){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
		
		try {
			service.saveModel(request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:");
	}
    
	/**
	 * 保存机型参数
	 */
	@RequestMapping({"/savemodelparam"})
	public ModelAndView saveModelParam(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//		String model=request.getParameter("aircraftType");
//		String cname=request.getParameter("form-cname");
		Map<String,String[]> map=request.getParameterMap();
		service.saveModelParam(map);
		//return null;
		return new ModelAndView("redirect:");
	}
	
	/**
	 * 删除机型参数
	 */
	@RequestMapping({"/delmodel"})
	public void delModel(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id=request.getParameter("overviewID");
		String s="";
		if(!id.equals("")){
			service.delModel(id.split(","));
			s="删除成功!";
		}
		CommonTool.writeJSONToPage(response, s);
	}
	
	/**
	 * 删除机型参数
	 */
	@RequestMapping({"/delmodelparam"})
	public void delModelParam(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
		String id=request.getParameter("basicID");
		String s="";
		if(!id.equals("")){
			service.delModelParam(id.split(","));
			s="删除成功!";
		}
		
		//return null;
		CommonTool.writeJSONToPage(response, s);
	}
}