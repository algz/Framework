package com.ras.searchTag;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import com.ras.aircraftArchive.AircraftArchive;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftPicture.AircraftPicture;
import com.ras.aircraftPicture.AircraftPictureDao;
import com.ras.tool.CommonTool;
import com.sun.org.apache.bcel.internal.generic.Type;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

@Repository
public class SearchTagDaoImpl implements SearchTagDao {
	
//	@Autowired
//	private AircraftTagDaoImpl aircraftTagDao;
	
	@Autowired
	private AircraftPictureDao aircraftPictureDao;
	
	@Autowired
	private SessionFactory sf;
	
	public void searchIndexPage(SearchTagVo vo) {
		String sql=" from ras_aircraft_overview ao "
				+ " left join ras_aircraft_basic ab on ab.overviewid=ao.id and ab.maininfo='1' "
				+ " where lower(modelname) like '%"+vo.getModelName().toLowerCase()+"%'";
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		List list= sf.getCurrentSession().createSQLQuery("select distinct ao.id overviewid,ao.modelname,ab.aircrafttype,ab.producercountries,ab.manufacturer,ao.photourl "+sql)
				 .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				 .setFirstResult(vo.getStart())
				 .setMaxResults(vo.getLength())
				 .list();
		vo.setData(list);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void findTagSearchForParamGird(SearchTagVo vo) {
		String sql="from ras_aircraft_overview ao "
				+ "inner join ras_aircraft_basic ab on ab.overviewid=ao.id "
				+ "where Lower(ab.tag) like '%"+vo.getTag().toLowerCase()+"%' "
				+ " or Lower(ao.modelName) like '%"+vo.getTag().toLowerCase()+"%' ";
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			sql+=" and (ab.editor='"+curUser.getUserid()+"' or ab.PERMISSION_LEVEL in ('2','3'))";
		}
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		if(count.intValue()!=0){
			List list=sf.getCurrentSession().createSQLQuery("select ao.id overviewID,ao.modelName,ao.modelCname,ao.modelEname,ab.dataSources,ab.tag,ab.id basicID  "+sql)
//					  .addEntity(AircraftOverview.class)
					  .addScalar("overviewID", StandardBasicTypes.STRING)
					  .addScalar("modelName")
					  .addScalar("modelCname")
					  .addScalar("modelEname")
					  .addScalar("tag")
					  .addScalar("dataSources")
					  .addScalar("basicID")
					  .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					  .setFirstResult(vo.getStart())
					  .setMaxResults(vo.getLength())
					  .list();
			vo.setData(list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findTagSearchForArchiveGird(SearchTagVo vo) {
		String sql="from RAS_AIRCRAFT_ARCHIVE aa inner join ras_aircraft_overview ao on aa.overviewid=ao.id "
				+ "where Lower(aa.tag) like '%"+vo.getTag().toLowerCase()+"%'";
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			sql+=" and (aa.editor='"+curUser.getUserid()+"' or aa.PERMISSION_LEVEL in ('2','3'))";
		}
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		if(count.intValue()!=0){
			List list=sf.getCurrentSession().createSQLQuery("select ao.modelName,aa.*  "+sql)
					  .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					  .setFirstResult(vo.getStart())
					  .setMaxResults(vo.getLength())
					  .list();
			vo.setData(list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findTagSearchForPictureGird(SearchTagVo vo) {
		String sql=" from RAS_AIRCRAFT_PICTURE pic inner join ras_aircraft_overview ao on pic.overviewid=ao.id "
				+ "where Lower(pic.tag) like '%"+vo.getTag().toLowerCase()+"%'";
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			sql+=" and (pic.editor='"+curUser.getUserid()+"' or pic.PERMISSION_LEVEL in ('2','3'))";
		}
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		if(count.intValue()!=0){
			List list=sf.getCurrentSession().createSQLQuery("select ao.modelName,pic.*  "+sql)
					  .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					  .setFirstResult(vo.getStart())
					  .setMaxResults(vo.getLength())
					  .list();
			vo.setData(list);
		}
	}
}
