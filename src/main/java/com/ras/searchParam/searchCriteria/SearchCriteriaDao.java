package com.ras.searchParam.searchCriteria;

import java.util.List;
import java.util.Map;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.searchParam.SearchParamVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SearchCriteriaDao {

	public void SearchCriteriaGird(SearchCriteriaVo<JSONArray> vo);
	
	public List<AircraftBasic> SearchCriteriaSubGird(String overviewID);
}
