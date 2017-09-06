package algz.platform.core.configure.WebAppInitializer;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
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
import algz.platform.core.shiro.realm.SSOAuthoryRealm;

/**
 *  
 * @author algz
 *
 */
@Configuration //不能用@Autowired 标签，容器没加载完不能注入。
public class SecurityConfig {
	
	/* (non-Javadoc)
	 * @see algz.platform.core.configure.WebAppInitializer.ISecurityConfig#authoryRealm()
	 */
//	@Bean
//	public AuthoryRealm authoryRealm(){
//		AuthoryRealm ar=new AuthoryRealm();
//		return ar;
//	}
	
	@Bean
	public List<Realm> authoryRealms(){
		List<Realm> list=new ArrayList<Realm>();
		list.add(new AuthoryRealm());
		list.add(new SSOAuthoryRealm());
		return list;
	}
	
	/**
	 * 
	 * @see algz.platform.core.configure.WebAppInitializer.AppInitializer#securityManager(algz.platform.core.shiro.realm.AuthoryRealm)
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(List<Realm> authoryRealms,/*AuthoryRealm authoryRealm,*/CacheManager cacheManager){
		DefaultWebSecurityManager sm=new DefaultWebSecurityManager();
		
//		sm.setRealm(authoryRealm);
		
		sm.setRealms(authoryRealms);//配置多个Realm时,需设置以下参数或采用默认值.
		sm.setCacheManager(cacheManager);
		/**
		 * 
		 SecurityManager接口继承了Authenticator， 另外还有一个 ModularRealmAuthenticator实现，
		其委托给多个 Realm 进行验证，验证规则通过 AuthenticationStrategy 接口指定，默认提供
		的实现： 
		FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个 Realm身份验证
		成功的认证信息，其他的忽略； 
		AtLeastOneSuccessfulStrategy[默认]： 只要有一个Realm验证成功即可， 和 FirstSuccessfulStrategy
		不同，返回所有Realm身份验证成功的认证信息； 
		AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的
		认证信息，如果有一个失败就失败了
		 */
//		ModularRealmAuthenticator d=new ModularRealmAuthenticator();
//		d.setAuthenticationStrategy(new FirstSuccessfulStrategy());
//		d.setRealms(authoryRealms);// 定义 FirstSuccessfulStrategy 必须在添加Realms.
//		sm.setAuthenticator(d);
		
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
	    return new LifecycleBeanPostProcessor();
	}

	/**
	 * <!-- 开启shiro注解-->
	 * <!-- Shiro生命周期处理器-->
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true); // 开启shiro注解,it's false by default
        return creator;
	}

	/**
	 * 
	 * @see algz.platform.core.configure.WebAppInitializer.AppInitializer#authorizationAttributeSourceAdvisor(org.apache.shiro.web.mgt.DefaultWebSecurityManager)
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager secMan) {
	    AuthorizationAttributeSourceAdvisor advBean = new AuthorizationAttributeSourceAdvisor();
	    advBean.setSecurityManager(secMan);
	    return advBean;
	}

	/* (non-Javadoc)
	 * @see algz.platform.core.configure.WebAppInitializer.ISecurityConfig#shiroFilter(org.apache.shiro.web.mgt.DefaultWebSecurityManager)
	 */
	@Bean
	// 如果设置为原型模式，无循环依赖问题，但每次请求都将创建新对象。@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager secMan){
		ShiroFilterFactoryBean filterFactory=new ShiroFilterFactoryBean();
		//Shiro的核心安全接口,这个属性是必须的 
		filterFactory.setSecurityManager(secMan);
		//要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/login.jsp"页面
		filterFactory.setLoginUrl("/login");

//        <!-- 登录成功后要跳转的连接(本例中此属性用不到,因为登录成功后的处理逻辑在LoginController里硬编码为main.jsp了) -->  
//        <!-- <property name="successUrl" value="/system/main"/> -->  
		filterFactory.setSuccessUrl("/index");
//        <!-- 用户访问未对其授权的资源时,所显示的连接 -->  
//        <!-- 若想更明显的测试此属性可以修改它的值,如unauthor.jsp,然后用[玄玉]登录后访问/admin/listUser.jsp就看见浏览器会显示unauthor.jsp -->  
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
		
		//LinkedHashMap put顺序与get顺序一样.
		Map<String,String> filterChainMap=new LinkedHashMap<String,String>();
		filterChainMap.put("/login", "anon"); //不需要验证的URL
		filterChainMap.put("/ras/ssologin/**", "anon");
		filterChainMap.put("/ras/common/**", "anon"); //不需要验证的URL
		filterChainMap.put("/ras/ws/**", "anon"); //不需要验证的URL

//		filterChainMap.put("/test/login", "anon"); 
//		filterChainMap.put("/test/getVerifyCodeImage", "anon");
		filterChainMap.put("/ras/**", "authc"); //需要验证的URL
		filterChainMap.put("/test/hello**", "authc");
		filterChainMap.put("/admin/listUser**", "authc,perms[admin:manage]");
//		
		filterFactory.setFilterChainDefinitionMap(filterChainMap);
		
		//String definitions="/login=anon \r\n /ras/ssologin/**=anon \r\n /ras/common/**=anon \r\n /ras/**=authc";
//		filterFactory.setFilterChainDefinitions(definitions);
		return filterFactory;
	}
	
	
//	   <!-- 缓存管理 -->
//	    <bean id="shiroCacheManager" class="org.apache.shiro.cache.MemoryConstrainedCacheManager"></bean>
	@Bean
	public MemoryConstrainedCacheManager shiroCacheManager(){
		return new MemoryConstrainedCacheManager();
	}
	
//    <!-- 数据库保存的密码是使用MD5算法加密的，所以这里需要配置一个密码匹配对象 -->
//    <bean id="credentialsMatcher" class="org.apache.shiro.authc.credential.Md5CredentialsMatcher"></bean>
//    @Bean
//	public SimpleCredentialsMatcher credentialsMatcher(){
//    	return new Md5CredentialsMatcher();
//    }
	
	
	
}
