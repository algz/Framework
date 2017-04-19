/**
 * 
 */
package com.ras.documnet.data;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftPhoto.AircraftPhoto;
import com.ras.tool.file.UploadFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * @author algz
 *
 */
public interface DataService {
	
	public AircraftOverview findModel(String overviewID);
	
	public void findTableModelGrid(DataVo vo);
	
	public void findTableModelParamGrid(DataVo vo);
	
	public JSONObject  addModelParamPage(DataVo vo);
	
	public JSONArray findModelImageParam(String category,String basicID);
	
	public void saveImageFile(AircraftPhoto photo);
	
	public void delImageFile(String photoID);
	
	/**
	 * 保存 机型概述
	 * @see com.ras.documnet.data.DataService#saveModel(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void saveModel(Map<String,String> map) throws Exception;
	
	public void saveModelParam(Map<String,String> map) throws Exception ;
	
	public void delModel(String[] ids);
	
	public void delModelParam(String[] ids,String overviewID);
	
	public void setMainModelParam(String basicID,String overviewID);
}
