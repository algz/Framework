//package com.ras.aircraftTag;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Random;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.ras.aircraftPicture.AircraftPicture;
//import com.ras.tool.CommonTool;
//
//@Repository
//public class AircraftTagDaoImpl implements AircraftTagDao {
//
//	@Autowired
//	private SessionFactory sf;
//
//	@Override
//	public Long count(AircraftTag tag) {
//		StringBuilder hql=new StringBuilder(" from AircraftTag where 1=1 ");
//		if(tag.getTagName()!=null){
//			hql.append(" and tagName='"+tag.getTagName()+"'");
//		}
//		if(tag.getRelationTable()!=null){
//			hql.append(" and relationTable='"+tag.getRelationTable()+"'");
//		}
//		return (Long)sf.getCurrentSession().createQuery("select count(1)"+hql.toString()).uniqueResult();
//	}
//	
//	@Override
//	public List<AircraftTag> find(AircraftTag tag,Integer start,Integer limit) {
//		StringBuilder hql=new StringBuilder("from AircraftTag where 1=1 ");
//		if(tag.getTagName()!=null){
//			hql.append(" and tagName='"+tag.getTagName()+"'");
//		}
//		if(tag.getRelationTable()!=null){
//			hql.append(" and relationTable='"+tag.getRelationTable()+"'");
//		}
//		return sf.getCurrentSession().createQuery(hql.toString())
//				 .setFirstResult(start)
//				 .setMaxResults(limit).list();
//	}
//
//	@Override
//	public void saveOrUpdate(AircraftTag tag) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void del(String tagID) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delRelationID(String relationID) {
//		String hql="from AircraftTag as t where t.relationID='"+relationID+"'";
//		AircraftTag archive=(AircraftTag)sf.getCurrentSession().createQuery(hql).uniqueResult();
//		if(archive!=null){
//			sf.getCurrentSession().delete(archive);
//		}
//		
//	}
//
//
//	
//	
//
//
//	
//}
