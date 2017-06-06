package algz.platform.core.shiro.authority;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import algz.platform.core.shiro.authority.userManager.User;

/**
 * 用于登录，登出，权限跳转的控制
 * @author algz
 *
 */

@Controller  
public class AuthenticatorControl {

    @RequestMapping(value="/login",method=RequestMethod.GET)  
    public String loginForm(Model model,HttpServletRequest request){  
    	//WebUtils.getSavedRequest(request);
//        model.addAttribute("user", new User());  
        return "/login";  
    }  
      
    @RequestMapping(value="/login",method=RequestMethod.POST)  
    public String login(User user,Model model,HttpServletRequest request){  
        String error = null;
        Subject subject = SecurityUtils.getSubject();
    	try {  
            String username=user.getUsername();
//            if(bindingResult.hasErrors()){  
//                return "/login";  
//            }  
            UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
            subject.login(token);  

            if (subject.isAuthenticated()) {
            	//通过权限,保存登陆成功的用户.
                request.getSession().setAttribute("LoginUser",subject.getPrincipal()); 
//                ((User)subject.getPrincipal()).getRoles().size();
                //获得"保存请求"的对象
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                // 获取保存的URL
                if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                    return "redirect:/";
                } else {
                	return "redirect:.."+savedRequest.getRequestUrl();
                	//String url = savedRequest.getRequestUrl().substring(12, savedRequest.getRequestUrl().length());
                    //return "forward:.." + savedRequest.getRequestUrl();
                }
//              SecurityUtils.getSubject().getSession().setAttribute("LoginUser", user);
//                if(error==null){
//            		
//            		String url = WebUtils.getSavedRequest(request).getRequestUrl();
//            		if(url==null){
//            			url="/";
//            		}
//            		return "redirect:.."+url;
//            	}
            } else {
                return "login";
            }
        }catch(UnknownAccountException uae){
        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,未知账户";
            System.out.println(error);  
//            request.setAttribute("message_login", "未知账户");  
        }catch(IncorrectCredentialsException ice){  
        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误的凭证";
        	System.out.println(error); 
//          request.setAttribute("message_login", "密码不正确");  
        }catch(LockedAccountException lae){  
        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,账户已锁定";
        	System.out.println(error); 
//            request.setAttribute("message_login", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误次数过多";
        	System.out.println(error); 
//            request.setAttribute("message_login", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
        	error="对用户[" + user.getUsername() + "]进行登录验证..验证未通过";
        	System.out.println(error); 
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
//            ae.printStackTrace();  
//            request.setAttribute("message_login", "用户名或密码不正确");  
        } 
    	model.addAttribute("error", error);
        return "redirect:/login"; 
    }  
      
    @RequestMapping(value="/logout",method=RequestMethod.GET)    
    public String logout(RedirectAttributes redirectAttributes ){   
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
        return "redirect:/login";  
    }   
      
    @RequestMapping("/403")  
    public String unauthorizedRole(){  
        return "/403";  
    }  
    
    
}
