/**
 * 
 */
package com.ras.aircraftCapability;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author algz
 *
 */
@Repository
public class AircraftCapabilityDaoImpl implements AircraftCapabilityDao {

	@Autowired
	private SessionFactory sf;
	
	/* (non-Javadoc)
	 * @see com.ras.aircraftBasic.AircraftBasicDao#findAll()
	 */
	@Override
	public List<AircraftCapability> findAll() {
		String sql="select * from ras_aircraft_basic";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftCapability.class).list();
	}


	public Integer count(AircraftCapability ab){
		String hql="select count(1) from AircraftBasic where overviewID=:overviewID";
		return Integer.parseInt(sf.getCurrentSession().createQuery(hql)
				      .setProperties(ab).uniqueResult()+"");
	}
	
	@Override
	public void saveOrUpdate(AircraftCapability ab) {
		if(ab.getCapabilityID().equals("")){
			sf.getCurrentSession().save(ab);
		}else{
			sf.getCurrentSession().update(ab);
		}
	}

	@Override
	public List<AircraftCapability> find(AircraftCapability ac) {
		String hql="from AircraftBasic where overviewID=:overviewID";
		Query query= sf.getCurrentSession().createQuery(hql);
		List list= query.setProperties(ac).list();//.setEntity("ab", ab).list();
		return list;
	}

	
}
