/**
 * 
 */
package com.ras.documnet;

import java.util.List;
import java.util.Map;

import com.ras.aircraftOverview.AircraftOverview;



/**
 * @author algz
 *
 */
public interface DocumentService {
	
	public AircraftOverview findModel(String overviewID);
	
	public void findTableModelGrid(DocumentVo vo);
	
	public void findTableModelParamGrid(DocumentVo vo);
	
	public Map<String,String>  addModelParamPage(DocumentVo vo);
	
	/**
	 * 保存 机型概述
	 * @see com.ras.documnet.DocumentService#saveModel(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void saveModel(Map<String,String[]> map) throws Exception;
	
	public void saveModelParam(Map<String,String[]> map) throws Exception ;
	
	public void delModel(String[] ids);
	
	public void delModelParam(String[] ids);
}
