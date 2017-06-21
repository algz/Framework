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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.authority.AuthorityService;
import com.ras.index.Page;

@Controller
@RequestMapping(value="/ras/authority/resource")
public class RASResourceController{
	
	@Autowired
	private AuthorityService service;
	
	@RequestMapping({"","/"})
	public ModelAndView AuthorityIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("权限管理");
    	page.setHeader_small("资源管理");
    	map.put("page", page);
		return new ModelAndView("ras/authority/resource/ras_resource",map);
	}
	
}