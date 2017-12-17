package com.ras.ws.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.approval.Approval;
import com.ras.approval.ApprovalDao;
import com.ras.approvalRange.ApprovalRange;
import com.ras.approvalRange.ApprovalRangeDao;
import com.ras.userDataPrivilidge.UserDataPrivilidge;
import com.ras.userDataPrivilidge.UserDataPrivilidgeService;

//@WebService(endpointInterface="com.ras.ws.service.RASWebservice",serviceName="RASWebservice")
@Service
public class RASWebserviceImpl implements RASWebservice {

	@Autowired
	private ApprovalDao approvalDao;

	@Autowired
	private UserDataPrivilidgeService userDataPrivilidgeService;
	
	@Autowired
	private ApprovalRangeDao approvalRangeDao;
	
	/**
	 * 结束审批流
	 * @param paramToken_ras参数：飞机系统送审时传给P2M的值，由P2M系统回传给飞机系统。
	 * @param username参数：飞机系统送审时传给P2M的值，作为整个流程的发起人，由P2M系统回传给飞机系统。值为sys_user表的login_Name字段。
	 * @param approvalresulte参数：值由P2M提供，1接收成功或0发起审签失败。
	 * @param returnreason参数：值由P2M提供，失败的原因，成功发起则为空。
	 * @return 1接收成功或0接收失败。
	 */
	@Transactional
	@Override
	public String endApproval(String paramToken_ras, String username, String approvalresulte, String returnreason) {
		Approval approval=new Approval();
		approval.setApprovalID(paramToken_ras);
		approval.setApprovalStatus("2"); //2审批完成
		approval.setApprovalResult(approvalresulte);
		approvalDao.saveApproval(approval);
		
		if(approvalresulte.equals("1")){
			//审批通过,同意.
			
			//删除范围权限
			UserDataPrivilidge udp=new UserDataPrivilidge();
//			udp.setUserID(approval.getApprovalEditor());
			udp.setDataID(approval.getDataID());
			udp.setDataTable(approval.getDataTable());
			userDataPrivilidgeService.delUserDataPrivilidge(udp);
			
			if(approval.getPermissionLevel().equals("4")){
				//添加范围权限
				ApprovalRange ar=new ApprovalRange();
				ar.setApprovalID(approval.getApprovalID());
				List<ApprovalRange> arList=approvalRangeDao.findByProperty(ar, null, null);
				for(ApprovalRange tem:arList){
					udp=new UserDataPrivilidge();
					udp.setUserID(tem.getUserID());
					udp.setDataID(approval.getDataID());
					udp.setDataTable(approval.getDataTable());
					userDataPrivilidgeService.saveUserDataPrivilidge(udp);
				}

			}
		}

		
		return "1";
	}
	
}
