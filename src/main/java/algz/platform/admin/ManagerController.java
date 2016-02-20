package algz.platform.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("manager")
public class ManagerController {
	
	@Autowired
	private ManagerService service;
	
    @RequestMapping ( "/" )  
    public ModelAndView index() {  
    	
//       ModelAndView modelAndView = new ModelAndView();  
//       modelAndView.setViewName( "viewName" );  
//       modelAndView.addObject( " 需要放到 model 中的属性名称 " , " 对应的属性值，它是一个对象 " );  
//       return modelAndView;  
       return new ModelAndView("manager/algz", "menus", service.getSilderNav());
    }  
}
