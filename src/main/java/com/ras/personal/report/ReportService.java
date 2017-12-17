package com.ras.personal.report;

import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ras.aircraftReport.AircraftReport;



public interface ReportService {

	public void findReportGrid(ReportVo<?> vo);
	
	public void findReportContentGrid(ReportVo<?> vo);
	
	public AircraftReport findReport(ReportVo<?> vo);
	
	public void saveReport(String reportName,String reportDes, String[] reportContent);
	
	public void delReport(ReportVo<?> vo);
}
