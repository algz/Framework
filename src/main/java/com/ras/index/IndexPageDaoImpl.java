package com.ras.index;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IndexPageDaoImpl implements IndexPageDao {

	@Autowired
	private SessionFactory sf;
	
	public List<?> searchIndexPage(String modelname) {
		String sql="select * from searchindexpage s where lower(s.modelname) like '%"+modelname.toLowerCase()+"%'";
		return sf.getCurrentSession().createSQLQuery(sql)
				 .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
}
