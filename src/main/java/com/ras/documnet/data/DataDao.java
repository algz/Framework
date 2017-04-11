package com.ras.documnet.data;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;

public interface DataDao {
	
	public AircraftOverview findModel(String overviewID);
	
	public void FindTableModelParamGrid(DataVo vo);
	
	public Map<String,String> addModelParamPage(DataVo vo);
	
	public void delModel(String[] ids);
	
	public void delModelParam(String[] ids);
}
