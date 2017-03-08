package com.ras.aircraftOverview;

import java.util.List;

public interface AircraftOverviewDao {

	public List<AircraftOverview> findAll();
	
	public List<AircraftOverview> findByProperty(AircraftOverview ao);
	
	public Integer count();
	
	public void save(AircraftOverview ab);
	
	public void saveOrUpdate(AircraftOverview ao);
}
