/**
 * 
 */
/**
 * @author algz
 *
 */
package algz.platform.core.license;

import javax.servlet.ServletContext;

public class LicenseManager{
	
	public static String LicenseResult="";
	
	public static boolean currentState=false;
	
	public static boolean checkLicense(ServletContext servletContext){
		String realPath=servletContext.getRealPath("/platform/license.dat");
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//				+ path + "/";
		currentState=false;
		LicenseResult="会话失效";
		
		//currentState=false;
		return false;
	}
}