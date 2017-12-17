package com.ras.simpleSearch;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SimpleSearchDao {
	public JSONArray getSearchTreeNode(Map<String,String> m);
	
	public void simpleSearchGird(SimpleSearchVo vo);
	
	public JSONObject addNoteForTagInput(String overviewID,String[] inputNames);
	
	public List<AircraftOverview> searchSubModelGrid(String parentID);
}
