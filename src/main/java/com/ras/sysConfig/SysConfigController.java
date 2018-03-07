/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.sysConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ras/sysconfig/api")
public class SysConfigController{
	
	@RequestMapping({"","/"})
	public ModelAndView indexPage(){
		return new ModelAndView("ras/sysconfig/api");
	}
}