package com.ras.analyze;

import java.util.List;

public interface AnalyzeService {

	public List<?> findModelGird(String modelName);
	
	public List<?> findComparisonDetailGrid(String[] modelNames);
	
	public List<?> findModelForTypeahead(String modelName);
	
	public List<?> analyzeChart(String[] modelNames,String[] axis);
}
