package com.ras.aircraftCapability;

import java.util.List;
import java.util.Map;

public interface AircraftCapabilityDao {

	public List<AircraftCapability> findAll();
	
	public List<AircraftCapability> find(AircraftCapability ac);
	
	public Integer count(AircraftCapability ab);
	
	public void saveOrUpdate(AircraftCapability ab);
}
