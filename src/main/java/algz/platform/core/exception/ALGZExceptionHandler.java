package algz.platform.core.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * Sping全局异常，自定义异常类和异常解析
 * 
 * @author algz
 * 需要将自己的HandlerExceptionResolver实现类配置到Spring配置文件中，或者加上@Component注解。
 */
@Component
public class ALGZExceptionHandler implements HandlerExceptionResolver {
	
	/**
	 * 如果返回的modelview不存在,则自动调用web.xml配置的ERROR页面.
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
//
		ModelAndView model = new ModelAndView();
		//HandlerMethod method=(HandlerMethod)handler;
		e.printStackTrace(System.out);
		//System.out.println(method.getBean().toString()+":"+method.getMethod().getName()+e.getLocalizedMessage());
//        // 根据不同错误转向不同页面  
        if(e instanceof UnauthenticatedException) {
//            model.addObject("exception", "UnauthenticatedException.没有访问权限。");
//            model.setViewName("unauthorized");
        	return new ModelAndView("login","error","没有访问权限");  
        }else if(e instanceof IOException){
        	return new ModelAndView("error","error", model);  
        }else if(e instanceof NullPointerException){
        	return new ModelAndView("error","error", model); 
        }else{
        	String s=e.getCause().toString();
        	Map mmap = new HashMap();  
        	mmap.put("msg", s);  
        	return new ModelAndView("redirect:/error.jsp",mmap);//new RedirectView("error"),"msg", s); 
        	//return new ModelAndView("error","error", model); 
        }
        /*else if(ex instanceof ParameterException) {  
            return new ModelAndView("parameter_error", model);  
        } else {  
            return new ModelAndView("error", model);  
        } */
		
		//return model;
	}
}
