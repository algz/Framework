package com.ras.approval;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.approvalRange.ApprovalRange;
import com.ras.tool.CommonTool;

@Repository
public class ApprovalDaoImpl implements ApprovalDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public String saveApproval(Approval approval) {
		try {

			if (approval.getApprovalID() == null) {
				// 保存
				String hql = "select count(1) from Approval where approvalStatus='1' and dataID='"
						+ approval.getDataID() + "'";
				Long count = (Long) sf.getCurrentSession().createQuery(hql).uniqueResult();
				if (count.intValue() != 0) {
					return "数据审批中 ,不能重复提交!";
				}

				sf.getCurrentSession().save(approval);
				if (approval.getPermissionUserRange() != null && !approval.getPermissionUserRange().equals("")) {
					for (String userid : approval.getPermissionUserRange().split(",")) {
						ApprovalRange ar = new ApprovalRange();
						ar.setApprovalID(approval.getApprovalID());
						ar.setOverviewID(approval.getDataID());
						ar.setUserID(userid);
						if (approval.getApprovalStatus().equals("2") && approval.getApprovalResult().equals("1")) {
							ar.setValid("1");
						} else {
							ar.setValid("0");
						}
						sf.getCurrentSession().save(ar);
					}
				}
			} else {
				// 更新
				// sf.getCurrentSession().saveOrUpdate(approval);
				Approval data = (Approval) sf.getCurrentSession().get(Approval.class, approval.getApprovalID());
				data.setApprovalResult(approval.getApprovalResult());
				data.setApprovalDate(new Date());
				data.setApprovalStatus(approval.getApprovalStatus());
				sf.getCurrentSession().saveOrUpdate(data);
			}
			// 1审批同意
			if (approval.getDataTable() != null && approval.getApprovalResult() != null
					&& approval.getApprovalResult().equals("1")) {
				String sql = "update RAS_AIRCRAFT_" + approval.getDataTable() + " set PERMISSION_LEVEL='"
						+ approval.getPermissionLevel() + "' " + " where id='" + approval.getDataID() + "'";
				sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
			}
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
		return null;
	}

	@Override
	public void findApprovalGrid(ApprovalVo vo) {
		String sql="";
		switch(vo.getDataCategory()){
		case "BASIC":
			sql="select t.id approvalID,t.data_id dataID,'数据' dataCategory,ab.datasources dataName,u.username editor,t.permission_level permissionLevel,t.create_date createDate from RAS_APPROVAL t "
					+ "inner join ALGZ_USER u on u.id=t.submitter "
					+ "inner join RAS_AIRCRAFT_BASIC ab on ab.id=t.data_id";
			break;
		case "PICTURE":
			break;
		case "ARCHIVE":
			break;
		}
		
		sql+=" where t.id='"+vo.getApprovalID()+"'";
		List<Map> list=sf.getCurrentSession().createSQLQuery(sql)
						  .addScalar("approvalID", StandardBasicTypes.STRING)
						  .addScalar("dataID", StandardBasicTypes.STRING)
						  .addScalar("dataName")
						  .addScalar("dataCategory")
						  .addScalar("editor")
						  .addScalar("permissionLevel")
						  .addScalar("createDate")
						  .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						  .list();
		vo.setData(list);
		
		sql="select count(1) from RAS_APPROVAL t where t.id='"+vo.getApprovalID()+"'";
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
	}

	@Override
	public Approval findOne(Approval approval) {
		StringBuilder sql=new StringBuilder("select distinct * from RAS_APPROVAL where 1=1 ");
		return CommonTool.<Approval>findEntitiesByProperty(sf, sql, approval, null, null, null).get(0);
	}
	


}
