/**
 * 
 */
package com.ras.documnet.param;

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
import com.ras.searchParam.SearchParamDao;
import com.ras.searchParam.SearchParam;
import com.ras.searchParam.SearchParamVo;
import com.ras.tool.CommonTool;

/**
 * @author algz
 *
 */
@Service
public class ParamConfigServiceImpl implements ParamConfigService {
	
	@Autowired
	private SearchParamDao searchTagDao;
	
	@Autowired
	private ParamConfigDao dao;
	
	@Override
	public void findTagGrid(SearchParamVo<SearchParam> vo) {
		 searchTagDao.findAll(vo);
	}
	
	@Override
	@Transactional
	public void modifyTag(SearchParam tag) {
		try {
			dao.modifyTag(tag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	@Transactional
	public String addTag(SearchParam tag) {
		return dao.addTag(tag);
		
	}


	@Override
	@Transactional
	public String delTag(String[] ids) {
		try {
			dao.delTag(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		return null;
	}

}
