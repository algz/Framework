/**
 * 
 */
package com.ras.documnet.tag;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftBasic.AircraftBasicDao;
import com.ras.aircraftCapability.AircraftCapability;
import com.ras.aircraftCapability.AircraftCapabilityDao;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;
import com.ras.aircraftWeight.AircraftWeight;
import com.ras.search.SearchTag;
import com.ras.search.SearchTagDao;
import com.ras.search.SearchTagVo;
import com.ras.tool.CommonTool;

/**
 * @author algz
 *
 */
@Service
public class TagEditServiceImpl implements TagEditService {
	
	@Autowired
	private SearchTagDao searchTagDao;
	
	@Autowired
	private TagEditDao dao;
	
	@Override
	public void findTagGrid(SearchTagVo<SearchTag> vo) {
		 searchTagDao.findAll(vo);
	}
	
	@Override
	@Transactional
	public void modifyTag(SearchTag tag) {
		dao.modifyTag(tag);
		
	}


	@Override
	@Transactional
	public void addTag(SearchTag tag) {
		dao.addTag(tag);
		
	}


	@Override
	@Transactional
	public void delTag(String[] ids) {
		dao.delTag(ids);
		
	}

}
