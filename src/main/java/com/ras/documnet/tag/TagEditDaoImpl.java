/**
 * 
 */
package com.ras.documnet.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.search.SearchTag;

/**
 * @author algz
 *
 */
@Repository
public class TagEditDaoImpl implements TagEditDao {

	@Autowired
	private SessionFactory sf;
	
	/**
	 * 增加标签
	 */
	@Override
	public void addTag(SearchTag tag) {
		tag.setOnlyRead("0");
		sf.getCurrentSession().save(tag);
		
		//增加数据库字段
		String ename=(String)sf.getCurrentSession().createSQLQuery("select t.enname from RAS_SEARCH_TAG t where t.id='"+tag.getParent_id()+"'").uniqueResult();
		
		StringBuilder sql=new StringBuilder("ALTER TABLE RAS_AIRCRAFT_"+ename);
		sql.append(" ADD ( "+tag.getName());
		switch(tag.getUi_type()){
		case "text":
			//文本
			sql.append(" VARCHAR2("+(tag.getUi_value().equals("")?"32":tag.getUi_value())+") NULL  )");
			break;
		case "numberRegion":
			//数值
			sql.append(" NUMBER("+(tag.getUi_value().equals("")?"10":tag.getUi_value())+") NULL  )");
			break;
		}
		sf.getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
	}
	
	@Override
	public void modifyTag(SearchTag tag) {
		if(tag.getOnlyRead()!=null&&tag.getOnlyRead().equals("0")){
			if(tag.getParent_id()!=null){
				tag.setParentTag((SearchTag)sf.getCurrentSession().get(SearchTag.class, Long.parseLong(tag.getParent_id())));
			}
			sf.getCurrentSession().saveOrUpdate(tag);
		}
	}

	@Override
	public void delTag(String[] ids) {
		for(String id:ids){
			SearchTag tag=(SearchTag)sf.getCurrentSession().createSQLQuery("select * from RAS_SEARCH_TAG t where t.id='"+id+"'")
									   .addEntity(SearchTag.class)
									   .uniqueResult();
			
			sf.getCurrentSession().createQuery("delete SearchTag where id='"+id+"' and onlyRead!=1").executeUpdate();
			
			//删除数据库字段
			sf.getCurrentSession().createSQLQuery("alter table RAS_AIRCRAFT_"+tag.getParentTag().getEnname()+" drop column "+tag.getEnname()).executeUpdate();
		}
		
		
	}

	


	
}
