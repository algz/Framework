package com.ras.aircraftType;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AircraftTypeDaoImpl implements AircraftTypeDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<AircraftType> findAll() {
		String sql="select * from RAS_AIRCRAFTKIND";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftType.class).list();
	}

}
