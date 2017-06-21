package com.ras.approval;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ras.tool.CommonTool;
import com.ras.ws.client.CXFClientUtil;

import algz.platform.util.xml.StAXUtil;

@Controller
@RequestMapping(value="/ras/approval")
public class ApprovalController {

	@Autowired
	private ApprovalService service;
	
	@RequestMapping(value={"/submitapproval"})
	public void submitApproval(Approval approval,HttpServletRequest request,HttpServletResponse response){
		if(approval.getDataID()!=null&&!approval.getDataID().equals("")){ 
			CommonTool.writeJSONToPage(response,service.submitApproval(approval) );
		}
	}
}
