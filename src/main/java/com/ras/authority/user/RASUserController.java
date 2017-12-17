/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.authority.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.authority.resource.RASResourceVo;
import com.ras.authority.role.RASRoleVo;
import com.ras.authority.resource.RASResourceService;
import com.ras.index.Page;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.AUserService;
import algz.platform.core.shiro.authority.userManager.User;

@Controller
@RequestMapping("/ras/authority/user")
public class RASUserController{
	
	@Autowired
	private RASUserService service;
	
	@Autowired
	private AUserService auserService;
	
	@RequestMapping({"","/"})
	public ModelAndView AuthorityIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("权限管理");
    	page.setHeader_small("用户管理");
    	map.put("page", page);
		return new ModelAndView("ras/authority/user/ras_user",map);
	}
	
	@RequestMapping("/findusergrid")
	public void findAuthrityGrid(RASUserVo vo,HttpServletRequest request,HttpServletResponse response){
    	service.findUserGrid(vo);
    	CommonTool.writeJSONToPage(response,vo );
	}
	
	@RequestMapping("/saveuserrole")
	public void saveUserRole(RASRoleVo vo,HttpServletRequest request,HttpServletResponse response){
		//String[] roleids=request.getParameterValues("roleid");
		service.saveUserRole(vo.getUserid(), vo.getRoleid(),vo.getOperate());
		CommonTool.writeJSONToPage(response, "{\"success\":true}");
	}
	
	/**
	 * 保存用户
	 * @param vo
	 * @param request
	 * @param response
	 */
	@RequestMapping("/saveuser")
	public void saveUser(RASUserVo vo,HttpServletRequest request,HttpServletResponse response){
		//String[] roleids=request.getParameterValues("roleid");
		service.saveUser(vo);
//		User user=new User();
//		user.setUsername("mmmmmmm");
//		auserService.saveUser(user);
		CommonTool.writeJSONToPage(response, "{\"success\":true}");
	}
	
	/**
	 * 删除用户
	 * @param vo
	 * @param request
	 * @param response
	 */
	@RequestMapping("/deluser")
	public void delUser(RASUserVo vo,HttpServletRequest request,HttpServletResponse response){
		//String[] roleids=request.getParameterValues("roleid");
		service.delUser(vo);
		CommonTool.writeJSONToPage(response, "{\"success\":true}");
	}
}