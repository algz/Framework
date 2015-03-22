package algz.platform.core.shiro.authority.userManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import algz.platform.util.db.DataTablesResult;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
	
	@RequestMapping("/")
	public String index(){
		List<User> userList=userService.findAll();
		return "platform/core/shiro/authority/userManager/userManager";
	}
	
	/**
	 * @ResponseBody :把对象转换成json返回给用户.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findUsers")
	public @ResponseBody DataTablesResult findUsers(HttpServletRequest request, HttpServletResponse response){
		User u=new User();
		u.setUsername("algz1234567890");
		u.setPassword("password12345");
		DataTablesResult<User> result=new DataTablesResult<>();
		List dataList=new ArrayList();
		dataList.add(u);
		result.setData(dataList);
		return result;
//		QueryResult<DocSystemCodeVo> voRst = new QueryResult<DocSystemCodeVo>();
//		List<DocSystemCodeVo> voList = docSystemCodeService.getDocSystemCodeGrid(vo);
//		voRst.setRecordtotal(vo.getCount());
//		voRst.setResultSet(voList);
		
//		Map m=new HashMap();
//		m.put("username", "algz111");
//		m.put("truename", "a11");
//		List list=new ArrayList();
//		list.add(u);
//		JSONArray ja=JSONArray.fromObject(list);
//		Map result=new HashMap();
//		result.put("data", ja);
//		JSONObject jObject = JSONObject.fromObject(result);
		
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/JSON");
//		try {
//			response.getWriter().print(result);//("{\"data\":[{\"username\":\"algz\",\"truename\":\"a\"}]}");//(jObject);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "platform/core/shiro/authority/userManager/userManager";
	}
	
	/**
	 * ajax 提交,返回json
	 * @param user
	 * @param printWriter
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void saveUser(@ModelAttribute User user,PrintWriter printWriter){
//		JSONObject J;
//		System.out.println(person); 
        String jsonString = "{message:'ok'}";//JSON.toJSONString(user, SerializerFeature.PrettyFormat); 
        printWriter.write(jsonString); 
        printWriter.flush(); 
        printWriter.close(); 
	}
}
