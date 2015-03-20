package com.hello.example.utils;


//import org.apache.commons.io.FilenameUtils;

/**
 *
 * 描述			: 文件操作相关工具类
 * @version     : 1.0 
 * @since       : 2012-5-15下午4:34:44
 * @team	    : 基础组件
 * @author      : 杨怀
*/
public class SyswareFileUtil {
	
	
	/**
	 * 描述	   : 获取文件名后缀
	 * @since  : 2012-5-15:下午4:33:31
	 * @author : 杨怀
	 * @param filename 文件名称
	 * @return 文件名的后缀
	 */
	public static String getFileSuffix(String filename){
		
		if(SyswareUtil.isEmpty(filename)){
			return null;
		}
		
		int flag = filename.lastIndexOf(".");
		
		return flag==-1?"":filename.substring(flag, filename.length());
		
	}
	
	/**
	 * 描述	   : 根据文件的全路径得到文件的名称 如：C:\abc\xyz\bbs.xml   返回 bbs.xml
	 * @since  : 2012-5-15:下午4:36:12
	 * @author : 杨怀
	 * @param psFileFullpath
	 * @return
	 */
	public static String getSimpleFileName(String psFileFullpath){
		return null;//FilenameUtils.getName(psFileFullpath);
	}
	
}
