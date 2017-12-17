package com.ras.aircraftSystem;

import java.util.List;

import com.ras.aircraftBasic.AircraftBasic;

public interface AircraftSystemDao {

	public void saveOrUpdate(AircraftSystem ad);
	
	public AircraftSystem copy(AircraftSystem example);
}
