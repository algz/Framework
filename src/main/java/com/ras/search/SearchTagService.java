package com.ras.search;

import java.util.List;

public interface SearchTagService {
	
	public List<SearchTag> findAllParent();
	
	public List<SearchTag> findAllByIds(String[] ids);
	
	public void save(SearchTag searchTag);
}
