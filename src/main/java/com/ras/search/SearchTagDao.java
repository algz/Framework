package com.ras.search;

import java.util.List;

public interface SearchTagDao {
    
	public List<SearchTag> findAllParent();
	
	public List<SearchTag> findAllByIds(String[] ids);
	
	public List findAttribute(String tableName);
	
	public void save(SearchTag searchTag);
}
