package com.ras.comparison;

import java.util.List;

public interface ComparisonDao {
	public List<?> findComparisonDetailGrid(String[] modelNames,String[] basicID);
	
	public void saveReport(String reportName,String reportDes, String[] reportContent);
}

