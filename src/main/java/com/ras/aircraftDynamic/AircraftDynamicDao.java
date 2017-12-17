package com.ras.aircraftDynamic;

import java.util.List;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;

public interface AircraftDynamicDao {
	
	public void saveOrUpdate(AircraftDynamic ad);
	
	public AircraftDynamic copy(AircraftDynamic example);
}
