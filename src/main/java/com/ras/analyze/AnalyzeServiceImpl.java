package com.ras.analyze;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;
	
	@Autowired
	private AnalyzeDao dao;
	
	@Override
	public List<?> findModelGird(AnalyzeVo vo) {
		AircraftOverview ao=new AircraftOverview();
		ao.setModelName(vo.getModelName());
		aircraftOverviewDao.count(ao);
		return aircraftOverviewDao.findByProperty(ao,vo.getStart(),vo.getLength());
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
	public JSONArray analyzeChart(String[] overviewID, String[] axis) {
		return dao.analyzeChart(overviewID, axis);
	}

	@Override
	public JSONArray getAircraftAll() {
		List<AircraftOverview> list=aircraftOverviewDao.findAll();
		JSONArray ja=new JSONArray();
		for(AircraftOverview ao : list){
			JSONObject jo=new JSONObject();
			jo.put("modelName", ao.getModelName());
			jo.put("overviewID", ao.getOverviewID());
			ja.add(jo);
		}
		return ja;
	}

}
