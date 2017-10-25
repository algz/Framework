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
public class PictureDataController{
		
	@Autowired
	private DataService service;
	
	/**
	 * 图片管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping({"/picturemanager"})
	public ModelAndView pictureManager(HttpServletRequest request){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	map.put("modelName", request.getParameter("modelName"));
    	map.put("overviewID", request.getParameter("overviewID"));
    	
    	Page page=new Page();
    	page.setHeader_h1("图片管理");
    	page.setHeader_small(map.get("modelName")+"图片编辑");
    	map.put("page", page);
    	
    	
    	
    	//Map<String, Object> searchParam=new HashMap<String, Object>();
//    	List<SearchTag> l=searchTagService.findAllParent();
//    	map.put("searchTags", searchTagService.findAllParent());
		return new ModelAndView("/ras/document/data/pictureManager",map);
	}
	
	
	/**
	 * 查找图片Grid
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/findpicturegrid"})
    public void findPictureGrid(DataVo vo,HttpServletResponse response){
    	service.findPictureGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
	
	
	
	
	/**
	 * 上传图片
	 * @param file
	 * @param photo
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/uppicturefile"})
	public void upPictureFile(/*@RequestParam("file") MultipartFile file,*/ AircraftPicture photo,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        if(photo.getOverviewID()==null||photo.getOverviewID().equals("null")||photo.getPhotoFile()==null){
        	msg="{\"success\":false}";
        	//msg="{\"id\":\"ff8081814cdf6f22014cdf6fafb40000\"}";
        	CommonTool.writeJSONToPage(response,msg ); 
        }else{
           // photo.setPhotoFile(file);
            service.saveModelParamPhotoFile(photo);
          //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
            CommonTool.writeJSONToPage(response,photo ); 
        }
	}
	
	/**
	 * 删除图片
	 * @param photo
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/delpicturefile"})
	public void delPictureFile(AircraftPicture photo,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        service.delPictureFile(photo.getPhotoID());

      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,msg ); 
	}
	

}