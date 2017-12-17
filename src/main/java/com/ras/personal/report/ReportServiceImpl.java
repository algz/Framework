package com.ras.personal.report;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftReport.AircraftReport;
import com.ras.aircraftReport.AircraftReportDao;

import algz.platform.util.Common;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private AircraftReportDao dao;

	@Override
	public void findReportGrid(ReportVo vo) {
		AircraftReport aircraftReport=new AircraftReport();
//		aircraftReport.setReportName(reportName);
//		aircraftReport.setReportDes();
		aircraftReport.setEditor(Common.getLoginUser().getUserid());
		Integer[] arr={vo.getStart(),vo.getLength(),0};
		vo.setData(dao.findAircraftReportGrid(aircraftReport,arr));
		vo.setCount(arr[2]);
	}

	@Override
	public void findReportContentGrid(ReportVo vo) {
		vo.setData(dao.findAircraftReportContentGrid(vo.getReportID()));
	}

	@Override
	public AircraftReport findReport(ReportVo vo) {
		AircraftReport aircraftReport=new AircraftReport();
		aircraftReport.setReportID(vo.getReportID());
		return dao.findAircraftReport(aircraftReport);
	}
	
	@Override
	@Transactional
	public void saveReport(String reportName,String reportDes, String[] reportContent) {
		AircraftReport aircraftReport=new AircraftReport();
		aircraftReport.setReportName(reportName);
		aircraftReport.setReportDes(reportDes);
		aircraftReport.setEditor(Common.getLoginUser().getUserid());
		dao.saveAircraftReport(aircraftReport, reportContent);
	}

	@Override
	@Transactional
	public void delReport(ReportVo<?> vo) {
		AircraftReport aircraftReport=new AircraftReport();
		aircraftReport.setReportID(vo.getReportID());
		aircraftReport.setEditor(Common.getLoginUser().getUserid());
		dao.del(aircraftReport);
	}
	
}
