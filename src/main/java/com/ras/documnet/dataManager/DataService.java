/**
 * 
 */
package com.ras.documnet.dataManager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftArchive.AircraftArchive;
import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftPicture.AircraftPicture;
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
	
	public void findTableModelParamGrid(DataVo<AircraftBasic> vo);
	
	public JSONObject  addModelParamPage(DataVo vo);
	
	public JSONArray findModelImageParam(String category,String basicID);
	
	public void saveModelPhotoFile(AircraftOverview ao);
	
	public void saveModelParamPhotoFile(AircraftPicture photo);
	
	public void findArchiveGrid(DataVo vo);
	
	public void findPictureGrid(DataVo vo);
	
	public void saveModelParamArchiveFile(AircraftArchive archive);
	
	public void delArichiveFile(String archiveID);
	
	public AircraftArchive downloadArchiveFile(String archiveID);
	
	public void delPictureFile(String photoID);
	
	public void delModelImageFile(String overviewID);
	
	/**
	 * 保存 机型概述
	 * @see com.ras.documnet.dataManager.DataService#saveModel(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void saveModel(Map<String,String> map) throws Exception;
	
	public void saveSubModel(String modelName,String parentID);
	
	
	public void saveModelParam(CaseInsensitiveMap map) throws Exception ;
	
	public void delModel(String[] ids);
	
	public void delModelParam(String[] ids,String overviewID);
	
	public void setMainModelParam(String basicID,String overviewID);
	
	public List findTableSQL(String tableName);
	
	public List<?> findCategoryNameForTypeahead(String categoryName); 
	
	public JSONArray findAircraftOverviewPrivilidgeUser(String overviewID);
}
