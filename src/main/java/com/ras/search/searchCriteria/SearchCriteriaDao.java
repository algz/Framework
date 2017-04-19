package com.ras.search.searchCriteria;

import java.util.List;
import java.util.Map;

import com.ras.search.SearchTagVo;

import net.sf.json.JSONArray;

public interface SearchCriteriaDao {

	public void SearchCriteriaGird(SearchCriteriaVo<JSONArray> vo);
}
