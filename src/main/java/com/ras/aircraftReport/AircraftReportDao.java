package com.ras.aircraftReport;

import java.util.List;

public interface AircraftReportDao {
	public List<AircraftReport> findAircraftReportGrid(AircraftReport report,Integer[] arr);
	
	public List<List<String>> findAircraftReportContentGrid(String reportID);
	
	public AircraftReport findAircraftReport(AircraftReport vo);
	
	public void saveAircraftReport(AircraftReport aircraftReport, String[] reportContent);
	
	public void del(AircraftReport aircraftReport);
}
