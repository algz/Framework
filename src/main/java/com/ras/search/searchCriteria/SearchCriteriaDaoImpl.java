package com.ras.search.searchCriteria;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchCriteriaDaoImpl implements SearchCriteriaDao {

	@Autowired
	private SessionFactory sf;
	
	public List<String[]> SearchCriteriaGird(){
		String sql="select abl.model,abl.cn_name from ras_aircraft_basic_library abl";
		return sf.getCurrentSession().createSQLQuery(sql).list();
	}
	
}
