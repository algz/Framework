package com.ras.searchTag;

import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
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
import com.ras.index.Page;
import com.ras.searchParam.SearchParamService;
import com.ras.searchParam.SearchParam;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.resourceManager.ResourceService;
import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;
import algz.platform.util.json.JSONTools;
import algz.platform.util.xml.StAXUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


//import algz.platform.util.excel.ExcelView;


/*
 *  当@RequestMapping 标记在Controller 类上的时候，里面使用@RequestMapping 标记的方法的请求地址都是相对于类上的@RequestMapping 而言的；
 *  当Controller 类上没有标记@RequestMapping 注解时，方法上的@RequestMapping 都是绝对路径。
 *  这种绝对路径和相对路径所组合成的最终路径都是相对于根路径“/ ”而言的。
 */

@Controller   
@RequestMapping(value="/ras/searchtag")
public class SearchTagController {
	
	@Autowired
	private SearchTagService service;
	
	@Autowired
	private DataService dataService;
	
//	@Autowired
//	private ExcelService excelService;
	
    @RequestMapping(value={"","/"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  index(SearchTagVo vo,HttpServletRequest request,HttpServletResponse response) {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
//    	HttpSession session=request.getSession();
    	Iterator it=SecurityUtils.getSubject().getSession().getAttributeKeys().iterator();
    	while(it.hasNext()){
    		Object key=it.next();
    		Object val=SecurityUtils.getSubject().getSession().getAttribute(key);
    		System.out.println(key+"="+val);
    	}
    	
    	Page page=new Page();
    	page.setHeader_h1("标签查询");
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
//    	String tagName=vo.getTagName();//request.getParameter("modelName");
    	map.put("tag", vo.getTag());
////    	request.setAttribute("modelName", modelname);
//
//    	
//    	if(tagName!=null&&!tagName.equals("")){
//    		service.searchIndexPage(vo);
//            map.put("searchs", vo.getData());
//            
//            map.put("pageCount", Math.ceil(vo.getRecordsTotal()/10.0));
//            map.put("curPage", vo.getStart()/10+1);
//    	}
    	//String s=StAXUtil.writeToXmlString();
        return new ModelAndView("ras/searchtag/index",map);
    }
    
    
    /**
     * 查询搜索标签表格
     * @param vo
     * @param request
     * @param response
     * @throws IllegalAccessException 
     * @throws NoSuchMethodException 
     */
    @RequestMapping(value={"/findtagsearchgird"})
    public void findTagSearchGird(SearchTagVo vo,HttpServletRequest request,HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException{
    	if(vo.getTag()!=null){
    		service.findTagSearchGird(vo);
    	}
    	MethodHandles.Lookup lookup = MethodHandles.lookup();  
        MethodHandle mh = lookup.findStatic(JSONTools.class, "BeanToJson", MethodType.methodType(JSONObject.class, Object.class));  
        
		CommonTool.writeJSONToPage(response, vo,mh);
		
    }
    
}
