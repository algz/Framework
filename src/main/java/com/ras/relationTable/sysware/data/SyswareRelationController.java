package com.ras.relationTable.sysware.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.approval.Approval;
import com.ras.approval.ApprovalService;
import com.ras.documnet.dataManager.DataService;
import com.ras.documnet.dataManager.DataVo;
import com.ras.index.Page;
import com.ras.tool.CommonTool;
import com.ras.ws.client.CXFClientUtil;

import algz.platform.util.xml.StAXUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/ras/relation")
public class SyswareRelationController {

	@Autowired
	private SyswareRelationService service;
	
	@Autowired
	private DataService dataService;
	
	
	@RequestMapping({"/sysware/datacheck"})
	public ModelAndView dataCheckIndex(HttpServletRequest request, HttpServletResponse response){
    	Map<String, Object> map=new HashMap<String, Object>();
    	
//    	map.put("tab", request.getParameter("tab"));
    	
		SyswareRelationVo sysvo=new SyswareRelationVo();
		sysvo.setApprovalID(request.getParameter("approvalID"));
		service.getDataCheckIndex(sysvo);
		
    	DataVo<?> vo=new DataVo();
    	vo.setOverviewID(sysvo.getOverviewID());
////    	vo.setBasicID(request.getParameter("basicID"));
    	vo.setOption("modify");
//    	JSONObject jo=dataService.addModelParamPage(vo);
//    	if(jo!=null){
//    		map.putAll(jo);
//    	}
    	//开启UI参数控制
    	map.put("isModify", "true");
    	
    	//加载参数
    	map.putAll(dataService.addModelParamPage(vo));//paramMap
    	map.put("basicID", vo.getBasicID());
    	map.put("overviewID", sysvo.getOverviewID());
    	
		return new ModelAndView("/ras/relation/sysware/datacheck",map);
	}
	
	@RequestMapping({"/sysware/dataapproval"})
	public ModelAndView dataApproval(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map=new HashMap<String, Object>();

		SyswareRelationVo sysvo=new SyswareRelationVo();
		sysvo.setApprovalID(request.getParameter("approvalID"));
    	map.putAll(service.getDataApproval(sysvo));
    	
    	DataVo<?> vo=new DataVo();
    	vo.setOverviewID(sysvo.getOverviewID());
    	vo.setOption("load");
    	//加载参数
    	map.putAll(dataService.addModelParamPage(vo));//paramMap
    	map.put("basicID", vo.getBasicID());
    	
		return new ModelAndView("/ras/relation/sysware/dataapproval",map);
	}
	

	
//    @RequestMapping(value={"/findapprovalgrid"})
//    public void findApprovalGrid(SyswareRelationVo vo,HttpServletResponse response){
//    	//service.findApprovalGrid(vo);
//		CommonTool.writeJSONToPage(response, vo);
//    }
	
    /**
     * 提交送审
     * @param approval
     * @param request
     * @param response
     */
	@RequestMapping(value={"/sysware/updateapproval"})
	public void updateApproval(SyswareRelationVo vo,HttpServletRequest request,HttpServletResponse response){
//		if(approval.getDataID()!=null&&!approval.getDataID().equals("")){ 
			try {
				Approval approval=new Approval();
				approval.setApprovalID(vo.getApprovalID());
				approval.setPermissionLevel(vo.getPermissionLevel());
				service.updateApproval(approval);
				CommonTool.writeJSONToPage(response,"{\"success\":true}");
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
//		}
	}
	
    
}
