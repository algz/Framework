package algz.platform.core.exception;

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
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

/**
 * Sping全局异常，自定义异常类和异常解析
 * 
 * @author algz
 *
 */
public class ALGZExceptionHandler implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object handler, Exception e) {

		ModelAndView model = new ModelAndView();
//        // 根据不同错误转向不同页面  
        if(e instanceof UnauthenticatedException) {
//            model.addObject("exception", "UnauthenticatedException.没有访问权限。");
//            model.setViewName("unauthorized");
        	return new ModelAndView("login","error","没有访问权限");  
        }/*else if(ex instanceof ParameterException) {  
            return new ModelAndView("parameter_error", model);  
        } else {  
            return new ModelAndView("error", model);  
        } */
		
		return model;
	}

}
