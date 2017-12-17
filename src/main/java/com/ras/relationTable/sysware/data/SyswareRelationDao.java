package com.ras.relationTable.sysware.data;

import java.util.Map;

import com.ras.approval.Approval;

public interface SyswareRelationDao {
	
	public void getDataCheckIndex(SyswareRelationVo vo);
	
	public Map getDataApproval(SyswareRelationVo vo);
	
	public void updateApproval(Approval approval) ;
}
