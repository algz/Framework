package com.hello;

import java.io.*;
import java.net.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;


//import algz.platform.util.excel.ExcelView;


/*
 *  当@RequestMapping 标记在Controller 类上的时候，里面使用@RequestMapping 标记的方法的请求地址都是相对于类上的@RequestMapping 而言的；
 *  当Controller 类上没有标记@RequestMapping 注解时，方法上的@RequestMapping 都是绝对路径。
 *  这种绝对路径和相对路径所组合成的最终路径都是相对于根路径“/ ”而言的。
 */

@Controller   //@Controller 定义了一个控制器类
@RequestMapping(value="/test")//printHello访问url: /test/hello.do
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
//	@Autowired
//	private ExcelService excelService;
	
    @RequestMapping(value="/hello") //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  printHello() {
		try {
			
//			
//			/**
//			* 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 通过把URLConnection设为输出，可以把数据向你个Web页传送。下面是如何做：
//			*/
//			connection.setRequestProperty("accept", "text/html");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; rv:2.0b11) Gecko/20100101 Firefox/4.0b11");
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			/**
//			*为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
//			*/
//			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
//			String name = "060610106";
//			String password = getPassword();
//			out.write("IPT_LOGINUSERNAME=" + name + "&IPT_LOGINPASSWORD=" + password); // 向页面传递数据。post的关键所在！
//			// remember to clean up
//			out.flush();
//			out.close();
//			/**
//			* 这样就可以发送一个看起来象这样的POST： POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT: text/plain Content-type:
//			* application/x-www-form-urlencoded Content-length: 99 username=bob password=someword
//			*/
//			// 一旦发送成功，用以下方法就可以得到服务器的回应：
//			String sCurrentLine;
//			String sTotalString;
//			StringBuffer bufferString=new StringBuffer();
//			InputStream  l_urlStream= connection.getInputStream();
//			BufferedReader buffer=new BufferedReader(new InputStreamReader(l_urlStream));
//			while ((sCurrentLine=buffer.readLine())!=null)
//			{
//			sTotalString=buffer.readLine();
//			bufferString.append(sTotalString);
//			}
//			l_urlStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
//    	InetAddress addr = InetAddress.getLocalHost();
//    	ip=addr.getHostAddress().toString;//获得本机IP
//    	address=addr.getHostName()toString;//获得本机名称 
        return new ModelAndView("pages/hello", "message", "Hello Spring to "+helloService.printHello()+" !");
    }
    
    
    /** 
  	* @Title: exportExcel 
  	* @Description: 导出用户数据生成的excel文件
  	* @param  model
  	* @param  request
  	* @param  response
  	* @param  设定文件 
  	* @return ModelAndView    返回类型 
  	* @throws 
  	*/
  	@RequestMapping(value="/exportExcel.shtml",method=RequestMethod.POST)  
    public ModelAndView exportExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response) {  
         View excelView = null;//new ExcelView();   
         //Map model = new HashMap();
         //model.put("users", dao.findUserAll());
         
        // Map<String, Object> obj = null;
         
         //获取数据库表生成的workbook
         //Map<String, Object> condition = new HashMap<String, Object>();
         //HSSFWorkbook workbook = activityManageService.generateWorkbook(condition);
         try {
      	   //excelView.buildExcelDocument(obj, workbook, request, response);
//        	 excelView= excelService.createExcelView(model,request, response);
         } catch (Exception e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
         }
         return new ModelAndView(excelView, model);   
     }  
  	

  	@RequestMapping(value="/getIP")
  	@RequiresAuthentication
    public void getIP(HttpServletRequest request, HttpServletResponse response) {  
  		String ip=null;
		try {
			
			URL url = new URL("http://ip.3322.org");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			System.out.println("=============================");
			System.out.println("Contents of get request");
			System.out.println("=============================");
			String lines;
			while ((lines = reader.readLine()) != null) {
				ip = new String(lines.getBytes(), "utf-8");
				System.out.println(lines);
			}
			reader.close();
			PrintWriter out = null;
			response.setContentType("application/json");
			//response.setContentType("text/html;charset=UTF-8");

			out = response.getWriter();
			out.write(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
     }  
  	
 	@RequestMapping(value="/getGuest")
 	@RequiresGuest
    public void getGuest(HttpServletRequest request, HttpServletResponse response) {  
  		String ip=null;
		try {
			
			URL url = new URL("http://ip.3322.org");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			System.out.println("=============================");
			System.out.println("Contents of get request for Guest");
			System.out.println("=============================");
			String lines;
			while ((lines = reader.readLine()) != null) {
				ip = new String(lines.getBytes(), "utf-8");
				System.out.println(lines);
			}
			reader.close();
			PrintWriter out = null;
			response.setContentType("application/json");
			//response.setContentType("text/html;charset=UTF-8");

			out = response.getWriter();
			out.write(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
     }  
 	
 	@RequestMapping(value="/getAuth")
  	@RequiresAuthentication
    public void getAuth(HttpServletRequest request, HttpServletResponse response) {  
  		String ip=null;
		try {
			
			URL url = new URL("http://ip.3322.org");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			System.out.println("=============================");
			System.out.println("Contents of get request for getAuth");
			System.out.println("=============================");
			String lines;
			while ((lines = reader.readLine()) != null) {
				ip = new String(lines.getBytes(), "utf-8");
				System.out.println(lines);
			}
			reader.close();
			PrintWriter out = null;
			response.setContentType("application/json");

			out = response.getWriter();
			out.write(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
     }  
}
