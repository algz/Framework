package algz.platform.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ALGZExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object handler, Exception ex) {

        Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
//        // 根据不同错误转向不同页面  
        if(ex instanceof UnauthenticatedException) {  
            System.out.println("UnauthenticatedException.没有访问权限。"); 
        }/*else if(ex instanceof ParameterException) {  
            return new ModelAndView("parameter_error", model);  
        } else {  
            return new ModelAndView("error", model);  
        } */
		
		return new ModelAndView("error");
	}

}
