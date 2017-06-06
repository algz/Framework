package com.ras.analyze;

import java.util.List;

import net.sf.json.JSONArray;

public interface AnalyzeService {

	public List<?> findModelGird(AnalyzeVo vo);
	
	public List<?> findComparisonDetailGrid(String[] modelNames);
	
	public List<?> findModelForTypeahead(String modelName);
	
	public JSONArray analyzeChart(String[] modelNames,String[] axis);
}
