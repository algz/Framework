package algz.platform.core.configure;

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
		builder.addDecoratorPath("/*", "/platform/decorators/main.jsp")  
           // 配置特定路径的渲染器.  
//           .addDecoratorPath("/*.special.jsp", "/special-decorator.html")  
//            配置多个渲染器.即一个页面可以被多个装饰器装饰,如platform/*->main->top
//           .addDecoratorPaths("/platform/*", "/platform/decorators/main.jsp",   
//                                             "/platform/decorators/top.jsp")  
           // 不被渲染的URI路径.  
           //.addExcludedPath("/brochures/*")
           .addExcludedPath("/ace/*")
           .addExcludedPath("/jkbb/*")
           .addExcludedPath("/jsTree/*")
           .addExcludedPath("/se7en/*")
           .addExcludedPath("/login");  
		

	}

}
