package com.ras.authority;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import algz.platform.core.shiro.authority.userManager.User;

@Repository
public class AuthorityDaoImpl implements AuthorityDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void findAuthrityGrid(AuthorityVo<?> vo) {
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

}
