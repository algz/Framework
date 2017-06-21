package algz.platform.util.json;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;
import org.springframework.aop.support.AopUtils;

import org.springframework.util.ClassUtils;

import javassist.ClassPool;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.RuntimeSupport;
import net.sf.json.util.PropertyFilter;

/**
 * 
 * 使用方法:
public String listAjax(){
  Map<String, Object> returnMap = new HashMap<String, Object>();
  List<Product> productList = productService.getAll();
  returnMap.put("results", productList);
  //......
  JsonConfig config = new JsonConfig();
  //在这里传入AnnotationPropertyFilter对象
  config.setJsonPropertyFilter(new AnnotationPropertyFilter());
  returnObj = JSONObject.fromObject(returnMap,config);
  
  ......
}
 * 
 * @author algz
 *
 */
public class AnnotationPropertyFilter implements PropertyFilter  {

	public boolean apply(Object object, String name, Object value) {
		 
	    // 通过读取注解来决定是否序列化
	    try {
			// if (ProxyFactory.isProxyClass(tag.getOverview().getClass())) {
			// Proxy proxy=(Proxy)tag.getOverview();
			// JavassistLazyInitializer
			// methodHandler=(JavassistLazyInitializer)ProxyFactory.getHandler(proxy);
			// ao= (AircraftOverview)methodHandler.getImplementation();
			// }else{
			// ao=tag.getOverview();
			// }
	    	
	    	//过滤 javassist 属性,否则会进行循环异常.
	    	if(name.equals("handler")||name.equals("hibernateLazyInitializer")){
	    		return true;
	    	}
	    
	      // (1)获取类上的注解
	      JSONPropertyFilter clazzAnnotation = object.getClass().getAnnotation(JSONPropertyFilter.class);
	      // 如果类上有注解,并且值为true，表示需要过滤
	      if (clazzAnnotation != null && clazzAnnotation.Value()) {
	        // 不要这个字段 返回true
	        return true;
	      }
	      
	      // (2)获取字段上的注解
	      Field field =ClassUtils.getUserClass(object).getDeclaredField(name);
	      JSONPropertyFilter fieldAnnotation = field.getAnnotation(JSONPropertyFilter.class);
	      // 如果字段上注解了，并且值为true，表示需要过滤
	      if (null != fieldAnnotation && fieldAnnotation.Value()) {
	        // 不要这个字段 返回true
	        return true;
	      }
//	      object.getClass().getSuperclass().getField("handler");
//	      ClassPool pool = ClassPool.getDefault();
//	      RuntimeSupport.makeSerializedProxy(object).
//	      ProxyFactory..getHandler(p);
//	      ProxyFactory.isProxyClass((Proxy)object).getHandler(object);
//	      org.hibernate.proxy.pojo.javassist
//	      ClassPool pool=ClassPool.getDefault();
//	     object.getClass()..getClassLoader().toString()
//	      Proxy.isProxyClass(JavassistLazyInitializer..class)
//	      AopUtils.isJdkDynamicProxy(object)(object).isCglibProxy(object)
	      // (3)通过属性描述器 获取属性get方法的注解
	      PropertyDescriptor pd = new PropertyDescriptor(field.getName(), object.getClass());
	      Method getMethod = pd.getReadMethod();
	      if (null != getMethod) {
	        JSONPropertyFilter methodAnnotation = getMethod.getAnnotation(JSONPropertyFilter.class);
	        // 如果get方法上注解了，并且值为true，表示需要过滤
	        if (null != methodAnnotation && methodAnnotation.Value()) {
	          // 不要这个字段 返回true
	          return true;
	        }
	      }
	      
	    } catch (NoSuchFieldException e) {
	      return false;
	    } catch (SecurityException e) {
	      return false;
	    } catch (IntrospectionException e) {
	      return false;
	    }
	 
	    return false;
	  }
	 

}
