package algz.platform.core.configure.WebAppInitializer;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;








import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

/**
 * 
 * 应用服务器加载后第一个调用
 * Web容器加载顺序ServletContext--context-param--listener--filter--servlet
 * 
 * @author algz
 * 
 */
@Order(1)
public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext){
        //Log4jConfigListener  
//        servletContext.setInitParameter("log4jConfigLocation", "classpath:config/properties/log4j.properties");  
//        servletContext.addListener(Log4jConfigListener.class);  
		
		  /**Spring配置文件开始 */
	      
		  //防止发生java.beans.Introspector内存泄露,应将它配置在ContextLoaderListener的前面   
	      servletContext.addListener(new IntrospectorCleanupListener());
		
	      /** 实例化Spring容器,应用启动时,该监听器被执行,它会读取Spring相关配置.
	          Create the 'root' Spring application context  */
	      AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
	      rootContext.register(AppConfig.class);
	      servletContext.addListener(new ContextLoaderListener(rootContext)); // 注册监听器
	      /**Spring配置文件结束*/
	      
	    /**SpringMVC配置文件开始*/
		//XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		//appContext.setConfigLocation("classpath:*algz/platform/core/configure/xml/spring-mvc-servlet.xml");
		// Register Servlet // Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		/**SpringMVC配置文件结束*/
		
		/**对springSecurityFilterChain定义映射路径。我们很容易通过继承AbstractSecurityWebApplicationInitializer实现，并可以有选择的通过覆盖方法来定制映射。*/
        // Register Filter 
//        FilterRegistration filter = servletContext.addFilter("springSecurityFilterChain", 
//            DelegatingFilterProxy.class); 
//        filter.addMappingForUrlPatterns(null, true, "/*"); 
		
	  /** 解决乱码问题  
	      forceEncoding默认为false,此时效果可大致理解为request.setCharacterEncoding("UTF-8") 
	      forceEncoding=true后,可大致理解为request.setCharacterEncoding("UTF-8")和response.setCharacterEncoding("UTF-8") */
		/**FilterRegistration scefilter = servletContext.addFilter("Set Character Encoding", CharacterEncodingFilter.class); 
      Map<String,String> sceMap=new HashMap<String,String>();
      sceMap.put("encoding", "UTF-8");
      sceMap.put("forceEncoding", "true");
      scefilter.setInitParameters(sceMap);
      scefilter.addMappingForUrlPatterns(null, true, "/*"); 
      设置servlet编码结束*/
      
	  /**  配置Shiro过滤器,先让Shiro过滤系统接收到的请求 -->  
    <!-- 这里filter-name必须对应applicationContext.xml中定义的<bean id="shiroFilter"/> -->  
    <!-- 使用[/*]匹配所有请求,保证所有的可控请求都经过Shiro的过滤 -->  
    <!-- 通常会将此filter-mapping放置到最前面(即其他filter-mapping前面),以保证它是过滤器链中第一个起作用的 -->   */
		FilterRegistration shiroFilter = servletContext.addFilter("shiroFilter", new DelegatingFilterProxy( "shiroFilter", rootContext ));
		Map<String, String> shiroFilterMap = new HashMap<String, String>();
		//该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		shiroFilterMap.put("targetFilterLifecycle", "true");
		shiroFilter.setInitParameters(shiroFilterMap);
		shiroFilter.addMappingForUrlPatterns(null, true, "/*");
	}

}
