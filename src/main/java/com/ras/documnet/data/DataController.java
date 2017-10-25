/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.documnet.data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ras.aircraftArchive.AircraftArchive;
import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftPicture.AircraftPicture;
import com.ras.analyze.AnalyzeService;
import com.ras.index.Page;
import com.ras.searchParam.SearchParam;
import com.ras.tool.CommonTool;
import com.ras.tool.ReturnVo;
import com.ras.tool.file.FileService;
import com.ras.tool.file.UploadFile;

import algz.platform.core.shiro.authority.userManager.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ras/document/data")
public class DataController{
		
	@Autowired
	private DataService service;
	
	@Autowired
	private AnalyzeService analyzeService;
	
	@RequestMapping({"","/"})
	public ModelAndView DocumentIndex(){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("数据编辑");
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
    	
    	
    	//Map<String, Object> searchParam=new HashMap<String, Object>();
//    	List<SearchTag> l=searchTagService.findAllParent();
//    	map.put("searchTags", searchTagService.findAllParent());
		return new ModelAndView("/ras/document/data/document",map);
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
    	
    	
    	return new ModelAndView("/ras/document/data/addModel",map);
	}

	/**
	 * 新增机型参数页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({"/addmodelparampage"})
	public ModelAndView AddModelParamPage(DataVo vo,HttpServletRequest request,HttpServletResponse response){
    	Map<String, Object> map=new HashMap<String, Object>();

    	
    	//开启UI参数控制
    	map.put("isModify", "true");
    	
    	//加载参数
    	map.putAll(service.addModelParamPage(vo));//paramMap
   	
    	//加载图片
    	//map.put("img",service.findModelImageParam(null,vo.getOverviewID()));
    	
    	Page page=new Page();
    	page.setHeader_h1("编辑");
    	page.setHeader_small(map.get("modelName")+"");
    	map.put("page", page);
    	
    	return new ModelAndView("/ras/document/data/addModelParam",map);
	}
	

	
	/**
	 * 查询机型表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/findtablemodelgrid"})
    public void findTableModelGrid(DataVo<AircraftOverview> vo,HttpServletResponse response){
    	service.findTableModelGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
    
    /**
     * 查询机型参数表格数据
     * @param vo
     * @param response
     */
    @RequestMapping(value={"/findtablemodelparamgrid"})
    public void findTableModelParamGrid(DataVo<AircraftBasic> vo,HttpServletResponse response){
    	if(!vo.getOverviewID().equals("-1")){
        	service.findTableModelParamGrid(vo);
    	}
    	CommonTool.writeJSONToPage(response, vo);
    }
    
	/**
	 * 保存机型
	 */
	@RequestMapping({"/savemodel"})
	public ModelAndView saveModel(HttpServletRequest request,HttpServletResponse response){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
		Map<String,String> map=new HashMap<String,String>();
		Enumeration<String> paramEnum = request.getParameterNames();
		while (paramEnum.hasMoreElements()) {
			String key=paramEnum.nextElement();
			String value=request.getParameter(key);
			map.put(key, value);
		}
		try {
			service.saveModel(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:");
	}
    
	/**
	 * 创建子机型
	 * @param photo
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/savesubmodel"})
	public void saveSubModel(HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        String parentID=request.getParameter("parentID");
        String modelName=request.getParameter("modelName");
        service.saveSubModel(modelName,parentID);

      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,msg ); 
	}
	
	/**
	 * 保存机型参数
	 */
	@RequestMapping({"/savemodelparam"})
	public ModelAndView saveModelParam(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
		Map<String,String[]> m=request.getParameterMap();
		CaseInsensitiveMap map=new CaseInsensitiveMap();
		Iterator<String> it=m.keySet().iterator();
		while (it.hasNext()) {
			String key=it.next();
			String value=null;
			if(m.get(key).length>1){
				value=StringUtils.join(m.get(key),",");
			}else if(m.get(key).length==1){
				value=m.get(key)[0];
			}
			if(!value.equals("null")){
				map.put(key, value);
			}
		}
		
		if(map.get("overviewID")==null){
			return null;
		}
		service.saveModelParam(map);

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
		String basicID=request.getParameter("basicID");
		String overviewID=request.getParameter("overviewID");
		String s="";
		if(!basicID.equals("")){
			service.delModelParam(basicID.split(","),overviewID);
			s="删除成功!";
		}
		
		//return null;
		CommonTool.writeJSONToPage(response, s);
	}
	
	/**
	 * 设置此机型参数为主要信息
	 */
	@RequestMapping({"/setMainModelparam"})
	public void setMainModelParam(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
		String basicID=request.getParameter("basicID");
		String overviewID=request.getParameter("overviewID");
		
		String s="";
		if(!basicID.equals("")){
			service.setMainModelParam(basicID,overviewID);
			s="设置成功!";
		}
		
		CommonTool.writeJSONToPage(response, s);
	}
	
	/**
	 *  上传机型图片
	 * @param file
	 * @param photo
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/upmodelimagefile"})
	public void upModelImageFile(@RequestParam("file") MultipartFile file,AircraftOverview ao,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        String overviewID=ao.getOverviewID();
        if(overviewID==null||overviewID.equals("null")){
        	msg="{\"success\":false}";
        	//msg="{\"id\":\"ff8081814cdf6f22014cdf6fafb40000\"}";
        	CommonTool.writeJSONToPage(response,msg ); 
        }else{
            ao.setPhotoFile(file);
            service.saveModelPhotoFile(ao);
        }
        
      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,ao ); 
	}
	
	/**
	 * 删除图片
	 * @param photo
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/delmodelimagefile"})
	public void delModelImageFile(AircraftOverview ao,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        service.delModelImageFile(ao.getOverviewID());

      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,msg ); 
	}
	
	@RequestMapping({"/findtablesql"})
	public void findTableSQL(HttpServletRequest request,HttpServletResponse response){
		String tableName=request.getParameter("tableName");
		List list=service.findTableSQL(tableName);
		Map m=new HashMap();
		m.put("vals", list);
		CommonTool.writeJSONToPage(response,m); 
	}
	    
	/**
	 * 查找所有的机型分类
	 * @param request
	 * @param response
	 */
    @RequestMapping(value="/findcategorynamefortypeahead")
    public void findCategoryNameForTypeahead(HttpServletRequest request,HttpServletResponse response){
    	String categoryName=request.getParameter("categoryName");
    	if(categoryName!=null){
        	try {
        		JSONArray ja=JSONArray.fromObject(service.findCategoryNameForTypeahead(categoryName));
    			response.getWriter().print(ja);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
	/**
	 * 查找所有的机型分类
	 * @param request
	 * @param response
	 */
    @RequestMapping(value="/findaircraftall")
    public void findAircraftAll(HttpServletRequest request,HttpServletResponse response){
		try {
			JSONArray ja = analyzeService.getAircraftAll(true);// JSONArray.fromObject(service.findCategoryNameForTypeahead(categoryName));
			response.getWriter().print(ja);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

}