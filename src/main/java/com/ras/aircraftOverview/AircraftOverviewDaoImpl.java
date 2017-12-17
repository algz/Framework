/**
 * 
 */
package com.ras.aircraftOverview;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftCapability.AircraftCapability;
import com.ras.aircraftTag.AircraftTag;
import com.ras.aircraftTag.AircraftTagDao;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;
import javafx.util.Callback;

/**
 * @author algz
 *
 */
@Repository
public class AircraftOverviewDaoImpl implements AircraftOverviewDao {

	@Autowired
	private AircraftTagDao aircraftTagDao;
	
	@Autowired
	private SessionFactory sf;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ras.aircraftBasic.AircraftBasicDao#findAll()
	 */
	@Override
	public List<AircraftOverview> findAll() {
		String sql = "select * from ras_aircraft_overview ao "
				+ " inner join ras_aircraft_basic ab on ao.id=ab.OVERVIEWID where 1=1 ";
		
		sql+=CommonTool.getAuthoritySQL(Common.getLoginUser().getUserid());
//		//权限控制
//		if(!CommonTool.isDataManager()){
//			User curUser=Common.getLoginUser();
//			sql+=" and (ao.editor='"+curUser.getUserid()+"' and ao.PERMISSION_LEVEL='1') "
//					+ "or ao.PERMISSION_LEVEL in ('2','3') "
//					+ "or (ao.PERMISSION_LEVEL='4' and ao.editor in (select udp.userid from RAS_USER_DATA_PRIVILIDGE udp where udp.overviewid=ao.id))";
//		}
		
		return sf.getCurrentSession().createSQLQuery(sql+" order by ao.modelname").addEntity(AircraftOverview.class).list();
	}

	@Override
	public Integer count(AircraftOverview ao) {
		StringBuilder str=new StringBuilder("select count(distinct ao.overviewID) from AircraftOverview ao, "
				+ " AircraftBasic ab  where ab.overviewID=ao.overviewID ");
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			str.append(" and (ab.editor='"+curUser.getUserid()+"' or ab.permissionLevel in ('2','3'))");
		}
		
		
		Field[] fs = ao.getClass().getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true); 
			Object obj=null;
			try {
				obj = f.get(ao);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(obj!=null){
				str.append(" and LOWER(ao."+f.getName()+") like '%"+obj.toString().toLowerCase()+"%'");
			}
			
		}
		return Integer.parseInt(sf.getCurrentSession().createQuery(str.toString()).uniqueResult() + "");
	}

	@Override
	@Transactional
	public void save(AircraftOverview ao) {
		sf.getCurrentSession().save(ao);
	}

	@Override
	public void saveOrUpdate(AircraftOverview ao) {
		ao.setEditor(Common.getLoginUser().getUserid());
		if (ao.getOverviewID()==null||ao.getOverviewID().equals("")) {
			sf.getCurrentSession().save(ao);
			
		} else {
			sf.getCurrentSession().update(ao);
		}
	}

	@Override
	public List<AircraftOverview> findByProperty(AircraftOverview ao,Integer start,Integer length) {
		StringBuilder str=new StringBuilder("select distinct ao from AircraftOverview ao "
				+ " inner join ao.aircraftBasicSet ab where 1=1 ");
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			str.append(" and (ao.editor='"+curUser.getUserid()+"' or ao.permissionLevel in ('2','3'))");
		}
		
//		CommonTool.findEntitiesByProperty(sf, sql, example, start, length, callback)
		Field[] fs = ao.getClass().getDeclaredFields();
		try {
			for (Field f : fs) {
				f.setAccessible(true); 
				Object obj = f.get(ao);
				if(obj!=null){
					str.append(" and LOWER(ao."+f.getName()+") like '%"+obj.toString().toLowerCase()+"%'");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Query query=sf.getCurrentSession().createQuery(str.toString());
		if(start!=null&&length!=null){
			query.setFirstResult(start).setMaxResults(length);
		}
		return query.list();
	}

	@Override
	public AircraftOverview copy(AircraftOverview example) {
		StringBuilder sql=new StringBuilder("select distinct ao from RAS_AIRCRAFT_CAPABILITY ao where 1=1 ");
		List<AircraftOverview> list=CommonTool.<AircraftOverview>findEntitiesByProperty(sf, sql, example, 0, 1, null);
		if(list.size()!=0){
			AircraftOverview ac=new AircraftOverview();
			try {
				BeanUtils.copyProperties(ac, list.get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return list.get(0);
		}else{
			return null;
		}
	}
}
