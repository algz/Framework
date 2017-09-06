package com.ras.approval;

import javax.servlet.http.HttpServletResponse;

public interface ApprovalService {

	public String submitApproval(Approval approval)throws Exception;
	
	public void findApprovalGrid(ApprovalVo vo);
}
