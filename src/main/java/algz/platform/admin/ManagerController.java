package algz.platform.admin;

import java.util.HashMap;
import java.util.Map;

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
    	return new ModelAndView("manager/algz_layout",service.getSilderNav());
    }  
    
    @RequestMapping ( "/algz2" )  
    public ModelAndView index2() {  
    	String s="[{'text':'选项1',url:'index.html'},"+
    			"{'text':'选项2',url:'index.html'}]" ;
    			JSONArray ja=JSONArray.fromObject(s);

    	Map<String, Object> m=new HashMap();
    	m.put("title", "数据报告");

    	m.put("isclose", "true"); 
    	m.put("iscollapse","true");
//    	m.put("msg", 123);
    	m.put("setmenus", ja);
    	return new ModelAndView("manager/algz_v2",m);//参数1:视图;参数2:Map.
    }  
}
