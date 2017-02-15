/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.documnet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.search.SearchTag;
import com.ras.tool.CommonTool;
import com.ras.tool.ReturnVo;

import algz.platform.core.shiro.authority.userManager.User;

@Controller
@RequestMapping("/ras/document")
public class DocumentController{
	
	
	
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

	@RequestMapping({"/addmodel"})
	public ModelAndView AddModelPage(){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("机型编辑");
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
		return new ModelAndView("/ras/document/addModel",map);
	}

	@RequestMapping({"/dataedit"})
	public ModelAndView DataEditPage(){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("机型编辑");
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
		return new ModelAndView("/ras/document/dataEdit",map);
	}
	
	/**
	 * 保存机型
	 */
	@RequestMapping({"/savemodel"})
	public ModelAndView saveModel(HttpServletRequest request,HttpServletResponse response){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
		String s=request.getParameter("form-model");
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
//    	Page page=new Page();
//    	page.setHeader_h1("首页");
//    	page.setHeader_small("机型编辑");
//    	map.put("page", page);
//    	
//    	Navbar navbar=new Navbar();
//    	navbar.setNavbarbrand("飞机论证参照模块");
//    	navbar.setIsTask("1");
//    	navbar.setIsNotice("1");
//    	navbar.setIsMessage("1");
//    	map.put("navbar", navbar);
//    	
//    	User user=new User();
//    	user.setUsername("algz");
//    	map.put("user", user);
//    	
    	return new ModelAndView("redirect:");//("/ras/document/document",map);
//    	Map<String, Object> searchParam=new HashMap<String, Object>();
//    	List<SearchTag> l=searchTagService.findAllParent();
//    	map.put("searchTags", searchTagService.findAllParent());
//		return new ModelAndView("/ras/document/dataEdit",map);
    	
    	
//    	ReturnVo rtvo=new ReturnVo();
//    	rtvo.setDraw(Integer.parseInt(request.getParameter("draw")));
//    	rtvo.setRecordsTotal(2);
//    	rtvo.setRecordsFiltered(2);
//    	rtvo.setData(searchCriteriaService.SearchCriteriaGird());//((new String[][]{{"a1","b1"},{"a111","b111"}}));
//    	List list=new ArrayList();
//    	list.add(new String[]{"a1","b1","c1","d1"});
//    	rtvo.setData(list);
//    	
//    	    	String  sss="{draw:1,recordsTotal:2,recordsFiltered:2,"
//    			+ "'data': [['a1','b1'],['a111','b111']]}";
//    	JSONArray ja=JSONArray.fromObject(searchCriteriaService.SearchCriteriaGird());
//		JSONObject jObject = JSONObject.fromObject(rtvo, new JsonConfig().copy());
//    	    	rtvo.setData(list);
//		CommonTool.writeJSONToPage(response, rtvo);
	}
}