package com.ras.search;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface SearchTagService {
	
	public List<SearchTag> findAllParent();
	
	public List<SearchTag> findAllByIds(String[] ids);
	
	public void save(SearchTag searchTag);
	
	public JSONObject addNoteForTagInput(String overviewID,String[] inputNames);
	
}
