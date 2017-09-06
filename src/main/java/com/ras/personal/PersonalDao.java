package com.ras.personal;

import java.util.List;

import com.ras.personal.report.Report;
import com.ras.tool.ReturnVo;

public interface PersonalDao {
	public void findPersonalReportGrid(PersonalVo vo);
	
	public void findPersonalReportContentGrid(PersonalVo vo);
	
	public Report findPersonalReport(PersonalVo vo);
}

