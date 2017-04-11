/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.tool;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ras.search.SearchTag;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

@Component
public class  CommonTool{
	
	public static String ARCHIVE_DIR;

	public static String PHOTO_DIR;
	
	public static String PHOTO_URL_PREFIX;
	
	/**
	 * static 变量注入值(仅只能通过方法完成.直接在静态变量上标示是无法注入的,值为null.注入值的来源,详见@PropertySource 一次使用,扫描类都可使用.)
	 * 
	 * @Component注解标示的类,@Value注解标示的方法,在初始化时,将自动调用.
	 * 如果@Value标示在参数中,则不调用,仅标示到方法名上才调用;如果是多参数,则仍然注入相同的值给多个参数.
	 * @Component 表示类将被扫描; @Value 表示参数值将注入.
	 * 
	 * @param archive
	 */
	@Value("${archive_dir}")
	public void archiveMethod(String archive){
		ARCHIVE_DIR=archive;
	}
	
	@Value("${photo_dir}")
	public void photoMethod(String photo){
		PHOTO_DIR=photo;
	}
	
	@Value("${photo_url_prefix}")
	public void photoUrlPrefixMethod(String photo){
		PHOTO_URL_PREFIX=photo;
	}
	
	/**
	 * 输出 JSON 对象
	 * @param response
	 * @param obj
	 */
	public static <T> void writeJSONToPage(HttpServletResponse response,Object obj){
		Object object=null;
		if(JSONUtils.isString(obj)){
		    //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
	        //CommonTool.writeJSONToPage(response, "{\"success\":true}"); 
			object=obj;
		}else{
			object = beanToJSONObject(obj,true);
			response.setContentType("text/json;charset=UTF-8");
		}
		
		try {
			response.getWriter().print(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 集合类型转换为JSONArray
	 * @param c
	 * @return
	 */
	public static <T>JSONArray arrayToJSONArray(Collection<T> c,final boolean  isSaveNull){
		JSONArray ja=new JSONArray();
		for(T t:c){
			ja.add(beanToJSONObject(t,isSaveNull));
		}
		return ja;
	}
	
	/**
	 * 实体bean 转换成JSONObject
	 * @param object bean
	 * @param isSaveNull 是否保存空字符串,true 保存;false 不保存
	 * @return
	 */
	public static JSONObject beanToJSONObject(Object obj,final boolean  isSaveNull){
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.setJsonPropertyFilter(new PropertyFilter(){
			/**
			 * @param source 输入的原对象
			 * @param name (对象的)属性名
			 * @param value (对象的)属性值
			 */
		public boolean apply(Object source, String name, Object value){
				source.getClass();
				// 这里填写需要过滤的属性名,返回 true,则过滤;
				//class.isInstance(obj),判断 obj 对象能否转换成 class 类型;
				//obj instanceof Class,判断 obj 对象是不是 Class 类型.
			if (source.getClass().isInstance(value)||value instanceof Set||(!isSaveNull&&value==null)){
				return true;
			}
			return false;
		}
		});
		return JSONObject.fromObject(obj, jsonConfig);
	}
	
	/**
	 * Map 转 Bean
	 * @param map
	 * @param obj
	 * @throws Exception
	 */
	public static void mapToBean(Map<String, String[]> map,Object obj)throws Exception{
	            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	            for (PropertyDescriptor property : propertyDescriptors) {
	                String key = property.getName();
	                if (map.containsKey(key)) {
	                    String[] value = (String[])map.get(key);
	                    // 得到property对应的setter方法
	                    Method setter = property.getWriteMethod();
	                    switch (setter.getParameterTypes()[0].getName()){
		                    case "java.lang.Integer":
		                    	if(!value[0].equals("")){
			                    	setter.invoke(obj, Integer.parseInt(value[0]));
		                    	}
		                    	break;
		                    case "java.lang.Double":
		                    	if(!value[0].equals("")){
		                    		setter.invoke(obj, Double.parseDouble(value[0]));
		    	                }
		                    	break;
		                    default:
		                    	setter.invoke(obj, value[0]);
			                    break;
	                    }
	                    
	                }
	            }
	}
	
	/**
	 * 表单查询字符串 转 Map 对象
	 * @param arr
	 * @param isSaveNull 是否保存空字符串,true 保存;false 不保存
	 * @return
	 */
	public static Map<String,String> stringFormToJson(String[] arr,boolean isSaveNull){
    	Map<String,String> map=new HashMap<String,String>();
    	for(String s: arr){
    		String[] tem=s.split("=");
    		if(tem.length!=2&&isSaveNull){
    			map.put(tem[0], "");
    		}else if(tem.length==2){
    			map.put(tem[0], tem[1]);
    		}
    	}
        return map;
    }
	
//	 private static void printType (Object object) {
//		    Class type = object.getClass();
//		    if (type.isArray()) {
//		      Class elementType = type.getComponentType();
//		      System.out.println("Array of: " + elementType);
//		      System.out.println(" Length: " + Array.getLength(object));
//		    }
//		  }
}