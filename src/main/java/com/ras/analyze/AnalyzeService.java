package com.ras.analyze;

import java.util.List;

public interface AnalyzeService {

	public List<?> findModelGird(String modelName);
	
	public List<?> findComparisonDetailGrid(String[] modelNames);
}
