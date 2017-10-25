package com.ras.approval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.documnet.data.DataVo;
import com.ras.index.Page;
import com.ras.tool.CommonTool;
import com.ras.ws.client.CXFClientUtil;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserService;
import algz.platform.util.xml.StAXUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/ras/approval")
public class ApprovalController {

	@Autowired
	private ApprovalService service;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping({"","/"})
	public ModelAndView DocumentIndex(){
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("menus", menuService.findAll());
    	
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("流程审批");
    	map.put("page", page);
		return new ModelAndView("/ras/approval/approval",map);
	}
	
	@RequestMapping(value={"/approvalpage"})
	public ModelAndView approvalPage(HttpServletRequest request,HttpServletResponse response){
//		if(approval.getDataID()!=null&&!approval.getDataID().equals("")){ 
//			CommonTool.writeJSONToPage(response,service.submitApproval(approval) );
//		}
		Map map=null;
		return new ModelAndView("/ras/approval/approval",map);
	}
	
    @RequestMapping(value={"/findapprovalgrid"})
    public void findApprovalGrid(ApprovalVo vo,HttpServletResponse response){
    	service.findApprovalGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
    }
	
    /**
     * 提交送审
     * @param approval
     * @param request
     * @param response
     */
	@RequestMapping(value={"/submitapproval"})
	public void submitApproval(Approval approval,HttpServletRequest request,HttpServletResponse response){
		if(approval.getDataID()!=null&&!approval.getDataID().equals("")
				&&approval.getPermissionLevel()!=null&&!approval.getPermissionLevel().equals("")){ 
			try {
				
				CommonTool.writeJSONToPage(response,service.submitApproval(approval) );
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}else{
			CommonTool.writeJSONToPage(response,"提交失败!");
		}
	}
	
    /**
     * 提交送审
     * @param approval
     * @param request
     * @param response
     */
	@RequestMapping(value={"/getuserall"})
	public void getUserAll(HttpServletRequest request,HttpServletResponse response){
		List<User> userList=userService.findAll(null,0,null);
		JSONArray ja=new JSONArray();
		for(User u:userList){
			if(u.getCname()!=null){
				JSONObject jo=new JSONObject();
				jo.put("userid", u.getUserid());
				jo.put("cname", u.getCname());
				ja.add(jo);
			}
		}
		if(userList.size()!=0){ 
			try {
				CommonTool.writeJSONToPage(response,ja);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	
}
