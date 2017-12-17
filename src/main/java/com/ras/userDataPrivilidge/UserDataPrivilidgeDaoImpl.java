package com.ras.userDataPrivilidge;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataPrivilidgeDaoImpl implements UserDataPrivilidgeDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void saveUserDataPrivilidge(UserDataPrivilidge udp) {
		sf.getCurrentSession().saveOrUpdate(udp);
	}

	@Override
	public String delUserDataPrivilidge(UserDataPrivilidge udp) {
		String sql="delete RAS_USER_DATA_PRIVILIDGE udp "
				+ "where udp.dataid='"+udp.getDataID()+"' and udp.datatable='"+udp.getDataTable()+"'";
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		return null;
	}

}
