package com.ras.aircraftLayout;

import java.util.List;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;

public interface AircraftLayoutDao {
	
	public void saveOrUpdate(AircraftLayout al);
	
	public AircraftLayout copy(AircraftLayout example);
}
