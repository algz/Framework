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

import com.ras.authority.AuthorityService;
import com.ras.authority.AuthorityVo;
import com.ras.index.Page;
import com.ras.tool.CommonTool;

@Controller
@RequestMapping("/ras/authority/user")
public class RASUserController{
	
	@Autowired
	private AuthorityService service;
	
	@RequestMapping({"","/"})
	public ModelAndView AuthorityIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("权限管理");
    	page.setHeader_small("用户管理");
    	map.put("page", page);
		return new ModelAndView("ras/authority/user/ras_user",map);
	}
	
	@RequestMapping("/findauthritygrid")
	public void findAuthrityGrid(AuthorityVo<?> vo,HttpServletRequest request,HttpServletResponse response){
//    	Map<String, Object> map=new HashMap<String, Object>();
//    	String modelName=request.getParameter("modelName");
    	service.findAuthrityGrid(vo);
    	CommonTool.writeJSONToPage(response,vo );
	}
}