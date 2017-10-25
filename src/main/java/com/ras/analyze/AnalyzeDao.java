package com.ras.analyze;

import java.util.List;

import com.ras.aircraftOverview.AircraftOverview;

import net.sf.json.JSONArray;

public interface AnalyzeDao {
	public List<?> findComparisonDetailGrid(String[] modelNames);
	
	public List<?> findModelForTypeahead(String modelName);
	
	public JSONArray analyzeChart(String[] modelNames,String[] axis);
	
	public List<AircraftOverview> getAircraftAll(boolean isParent);
}

