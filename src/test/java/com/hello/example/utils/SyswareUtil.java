package com.hello.example.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import org.springframework.util.Assert;



 
/**
 * TODO
 * Copyright    :www.sysware.com.cn
 * Company      :sysware Info. Ltd.
 * Created      :2012-2-18上午10:10:47
 * @author      :杨怀
*/
public class SyswareUtil {

	public static final String EMPTY_STRING = "";
	
	/**
	 * 用于判断是否为有效数字的正则掩码
	 */
	private static final Pattern  PATTERN_ISNUMBER = Pattern.compile("(^-?\\d+)|(^-?\\d+\\.\\d+)");
	
	private static final Pattern  PATTERN_ISINTEGER = Pattern.compile("(^-?\\d+)|(^-?\\d+\\+)");
	
	
	private static Properties proper =null;
	
	static{
		proper = System.getProperties();
	}
	
	
	
	/**
	 * @param psValue 需要检查是否为数字的参数
	 * @return 如果是有效数字，返回true，反之返回false（包括小数、整数）
	*/
	public static boolean isNumber(String psValue){
		return PATTERN_ISNUMBER.matcher(psValue).matches();
	}
	
	/**
	 * 检查字符串是否为整数
	 * 
	 * @created : 2012-4-9:下午04:17:53
	 * @author : liuxj
	 * @param psValue
	 *            需要检查的字符串
	 * @return 整数返回true，否则返回false
	 */
	public static boolean isInteger(String psValue) {
		return PATTERN_ISINTEGER.matcher(psValue).matches();
	}
	
	
	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String psString){
		if(psString==null||psString.trim().length()==0){
			return true; 
		}
		return false;
	}
	
	/**
	 * 判断一个对象是否为空
	 */
	public static boolean isEmpty(Object pObj){
		return pObj == null;
	}
	
	/**
	 * 判断一个Map集合是否为空
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map pMap){
		if(pMap==null||pMap.size()==0){
			return true; 
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(Properties properties){
		if(properties == null || properties.size() ==0 ){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 判断一个Map集合是否为空
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List pList){
		if(pList==null||pList.size()==0){
			return  true; 
		}
		return false;
	}
	
	/**
	 * 判断一个对象数组是否为空
	 */
	public static boolean isEmpty(Object[] paryObj){
		if(paryObj==null||paryObj.length==0){
			return true; 
		}
		return false;
	}
	
	
	/**
	 * 判断一个StringBuffer是否为空
	 */
	public static boolean isEmpty(StringBuffer pstringBuffer){
		if(pstringBuffer!=null&&pstringBuffer.length()>0){
			return  true;
		}
		return false;
	}
	
	
	/**
	 * 判断一个对象数组是否为空
	 */
	public static boolean isEmpty(byte[] paryByte){
		if(paryByte==null||paryByte.length==0){
			return true; 
		}
		return false;
	}
	
	
	
	/**
	 * @param pObj
	 * @param psDefaultValue
	 * @return
	 */
	public static String toString(Object pObj,String psDefaultValue){
		if(isEmpty(pObj)&&!isEmptyNotTrim(psDefaultValue)){
			return psDefaultValue;
		}else if(isEmpty(pObj)){
			return null;
		}
		return pObj.toString();
	}
	
	
	public static boolean isEmptyNotTrim(String psStr){
		return psStr == null;
	}
	
	/**
	 * 得到参数的目录路径
	 * @param pFile 文件
	 */
	public static String getFileDir(File pFile){
		if(isEmpty(pFile)){
			return null;
		}
		return pFile.getParentFile().getPath();
	}
	
	
	@SuppressWarnings("rawtypes")
	public static String getFilePath(Class pClass,String psFile){
		return pClass.getResource(psFile).getFile();
	}
	
	
	/**
	 * @param psFilename
	 * @return 返回可下载的中文文件名称
	 */
	public static String convertDownloadFilename(String psFilename) {
		Assert.notNull(psFilename, "文件名称不能为空");
		try {
			return new String(psFilename.getBytes("GBK"), "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	
	/**
	 * @TODO 根据文件名称，获取其数据流
	 * @Created : 2012-4-9:下午1:26:02
	 * @param psFileName
	 * @return 返回文件的数据流
	 * @throws IOException
	 */
	public static InputStream getInputStream(String psFileName) throws IOException {
		InputStream is;
		
		is = SyswareUtil.class.getResourceAsStream(psFileName);
		if(is==null){
			is = SyswareUtil.class.getClassLoader().getResourceAsStream(psFileName);
		}
		if (is == null) {
			throw new FileNotFoundException(psFileName + " cannot be opened because it does not exist");
		}
		return is;
	}
	
	
	
	
	
	/**
	 * @TODO 把String类型的值，转换成指定的类型的对象
	 * @Created : 2012-4-9:下午1:28:39
	 * @param psValue   要转换的值
	 * @param pClass    转换类型
	 * @param poDefaultValue 默认值
	 * @return 转换成指定的类型的对象
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertToClass ( String psValue , Class pClass , Object poDefaultValue ) {
		
		if ( psValue == null )
			return null ;
		
		Object objValue = null ;
		
		if ( pClass.equals( java.sql.Timestamp.class )) {
			objValue =  SyswareDateUtils.convertToTimestamp( psValue ) ;
		} else if (pClass.equals( java.sql.Date.class ) ) {
			objValue = SyswareDateUtils.convertToSqlDate( psValue ) ;
		} else if ( pClass.equals( java.lang.Long.class ) ) {
			try{
					objValue = Long.valueOf(psValue) ;
				}catch(Exception e) {}
		} else if ( pClass.equals( java.lang.String.class ) ) {
			objValue = psValue ;
		} else {
			throw new SyswareRuntimeException ( " Unknow from String convert to " + pClass  ) ;
		}
		
		return objValue == null ? poDefaultValue :objValue ;
	}
	
	
	
	/**
	 * 把字符串按照指定的分隔符 分割成数组
	 * @param psStringValue 字符串值 
	 * @param replaced 分隔符
	 * @return 字符串分割后的数组
	 */
	public static String[] getSplitedStringValue(String psStringValue,String replaced){
		String[] ary = null;
		if(!isEmpty(replaced)){
			psStringValue = psStringValue.trim();
			ary = psStringValue.split(replaced);
		}
		return ary;
	}
	
	/**
	 * 根据系统的参数名称，获取系统参数的值
	 * @param psProperName 参数名称
	 * @return 系统参数值
	 */
	public static String getSystemProperByName(String psProperName){
		return proper.getProperty(psProperName);
	}
	
	
	
	/**
	 * 判断系统运行的是不是Windows
	 * @return判断系统运行的是不是Windows（true：是windows,no:非windows）
	 */
	public static boolean isWindowOS(){
		return true;//SystemUtils.IS_OS_WINDOWS;
	}
	
	/**
	 * 根据ip，判断该主机是否网络畅通
	 * 
	 * @author : 杨怀
	 * @param hostName
	 * @return
	 */
	public static boolean hostReachable(String hostName){
		boolean reachable = false;
		try {
			reachable = InetAddress.getByName(hostName).isReachable(2000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reachable;
	}
	
	public static void assertNotNull(List plstValue, String psMessage){
		if ( isEmpty(plstValue) ) {
			throw new SyswareRuntimeException ( psMessage );
		}
	}
	
	
	/**
	 * Object 非空断言
	 * 
	 * @param psValue 被检查 Object
	 * 
	 * @param psMessage 断言失败的提示信息
	 */
	public static void assertNotNull(String psValue, String psMessage){
		if ( isEmpty(psValue) ) {
			throw new SyswareRuntimeException (psMessage );
		}
	}
	
	
}
