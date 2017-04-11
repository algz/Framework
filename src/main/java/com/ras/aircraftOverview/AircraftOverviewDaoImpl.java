/**
 * 
 */
package com.ras.aircraftOverview;

import java.lang.reflect.Field;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftBasic.AircraftBasic;

/**
 * @author algz
 *
 */
@Repository
public class AircraftOverviewDaoImpl implements AircraftOverviewDao {

	@Autowired
	private SessionFactory sf;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ras.aircraftBasic.AircraftBasicDao#findAll()
	 */
	@Override
	public List<AircraftOverview> findAll() {
		String sql = "select * from ras_aircraft_overview";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftOverview.class).list();
	}

	@Override
	public Integer count() {
		String sql = "select count(1) from ras_aircraft_overview";
		return Integer.parseInt(sf.getCurrentSession().createSQLQuery(sql).uniqueResult() + "");
	}

	@Override
	@Transactional
	public void save(AircraftOverview ao) {
		sf.getCurrentSession().save(ao);
	}

	@Override
	public void saveOrUpdate(AircraftOverview ao) {
		if (ao.getOverviewID().equals("")) {
			sf.getCurrentSession().save(ao);
		} else {
			sf.getCurrentSession().update(ao);
		}
	}

	@Override
	public List<AircraftOverview> findByProperty(AircraftOverview ao) {
		StringBuilder str=new StringBuilder("from AircraftOverview where 1=1 ");
		Field[] fs = ao.getClass().getDeclaredFields();
		try {
			for (Field f : fs) {
				f.setAccessible(true); 
				Object obj = f.get(ao);
				if(obj!=null){
					str.append(" and LOWER("+f.getName()+") like '%"+obj.toString().toLowerCase()+"%'");
				}
				
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return sf.getCurrentSession().createQuery(str.toString()).list();
	}
}
