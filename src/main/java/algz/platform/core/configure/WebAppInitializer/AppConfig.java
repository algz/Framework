package algz.platform.core.configure.WebAppInitializer;


import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
@ImportResource({"classpath:algz/platform/core/configure/xml/spring-shiro.xml"})
@ComponentScan(basePackages = {"algz.platform","com"})//扫描注解组件的包的基础位置(@Controller,@Service...)
@EnableWebMvc //启用 MVC Java config ，在你的 @Configuration类上增加@EnableWebMvc注解
@EnableTransactionManagement  //声明式事务管理，通过spring root application context扫描包septem.config.app：
//@PropertySource("/conf/jdbc.properties")
public class AppConfig /*extends WebMvcConfigurerAdapter*/ {


//  //*********************springmvc开始配置*************************
//  /**
//   * Resolve logical view names to .jsp resource in the /WEB-INF/views directory
//   * 解析所有视图为前缀(/WEB-INF/)+视图名称+后缀(.jsp)的文件.
//   springmvc XML:
//       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//        <property name="prefix">WEB-INF/views/</property>
//        <property name="suffix">.jsp</property>
//      </bean>
//   */
  @Bean
  public ViewResolver viewResolver(){
      InternalResourceViewResolver resolver =new InternalResourceViewResolver();
      resolver.setPrefix("/");//("/WEB-INF/");
      resolver.setSuffix(".jsp");
      return resolver;
  }
  
  /**
   * 
   * <mvc:resources mapping="/resources/**" location="/resources/" /> 
   * 
   */
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
  
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
              .ignoreAcceptHeader(true).  
              //defaultContentType(MediaType.APPLICATION_JSON).  
              mediaType("xml", MediaType.APPLICATION_XML).  
              mediaType("json", MediaType.APPLICATION_JSON);  
  }  

  
  
}
