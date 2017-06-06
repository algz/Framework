package algz.platform.core.configure.siteMesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * sitemesh3
 * 
 * 配置渲染
•给所有路径配置一个默认的渲染
•给特殊的路径配置一个渲染
•给某个路径配置多个渲染，一个渲染依赖于前面的渲染
•排除某个路径

 * @author algz
 *
 */
public class ALGZSiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        // 配置默认的渲染器. 将应用于所有路径.  
		builder//.addDecoratorPath("/*", "/platform/decorators/top.jsp")  
			   .addDecoratorPath("/ras/*", "/ras/main.jsp")
           // 配置特定路径的渲染器.  
//           .addDecoratorPath("/*.special.jsp", "/special-decorator.html")  
           // 配置多个渲染器.即一个页面可以被多个装饰器装饰,如decorator装饰main,然后main在装饰slider
//           .addDecoratorPaths("/platform/uiExample/uicomponent/*", "/platform/uiExample/main.jsp",   
//                                             "/platform/decorators/top.jsp")  
           // 不被渲染的url路径.
		   .addExcludedPath("/manager/*") 
		   .addExcludedPath("/H+3.0/*") 
           //.addExcludedPath("/algz/*") //http://localhost:8080/algz/algz/algz.jsp 
           .addExcludedPath("/ace/*")
           .addExcludedPath("/ace1.3.2/*")
           .addExcludedPath("/jkbb/*")
           .addExcludedPath("/jsTree/*")
           .addExcludedPath("/se7en/*")
//           .addExcludedPath("/ras/*")
           .addExcludedPath("/platform/login.jsp")
           .addExcludedPath("/error.jsp")
           .addExcludedPath("/*.jsp")
           .addExcludedPath("/*.html")
           .addExcludedPath("/*/login");  
		
		//Sitemesh 3 默认只提供了 body，title，head 等 tag 类型，我们可以通过实现 TagRuleBundle 扩展自定义的 tag 规则;
		builder.addTagRuleBundles(new ExtHtmlTagRuleBundle());
	}

}
