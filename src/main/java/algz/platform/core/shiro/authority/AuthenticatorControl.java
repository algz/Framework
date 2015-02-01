package algz.platform.core.shiro.authority;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
    public String loginForm(Model model){  
//        model.addAttribute("user", new User());  
        return "/login";  
    }  
      
    @RequestMapping(value="/login",method=RequestMethod.POST)  
    public String login(User user,BindingResult bindingResult,RedirectAttributes redirectAttributes){  

    	try {  
            String username=user.getUsername();
            if(bindingResult.hasErrors()){  
                return "/login";  
            }  
            UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
            SecurityUtils.getSubject().login(token);  
 
        }catch(UnknownAccountException uae){  
            System.out.println("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,未知账户");  
//            request.setAttribute("message_login", "未知账户");  
        }catch(IncorrectCredentialsException ice){  
            System.out.println("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误的凭证");  
//            request.setAttribute("message_login", "密码不正确");  
        }catch(LockedAccountException lae){  
            System.out.println("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,账户已锁定");  
//            request.setAttribute("message_login", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            System.out.println("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误次数过多");  
//            request.setAttribute("message_login", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,堆栈轨迹如下");  
            ae.printStackTrace();  
//            request.setAttribute("message_login", "用户名或密码不正确");  
        } 
        return "redirect:/user"; 
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
