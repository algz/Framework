package com.ras.aircraftUserFavorites;

import java.math.BigDecimal;
import java.util.List;

import com.ras.aircraftPicture.AircraftPicture;


public interface AircraftUserFavoritesDao {
	
	public List<AircraftUserFavorites> find(AircraftUserFavorites favorites,Integer[] arr);
	
	public BigDecimal count(AircraftUserFavorites favorites);
	
	public String saveOrUpdate(AircraftUserFavorites favorites);
	
	public void del(AircraftUserFavorites favorites);
}
