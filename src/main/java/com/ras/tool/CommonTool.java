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
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.xml.internal.security.encryption.Serializer;

import algz.platform.core.shiro.authority.roleManager.Role;
import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;
import javafx.util.Callback;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

@Component
public class  CommonTool{
	
	public static String ARCHIVE_DIR;

	public static String ARCHIVE_URL_PREFIX;
	
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
	
	@Value("${archive_url_prefix}")
	public void archiveUrlPrefixMethod(String archive){
		ARCHIVE_URL_PREFIX=archive;
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
		if(JSONUtils.isString(obj)||net.sf.json.util.JSONUtils.isArray(obj)){
		    //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
	        //CommonTool.writeJSONToPage(response, "{\"success\":true}"); 
			object=obj;
		}else{
			object = beanToJSONObject(obj,true);
//			response.setContentType("text/json;charset=UTF-8");
		}
		
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 动态方式: 输出 JSON 对象
	 * @param response
	 * @param obj
	 * @param handle
	 */
	public static <T> void writeJSONToPage(HttpServletResponse response,Object obj,MethodHandle handle){
		Object object=null;
		try {
		if(JSONUtils.isString(obj)){
		    //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
	        //CommonTool.writeJSONToPage(response, "{\"success\":true}"); 
			object=obj;
		}else{
			object = handle.invoke(obj);
			response.setContentType("text/json;charset=UTF-8");
		}
			response.getWriter().print(object);
		} catch(Throwable e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 回调方式:  输出 JSON 对象
	 * @param response
	 * @param obj
	 */
	public static <T> void writeJSONToPage(HttpServletResponse response,Object obj,Callback callback){
		Object object=null;
		if(JSONUtils.isString(obj)){
		    //$.ajax({success:...}),要跳到success函数,必须返回值success为双引号括起来,单引号不跳到.error.
	        //CommonTool.writeJSONToPage(response, "{\"success\":true}"); 
			object=obj;
		}else{
			object = callback.call(obj);//JSONTools.BeanToJson(obj);//..beanToJSONObject(obj,true);
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
				// 这里填写需要过滤的属性名,返回 true,则过滤;
				//class.isInstance(obj),判断 obj 对象能否转换成 class 类型;
				//obj instanceof Class,判断 obj 对象是不是 Class 类型.
			if (source.getClass().isInstance(value)|| value instanceof JavassistLazyInitializer||value instanceof Set||value instanceof PersistentBag||value instanceof MultipartFile||(!isSaveNull&&value==null)){
				return true;
			}
			
			try{
				// (2)获取字段上的注解
			      Field field = source.getClass().getDeclaredField(name);
			      if(field!=null){
				      for(Annotation annotation : field.getAnnotations()){
				    	  String annotationName=annotation.annotationType().getName();
				    	  if(annotationName.contains("javax.persistence.OneToOne")
				    	     ||annotationName.contains("javax.persistence.ManyToOne")
				    	     ||annotationName.contains("javax.persistence.OneToMany")
				    	     ||annotationName.contains("JSONPropertyFilter")){
				    		  return true;
				    	  }
				      }
			      }

//			      JSONPropertyFilter fieldAnnotation = field.getAnnotations().getAnnotation(JSONPropertyFilter.class);
//			      // 如果字段上注解了，并且值为true，表示需要过滤
//			      if (null != fieldAnnotation && fieldAnnotation.Value()) {
//			        // 不要这个字段 返回true
//			        return true;
//			      }
			      
			}catch(Exception e){
				//e.printStackTrace();
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
	public static void mapToBean(Map<String, String> map,Object obj)throws Exception{
	            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	            for (PropertyDescriptor property : propertyDescriptors) {
	                String key = property.getName();
	                if (map.containsKey(key)) {
	                    String value = map.get(key);
	                    // 得到property对应的setter方法
	                    Method setter = property.getWriteMethod();
	                    switch (setter.getParameterTypes()[0].getName()){
		                    case "java.lang.Integer":
		                    	if(!value.equals("")){
			                    	setter.invoke(obj, Integer.parseInt(value));
		                    	}
		                    	break;
		                    case "java.lang.Double":
		                    	if(!value.equals("")){
		                    		setter.invoke(obj, Double.parseDouble(value));
		    	                }
		                    	break;
		                    default:
		                    	setter.invoke(obj, value);
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
	
	/**
	 * 获得与Hibernate 自动生成ID的相同方法
	 * @param sf
	 * @return
	 */
	public static String getGUID(Session sf){
		String sql="select rawtohex(sys_guid()) from dual";
		return (String)sf.createSQLQuery(sql).uniqueResult();
	}
	
	/**
	 * 保存不成功返回null;成功则返回文件.
	 * @param mfile
	 * @param path
	 * @return
	 */
	public static File saveFile(MultipartFile mfile,String path){
//		String contentType = mfile.getContentType();//文件类型.image/jpeg.
		String mfileName=mfile.getOriginalFilename();
        String type = mfileName.substring(mfileName.lastIndexOf(".")+1);//contentType.substring(contentType.indexOf("/") + 1);//文件扩展名
		String fileName=System.currentTimeMillis()+new Random().nextInt(100) + "." + type;
//		String filePath=path+File.separator+fileName;
		File destFile=new File(path+fileName);
		File destDir=destFile.getParentFile();
		try {
			
			if(!destDir.exists()){
				destDir.mkdirs();//自动创建多层目录
			}
			mfile.transferTo(destFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return destFile;
	}
	
	/**
	 * 文件不存在返回null;删除成功返回true.
	 * @param mfile
	 * @param path
	 * @return
	 */
	public static boolean delFile(String pathFile){
		File file=new File(pathFile);
		if(file.exists()){
			return file.delete();
		}
		return false;
	}
	
	/**
	 * 下载文件
	 * @param pathFile 文件路径(含文件名)
	 * @param displayName 显示下载文件名
	 * @param response
	 */
	public static void downloadFile(String pathFile,String displayName,HttpServletResponse response){
        File file = new File(pathFile);
        if (file.exists()) {
            response.setContentType("application/force-download;charset=UTF-8");// 设置强制下载不打开
            //response.addHeader("Content-Disposition","attachment;fileName=" + displayName);// 设置文件名
            response.addHeader("Content-Length", "" + file.length()); 
            try {
            	//解决文件名为中文时的乱码问题,需要将文件名编码成ISO8859-1.
				response.addHeader("Content-Disposition","attachment;fileName=" +new String(displayName.getBytes("gbk"),"iso-8859-1") );
			} catch (Exception e1) {
				e1.printStackTrace();
			}// 设置文件名
            
            
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(pathFile));
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
        	try {
        		response.setContentType("text/json;charset=UTF-8");
				response.getWriter().print("文件不存在!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * 判断是否是数据管理员
	 * @param roles
	 * @return true 是数据管理员,通过;false 不是数据管理员,添加查询自己编辑的数据.
	 */
	public static boolean isDataManager(){
		List<Role> roles=Common.getLoginUser().getRoles();
		if(roles!=null){
			for(Role role:roles){
				if(role.getRoleid().equals("2")){
					return true;
				}
			}
		}
		return false;
	}
	
	public static String getAuthoritySQL(String userid){
		String sql="";
		//权限控制
		if(!CommonTool.isDataManager()){
			sql+=" and ((ao.editor='"+userid+"' and ao.PERMISSION_LEVEL='1') "
					+ "or ao.PERMISSION_LEVEL in ('2','3') "
					+ "or (ao.PERMISSION_LEVEL='4' and ao.editor in (select udp.userid from RAS_USER_DATA_PRIVILIDGE udp where udp.dataid=ao.id)))";
		}
		return sql;
	}

	/**
	 * 回调接口,只能有一个方法.
	 * 回调=接口+匿名类; 匿名类=lambda.
	 * @author algz
	 *
	 */
	public interface MethodCallBack  {
		public String callBack(Field f, Object val);
	}
	
	/**
	 * 
	 * @param sf
	 * @param sql
	 * @param example
	 * @param start
	 * @param length
	 * @param MethodCallBack 参数回调(回调接口只能有一个方法)
	 * @return
	 */
	public static <T> List<T> findEntitiesByProperty(SessionFactory sf,StringBuilder sql,Object example,Integer start,Integer length,MethodCallBack callback) {
//		StringBuilder sql=new StringBuilder("select distinct * from "+tableName+" where 1=1 ");
		
		Field[] fs = example.getClass().getDeclaredFields();
		try {
			for (Field f : fs) {
				f.setAccessible(true); 
				Object val = f.get(example);
				if(val!=null&&!val.equals("")){
					//参数回调
					if(callback!=null){
						sql.append(callback.callBack(f,val));
					}else{
						Column col=f.getAnnotation(Column.class);
						sql.append(" and LOWER("+col.name()+") = '"+val.toString().toLowerCase()+"'");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SQLQuery query=sf.getCurrentSession().createSQLQuery(sql.toString())
						 .addEntity(example.getClass());
		if(start!=null){
			query.setFirstResult(start);
		}
		if(length!=null){
			query.setMaxResults(length);
		}
		return query.list();
	}
	
	public static void saveSingleEntityForProperty(SessionFactory sf,Object example) {
		
		
		//set t.paramvalue='' where t.paramid='' and t.overviewid='' select distinct * from "+tableName+" where 1=1 
		
		String colSql="";
		String valSql="";
		
		Field[] fs = example.getClass().getDeclaredFields();
		try {
			for (Field f : fs) {
				f.setAccessible(true); 
				Object val = f.get(example);
				if(val!=null&&!val.equals("")){
					Column col=f.getAnnotation(Column.class);
					
					if(!colSql.equals("")){
						colSql+=",";
						valSql+=",";
					}
					
					//列
					colSql+=col.name();
					
					//值
					if(f.getAnnotation(Id.class)!=null){
						valSql+="'"+CommonTool.getGUID(sf.getCurrentSession())+"'";
					}else{
						valSql+="'"+val+"'";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String tableName=example.getClass().getAnnotation(Table.class).name();
		sf.getCurrentSession().createSQLQuery("insert into "+tableName+"("+valSql+") values ("+colSql+")").executeUpdate();
	}
	
	public static void updateSingleEntityForProperty(SessionFactory sf,Object example) {
		
		String tableName=example.getClass().getAnnotation(Table.class).name();
		StringBuilder sql=new StringBuilder(" update "+tableName+" set ");
		//set t.paramvalue='' where t.paramid='' and t.overviewid='' select distinct * from "+tableName+" where 1=1 
		
		String valueSql="";
		String whereSql="";
		
		Field[] fs = example.getClass().getDeclaredFields();
		try {
			for (Field f : fs) {
				f.setAccessible(true); 
				Object val = f.get(example);
				Column col=f.getAnnotation(Column.class);
				if(val!=null&&col!=null){
					if(f.getAnnotation(Id.class)!=null){
						whereSql+=" where "+col.name()+"='"+val+"'";
					}else{
						if(!valueSql.equals("")){
							valueSql+=",";
						}
						valueSql+=col.name()+"='"+val+"'";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sf.getCurrentSession().createSQLQuery(sql.toString()+valueSql+whereSql).executeUpdate();
	}
	
	public static BigDecimal countEntitiesByProperty(SessionFactory sf,StringBuilder sql,Object example) {
//		StringBuilder sql=new StringBuilder("select count(*) from "+tableName+" where 1=1 ");
		
		Field[] fs = example.getClass().getDeclaredFields();
		try {
			for (Field f : fs) {
				f.setAccessible(true); 
				Object val = f.get(example);
				if(val!=null&&!val.equals("")){
					Column col=f.getAnnotation(Column.class);
					sql.append(" and LOWER("+col.name()+") = '"+val.toString().toLowerCase()+"'");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (BigDecimal)sf.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}
	
	public static BigDecimal countEntitiesByProperty(SessionFactory sf,Object example) {
		String tableName=example.getClass().getAnnotation(Table.class).name();
		StringBuilder sql=new StringBuilder("select count(*) from "+tableName+" where 1=1 ");
		
		Field[] fs = example.getClass().getDeclaredFields();
		try {
			for (Field f : fs) {
				f.setAccessible(true); 
				Object val = f.get(example);
				if(val!=null&&!val.equals("")){
					Column col=f.getAnnotation(Column.class);
					sql.append(" and LOWER("+col.name()+") = '"+val.toString().toLowerCase()+"'");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (BigDecimal)sf.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}
	
	/**
	 * 复制对象
	 * @param example
	 * @return
	 */
	public static <T> T copyObject(T example) {
//		String tableName=example.getClass().getAnnotation(Table.class).name();
//		StringBuilder sql=new StringBuilder("select distinct * from "+tableName+" where 1=1 ");
//		List<T> list=CommonTool.<T>findEntitiesByProperty(sf, sql, example, 0, 1, null);
//		if(list.size()!=0){
			T newObje=null;
			try {
				newObje=(T)example.getClass().newInstance();
//				ConvertUtils.register(converter, clazz);
				// 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空 
				ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.sql.Date.class); 
				ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.util.Date.class);  
				ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlTimestampConverter(null), java.sql.Timestamp.class); 
				// 注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空 
				
				ConvertUtils.register(new org.apache.commons.beanutils.converters.BigDecimalConverter(null), java.math.BigDecimal.class); 
				
				BeanUtils.copyProperties(newObje, example);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} 
			return newObje;
//		}else{
//			return null;
//		}
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