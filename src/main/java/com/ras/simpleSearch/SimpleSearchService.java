package com.ras.simpleSearch;

import java.util.Map;

import com.ras.searchParam.searchCriteria.SearchCriteriaVo;

import net.sf.json.JSONArray;

public interface SimpleSearchService {
	public JSONArray getSearchTreeNode(Map<String,String> m);
	
	public void simpleSearchGird(SimpleSearchVo vo);
}
