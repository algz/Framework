package com.ras.aircraftProductParam;

import java.util.List;

import com.ras.aircraftBasic.AircraftBasic;

public interface AircraftProductParamDao {

	public List<AircraftProductParam> findAllLeaf(AircraftProductParam ap,Integer start,Integer length);
	
	public List<AircraftProductParam> findChilren(String productID);
	
	public List<Object[]> findChilParamValue(String productID,String overviewID);
	
	public List<AircraftProductParam> findByProperty(AircraftProductParam ap,Integer start,Integer length);
	
	public Integer count(AircraftProductParam app);
	
//	public void save(AircraftProduct ab);
	
	public void saveOrUpdate(AircraftProductParam app);
	
	public void del(String productID);
	
//	public AircraftOverview copy(AircraftOverview example);
}
