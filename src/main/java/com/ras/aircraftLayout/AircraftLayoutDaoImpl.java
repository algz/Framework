package com.ras.aircraftLayout;

import java.util.List;

import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.tool.CommonTool;

@Repository
public class AircraftLayoutDaoImpl implements AircraftLayoutDao {
	
	@Autowired
	private SessionFactory sf;
	
	public AircraftLayout copy(AircraftLayout example) {
		String tableName=example.getClass().getAnnotation(Table.class).name();
		StringBuilder sql=new StringBuilder("select distinct * from "+tableName+" where 1=1 ");
		List<AircraftLayout> list=CommonTool.<AircraftLayout>findEntitiesByProperty(sf, sql, example, 0, 1, null);
		if(list.size()!=0){
			AircraftLayout ac=new AircraftLayout();
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
	public void saveOrUpdate(AircraftLayout al) {
		if (al.getLayoutID()==null||al.getLayoutID().equals("")) {
			sf.getCurrentSession().save(al);
		} else {
			sf.getCurrentSession().update(al);
		}
	}
}
