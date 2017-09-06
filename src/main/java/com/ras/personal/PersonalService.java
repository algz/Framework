package com.ras.personal;

import java.util.List;

import com.ras.personal.report.Report;

public interface PersonalService {

	public void findPersonalReportGrid(PersonalVo vo);
	
	public void findPersonalReportContentGrid(PersonalVo vo);
	
	public Report findPersonalReport(PersonalVo vo);
}
