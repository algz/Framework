/**
 * 
 */
package com.ras.documnet.paramConfig.dataParam;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.searchParam.SearchParam;
import com.ras.searchParam.SearchParamVo;



/**
 * @author algz
 *
 */
public interface ParamConfigService {
	
	public void findTagGrid(SearchParamVo<SearchParam> vo);
	
	public String addTag(SearchParam tag);
	
	public void modifyTag(SearchParam tag);
	
	public String delTag(String[] ids);
}
