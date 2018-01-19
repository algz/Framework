package com.ras.authority.resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.roleManager.Role;
import algz.platform.core.shiro.authority.userManager.User;

@Repository
public class RASResourceDaoImpl implements RASResourceDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Resource> findResourceByRoleid(RASResourceVo vo) {
//		StringBuilder sql=new StringBuilder("from ALGZ_RESOURCE res inner join ALGZ_ROLE_RESOURCE rres on res.id=rres.resourceid where 1=1 ");
//		if(vo.getRoleid()!=null&&!vo.getRoleid().equals("")){
//			sql.append(" and rres.roleid='"+vo.getRoleid()+"' ");
//		}else{
		StringBuilder sql=new StringBuilder("from ALGZ_RESOURCE res where 1=1 ");
//		}
		
		if(vo.getIsPublic()!=null&&!vo.getIsPublic().equals("")&&vo.getIsPublic().equals("0")){
			sql.append(" and res.isPublic='0' ");
		}
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql.toString()).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		
		List list=sf.getCurrentSession().createSQLQuery("select res.* "+sql.toString()+" order by parent_id,sequence,ispublic ")
				.addEntity(Resource.class)
			    .setFirstResult(vo.getStart())
			    .setMaxResults(vo.getLength())
			    .list();

		return list;
	}
	
	@Override
	public List<String> findResourceByRoleID(String roleid) {
		String sql="select t.resourceid from ALGZ_ROLE_RESOURCE t where t.roleid=:roleid";
		return sf.getCurrentSession().createSQLQuery(sql).setParameter("roleid", roleid).list();
	}

	@Override
	public void saveRoleResource(String roleid, String resourceid, String operate) {
		String sql="";
		switch(operate){
		case "add":
			sql="insert into ALGZ_ROLE_RESOURCE VALUES ('"+CommonTool.getGUID(sf.getCurrentSession())+"','"+roleid+"','"+resourceid+"')";
			break;
		case "del":
			sql="delete from ALGZ_ROLE_RESOURCE where roleid='"+roleid+"' and resourceid='"+resourceid+"' ";
			break;
		}
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		
	}

}
