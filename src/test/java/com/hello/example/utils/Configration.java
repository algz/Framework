package com.hello.example.utils;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 
/**
 * @copyright    : Sysware Technology Co., Ltd
 *
 *  组件配置初始化
 * @version     : 1.0 
 * @created     : 2012-4-28下午2:56:23
 * @team	    : 基础组件
 * @author      : 杨怀
*/
public class Configration {

	private static Log log = LogFactory.getLog(Configration.class);
	
	public final static String WEB_CLASSPATH_FLAG = "WEB-INF";
	
	public final static String APP_CLASSPATH_FLAG = "/com/";
	
	
	/**
	 * 返回应用程序路径：
	 * <li> web工程        :/xxx/yyy/WebRoot/
	 * <li> 非web工程   :即classpath路径
	 * @return 应用程序路径
	 */
	public static String getApplicationRootPath(){
		
		String path = getProperty("ROOT_PATH");
		
		if(SyswareUtil.isEmpty(path)){
			path= getWebApplicationRootPath();
		}
		
		return path;		
	}
	
	
	/**
	 * 返回一个WEB项目的应用路径
	 * @return WEB项目的应用路径
	 */
	public static String getWebApplicationRootPath(){
		
		String applicationWebRootPath = null;
		
		URL url= Configration.class.getResource("Configration.class");
		
		String fileFullPath = url.getPath();
		
		log.info("The WebApplication Path IS :"+fileFullPath);
		 
		if(!SyswareUtil.isEmpty(fileFullPath)){
			
			int flag_web = fileFullPath.indexOf(WEB_CLASSPATH_FLAG);
			
			int flag_app = fileFullPath.indexOf(APP_CLASSPATH_FLAG);
			
			if(flag_web>0){
				applicationWebRootPath = parseWebApplication(fileFullPath,flag_web);
			}else if(flag_app>0){
				applicationWebRootPath = parseWebApplication(fileFullPath,flag_app);
			}else{
				log.error("You aren't execute this method in a Web Application !");
			}
		}
		return applicationWebRootPath;
	}
	
	
	private static String parseWebApplication(String psApplicationPath ,int flag){
 
		String webApplicationPath = "";
		
		if(psApplicationPath.startsWith("/")){
		
			if(SyswareUtil.isWindowOS()){
				webApplicationPath = psApplicationPath.substring(1, flag);
			}else{
				webApplicationPath = psApplicationPath.substring(0, flag);
			}
		}else{
			webApplicationPath = psApplicationPath.substring(0, flag);
		}
		
		
		if(SyswareUtil.isWindowOS()){
			webApplicationPath = webApplicationPath.replaceFirst("file:/","");
		}else{
			webApplicationPath = webApplicationPath.replaceFirst("file:","");
		}
		
		return webApplicationPath;
	}
	
	
	
	
	/**
	 * 保存系统初始化参数
	 */
	private static Properties s_variable;
	
	
	/**
	 *  配置项组分隔符号 ， 通过定义不同的组配置项区分配置信息，如果 DB.User / DB.Password / DB.URL  等
	 */
	private static final String SPLIT_PROPERTY_PREFIX = ".";
	
	/**
	 * 默认的系统配置文件名称
	 */
	private static String s_configFilename = "config.xml";
	
	
	
	

	/**
	 * 通过给定的属性名称前缀获取配置属性数组，如 DB.User / DB.Password / DB.Url / DB.Driver ， 
	 * 通过 getPropertiesByPrefix ( "DB") 方法获取所有DB组中的配置信息 
	 * @param psPrefix 配置信息组名称
	 * @return 返回给定组名称的所有配置数据键值对集合
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getPropertiesByPrefix(String psPrefix) {

		if (s_variable == null)
			doInitialSystemProperties();

		String sPrefixKey = psPrefix + SPLIT_PROPERTY_PREFIX;

		Iterator itKeys = s_variable.keySet().iterator();

		Map<String, String> mapRetProperties = new HashMap<String, String>(20);

		while (itKeys.hasNext()) {
			String sKey = (String) itKeys.next();
			if (sKey.startsWith(sPrefixKey)) {
				mapRetProperties.put(sKey.substring(sKey.lastIndexOf(SPLIT_PROPERTY_PREFIX) + 1), s_variable.getProperty(sKey));
			}
		}
		if (SyswareUtil.isEmpty(mapRetProperties)) {
			new SyswareRuntimeException("Not found properties in"+ s_configFilename + "  file as " + psPrefix + "  start with ");
		}
		return mapRetProperties;
	}

	/**
	 *  获取给定配置项名称的配置数值
	 * @param psKey 配置项键名称
	 * @return 返回给定配置键值对应的配置数据
	 */
	private static String getProperty(String psKey) {
		if (s_variable == null)
			doInitialSystemProperties();

		return s_variable.getProperty(psKey);
	}

	
	
	/**
	 * 预初始当前系统路径
	 */
	private static void doInitialSystemProperties() {
		synchronized (Configration.class) {
			if (s_variable != null){
				return;
			}
			InputStream is = null;
			try {
				is = SyswareUtil.getInputStream(s_configFilename);
				loadProperties(is);
			} catch (Exception e) {
					throw new SyswareRuntimeException("config do initial error  ，Not found cofnig file : "+ s_configFilename + "  in class path  ", e);
			} finally {
				if (is != null){
					try {
						is.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	
	
	/**
	 * 加载配置文件到 Properties 中
	 * @param is 配置文件输入流
	 */
	private static void loadProperties(InputStream is) {
		s_variable = new Properties();
		try {
			s_variable.loadFromXML(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SyswareRuntimeException("configration initial error on load  " + s_configFilename+ " in classpath " + e.getMessage());
		}
	}
	
}
