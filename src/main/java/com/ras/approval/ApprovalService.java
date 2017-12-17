package com.ras.approval;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ApprovalService {

	public String submitApproval(Approval approval);
	
	public String submitToP2M(Approval approval)throws Exception;
	
	public void findApprovalGrid(ApprovalVo vo);
	
	public Approval findOne(Approval approval);
	
	public JSONObject getAllUser();
	
	public JSONObject getUserRole();
}
