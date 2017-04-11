package com.ras.documnet.tag;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.search.SearchTag;

public interface TagEditDao {
	
	public void addTag(SearchTag tag);
	
	public void modifyTag(SearchTag tag);
	
	public void delTag(String[] ids);
}
