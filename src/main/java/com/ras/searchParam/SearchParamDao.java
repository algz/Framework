package com.ras.searchParam;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface SearchParamDao {
    
	public void findAll(SearchParamVo<SearchParam> vo);
	
	public List<SearchParam> findAllChildren(String parentID);
	
	public List<SearchParam> findAllParent();
	
	public List<SearchParam> findAllByIds(String[] ids);
	
	public List findAttribute(String tableName);
	
	public void save(SearchParam searchTag);
	
	public Map<String,String> searchSummarize(String overviewID);
	

	
	public Map<String,List<String>> findAllCheckboxTypeList();
	
}
