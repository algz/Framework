/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.authority;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;

@Controller
@RequestMapping("/ras/authority")
public class AuthorityController{
	
	@RequestMapping({"","/"})
	public ModelAndView AuthorityIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("权限管理");
    	map.put("page", page);
		return new ModelAndView("ras/authority/authority",map);
	}
}