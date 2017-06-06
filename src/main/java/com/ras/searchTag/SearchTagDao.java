package com.ras.searchTag;

import java.util.List;

import com.ras.aircraftTag.AircraftTag;

public interface SearchTagDao {

	public void searchIndexPage(SearchTagVo vo);
	
	public void findTagSearchForParamGird(SearchTagVo vo);
	
	public void findTagSearchForArchiveGird(SearchTagVo vo);
	
	public void findTagSearchForPictureGird(SearchTagVo vo);
}