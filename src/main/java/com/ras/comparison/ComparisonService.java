package com.ras.comparison;

import java.util.List;

public interface ComparisonService {

	public void findModelGird(ComparisonVo vo);
	
	public List<?> findComparisonDetailGrid(String[] overviewID,String[] modelNames,String[] basicID);
}
