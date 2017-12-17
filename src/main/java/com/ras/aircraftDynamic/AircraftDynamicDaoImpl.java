package com.ras.aircraftDynamic;

import java.util.List;

import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.tool.CommonTool;

import algz.platform.util.Common;

@Repository
public class AircraftDynamicDaoImpl implements AircraftDynamicDao {
	
	@Autowired
	private SessionFactory sf;
	
	@Override
	public AircraftDynamic copy(AircraftDynamic example) {
		String tableName=example.getClass().getAnnotation(Table.class).name();
		StringBuilder sql=new StringBuilder("select distinct * from "+tableName+" where 1=1 ");
		List<AircraftDynamic> list=CommonTool.<AircraftDynamic>findEntitiesByProperty(sf, sql, example, 0, 1, null);
		if(list.size()!=0){
			AircraftDynamic ac=new AircraftDynamic();
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

	@Override
	public void saveOrUpdate(AircraftDynamic ad) {
		if (ad.getDynamicID()==null||ad.getDynamicID().equals("")) {
			sf.getCurrentSession().save(ad);
			
		} else {
			sf.getCurrentSession().update(ad);
		}
	}
}
