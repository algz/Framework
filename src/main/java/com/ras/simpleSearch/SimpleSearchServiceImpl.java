/**
 * 
 */
package com.ras.simpleSearch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;
import com.ras.searchParam.searchCriteria.SearchCriteriaService;
import com.ras.searchParam.searchCriteria.SearchCriteriaVo;
import com.ras.tool.CommonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author algz
 *
 */
@Service
public class SimpleSearchServiceImpl implements SimpleSearchService {

	@Autowired
	private SimpleSearchDao dao;
	
	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;
	
	/* (non-Javadoc)
	 * @see com.ras.simpleSearch.SimpleSearchService#GetSearchTreeNode(java.util.Map)
	 */
	@Override
	public JSONArray getSearchTreeNode(Map<String, String> m) {
		return dao.getSearchTreeNode(m);
	}

	@Override
	public void simpleSearchGird(SimpleSearchVo vo) {
		dao.simpleSearchGird(vo);
	}

	@Override
	public JSONObject addNoteForTagInput(String overviewID, String[] inputNames) {
		return dao.addNoteForTagInput(overviewID, inputNames);
	}
	
	/**
	 * 查找子机型
	 */
	@Override
	public JSONObject searchSubModelGrid(String overviewID) {
		JSONObject jo=new JSONObject();
    	JSONArray ja=new JSONArray();
    	
    	AircraftOverview example=new AircraftOverview();
    	example.setParentID(overviewID);
    	List<AircraftOverview> list=aircraftOverviewDao.findByProperty(example, null, null);
    	for(AircraftOverview ao:list){
    		JSONObject tem=CommonTool.beanToJSONObject(ao, true);
//    		tem.put("dataSource", ao.getDataSources());
//    		tem.put("basicID", ao.getBasicID());
//    		tem.put("overviewID", ao.getOverviewID());
    		ja.add(tem);
    	}
    	jo.put("data", ja);
		return jo;
	}
}
