package com.ras.relationTable.sysware.data;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.approval.Approval;
import com.ras.approval.ApprovalService;
import com.ras.approvalRange.ApprovalRange;
import com.ras.approvalRange.ApprovalRangeDao;

@Service
public class SyswareRelationServiceImpl implements SyswareRelationService {
	
	@Autowired
	private SyswareRelationDao dao;
	
	@Override
	public Map getDataApproval(SyswareRelationVo vo) {
		return dao.getDataApproval(vo);
	}

	@Transactional
	@Override
	public void updateApproval(Approval approval) {
		dao.updateApproval(approval);
	}

	@Override
	public void getDataCheckIndex(SyswareRelationVo vo) {
		dao.getDataCheckIndex(vo);
	}

}
