package com.ras.aircraftOverview;

import java.util.List;

public interface AircraftOverviewDao {

	public List<AircraftOverview> findAll();
	
	public List<AircraftOverview> findByProperty(AircraftOverview ao,Integer start,Integer length);
	
	public Integer count(AircraftOverview ao);
	
	public void save(AircraftOverview ab);
	
	public void saveOrUpdate(AircraftOverview ao);
}
