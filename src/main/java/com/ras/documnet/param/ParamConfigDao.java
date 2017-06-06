package com.ras.documnet.param;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.searchParam.SearchParam;

public interface ParamConfigDao {
	
	public String addTag(SearchParam tag);
	
	public void modifyTag(SearchParam tag);
	
	public void delTag(String[] ids);
}
