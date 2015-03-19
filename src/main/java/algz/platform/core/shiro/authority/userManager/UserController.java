package algz.platform.core.shiro.authority.userManager;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/")
	public String findUsers(){
		return "platform/core/shiro/authority/userManager/userManager";
	}
	
	/**
	 * ajax 提交,返回json
	 * @param user
	 * @param printWriter
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void saveUser(@ModelAttribute User user,PrintWriter printWriter){
//		System.out.println(person); 
        String jsonString = "{message:'ok'}";//JSON.toJSONString(user, SerializerFeature.PrettyFormat); 
        printWriter.write(jsonString); 
        printWriter.flush(); 
        printWriter.close(); 
	}
}
