package com.ras.authority.role;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;

@Repository
public class RASRoleDaoImpl implements RASRoleDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void findAuthrityGrid(RASRoleVo<?> vo) {
		String hql="from User ";
		Long count=(Long)sf.getCurrentSession().createQuery("select count(1) "+hql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		List list=sf.getCurrentSession().createQuery(hql)
//					.addEntity(User.class)
				    .setFirstResult(vo.getStart())
				    .setMaxResults(vo.getLength())
				    .list();
		
		vo.setData(list);
	}

	public List<String> findRoleByUserID(String userid) {
		String sql="select t.roleid from ALGZ_USER_ROLE t where t.userid=:userid";
		return sf.getCurrentSession().createSQLQuery(sql).setParameter("userid", userid).list();
	}


	
}
