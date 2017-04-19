package com.ras.search;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface SearchTagDao {
    
	public void findAll(SearchTagVo<SearchTag> vo);
	
	public List<SearchTag> findAllChildren(String parentID);
	
	public List<SearchTag> findAllParent();
	
	public List<SearchTag> findAllByIds(String[] ids);
	
	public List findAttribute(String tableName);
	
	public void save(SearchTag searchTag);
	
	public Map<String,String> searchSummarize(String overviewID);
	
	public JSONObject addNoteForTagInput(String overviewID,String[] inputNames);
	
}
