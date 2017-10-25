package com.ras.aircraftTag;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftPicture.AircraftPicture;
import com.ras.tool.CommonTool;

import algz.platform.util.Common;

@Repository
public class AircraftTagDaoImpl implements AircraftTagDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public Long count(AircraftTag tag) {
		StringBuilder hql=new StringBuilder(" from AircraftTag where 1=1 ");
		if(tag.getTagName()!=null){
			hql.append(" and tagName='"+tag.getTagName()+"'");
		}
		if(tag.getRelationTable()!=null){
			hql.append(" and relationTable='"+tag.getRelationTable()+"'");
		}
		return (Long)sf.getCurrentSession().createQuery("select count(1)"+hql.toString()).uniqueResult();
	}
	
	@Override
	public List<AircraftTag> find(AircraftTag tag,Integer start,Integer limit) {
		StringBuilder hql=new StringBuilder("from AircraftTag where 1=1 ");
		if(tag.getTagName()!=null){
			hql.append(" and tagName='"+tag.getTagName()+"'");
		}
		if(tag.getRelationTable()!=null){
			hql.append(" and relationTable='"+tag.getRelationTable()+"'");
		}
		return sf.getCurrentSession().createQuery(hql.toString())
				 .setFirstResult(start)
				 .setMaxResults(limit).list();
	}

	@Override
	public void saveOrUpdate(AircraftTag tag) {
		//删除所有关联的指定标签后在,在重新关联.
		this.del(tag.getTagName(),tag.getRelationID(),tag.getRelationTable());
//		tag.setEditor(Common.getLoginUser().getUserid());
		if (tag.getTagID()==null||tag.getTagID().equals("")) {
			sf.getCurrentSession().save(tag);
		} else {
			sf.getCurrentSession().update(tag);
		}
	}

	
	@Override
	/**
	 * @param tagName 标签名称,可为空(null).
	 */
	public void del(String tagName,String relationID,String relationTable) {
		String sql="delete RAS_AIRCRAFT_TAG TAG where 1=1 ";
		if(tagName!=null&&!tagName.equals("")){
			sql+=" and TAG.TAG_NAME='"+tagName+"'";
		}
		sql+=" AND TAG.RELATION_TABLE='"+relationTable+"' AND TAG.RELATION_ID='"+relationID+"'";
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void delRelationID(String relationID) {
		String hql="from AircraftTag as t where t.relationID='"+relationID+"'";
		AircraftTag archive=(AircraftTag)sf.getCurrentSession().createQuery(hql).uniqueResult();
		if(archive!=null){
			sf.getCurrentSession().delete(archive);
		}
		
	}


	
	


	
}
