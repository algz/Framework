package com.ras.search;

import java.util.List;
import java.util.Map;

public interface SearchTagService {
	
	public List<SearchTag> findAllParent();
	
	public List<SearchTag> findAllByIds(String[] ids);
	
	public void save(SearchTag searchTag);
	
	public Map<String,String> searchSummarize(String overviewID);
}
