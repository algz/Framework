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
import java.io.File;
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

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftPhoto.AircraftPhoto;
import com.ras.index.Page;
import com.ras.search.SearchTag;
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
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("机型参数编辑");
    	map.put("page", page);
    	
    	//开启UI参数控制
    	map.put("isModify", "true");
    	
    	//加载参数
    	map.putAll(service.addModelParamPage(vo));//paramMap
   	
    	//加载图片
    	map.put("img",service.findModelImageParam(null,vo.getBasicID()));
    	
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
    public void findTableModelParamGrid(DataVo<Map<String,String>> vo,HttpServletResponse response){
    	service.findTableModelParamGrid(vo);
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
	 * 保存机型参数
	 */
	@RequestMapping({"/savemodelparam"})
	public ModelAndView saveModelParam(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
		Map<String,String> map=new CaseInsensitiveMap();
		Enumeration<String> paramEnum = request.getParameterNames();
		while (paramEnum.hasMoreElements()) {
			String key=paramEnum.nextElement();
			String value=request.getParameter(key);
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
	
	@RequestMapping({"/upimagefile"})
	public void upImageFile(@RequestParam("file") MultipartFile file, AircraftPhoto photo,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        if(photo.getBasicID()==null||photo.getBasicID().equals("null")){
        	msg="{\"success\":false}";
        	//msg="{\"id\":\"ff8081814cdf6f22014cdf6fafb40000\"}";
        	CommonTool.writeJSONToPage(response,msg ); 
        }else{
            photo.setPhotoFile(file);
            service.saveImageFile(photo);
        }


      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,photo ); 
	}
	
	/**
	 * 删除图片
	 * @param photo
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/delimagefile"})
	public void delImageFile(AircraftPhoto photo,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        service.delImageFile(photo.getPhotoID());

      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,msg ); 
	}
	
////////////////////////////////////////////////////////
	
	/*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("fileUpload")
    public String  fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {
         
         
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
         
        try {
            //获取输出流
            OutputStream os=new FileOutputStream("E:/"+new Date().getTime()+file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
           os.flush();
           os.close();
           is.close();
         
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "/success"; 
    }
	
    
    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("fileUpload2")
    public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
         long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String path="E:/"+new Date().getTime()+file.getOriginalFilename();
         
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "/success"; 
    }
    
    
    @RequestMapping("springUpload")
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
         long  startTime=System.currentTimeMillis();
         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
             
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="E:/springUpload"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
                 
            }
           
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
    return "/success"; 
    }
}