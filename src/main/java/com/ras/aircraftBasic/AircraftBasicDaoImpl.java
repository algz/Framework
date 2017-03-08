/**
 * 
 */
package com.ras.aircraftBasic;

import java.util.List;

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
public class AircraftBasicDaoImpl implements AircraftBasicDao {

	@Autowired
	private SessionFactory sf;
	
	/* (non-Javadoc)
	 * @see com.ras.aircraftBasic.AircraftBasicDao#findAll()
	 */
	@Override
	public List<AircraftBasic> findAll() {
		String sql="select * from ras_aircraft_basic";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftBasic.class).list();
	}

	@Override
	public Integer count() {
		String sql="select count(1) from ras_aircraft_basic";
		return Integer.parseInt(sf.getCurrentSession().createSQLQuery(sql).uniqueResult()+"");
	}

	public Integer count(AircraftBasic ab){
		String hql="select count(1) from AircraftBasic where overviewID=:overviewID";
		return Integer.parseInt(sf.getCurrentSession().createQuery(hql)
				      .setProperties(ab).uniqueResult()+"");
	}
	
	@Override
	public void saveOrUpdate(AircraftBasic ab) {
		if(ab.getBasicID().equals("")){
			sf.getCurrentSession().save(ab);
		}else{
			sf.getCurrentSession().update(ab);
		}
	}

	@Override
	public List<AircraftBasic> find(AircraftBasic ab) {
		String hql="from AircraftBasic where overviewID=:overviewID";
		Query query= sf.getCurrentSession().createQuery(hql);
		List list= query.setProperties(ab).list();//.setEntity("ab", ab).list();
		return list;
	}

	
}
