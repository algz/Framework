package com.ras.documnet.paramConfig.dataParam;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.searchParam.SearchParam;

public interface ParamConfigDao {
	
	public String addTag(SearchParam tag);
	
	public String addTagCategory(SearchParam tag);
	
	public void modifyTag(SearchParam tag)throws Exception;
	
	public void delTag(String[] ids)throws Exception;
}
