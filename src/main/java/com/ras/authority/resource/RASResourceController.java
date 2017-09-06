/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.authority.resource;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.authority.role.RASRoleVo;
import com.ras.index.Page;
import com.ras.tool.CommonTool;

@Controller
@RequestMapping(value="/ras/authority/resource")
public class RASResourceController{
	
	@Autowired
	private RASResourceService service;
	
	@RequestMapping({"","/"})
	public ModelAndView AuthorityIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("权限管理");
    	page.setHeader_small("资源管理");
    	map.put("page", page);
		return new ModelAndView("ras/authority/resource/ras_resource",map);
	}
	
	@RequestMapping("/findresourcegrid")
	public void findResourceGrid(RASResourceVo vo,HttpServletResponse response){
		service.findResourceGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
	}
	
	@RequestMapping("/saveroleresource")
	public void saveRoleResource(RASResourceVo vo,HttpServletRequest request,HttpServletResponse response){
		//String[] roleids=request.getParameterValues("roleid");
		service.saveRoleResource(vo.getRoleid(), vo.getResourceid(),vo.getOperate());
		CommonTool.writeJSONToPage(response, "{\"success\":true}");
	}
}