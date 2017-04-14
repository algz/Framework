package com.ras.aircraftBasic;

import java.util.List;
import java.util.Map;

public interface AircraftBasicDao {

	public List<AircraftBasic> findAll();
	
	public List<AircraftBasic> find(AircraftBasic ab);
	
	public Integer count();
	
	public Integer count(AircraftBasic ab);
	
	public void saveOrUpdate(AircraftBasic ab);
	
	public void saveOrUpdateMap(Map<String, String> m);
}
