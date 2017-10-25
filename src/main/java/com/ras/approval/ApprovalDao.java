package com.ras.approval;

public interface ApprovalDao {

	public String saveApproval(Approval approval);
	
	public void findApprovalGrid(ApprovalVo vo);
	
	public Approval findOne(Approval approval);
}
