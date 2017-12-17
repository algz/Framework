package com.ras.personal.faverites;

import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ras.aircraftReport.AircraftReport;


public interface FavoritesService {

	public void findFavoritesGrid(FavoritesVo vo);
	
	public Boolean showFavorites(FavoritesVo vo);
	
	public String addFavorites(FavoritesVo vo);
	
	public void delFavorites(FavoritesVo vo);
	
}
