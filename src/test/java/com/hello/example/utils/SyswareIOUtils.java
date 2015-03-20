package com.hello.example.utils;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * Copyright    :www.sysware.com.cn
 * Company      :sysware Info. Ltd.
 * Created      :2012-2-27上午11:01:25
 * @author      :杨怀
*/
public class SyswareIOUtils { 
	
	/**
	 * @param pfFile	目标文件目录（循环递归，找到所有给定后缀名称的文件）
	 * @param psSuffix  文件后缀
	 * @return	       	文件路径的集合
	 * @throws IOException
	 */
	public static List<String> getFileNames(File pfFile,String psSuffix) throws IOException {
		
		File[] files = pfFile.listFiles();

		if(SyswareUtil.isEmpty(files)){
			return null;
		}
		
		List<String> lstfileName = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if(SyswareUtil.isEmpty(psSuffix)){
					lstfileName.add(files[i].getCanonicalPath());
				}else if(files[i].getName().endsWith(psSuffix)){
					lstfileName.add(files[i].getCanonicalPath());
				}
			} else if (files[i].isDirectory()) {
				List<String> lstName = getFileNames(files[i],psSuffix);
				if(!SyswareUtil.isEmpty(lstName)){
					lstfileName.addAll(getFileNames(files[i],psSuffix));
				}
			}
		}
		return lstfileName;
	}
	
	
	public static String getFilePathByName(File pfFile,String psFilename) throws IOException{
		
		File[] files = pfFile.listFiles();

		if(SyswareUtil.isEmpty(files)){
			return null;
		}
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if(files[i].getName().endsWith(psFilename)){
					return files[i].getCanonicalPath();
				}
			}else if (files[i].isDirectory()) {
				getFilePathByName(files[i],psFilename);
			}
		}
		return null;
	}
	
	public static String getDirPathByName(File pfFile,String psDirName) throws IOException{
		File[] files = pfFile.listFiles();

		if(SyswareUtil.isEmpty(files)){
			return null;
		}
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				continue;
			}else if (files[i].isDirectory()) {
				if(files[i].getName().endsWith(psDirName)){
					return files[i].getCanonicalPath(); 
				}else{
					getDirPathByName(files[i],psDirName);
				}
			}
		}
		return null;
	}
	
	
	/**
	 * @param psFilePath	目标文件目录路径
	 * @param psSuffix		文件后缀
	 * @return				文件路径的集合
	 * @throws IOException
	 */
	public static List<String> getFileNames(String psFilePath,String psSuffix) throws IOException {
		
		Assert.isNull(psFilePath, "文件路径不能为空，请检查..........!");
		
		File file = new File(psFilePath);
		
		return getFileNames(file,psSuffix);
	}
	
	
	/**
	 * @param pfFile	目标文件目录（循环递归，找到所有给定后缀名称的文件）
	 * @return	       	文件路径的集合
	 * @throws IOException
	 */
	public static List<String> getFileNames(File pfFile) throws IOException{
		return getFileNames(pfFile,null);
	}
	
	
	/**
	 * @param psFilePath	目标文件目录路径
	 * @return				文件路径的集合
	 * @throws IOException
	 */
	public static List<String> getFileNames(String psFilePath) throws IOException{
		return getFileNames(psFilePath,null);
	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,String> getFileNamesAsMap(File pfFile, String psSuffix) throws IOException{
		
		Assert.isNull(pfFile, "文件不能为空，请检查..........!");
		
		List<String> lstFilePath= getFileNames(pfFile,psSuffix);
		
		if (SyswareUtil.isEmpty(lstFilePath)) {
			return null;
		}
		
		Map fileMap = new HashMap<String, String>(lstFilePath.size());
		
		for(String str : lstFilePath){
			fileMap.put(SyswareFileUtil.getSimpleFileName(str), str);
		}
		
		return fileMap;
	}
	
	
	public static Map<String,String> getFileNamesAsMap(String psFilePath, String psSuffix) throws IOException{
		
		Assert.isNull(psFilePath, "文件路径不能为空，请检查..........!");
		
		File file = new File(psFilePath);
		
		return getFileNamesAsMap(file,psSuffix);
	}
	
	
}
