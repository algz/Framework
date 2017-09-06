package com.ras.approval;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.documnet.data.DataVo;
import com.ras.tool.CommonTool;
import com.ras.ws.client.CXFClientUtil;

import algz.platform.util.xml.StAXUtil;

@Controller
@RequestMapping(value="/ras/approval")
public class ApprovalController {

	@Autowired
	private ApprovalService service;
	
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
	
	@RequestMapping(value={"/submitapproval"})
	public void submitApproval(Approval approval,HttpServletRequest request,HttpServletResponse response){
		if(approval.getDataID()!=null&&!approval.getDataID().equals("")){ 
			try {
				CommonTool.writeJSONToPage(response,service.submitApproval(approval) );
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
}
