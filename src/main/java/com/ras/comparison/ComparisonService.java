package com.ras.comparison;

import java.util.List;

public interface ComparisonService {

	public List<?> findModelGird(String modelName);
	
	public List<?> findComparisonDetailGrid(String[] modelNames);
}