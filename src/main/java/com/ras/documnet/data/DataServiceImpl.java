/**
 * 
 */
package com.ras.documnet.data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftBasic.AircraftBasicDao;
import com.ras.aircraftCapability.AircraftCapability;
import com.ras.aircraftCapability.AircraftCapabilityDao;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;
import com.ras.aircraftPhoto.AircraftPhoto;
import com.ras.aircraftPhoto.AircraftPhotoDao;
import com.ras.aircraftWeight.AircraftWeight;
import com.ras.search.SearchTag;
import com.ras.search.SearchTagDao;
import com.ras.tool.CommonTool;
import com.ras.tool.file.UploadFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author algz
 *
 */
@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private SearchTagDao searchTagDao;
	
	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;
	
	@Autowired
	private AircraftBasicDao aircraftBasicDao;
	
	@Autowired
	private AircraftCapabilityDao aircraftCapabilityDao;
	
	@Autowired
	private AircraftPhotoDao aircraftPhotoDao;
	
	@Autowired
	private DataDao dao;
	
	/**
	 * 保存 机型概述
	 * @throws Exception 
	 * @see com.ras.documnet.data.DataService#saveModel(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public void saveModel(Map<String, String> map) throws Exception{
		AircraftOverview ao=new AircraftOverview();
		CommonTool.mapToBean(map, ao);
		aircraftOverviewDao.saveOrUpdate(ao);
	}

	@Override
	public void findTableModelGrid(DataVo vo) {
		AircraftOverview ao=new AircraftOverview();
		ao.setModelName(vo.getModelName());
		vo.setData(aircraftOverviewDao.findByProperty(ao));
		vo.setRecordsTotal(aircraftOverviewDao.count(ao));
	}

	@Override
	public void findTableModelParamGrid(DataVo vo) {
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
	public void saveModelParam(Map<String, String> map) throws Exception {

		boolean isCreate=false;
		String basicID=map.get("basicID");

		
		if(basicID==null||basicID.toString().equals("")){
			isCreate=true;
			BigDecimal count=aircraftBasicDao.countMaininfo(map.get("overviewID"));
			map.put("MAININFO", count.compareTo(BigDecimal.ZERO)!=0?null:"1");
			dao.saveModelParam("BASIC", map,isCreate);
			map.put("basicID", map.get("ID"));
		}else{
			dao.saveModelParam("BASIC", map,isCreate);
		}
		dao.saveModelParam("WEIGHT", map,isCreate);
		dao.saveModelParam("LAYOUT", map,isCreate);
		dao.saveModelParam("CAPABILITY", map,isCreate);
		dao.saveModelParam("SYSTEM", map,isCreate);

	}
	
	private boolean validateTable(String tableID,List<SearchTag> allTags,Map<String, String> map){
		for (SearchTag tag : allTags) {
			if (tableID.equals(tag.getParent_id())&&map.containsKey(tag.getEnname())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public JSONObject addModelParamPage(DataVo vo) {
		return dao.findModelParam(vo);
	}

	@Override
	@Transactional
	public void delModelParam(String[] ids,String overviewID) {
		dao.delModelParam(ids,overviewID);
		
	}

	@Override
	public AircraftOverview findModel(String overviewID) {
		return dao.findModel(overviewID);
	}

	@Override
	@Transactional
	public void delModel(String[] ids) {
		dao.delModel(ids);
	}

	@Override
	@Transactional
	public void setMainModelParam(String basicID,String overviewID) {
		dao.setMainModelParam(basicID,overviewID);
	}
	
	/**
	 * 查找图片,按类别.
	 */
	@Override
	public JSONArray findModelImageParam(String photoCategory,String basicID) {
		AircraftPhoto photo=new AircraftPhoto();
		photo.setPhotoCategory(photoCategory);
		photo.setBasicID(basicID);
		List<AircraftPhoto> photoList=aircraftPhotoDao.find(photo);
		return CommonTool.arrayToJSONArray(photoList,true);
	}

	/**
	 * 保存图片
	 */
	@Transactional
	@Override
	public void saveImageFile(AircraftPhoto photo) {
		aircraftPhotoDao.saveOrUpdate(photo);		
	}

	@Transactional
	@Override
	public void delImageFile(String photoID) {
		aircraftPhotoDao.del(photoID);
	}



}
