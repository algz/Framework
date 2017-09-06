package algz.platform.util;

import java.io.File;

public class FileUtil {

	 /** 
	  *   File file=new File("D:\\demo");  
        //deleteFileByWinCom(file);  
        deleteFile(file); 
     * 通过调用系统命令删除一个文件夹及下面的所有文件 
     * @param file 
     */  
    public static void deleteFileByWinCom(File file){  
    	if(file.exists()){
            Runtime rt = Runtime.getRuntime();  
            String cmd = null;  
            try{  
                if(file.isFile()){  
                    cmd = "cmd.exe /c del /q/a/f/s "+file.getAbsolutePath();  
                }else{  
                    cmd = "cmd.exe /c rd /s/q "+file.getAbsolutePath();  
                }  
                rt.exec(cmd);  
                System.out.println("成功执行了命令...");  
            }catch(Exception e){  
                System.out.println("调用系统命令失败了...");  
            }  
    	}
    }  
      
    /** 
     * 通过递归调用删除一个文件夹及下面的所有文件 
     * @param file 
     */  
    public static void deleteFile(File file){  
    	if(file.exists()){
            if(file.isFile()){//表示该文件不是文件夹  
                file.delete();  
            }else{
                //首先得到当前的路径  
                String[] childFilePaths = file.list();  
                for(String childFilePath : childFilePaths){  
                    File childFile=new File(file.getAbsolutePath()+"\\"+childFilePath);  
                    deleteFile(childFile);  
                }  
                file.delete();  
            }  
    	}

    }  
}
