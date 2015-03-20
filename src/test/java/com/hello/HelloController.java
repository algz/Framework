package com.hello;

import java.io.*;
import java.net.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
//    @RequestMapping(value="/hello") //@RequestMapping 注解的方法才是真正处理请求的处理器
//    public ModelAndView  printHello() {
//		try {
//			
////			
////			/**
////			* 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 通过把URLConnection设为输出，可以把数据向你个Web页传送。下面是如何做：
////			*/
////			connection.setRequestProperty("accept", "text/html");
////			connection.setRequestProperty("connection", "Keep-Alive");
////			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; rv:2.0b11) Gecko/20100101 Firefox/4.0b11");
////			connection.setDoOutput(true);
////			connection.setDoInput(true);
////			/**
////			*为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
////			*/
////			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
////			String name = "060610106";
////			String password = getPassword();
////			out.write("IPT_LOGINUSERNAME=" + name + "&IPT_LOGINPASSWORD=" + password); // 向页面传递数据。post的关键所在！
////			// remember to clean up
////			out.flush();
////			out.close();
////			/**
////			* 这样就可以发送一个看起来象这样的POST： POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT: text/plain Content-type:
////			* application/x-www-form-urlencoded Content-length: 99 username=bob password=someword
////			*/
////			// 一旦发送成功，用以下方法就可以得到服务器的回应：
////			String sCurrentLine;
////			String sTotalString;
////			StringBuffer bufferString=new StringBuffer();
////			InputStream  l_urlStream= connection.getInputStream();
////			BufferedReader buffer=new BufferedReader(new InputStreamReader(l_urlStream));
////			while ((sCurrentLine=buffer.readLine())!=null)
////			{
////			sTotalString=buffer.readLine();
////			bufferString.append(sTotalString);
////			}
////			l_urlStream.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	
////    	InetAddress addr = InetAddress.getLocalHost();
////    	ip=addr.getHostAddress().toString;//获得本机IP
////    	address=addr.getHostName()toString;//获得本机名称 
//        return new ModelAndView("pages/hello", "message", "Hello Spring to "+helloService.printHello()+" !");
//    }
    
    
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
  	@RequiresAuthentication //用于表明当前用户需是经过认证的用户。 
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
 	//*************************************************************8
 	
 	/**
 	 * 1.1.对一个action配置多个URL映射
 	 * 
 	 * action配置了/index和/hello两个相同的映射.访问 uri: algz/test/hello和algz/test/index 调用同一个方法.
 	 * @return
 	 */
    @RequestMapping(value={"/hello","/index"}, method = {RequestMethod.GET}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  printHello() {
        return new ModelAndView("pages/hello", "message", "Hello Spring to "+helloService.printHello()+" !");
    }
 	
 	/**
 	 * 1.2 URL请求参数映射
 	 * 
 	 * 其中value="/detail/{id}",中的{id}为占位符表示可以映射请求为/detail/xxxx 的URL如：/detail/123等。
     * @param id
     * 		方法参数@PathVariable(value="id") Integer id 用于将URL中占位符所对应变量映射到参数id上，
     * 		@PathVariable(value="id") 中value的值要和占位符/{id}大括号中的值一致。
 	 * @return
 	 */
 	@RequestMapping(value="/detail/{id}", method = {RequestMethod.GET})
 	public ModelAndView getDetail(@PathVariable(value="id") Integer id){
 	    ModelAndView modelAndView = new ModelAndView();  
 	    modelAndView.addObject("id", id);  
 	    modelAndView.setViewName("test/detail");  
 	    return modelAndView;
 	}
 	
 	/**
 	 * 1.3.URL通配符映射
 	 * 
 	 * 我们还可以通过通配符对URL映射进行配置，通配符有“？”和“*”两个字符。其中“？”表示1个字符，“*”表示匹配多个字符，“**”表示匹配0个或多个路径。

例如：

“/helloworld/index?”可以匹配“/helloworld/indexA”、“/helloworld/indexB”，但不能匹配“/helloworld/index”也不能匹配“/helloworld/indexAA”；

“/helloworld/index*”可以匹配“/helloworld/index”、“/helloworld/indexA”、“/helloworld/indexAA”但不能匹配“/helloworld/index/A”；

“/helloworld/index/*”可以匹配“/helloworld/index/”、“/helloworld/index/A”、“/helloworld/index/AA”、“/helloworld/index/AB”但不能匹配    “/helloworld/index”、“/helloworld/index/A/B”;

“/helloworld/index/**”可以匹配“/helloworld/index/”下的多有子路径，比如：“/helloworld/index/A/B/C/D”;

如果现在有“/helloworld/index”和“/helloworld/*”，如果请求地址为“/helloworld/index”那么将如何匹配？Spring MVC会按照最长匹配优先原则（即和映射配置中哪个匹配的最多）来匹配，所以会匹配“/helloworld/index”
 	 * @return
 	 */
 	@RequestMapping(value="/*", method = {RequestMethod.GET})
 	public ModelAndView urlTest(){
 	    
 	    ModelAndView modelAndView = new ModelAndView();   
 	    modelAndView.setViewName("test/urltest");  
 	    return modelAndView;
 	}
 	
 	/**
 	 * 1.4.URL正则表达式映射
 	 * 
 	 * 
 	 * @param name
 	 * @param age
 	 * @return
 	 */
 	@RequestMapping(value="/reg/{name:\\w+}-{age:\\d+}", method = {RequestMethod.GET})
 	public ModelAndView regUrlTest(@PathVariable(value="name") String name, @PathVariable(value="age") Integer age){
 	    
 	    ModelAndView modelAndView = new ModelAndView();   
 	    modelAndView.addObject("name", name); 
 	    modelAndView.addObject("age", age); 
 	    modelAndView.setViewName("regurltest");  
 	    return modelAndView;
 	}
 	
 	 /** 
     * 测试返回JSON数据 
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/testJson")  
    @ResponseBody  
    public Object testJson(HttpSession session){  
          
        System.out.println("test....................");  
        return session.getAttribute("permit");  
    } 
}
