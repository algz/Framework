/**
 * 
 */
package com.ras.aircraftCapability;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.tool.CommonTool;

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


	@Override
	public AircraftCapability copy(AircraftCapability example) {
		String tableName=example.getClass().getAnnotation(Table.class).name();
		StringBuilder sql=new StringBuilder("select distinct * from "+tableName+" where 1=1 ");
		List<AircraftCapability> list=CommonTool.<AircraftCapability>findEntitiesByProperty(sf, sql, example, 0, 1, null);
		if(list.size()!=0){
			AircraftCapability ac=new AircraftCapability();
			try {
				BeanUtils.copyProperties(ac, list.get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return ac;
		}else{
			return null;
		}
	}

	
}
