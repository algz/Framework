package com.ras.authority.user;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;

@Repository
public class RASUserDaoImpl implements RASUserDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void findAuthrityGrid(RASUserVo<?> vo) {
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

	@Override
	public void saveUserRole(String userid,String roleid,String operate){
		String sql="";
		switch(operate){
		case "add":
			sql="insert into ALGZ_USER_ROLE VALUES ('"+CommonTool.getGUID(sf.getCurrentSession())+"','"+userid+"','"+roleid+"')";
			break;
		case "del":
			sql="delete from ALGZ_USER_ROLE where userid='"+userid+"' and roleid='"+roleid+"' ";
			break;
		}
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
}
