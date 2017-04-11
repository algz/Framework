package com.ras.analyze;

import java.util.List;

public interface AnalyzeDao {
	public List<?> findComparisonDetailGrid(String[] modelNames);
	
	public List<?> findModelForTypeahead(String modelName);
	
	public List<?> analyzeChart(String[] modelNames,String[] axis);
}

