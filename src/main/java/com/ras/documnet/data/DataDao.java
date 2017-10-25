package com.ras.documnet.data;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ras.aircraftOverview.AircraftOverview;

import net.sf.json.JSONObject;

public interface DataDao {
	
	public AircraftOverview findModel(String overviewID);
	
	public void findTableModelGrid(DataVo vo);
	
	public void findTableModelParamGrid(DataVo vo);
	
	public Map<String,String> addModelParamPage(DataVo vo);
	
	public JSONObject loadModelParam(DataVo vo);
	
	public JSONObject findModelParam(DataVo vo);
	
	public void delModelImageFile(String overviewID);
	
	public void delModel(String[] ids);
	
	public void delModelParam(String[] ids,String overviewID);
	
	public void setMainModelParam(String basic,String OverviewID);
	
	public void saveModelParam(String tableName,Map<String, String> map,boolean isCreate);
	
	public void saveModelPhotoFile(AircraftOverview ao);
	
	public List findTableSQL(String tableName);
	
	public List<?> findCategoryNameForTypeahead(String categoryName); 
	
	public <T> T copyObject(T example);
	
}
