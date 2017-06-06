package com.ras.aircraftArchive;

import java.util.List;

import com.ras.aircraftPicture.AircraftPicture;


public interface AircraftArchiveDao {
	
	public Integer count(AircraftArchive archive);
	
	public List<AircraftArchive> find(AircraftArchive archive,Integer start,Integer length);
	
	public AircraftArchive find(String archiveID);
	
	public void saveOrUpdate(AircraftArchive archive);
	
	public void del(String archiveID);
}
