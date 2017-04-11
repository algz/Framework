package com.ras.index;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.ras.documnet.data.DataService;
import com.ras.search.SearchTag;
import com.ras.search.SearchTagService;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.system.menu.Menu;
import algz.platform.system.menu.MenuService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


//import algz.platform.util.excel.ExcelView;


/*
 *  当@RequestMapping 标记在Controller 类上的时候，里面使用@RequestMapping 标记的方法的请求地址都是相对于类上的@RequestMapping 而言的；
 *  当Controller 类上没有标记@RequestMapping 注解时，方法上的@RequestMapping 都是绝对路径。
 *  这种绝对路径和相对路径所组合成的最终路径都是相对于根路径“/ ”而言的。
 */

@Controller   
@RequestMapping(value="/ras")
public class IndexPageController {
	
	@Autowired
	private IndexPageService service;
	
	@Autowired
	private DataService dataService;
	
//	@Autowired
//	private ExcelService excelService;
	
    @RequestMapping(value={"/index","/"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  index(HttpServletRequest request,HttpServletResponse response) {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("查询");
    	map.put("page", page);
    	
//    	Navbar navbar=new Navbar();
//    	navbar.setNavbarbrand("飞机论证参照模块");
//    	navbar.setIsTask("1");
//    	navbar.setIsNotice("1");
//    	navbar.setIsMessage("1");
//    	map.put("navbar", navbar);
    	
//    	User user=new User();
//    	user.setUsername("algz");
//    	map.put("user", user);
    	
//    	Map<String, Object> searchParam=new HashMap<String, Object>();
    	String modelname=request.getParameter("modelName");
    	request.setAttribute("modelName", modelname);
    	
    	if(modelname!=null){
        	List list=service.searchIndexPage(modelname);
            map.put("searchs", list);
    	}

    	
        return new ModelAndView("ras/index",map);
    }
    
}
