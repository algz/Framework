package com.ras.analyze;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;

import net.sf.json.JSONArray;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;
	
	@Autowired
	private AnalyzeDao dao;
	
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

	@Override
	public List<?> findModelForTypeahead(String modelName) {
		return dao.findModelForTypeahead(modelName);
	}

	@Override
	public JSONArray analyzeChart(String[] modelNames, String[] axis) {
		return dao.analyzeChart(modelNames, axis);
	}

}
