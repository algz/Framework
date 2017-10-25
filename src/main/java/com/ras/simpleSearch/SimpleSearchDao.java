package com.ras.simpleSearch;

import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SimpleSearchDao {
	public JSONArray getSearchTreeNode(Map<String,String> m);
	
	public void simpleSearchGird(SimpleSearchVo vo);
	
	public JSONObject addNoteForTagInput(String overviewID,String[] inputNames);
}
