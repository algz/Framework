package com.ras.aircraftSystem;

import java.util.List;

import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftDynamic.AircraftDynamic;
import com.ras.tool.CommonTool;

@Repository
public class AircraftSystemDaoImpl implements AircraftSystemDao {
	
	@Autowired
	private SessionFactory sf;
	
	public AircraftSystem copy(AircraftSystem example) {
		String tableName=example.getClass().getAnnotation(Table.class).name();
		StringBuilder sql=new StringBuilder("select distinct * from "+tableName+" where 1=1 ");
		List<AircraftSystem> list=CommonTool.<AircraftSystem>findEntitiesByProperty(sf, sql, example, 0, 1, null);
		if(list.size()!=0){
			AircraftSystem ac=new AircraftSystem();
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
	public void saveOrUpdate(AircraftSystem ad) {
		if (ad.getSystemID()==null||ad.getSystemID().equals("")) {
			sf.getCurrentSession().save(ad);
		} else {
			sf.getCurrentSession().update(ad);
		}
	}
}
