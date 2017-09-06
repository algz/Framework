package com.ras.comparison;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;


@Service
public class ComparisonServiceImpl implements ComparisonService {

	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;

	
	@Autowired
	private ComparisonDao dao;

	
	@Override
	public void findModelGird(ComparisonVo vo) {
		AircraftOverview ao=new AircraftOverview();
		ao.setModelName(vo.getModelName());
		vo.setRecordsTotal(aircraftOverviewDao.count(ao));
		vo.setData(aircraftOverviewDao.findByProperty(ao,vo.getStart(),vo.getLength()));
	}

	@Override
	public List<?> findComparisonDetailGrid(String[] modelNames,String[] basicID) {
		return dao.findComparisonDetailGrid(modelNames,basicID);
	}

	@Override
	@Transactional
	public void saveReport(String reportName,String reportDes, String[] reportContent) {
		dao.saveReport(reportName, reportDes, reportContent);
		
	}

}
