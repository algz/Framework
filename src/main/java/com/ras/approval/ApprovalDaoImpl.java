package com.ras.approval;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApprovalDaoImpl implements ApprovalDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public String saveApproval(Approval approval) {
		if(approval.getApprovalID()==null){
			String hql="select count(1) from Approval where approvalStatus='1' and dataID='"+approval.getDataID()+"'";
			Long count=(Long)sf.getCurrentSession().createQuery(hql).uniqueResult();
			if(count.intValue()!=0){
				return "数据审批中 ,不能重复提交!";
			}
			sf.getCurrentSession().save(approval);
		}else{
//			sf.getCurrentSession().saveOrUpdate(approval);
			Approval data=(Approval)sf.getCurrentSession().get(Approval.class, approval.getApprovalID());
			data.setApprovalResult(approval.getApprovalResult());
			data.setApprovalDate(new Date());
			data.setApprovalStatus(approval.getApprovalStatus());
			sf.getCurrentSession().saveOrUpdate(data);
			
			//1审批同意
			if(data.getDataTable()!=null&&data.getApprovalResult().equals("1")){
				String sql="update RAS_AIRCRAFT_"+data.getDataTable()+" set PERMISSION_LEVEL='"+data.getPermissionLevel()+"' "
						+ " where id='"+data.getDataID()+"'";
				sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
			}
			
		}
		return null;
	}

}
