/**
 * 
 */
package com.ras.documnet.param;

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
	
	public void delTag(String[] ids);
}
