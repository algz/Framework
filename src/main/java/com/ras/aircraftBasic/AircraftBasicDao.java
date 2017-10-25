package com.ras.aircraftBasic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ras.aircraftCapability.AircraftCapability;

public interface AircraftBasicDao {

	public List<AircraftBasic> findAll();
	
	public List<AircraftBasic> find(AircraftBasic ab);
	
	public Integer count();
	
	public Integer count(AircraftBasic ab);
	
	public void saveOrUpdate(AircraftBasic ab);
	
	public void saveOrUpdateMap(Map<String, String> m);
	
	public BigDecimal countMaininfo(String overviewID);
	
	public void setMaininfo(String basicID,String overviewID);
	
	public AircraftBasic getMainAircraftBasic(String overviewID);
	
	public AircraftBasic copy(AircraftBasic example);
}
