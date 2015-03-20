package com.hello.example.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 描述			: 到特定目录下读取Properties文件的公用类
 * @version     : 1.0 
 * @since       : 2012-5-3下午5:43:04
 * @team	    : 基础组件
 * @author      : 杨怀
*/
public class PropertiesUtil {

	Log log = LogFactory.getLog(PropertiesUtil.class);
	
	/**
	 * 缓存key值
	 */
	private final static String CACHE_KEY = "com.sysware.framework.commons.PropertiesUtil.getPropertiesByFilename";
	
	/**
	 * properties文件的路径
	 */
	private final static String PROPERTIES_PATH_SUFFIX = "WEB-INF/classes/properties";
	
	/**
	 * properties文件的扩展名
	 */
	private final static String FILENAME_SUFFIX = ".properties";
	
	/**
	 * properties文件的路径信息
	 */
	private static Map<String,String> mapPropertiesFiles = null;
	
	private static PropertiesUtil propertiesUtil = new PropertiesUtil();
	
	public static PropertiesUtil newInstance(){
		return propertiesUtil;
	}
	
	private PropertiesUtil(){
	}
	
	/**
	 * 描述	   : 根据配置文件名称、获取其配置信息
	 * @since  : 2012-5-4:上午11:21:28
	 * @param psProperties 配置文件名称
	 * @return
	 */
	public Properties getPropertiesByFilename(String psProperties){
		Properties properties = new Properties();
		try { 
			initPropertiesFilePath();
			if(SyswareUtil.isEmpty(mapPropertiesFiles)){
				return null;
			}
			if(!SyswareUtil.isEmpty(mapPropertiesFiles.get(psProperties))){
				properties.load(new FileInputStream(mapPropertiesFiles.get(psProperties)));
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 描述	   : 根据properties文件名 和key获取properties文件中对应的值
	 * @since  : 2012-7-17:下午05:20:59
	 * @param psProperties properties文件名
	 */
	public String getPropertiesByFilename(String psProperties, String key){
		
		Properties properties = getPropertiesByFilename(psProperties);
		
		if(!SyswareUtil.isEmpty(properties)){
			return properties.getProperty(key);
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 描述	   : 获取properties文件的路径信息
	 * @since  : 2012-5-7:上午10:02:08
	 * @return
	 */
	public Map<String,String> getPropertiesPaths(){
		
		if(SyswareUtil.isEmpty(mapPropertiesFiles)){
			initPropertiesFilePath();
		}
		
		return mapPropertiesFiles;
	}
	
	
	
	/**
	 * 描述	   : 初始化properties文件路径信息
	 * @since  : 2012-5-7:上午9:56:38
	 */
	@SuppressWarnings("unchecked")
	private void initPropertiesFilePath(){
		
		String webapplicationPath = Configration.getWebApplicationRootPath();
		
		String properties_path = webapplicationPath + PROPERTIES_PATH_SUFFIX;
		
		try { 
			
			if(SyswareUtil.isEmpty(mapPropertiesFiles)){
				mapPropertiesFiles = SyswareIOUtils.getFileNamesAsMap(properties_path,FILENAME_SUFFIX);
			//	CacheFactory.getCache(null).put(CACHE_KEY, mapPropertiesFiles);
			}else{
				//mapPropertiesFiles = (Map<String,String>)CacheFactory.getCache(null).get(CACHE_KEY);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
