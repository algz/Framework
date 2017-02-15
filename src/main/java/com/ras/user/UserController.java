/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;

@Controller
@RequestMapping("/ras/user")
public class UserController{
	
	@RequestMapping({"","/"})
	public ModelAndView UserIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("用户");
    	map.put("page", page);
		return new ModelAndView("ras/user/user",map);
	}
}