package com.ras.approvalRange;

import java.util.List;

import com.ras.aircraftBasic.AircraftBasic;

public interface ApprovalRangeDao {

	public List<ApprovalRange> findAll();
	
	public List<ApprovalRange> findByProperty(ApprovalRange ao,Integer start,Integer length);
	
	public Integer count(ApprovalRange ao);
	
	public void saveOrUpdate(ApprovalRange ao);
	
}
