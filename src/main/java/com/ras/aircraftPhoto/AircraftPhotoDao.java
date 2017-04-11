package com.ras.aircraftPhoto;

import java.util.List;


public interface AircraftPhotoDao {
	
	public List<AircraftPhoto> find(AircraftPhoto photo);
	
	public void saveOrUpdate(AircraftPhoto photo);
	
	public void del(String photoID);
}
