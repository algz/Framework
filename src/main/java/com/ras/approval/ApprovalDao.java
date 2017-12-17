package com.ras.approval;

import java.util.List;

public interface ApprovalDao {

	public String saveApproval(Approval approval);
	
	public void findApprovalGrid(ApprovalVo vo);
	
	public Approval findOne(Approval approval);
	
	public List<Object[]> getAllUserAndRole();
}
