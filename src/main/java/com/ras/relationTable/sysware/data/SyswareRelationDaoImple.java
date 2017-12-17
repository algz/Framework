package com.ras.relationTable.sysware.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.approval.Approval;
import com.ras.approval.ApprovalService;
import com.ras.approvalRange.ApprovalRange;
import com.ras.tool.CommonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class SyswareRelationDaoImple implements SyswareRelationDao {

	@Autowired
	private SessionFactory sf;

	@Autowired
	private ApprovalService approvalService;
	
	
	@Override
	public Map getDataApproval(SyswareRelationVo vo) {
		Map map=new HashMap();
		Approval approval=new Approval();
		approval.setApprovalID(vo.getApprovalID());
		approval=approvalService.findOne(approval);
		vo.setOverviewID(approval.getDataID());
		map.put("approvalID", approval.getApprovalID());
		map.put("dataID", approval.getDataID());
		map.put("permission_level", approval.getPermissionLevel());
		
		ApprovalRange example=new ApprovalRange();
		example.setApprovalID(vo.getApprovalID());
		String sql="select u.id,u.CNAME from RAS_APPROVAL_RANGE t "
				+ "inner join ALGZ_USER u on t.userid=u.id "
				+ "where t.approvalid='"+vo.getApprovalID()+"'";
		List<Object[]> objList=sf.getCurrentSession().createSQLQuery(sql).list();
		JSONArray ja=new JSONArray();
		String username="";
		for(Object[] objs:objList){
			if(username!=""){
				username+=",";
			}
			username+=objs[1];
//			JSONObject jo=new JSONObject();
//			jo.put("userid", objs[0]);
//			jo.put("username", objs[1]);
//			ja.add(jo);
		}
		map.put("permissionUserRange", username);
		return map;
	}


	@Override
	public void updateApproval(Approval approval) {
		if(approval.getApprovalID()!=null){
			String sql="update RAS_APPROVAL set  PERMISSION_LEVEL='"+approval.getPermissionLevel()+"' where ID='"+approval.getApprovalID()+"'";
			sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
	}


	@Override
	public void getDataCheckIndex(SyswareRelationVo vo) {
		if(vo.getApprovalID()!=null){
			String sql="select distinct t.data_id from RAS_APPROVAL t where t.id='"+vo.getApprovalID()+"'";
			String overviewID=(String)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
			vo.setOverviewID(overviewID);
		}
	}

}
