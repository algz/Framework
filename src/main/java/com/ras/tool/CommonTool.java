/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.tool;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class  CommonTool{
	
	public static void writeJSONToPage(HttpServletResponse response,Object obj){
		JSONObject jo = JSONObject.fromObject(obj, new JsonConfig().copy());
//		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(jo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}