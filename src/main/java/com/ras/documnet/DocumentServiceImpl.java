/**
 * 
 */
package com.ras.documnet;

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
import com.ras.tool.CommonTool;

/**
 * @author algz
 *
 */
@Service
public class DocumentServiceImpl implements DocumentService {
	
	
	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;
	
	@Autowired
	private AircraftBasicDao aircraftBasicDao;
	
	@Autowired
	private AircraftCapabilityDao aircraftCapabilityDao;
	
	@Autowired
	private DocumentDao dao;
	
	/**
	 * 保存 机型概述
	 * @throws Exception 
	 * @see com.ras.documnet.DocumentService#saveModel(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public void saveModel(Map<String, String[]> map) throws Exception{
		AircraftOverview ao=new AircraftOverview();
		CommonTool.mapToBean(map, ao);
		aircraftOverviewDao.saveOrUpdate(ao);
	}

	@Override
	public void findTableModelGrid(DocumentVo vo) {
		vo.setData(aircraftOverviewDao.findAll());
		vo.setRecordsTotal(aircraftOverviewDao.count());
	}

	@Override
	public void findTableModelParamGrid(DocumentVo vo) {
		AircraftBasic ab=new AircraftBasic();
		ab.setOverviewID(vo.getOverviewID());
		List list=aircraftBasicDao.find(ab);
		vo.setRecordsTotal(aircraftBasicDao.count(ab));
		vo.setData(list);
		//vo.setData(aircraftOverviewDao.findAll());
		//vo.setRecordsTotal(aircraftOverviewDao.count());
		
	}

	/*
	 * 保存机型参数
	 * @see com.ras.documnet.DocumentService#saveModelParam(java.util.Map)
	 */
	@Override
	@Transactional
	public void saveModelParam(Map<String, String[]> map) throws Exception {
			AircraftBasic ab=new AircraftBasic();
			CommonTool.mapToBean(map, ab);
			ab.setModifyDate(new Date());
			aircraftBasicDao.saveOrUpdate(ab);
			
			AircraftCapability ac=new AircraftCapability();
			ac.setBasicID(ab.getBasicID());
			CommonTool.mapToBean(map, ac);
			aircraftCapabilityDao.saveOrUpdate(ac);
			
			AircraftWeight aw=new AircraftWeight();
			CommonTool.mapToBean(map, aw);
			//aircraftBasicDao.saveOrUpdate(ab);
	}
	
	@Override
	public Map<String,String>  addModelParamPage(DocumentVo vo) {
		return dao.addModelParamPage(vo);
	}

	@Override
	@Transactional
	public void delModelParam(String[] ids) {
		dao.delModelParam(ids);
		
	}

	@Override
	public AircraftOverview findModel(String overviewID) {
		return dao.findModel(overviewID);
	}

	@Override
	public void delModel(String[] ids) {
		dao.delModel(ids);
	}

}
