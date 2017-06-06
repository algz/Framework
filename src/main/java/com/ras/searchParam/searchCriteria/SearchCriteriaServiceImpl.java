package com.ras.searchParam.searchCriteria;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.searchParam.SearchParamVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SearchCriteriaServiceImpl implements SearchCriteriaService {

	@Autowired
	private SearchCriteriaDao dao;
	
	@Override
	public void SearchCriteriaGird(SearchCriteriaVo vo) {
		dao.SearchCriteriaGird(vo);
	}

	@Override
	public JSONObject SearchCriteriaSubGird(String overviewID) {
		JSONObject jo=new JSONObject();
    	JSONArray ja=new JSONArray();
    	
    	List<AircraftBasic> list=dao.SearchCriteriaSubGird(overviewID);
    	for(AircraftBasic ab:list){
    		JSONObject tem=new JSONObject();
    		tem.put("dataSource", ab.getDataSources());
    		tem.put("basicID", ab.getBasicID());
    		tem.put("overviewID", ab.getOverviewID());
    		ja.add(tem);
    	}
    	jo.put("data", ja);
		return jo;
	}


}
