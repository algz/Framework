package algz.platform.core.configure.WebAppInitializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ras.tool.CommonTool;

import algz.platform.core.exception.ALGZExceptionHandler;

/**
 * @EnableWebMvc导入spring_mvc需要的诸多bean，再配合@ComponentScan扫描包里面所有@Component(@Repository @Service  @Constroller)，基本的mvc配置就完成了。
 * @EnableTransactionManagement  //启动事务管理配置
*  @ImportResource导入基于XML方式的配置文件，多个配置文件采{"",""}数组。
* 
*   若要自定义默认配置，在 Java 中您只需实现 WebMvcConfigurer 接口或扩展 WebMvcConfigurerAdapter 类和重写您所需要的方法。下面是一些可用方法重写的例子。
* 
* @author algz
* Spring Config配置(无XML)
*/
@Configuration
//@Import(DataSourceConfig.class) //引用一个配置
/*
 * shiro 部分设置必须配置在XML中，不然会出现依赖循环问题：
二月 01, 2015 4:38:03 下午 org.springframework.beans.factory.support.DefaultListableBeanFactory getTypeForFactoryBean
警告: Bean creation exception on FactoryBean type check: org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'shiroFilter': Requested bean is currently in creation: Is there an unresolvable circular reference?
*/
//@ImportResource({"classpath:algz/platform/core/configure/xml/spring-shiro.xml"})
//@ImportResource({"classpath:algz/platform/core/configure/xml/bak/cxf.xml"})
@ComponentScan(basePackages = {"algz.platform","com"})//扫描注解组件的包的基础位置(@Controller,@Service...)
@EnableWebMvc //启用 MVC Java config ，在你的 @Configuration类上增加@EnableWebMvc注解
//@PropertySource("/conf/jdbc.properties")
//@EnableAspectJAutoProxy(proxyTargetClass=true)  
public class WebConfig extends WebMvcConfigurerAdapter {
//	private static final Logger logger = Logger  
//            .getLogger(AppConfig.class);  

  //*********************springmvc开始配置*************************
  /**
   * Resolve logical view names to .jsp resource in the /WEB-INF/views directory
   * 解析所有视图为前缀(/WEB-INF/)+视图名称+后缀(.jsp)的文件.
   * 
   springmvc XML:
   	   <!-- 对模型视图名称的解析，即在模型视图名称添加前后缀 -->  
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix">WEB-INF/views/</property>
        <property name="suffix">.jsp</property>
      </bean>
   
  @Bean
  public ViewResolver viewResolver(){
//	  logger.info("ViewResolver");  
      InternalResourceViewResolver resolver =new InternalResourceViewResolver();
      resolver.setPrefix("/");//("/WEB-INF/");
      resolver.setSuffix(".jsp");
      return resolver;
  }*/
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");//("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	
  /**
   * 添加静态资源不拦截.即se7en目录下的文件都不拦截.
   * 由于类继续WebMvcConfigurerAdapter,所以不用定义为Bean
   * <mvc:resources mapping="/se7en/**" location="/se7en/" /> 
   * 
   */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	  logger.info("addResourceHandlers"); 
	  
	  /*
	   指定静态资源路径:访问addResourceHandler 指向 addResourceLocations
	  addResourceHandler(url).addResourceLocations(webContent下的相对路径)
	  */
	  //http://localhost:8080/algz/manager/ ==> /Framework/WebContent/manager
	  registry.addResourceHandler("/manager/**").addResourceLocations("/manager/"); 
	  

//	  registry.addResourceHandler("/ras/document/**").addResourceLocations("/ras/document/");

	  registry.addResourceHandler("/H+3.0/**").addResourceLocations("/H+3.0/");
	  registry.addResourceHandler("/algz/**").addResourceLocations("/algz/");
	  registry.addResourceHandler("/platform/**").addResourceLocations("/platform/");
      registry.addResourceHandler("/se7en/**").addResourceLocations("/se7en/");
     
	  registry.addResourceHandler("/ras/**").addResourceLocations("/ras/");
	  registry.addResourceHandler("/ace/**").addResourceLocations("/ace/");
	  registry.addResourceHandler("/ace1.3.2/**").addResourceLocations("/ace1.3.2/");
	  
	  //////////////////////////
	    /** 
	     * 内置的VersionResourceResolver有FixedVersionStrategy和ContentVersionStrategy两种策略, 
	     * FixedVersionStrategy可以使用某项属性,或者日期,或者其它来作为版本. 
	     * 而ContentVersionStrategy是使用资源内容计算出来的MD5哈希作为版本. 
	     * ContentVersionStrategy是个不错的默认选择,除了某些不能使用的情况下(比如,带有javascript模块加截器). 
	     * 以下配置:如果是js的话使用FixedVersionStrategy,其它(如css,img)使用默认的ContentVersionStrategy策略. 
	     */  
//	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
//	    .resourceChain(true).addResolver(
//	    		new VersionResourceResolver()
//	    		.addFixedVersionStrategy("1.10", "/**/*.js")
//	    		.addContentVersionStrategy("/**")); 
      
  }
  
	/**
	 *  <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">  
        <property name="basename">  
            <value>messages</value>  
        	</property>  
    	</bean>  
	 * 
	 * src/main/resources/messages.properties
	 * @return
	 
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
*/
	
  /**
   * <mvc:default-servlet-handler /> 
   * 在springMVC-servlet.xml中配置<mvc:default-servlet-handler />后，
   * 会在Spring MVC上下文中定义一个org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler，
   * 它会像一个检查员，对进入DispatcherServlet的URL进行筛查，如果发现是静态资源的请求，就将该请求转由Web应用服务器默认的Servlet处理，
   * 如果不是静态资源的请求，才由DispatcherServlet继续处理。 
   * 一般Web应用服务器默认的Servlet名称是"default"，因此DefaultServletHttpRequestHandler可以找到它。
   * 如果你所有的Web应用服务器的默认Servlet名称不是"default"，则需要通过default-servlet-name属性显示指定： 
   * <mvc:default-servlet-handler default-servlet-name="所使用的Web服务器默认使用的Servlet名称" /> 
   */
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {  
      configurer.enable();  
  }  

  /**
   * 三种方式都不到mediaType,那只能是默认的json::http://localhost:8080/gradletest-1.0/sample/test
通过后缀json得到mediaType,是json格式:http://localhost:8080/gradletest-1.0/sample/test.json

通过后缀xml得到mediaType,是xml格式:http://localhost:8080/gradletest-1.0/sample/test.xml

通过url参数得到mediaType,是json格式:http://localhost:8080/gradletest-1.0/sample/test?mediaType=json

通过url参数得到mediaType,是xml格式:http://localhost:8080/gradletest-1.0/sample/test?mediaType=jxml

后缀,url参数都有,后缀优先得到mediaType是xml格式:http://localhost:8080/gradletest-1.0/sample/test.xml?mediaType=json
   */ 
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {  
      configurer.favorPathExtension(true).useJaf(false)  
              .favorParameter(true).parameterName("mediaType")  
              .ignoreAcceptHeader(true)
              //defaultContentType(MediaType.APPLICATION_JSON).  
              .mediaType("xml", MediaType.APPLICATION_XML)
              .mediaType("js", MediaType.ALL)
              .mediaType("json", MediaType.APPLICATION_JSON);  
  }  
  
  
  /**
   * <!-- 支持上传文件 -->  
   * <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
   * Servlet3.0及以后，可以用这个StandardServletMultipartResolver来处理上传文件
   * 而3.0之前的版本通常用的是CommonsMultipartResolver来处理 
   */
  @Bean
  public MultipartResolver multipartResolver(){
	return new StandardServletMultipartResolver();
  }
  
  
//	@Bean
//	public ExceptionHandlerExceptionResolver exceptionResolver() {
//	  ExceptionHandlerExceptionResolver er= new ExceptionHandlerExceptionResolver();
//		return er;
//	};
//  /**
//   * 配置Spring 处理异常的类,用于捕获@Control产生的异常(需在此类中配置相应处理的异常)
//   * @return
//   */
//	@Bean
//	public ALGZExceptionHandler exceptionHandler() {
//		ALGZExceptionHandler exception= new ALGZExceptionHandler(); //自定义类添加@Component注解,不用配置Bean.
//		return exception;
//	};
//includeFilters

}
