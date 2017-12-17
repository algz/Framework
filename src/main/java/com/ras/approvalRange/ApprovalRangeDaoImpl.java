/**
 * 
 */
package com.ras.approvalRange;

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
public class ApprovalRangeDaoImpl implements ApprovalRangeDao {
	
	@Autowired
	private SessionFactory sf;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ras.aircraftBasic.AircraftBasicDao#findAll()
	 */
	@Override
	public List<ApprovalRange> findAll() {
		String sql = "select * from ras_aircraft_overview ao "
				+ " inner join ras_aircraft_basic ab on ao.id=ab.OVERVIEWID where 1=1 ";
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			sql+=" and (ao.editor='"+curUser.getUserid()+"' and ao.PERMISSION_LEVEL='1') or ao.PERMISSION_LEVEL in ('2','3') ";
		}
		
		return sf.getCurrentSession().createSQLQuery(sql+" order by ao.modelname").addEntity(ApprovalRange.class).list();
	}

	@Override
	public Integer count(ApprovalRange ao) {
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
	public void saveOrUpdate(ApprovalRange ao) {
		if (ao.getDataID()==null||ao.getDataID().equals("")) {
			sf.getCurrentSession().save(ao);
			
		} else {
			sf.getCurrentSession().update(ao);
		}
	}

	@Override
	public List<ApprovalRange> findByProperty(ApprovalRange ao,Integer start,Integer length) {
		StringBuilder str=new StringBuilder("select distinct * from RAS_APPROVAL_RANGE where 1=1 ");
		
		return CommonTool.findEntitiesByProperty(sf, str, ao, null, null, null);
	}

}
