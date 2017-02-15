package com.ras.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchTagDaoImpl implements SearchTagDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<SearchTag> findAllParent() {
		String sql="select * from ras_search_tag tag where tag.parent_id=0 order by tag.id";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchTag.class).list();
	}

	@Override
	public List<SearchTag> findAllByIds(String[] ids) {
		String sql="select * from ras_search_tag tag where tag.id in(:ids) order by tag.id";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchTag.class).setParameterList("ids", ids).list();
	}

	public List<?> findAttribute(String tableName){
		String sql="select * from "+tableName;
		return sf.getCurrentSession().createSQLQuery(sql).list();
	}
	
	@Override
	public void save(SearchTag searchTag){
		sf.getCurrentSession().save(searchTag);
	}
}
