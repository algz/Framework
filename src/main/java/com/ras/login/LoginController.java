package com.ras.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.searchParam.SearchParam;

import algz.platform.core.shiro.authority.AuthenticatorControl;
import algz.platform.core.shiro.authority.userManager.User;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/ras")
public class LoginController {

	@Autowired
	private AuthenticatorControl authenticatorControl;
	
	@RequestMapping(value = { "", "/login" }) // @RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  login() {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("companytext", "洪都650");
        return new ModelAndView("ras/login",map);
    }
	
	/**
	 * 单点登陆
	 * @return
	 */
	@RequestMapping(value = { "/ssologin" }) // @RequestMapping 注解的方法才是真正处理请求的处理器
    public String  ssologin(User user,Model model,HttpServletRequest request,HttpServletResponse response) {
		
//		return "forward:.." + savedRequest.getRequestUrl();
//        String error = null;
//        Subject subject = SecurityUtils.getSubject();
//    	try {  
//            String username=request.getParameter("username");
//            String password=request.getParameter("password");
//            UsernamePasswordToken token=new UsernamePasswordToken(username, password);
//            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
//            subject.login(token);  
//
//            if (subject.isAuthenticated()) {
//            	//通过权限,保存登陆成功的用户.
//                request.getSession().setAttribute("LoginUser",subject.getPrincipal()); 
////                ((User)subject.getPrincipal()).getRoles().size();
//                //获得"保存请求"的对象
//                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
//                // 获取保存的URL
//                if (savedRequest == null || savedRequest.getRequestUrl() == null) {
//                    return "redirect:/";
//                } else {
//                	return "redirect:.."+savedRequest.getRequestUrl();
//                	//String url = savedRequest.getRequestUrl().substring(12, savedRequest.getRequestUrl().length());
//                    //return "forward:.." + savedRequest.getRequestUrl();
//                }
////              SecurityUtils.getSubject().getSession().setAttribute("LoginUser", user);
////                if(error==null){
////            		
////            		String url = WebUtils.getSavedRequest(request).getRequestUrl();
////            		if(url==null){
////            			url="/";
////            		}
////            		return "redirect:.."+url;
////            	}
//            } else {
//                return "login";
//            }
//        }catch(UnknownAccountException uae){
//        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,未知账户";
//            System.out.println(error);  
////            request.setAttribute("message_login", "未知账户");  
//        }catch(IncorrectCredentialsException ice){  
//        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误的凭证";
//        	System.out.println(error); 
////          request.setAttribute("message_login", "密码不正确");  
//        }catch(LockedAccountException lae){  
//        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,账户已锁定";
//        	System.out.println(error); 
////            request.setAttribute("message_login", "账户已锁定");  
//        }catch(ExcessiveAttemptsException eae){  
//        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误次数过多";
//        	System.out.println(error); 
////            request.setAttribute("message_login", "用户名或密码错误次数过多");  
//        }catch(AuthenticationException ae){  
//        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过";
//        	System.out.println(error); 
//            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
////            ae.printStackTrace();  
////            request.setAttribute("message_login", "用户名或密码不正确");  
//        } 
//    	model.addAttribute("error", error);
		String url=authenticatorControl.login(user, model, request);
		return url;//"redirect:/";
//        return "/login123"; 
    }
}
