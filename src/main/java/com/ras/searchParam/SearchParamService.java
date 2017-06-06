package com.ras.searchParam;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface SearchParamService {
	
	public List<SearchParam> findAllParent();
	
	public List<SearchParam> findAllByIds(String[] ids);
	
	public void save(SearchParam searchTag);
	
	public JSONObject addNoteForTagInput(String overviewID,String[] inputNames);
	
}
