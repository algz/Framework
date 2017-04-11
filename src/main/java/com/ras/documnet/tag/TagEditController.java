/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.documnet.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.documnet.data.DataVo;
import com.ras.index.Page;
import com.ras.search.SearchTag;
import com.ras.search.SearchTagVo;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;

@Controller
@RequestMapping("/ras/document/tag")
public class TagEditController{
	
	@Autowired
	private TagEditService service;
	
	@RequestMapping({"","/"})
	public ModelAndView TagEditIndex(){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("标签编辑");
    	map.put("page", page);

		return new ModelAndView("/ras/document/tag/tagedit",map);
	}
	
	/**
	 * 查询标签表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/findtaggrid"})
    public void findTagGrid(SearchTagVo<SearchTag> vo,HttpServletResponse response){
    	service.findTagGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
	
	/**
	 * 增加标签表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/addtag"})
    public void addTag(SearchTag tag,HttpServletResponse response){
    	service.addTag(tag);
		CommonTool.writeJSONToPage(response, "保存成功!");
    }
    
	/**
	 * 修改标签表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/modifytag"})
    public void modifyTag(SearchTag tag,HttpServletResponse response){
    	service.modifyTag(tag);
		CommonTool.writeJSONToPage(response, "保存成功!");
    }
    
	/**
	 * 删除标签表格数据
	 * @param vo
	 * @param response
	 */
    @RequestMapping(value={"/deltag"})
    public void delTag(HttpServletRequest request,HttpServletResponse response){
    	String id=request.getParameter("id");
    	String msg="未找到数据!";
    	if(!id.equals("")){
    		service.delTag(id.split(","));
    		msg="删除成功!";
    	}
		CommonTool.writeJSONToPage(response, msg);
    }
}