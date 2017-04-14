package com.ras.documnet.data;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;

import net.sf.json.JSONObject;

public interface DataDao {
	
	public AircraftOverview findModel(String overviewID);
	
	public void FindTableModelParamGrid(DataVo vo);
	
	public Map<String,String> addModelParamPage(DataVo vo);
	
	public JSONObject findModelParam(DataVo vo);
	
	public void delModel(String[] ids);
	
	public void delModelParam(String[] ids);
}
