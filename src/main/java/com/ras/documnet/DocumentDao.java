package com.ras.documnet;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;

public interface DocumentDao {
	
	public AircraftOverview findModel(String overviewID);
	
	public void FindTableModelParamGrid(DocumentVo vo);
	
	public Map<String,String> addModelParamPage(DocumentVo vo);
	
	public void delModel(String[] ids);
	
	public void delModelParam(String[] ids);
}
