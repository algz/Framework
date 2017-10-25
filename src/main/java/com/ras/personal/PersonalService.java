package com.ras.personal;

import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ras.aircraftReport.AircraftReport;


public interface PersonalService {

	
	public void exportReportToExcel(HSSFWorkbook workbook,Map m);
}
