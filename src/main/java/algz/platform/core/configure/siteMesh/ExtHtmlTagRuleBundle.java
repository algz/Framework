/**
 * 
 */
package algz.platform.core.configure.siteMesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * @author algz
 * 扩展 siteMesh3 标签类
 */
public class ExtHtmlTagRuleBundle implements TagRuleBundle {

	/* (non-Javadoc)
	 * @see org.sitemesh.content.tagrules.TagRuleBundle#cleanUp(org.sitemesh.tagprocessor.State, org.sitemesh.content.ContentProperty, org.sitemesh.SiteMeshContext)
	 */
	@Override
	public void cleanUp(State arg0, ContentProperty arg1, SiteMeshContext arg2) {
		
	}

	/* 
	 * 添加自定义扩展 Tag 标签
	 * (如果添加多个 Tag 标签,即复制多条 addRule 语句.
	 */
	@Override
	public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
		//自定义 myfooter 标签
		defaultState.addRule("plugin_css", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("plugin_css"), false));
		defaultState.addRule("plugin_js", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("plugin_js"), false));
	}

}
