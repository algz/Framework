package com.ras.personal;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ras.aircraftReport.AircraftReport;
import com.ras.tool.ReturnVo;

public interface PersonalDao {

	
	public HSSFWorkbook exportReportToExcel(Map m) ;
}

