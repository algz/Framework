package com.ras.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.search.SearchTag;

import algz.platform.core.shiro.authority.userManager.User;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/ras/login")
public class LoginController {

	@RequestMapping(value = { "", "/" }) // @RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  login() {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap();
    	map.put("companytext", "洪都650");
        return new ModelAndView("ras/login",map);
    }
	
}
