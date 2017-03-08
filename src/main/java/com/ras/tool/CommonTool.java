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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class  CommonTool{
	
	/**
	 * 输出 JSON 对象
	 * @param response
	 * @param obj
	 */
	public static void writeJSONToPage(HttpServletResponse response,Object obj){
		Object object=null;
		if(JSONUtils.isString(obj)){
			object=obj;
		}else{
			object = JSONObject.fromObject(obj, new JsonConfig().copy());
			response.setContentType("text/json;charset=UTF-8");
		}
		
		try {
			response.getWriter().print(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
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