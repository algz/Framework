package com.ras.comparison;

import java.util.List;

public interface ComparisonDao {
	public void findModelGird(ComparisonVo vo);
	
	public List<?> findComparisonDetailGrid(String[] modelNames,String[] basicID);
	
}

