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
public class ArchiveDataController{
		
	@Autowired
	private DataService service;
	
	/**
	 * 图片管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping({"/archivemanager"})
	public ModelAndView archiveManager(HttpServletRequest request){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	map.put("modelName", request.getParameter("modelName"));
    	map.put("overviewID", request.getParameter("overviewID"));
    	
    	Page page=new Page();
    	page.setHeader_h1("文档管理");
    	page.setHeader_small(map.get("modelName")+"文档编辑");
    	map.put("page", page);
    	
    	
    	
    	//Map<String, Object> searchParam=new HashMap<String, Object>();
//    	List<SearchTag> l=searchTagService.findAllParent();
//    	map.put("searchTags", searchTagService.findAllParent());
		return new ModelAndView("/ras/document/data/archiveManager",map);
	}
	
	/**
	 * 查找所有文档
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/findarchivegrid"})
    public void findArchiveGrid(DataVo vo,HttpServletResponse response){
    	if(vo.getOverviewID()!=null){
        	service.findArchiveGrid(vo);
    	}
    	CommonTool.writeJSONToPage(response, vo);
    	

    }
	
	/**
	 * 上传文件
	 * @param file
	 * @param doc
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/uparchivefile"})
	public void upArchiveFile(/*@RequestParam("file") MultipartFile file,*/ AircraftArchive archive,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        if(archive.getOverviewID()==null||archive.getOverviewID().equals("null")){
        	msg="{\"success\":false}";
        	//msg="{\"id\":\"ff8081814cdf6f22014cdf6fafb40000\"}";
        	CommonTool.writeJSONToPage(response,msg ); 
        }else{
//            archive.setArchiveFile(file);
            service.saveModelParamArchiveFile(archive);
            //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
            CommonTool.writeJSONToPage(response,archive ); 
        }
	}
	
	/**
	 * 删除文档
	 * @param archive
	 * @param request
	 * @param response
	 */
	@RequestMapping({"/delarchivefile"})
	public void delArichiveFile(AircraftArchive archive,HttpServletRequest request, HttpServletResponse response){
        String msg="{\"success\":true}";
        if(archive.getArchiveID()!=null){
        	service.delArichiveFile(archive.getArchiveID());
        }
        

      //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
        CommonTool.writeJSONToPage(response,msg ); 
	}
	
	/**
	 * 文件下载
	 * 
	 * @Description:
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/downloadarchivefile")
	public void downloadArchiveFile(HttpServletRequest request, HttpServletResponse response) {
		String archiveID = request.getParameter("archiveID");
		if (archiveID != null) {
			AircraftArchive archive = service.downloadArchiveFile(archiveID);
			String modelName = archive.getOverview().getModelName();
			String fileName = archive.getArchiveUrl().substring(archive.getArchiveUrl().lastIndexOf("/") + 1);
			String pathFile = CommonTool.ARCHIVE_DIR + modelName + File.separator + fileName;

			CommonTool.downloadFile(pathFile, archive.getArchiveDisplayName(), response);
		}
	}
	
	@RequestMapping("/sendapproval")
	public void sendApproval(HttpServletRequest request, HttpServletResponse response) {
		String archiveID = request.getParameter("archiveID");
		if (archiveID != null) {
			AircraftArchive archive = service.downloadArchiveFile(archiveID);
			String modelName = archive.getOverview().getModelName();
			String fileName = archive.getArchiveUrl().substring(archive.getArchiveUrl().lastIndexOf("/") + 1);
			String pathFile = CommonTool.ARCHIVE_DIR + modelName + File.separator + fileName;

			CommonTool.downloadFile(pathFile, archive.getArchiveDisplayName(), response);
		}
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