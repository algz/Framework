package com.ras.comparison;

import java.util.List;

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
	public List<?> findModelGird(String modelName) {
		AircraftOverview ao=new AircraftOverview();
		ao.setModelName(modelName);
		return aircraftOverviewDao.findByProperty(ao);
	}

	@Override
	public List<?> findComparisonDetailGrid(String[] modelNames) {
		return dao.findComparisonDetailGrid(modelNames);
	}

}
