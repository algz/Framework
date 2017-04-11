/**
 * 
 */
package com.ras.documnet.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.search.SearchTag;
import com.ras.search.SearchTagVo;



/**
 * @author algz
 *
 */
public interface TagEditService {
	
	public void findTagGrid(SearchTagVo<SearchTag> vo);
	
	public void addTag(SearchTag tag);
	
	public void modifyTag(SearchTag tag);
	
	public void delTag(String[] ids);
}
