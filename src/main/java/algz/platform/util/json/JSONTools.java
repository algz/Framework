package algz.platform.util.json;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONTools {

	/**
	 * Bean的属性注解@JSONPropertyFilter,则过滤此属性. 输出 JSON 对象
	 * @param beanObj
	 */
	public static JSONObject BeanToJson(Object beanObj){
		  JsonConfig config = new JsonConfig();
		  //在这里传入AnnotationPropertyFilter对象
		  config.setJsonPropertyFilter(new AnnotationPropertyFilter());
		  return JSONObject.fromObject(beanObj,config);
	}
}
