package com.ras.searchTag;

import java.util.List;


public interface SearchTagDao {

	public void searchIndexPage(SearchTagVo vo);
	
	public void findTagSearchForParamGird(SearchTagVo vo);
	
	public void findTagSearchForArchiveGird(SearchTagVo vo);
	
	public void findTagSearchForPictureGird(SearchTagVo vo);
	
	public List<?> findTagForTypeahead(String tagName);
}