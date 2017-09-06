package com.ras.simpleSearch;

import java.util.Map;


import net.sf.json.JSONArray;

public interface SimpleSearchDao {
	public JSONArray getSearchTreeNode(Map<String,String> m);
	
	public void simpleSearchGird(SimpleSearchVo vo);
}
