package com.ras.simpleSearch;

import java.util.Map;

import com.ras.searchParam.searchCriteria.SearchCriteriaVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SimpleSearchService {
	public JSONArray getSearchTreeNode(Map<String,String> m);
	
	public void simpleSearchGird(SimpleSearchVo vo);
	
	public JSONObject addNoteForTagInput(String overviewID,String[] inputNames);
	
	public JSONObject searchSubModelGrid(String overviewID);
}
