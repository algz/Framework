package com.ras.aircraftPicture;

import java.math.BigDecimal;
import java.util.List;


public interface AircraftPictureDao {
	
	public BigDecimal count(AircraftPicture photo);
	
	public List<AircraftPicture> find(AircraftPicture photo);
	
	public void saveOrUpdate(AircraftPicture photo);
	
	public void del(String photoID);
}
