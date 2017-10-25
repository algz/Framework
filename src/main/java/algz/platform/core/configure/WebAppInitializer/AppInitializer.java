package algz.platform.core.configure.WebAppInitializer;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import algz.platform.core.configure.siteMesh.ALGZSiteMeshFilter;
import algz.platform.core.license.LicenseManager;


/**
 * 
 * 应用服务器加载后第一个调用
 * Web容器加载顺序ServletContext--context-param--listener--filter--servlet
 * 
 * @author algz
 * 
 */
@Order(1)
public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)throws ServletException{
		
//      /** License管理  **/
//		if (!LicenseManager.checkLicense(servletContext)) {
//			throw new ServletException(LicenseManager.LicenseResult);
//		}
		
		
		  /** Spring配置,防止发生java.beans.Introspector内存泄露,应将它配置在ContextLoaderListener的前面   */
	      servletContext.addListener(new IntrospectorCleanupListener());
		
	      /** 实例化Spring容器,应用启动时,该监听器被执行,它会读取Spring相关配置.
	          Create the 'root' Spring application context  */
	      AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
	      rootContext.register(WebConfig.class);
	      servletContext.addListener(new ContextLoaderListener(rootContext)); // 注册监听器
	      
		/** CXF webservice配置 */
		ServletRegistration.Dynamic cxfServlet = servletContext.addServlet("CXFServlet", new org.apache.cxf.transport.servlet.CXFServlet());
		cxfServlet.addMapping("/ras/ws/*");
		cxfServlet.setLoadOnStartup(2);
	      
	    /** SpringMVC dispatcher配置 */
		//XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		//appContext.setConfigLocation("classpath:*algz/platform/core/configure/xml/spring-mvc-servlet.xml");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		//isAsyncSupported true if the Servlet or Filter represented by this dynamic Registration supports asynchronous operations,false otherwise
		dispatcher.setAsyncSupported(true); 
		
		/** SpringMVC 增加多文件上传功能(必须设置,不然Controller不响应客户端请求) */
		//用来指定上传文件的临时写入目录,一定要注意是绝对目录，比如Windows下的F:\Workspace\spring，上面我们配置的是Linux下的/tmp/spittr/uploads
		String LOCATION = "C:/temp/"; // Temporary location where files will be stored
		//上传文件的最大容量（以字节为单位），默认无限制
		long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
		//整个multipart请求的最大容量（以字节为单位），不会关心有多少个part以及每个part的大小。默认是没有限制的
		long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
		//在文件上传的过程中，如果文件大小达到了一个指定最大容量（以字节为单位），将会写入到临时文件路径中。默认值为0，也就是所有上传的文件都会写入到磁盘上。
		int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(	LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		dispatcher.setMultipartConfig(multipartConfigElement);
		
		/** 设置servlet编码,解决乱码问题  */
		FilterRegistration scefilter = servletContext.addFilter("Set Character Encoding", CharacterEncodingFilter.class); 
		Map<String,String> encodeFilter=new HashMap<String,String>();
		//设置编码。forceEncoding=true后,等效于request.setCharacterEncoding("UTF-8")和response.setCharacterEncoding("UTF-8")
		encodeFilter.put("encoding", "UTF-8");
		//forceEncoding默认为false,等效于request.setCharacterEncoding("UTF-8")
		encodeFilter.put("forceEncoding", "true"); 
		scefilter.setInitParameters(encodeFilter);
		scefilter.addMappingForUrlPatterns(null, true, "/*");  //不能设置为"/",不然不会进行匹配,无效指定编码.
		/**设置servlet编码结束*/
      
	  /**  配置Shiro过滤器,先让Shiro过滤系统接收到的请求 -->  
    <!-- 这里filter-name必须对应applicationContext.xml中定义的<bean id="shiroFilter"/> -->  
    <!-- 通常会将此filter-mapping放置到最前面(即其他filter-mapping前面),以保证它是过滤器链中第一个起作用的 -->   */
		FilterRegistration shiroFilter = servletContext.addFilter("shiroFilter", new DelegatingFilterProxy( "shiroFilter", rootContext ));
		Map<String, String> shiroFilterMap = new HashMap<String, String>();
		//该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		shiroFilterMap.put("targetFilterLifecycle", "true");
		shiroFilter.setInitParameters(shiroFilterMap);
		//使用[/*]匹配所有请求,保证所有的可控请求都经过Shiro的过滤
		shiroFilter.addMappingForUrlPatterns(null, true, "/*");
		
		/** siteMesh */
		FilterRegistration siteMeshFilter = servletContext.addFilter("sitemesh",ALGZSiteMeshFilter.class);
		siteMeshFilter.addMappingForUrlPatterns(null, true, "/*");
		

		
		
		/** 配置Log4j */
        //Log4jConfigListener  
//      servletContext.setInitParameter("log4jConfigLocation", "classpath:config/properties/log4j.properties");  
//      servletContext.addListener(Log4jConfigListener.class);  
	}

}
