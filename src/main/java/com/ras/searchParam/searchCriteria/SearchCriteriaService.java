package com.ras.searchParam.searchCriteria;

import java.util.List;
import java.util.Map;

import com.ras.searchParam.SearchParamVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SearchCriteriaService {

	public void SearchCriteriaGird(SearchCriteriaVo vo);
	
	public JSONObject SearchCriteriaSubGird(String overviewID);
}
