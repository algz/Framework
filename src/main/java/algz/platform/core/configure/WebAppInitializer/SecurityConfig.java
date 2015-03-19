package algz.platform.core.configure.WebAppInitializer;


import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.filter.DelegatingFilterProxy;

import algz.platform.core.shiro.realm.AuthoryRealm;

/**
 *  
 * @author algz
 *
 */
@Configuration //不能用@Autowired 标签，容器没加载完不能注入。
public class SecurityConfig {
	
	/**
	 * 
	 * <!-- Realm实现 -->
	 * @return
	 */
	@Bean
	public AuthoryRealm authoryRealm(){
		AuthoryRealm ar=new AuthoryRealm();
		return ar;
	}
	
	/**
	 * <!-- 安全管理器 -->
	     <!-- Shiro默认会使用Servlet容器的Session,可通过sessionMode属性来指定使用Shiro原生Session -->  
    <!-- 即<property name="sessionMode" value="native"/>,详细说明见官方文档 -->  
    <!-- 这里主要是设置自定义的单Realm应用,若有多个Realm,可使用'realms'属性代替 -->  
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(AuthoryRealm authoryRealm){
		DefaultWebSecurityManager sm=new DefaultWebSecurityManager();
		sm.setRealm(authoryRealm);
		return sm;
	}
//	
//	@Bean
//    public DelegatingFilterProxy springSecurityFilterChain() {
//        return new DelegatingFilterProxy();
//    }
	
	/**
	 * This method needs to be static due to issues defined here:<br>
	 * https://issues.apache.org/jira/browse/SHIRO-222
	 */
	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
	    LifecycleBeanPostProcessor lbpp = new LifecycleBeanPostProcessor();
	    return lbpp;
	}

	/**
	 * <!-- Shiro生命周期处理器-->
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
	    return new DefaultAdvisorAutoProxyCreator();
	}

	/**
	 * Shiro权限注解
	 * @param secMan
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager secMan) {
	    AuthorizationAttributeSourceAdvisor advBean = new AuthorizationAttributeSourceAdvisor();
	    advBean.setSecurityManager(secMan);
	    return advBean;
	}

	/**
	 * 此配置必须放置在XML中，不然会出现循环依赖的问题。
	 * @param secMan spring自动帮助注入参数，不用直接调用
	 * @return
	 */
	@Bean
	// 如果设置为原型模式，无循环依赖问题，但每次请求都将创建新对象。@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager secMan){
		ShiroFilterFactoryBean filterFactory=new ShiroFilterFactoryBean();
		//Shiro的核心安全接口,这个属性是必须的 
		filterFactory.setSecurityManager(secMan);
		
		//登录时的链接(非必须的属性),默认会自动寻找Web工程根目录下的"/login.jsp"页面
		//setLoginUrl()参数为view(即action).发送的是get,调用AuthenticatorController.loginForm方法.
		filterFactory.setLoginUrl("/login");
		
//        <!-- 登录成功后要跳转的连接(本例中此属性用不到,因为登录成功后的处理逻辑在LoginController里硬编码为main.jsp了) -->  
//        <!-- <property name="successUrl" value="/system/main"/> -->  
		filterFactory.setSuccessUrl("/");
//        <!-- 用户访问未对其授权的资源时,所显示的连接 -->
		filterFactory.setUnauthorizedUrl("/unauthor");
//        <!-- Shiro连接约束配置,即过滤链的定义 -->  
//        <!-- 此处可配合我的这篇文章来理解各个过滤连的作用http://blog.csdn.net/jadyer/article/details/12172839 -->  
//        <!-- 下面value值的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的 -->  
//        <!-- anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种 -->  
//        <!-- authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter -->  
//        <property name="filterChainDefinitions">  
//            <value>  
//                /mydemo/login=anon  
//                /mydemo/getVerifyCodeImage=anon  
//                /main**=authc  
//                /user/info**=authc  
//                /admin/listUser**=authc,perms[admin:manage]  
//            </value>  
//        </property>  
		Map<String,String> filterChainMap=new HashMap<String,String>();
//		filterChainMap.put("/test/login", "anon");
//		filterChainMap.put("/test/getVerifyCodeImage", "anon");
		filterChainMap.put("/test**", "anon");
//		filterChainMap.put("/test/hello**", "authc");
//		filterChainMap.put("/admin/listUser**", "authc,perms[admin:manage]");
		filterFactory.setFilterChainDefinitionMap(filterChainMap);
		return filterFactory;
	}
	
}
