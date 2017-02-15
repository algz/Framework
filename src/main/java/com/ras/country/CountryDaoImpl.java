package com.ras.country;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Country> findAll() {
		String sql="select * from ras_country";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(Country.class).list();
	}
	
}
