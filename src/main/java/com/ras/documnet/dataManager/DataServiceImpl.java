/**
 * 
 */
package com.ras.documnet.dataManager;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftArchive.AircraftArchive;
import com.ras.aircraftArchive.AircraftArchiveDao;
import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftBasic.AircraftBasicDao;
import com.ras.aircraftCapability.AircraftCapability;
import com.ras.aircraftCapability.AircraftCapabilityDao;
import com.ras.aircraftDynamic.AircraftDynamic;
import com.ras.aircraftDynamic.AircraftDynamicDao;
import com.ras.aircraftLayout.AircraftLayout;
import com.ras.aircraftLayout.AircraftLayoutDao;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;
import com.ras.aircraftPicture.AircraftPicture;
import com.ras.aircraftPicture.AircraftPictureDao;
import com.ras.aircraftSystem.AircraftSystem;
import com.ras.aircraftSystem.AircraftSystemDao;
import com.ras.aircraftTag.AircraftTag;
import com.ras.aircraftTag.AircraftTagDao;
import com.ras.aircraftWeight.AircraftWeight;
import com.ras.searchParam.SearchParamDao;
import com.ras.searchParam.SearchParamService;
import com.ras.searchParam.SearchParam;
import com.ras.tool.CommonTool;
import com.ras.tool.file.UploadFile;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserService;
import algz.platform.util.Common;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author algz
 *
 */
@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private SearchParamService searchTagService;
	
	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;
	
	@Autowired
	private AircraftBasicDao aircraftBasicDao;
	
	@Autowired
	private AircraftCapabilityDao aircraftCapabilityDao;
	
	@Autowired
	private AircraftDynamicDao aircraftDynamicDao;
	
	@Autowired
	private AircraftLayoutDao aircraftLayoutDao;
	
	@Autowired
	private AircraftSystemDao aircraftSystemDao;
	
	@Autowired
	private AircraftPictureDao aircraftPictureDao;
	
	@Autowired
	private AircraftArchiveDao aircraftArchiveDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AircraftTagDao aircraftTagDao;
	
	@Autowired
	private DataDao dao;
	
	/**
	 * 保存 机型概述
	 * @throws Exception 
	 * @see com.ras.documnet.dataManager.DataService#saveModel(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public void saveModel(Map<String, String> map) throws Exception{
		AircraftOverview ao=new AircraftOverview();
		CommonTool.mapToBean(map, ao);
		ao.setPermissionLevel("1"); //所有新建的机型默认为个人.
		ao.setParentID("0"); //所有主机型默认为0
		aircraftOverviewDao.saveOrUpdate(ao);
		
		//添加备用标签
		AircraftTag tag=new AircraftTag();
		tag.setTagName(ao.getTag());
		tag.setRelationTable("RAS_AIRCRAFT_OVERVIEW");
		tag.setRelationID(ao.getOverviewID());
		aircraftTagDao.saveOrUpdate(tag);
	}

	@Override
	public void findTableModelGrid(DataVo vo) {
		dao.findTableModelGrid(vo);
//		if(vo.getData()!=null){
//			for(Object obj:vo.getData()){
//				AircraftOverview ao=(AircraftOverview)obj;
//				User user=userService.findOne(ao.getEditor());
//				ao.setEditor(user.getCname()==null?user.getUsername():user.getCname());
//			}
//		}
		
//		AircraftOverview ao=new AircraftOverview();
//		ao.setModelName(vo.getModelName());
//		vo.setData(aircraftOverviewDao.findByProperty(ao));
//		vo.setRecordsTotal(aircraftOverviewDao.count(ao));
	}

	@Override
	public void findTableModelParamGrid(DataVo<AircraftBasic> vo) {
		dao.findTableModelParamGrid(vo);
		if(vo.getData()!=null){
			for(AircraftBasic basic:vo.getData()){
				User user=userService.findOne(basic.getEditor());
				basic.setEditor(user.getCname()==null?user.getUsername():user.getCname());
			}
		}
	}

	/**
	 * 保存机型参数
	 * 
	 * @param map org.apache.commons.collections.map.CaseInsensitiveMap 忽略KEY大小写Map
	 */
	@Override
	@Transactional
	public void saveModelParam(CaseInsensitiveMap map) throws Exception {

		boolean isCreate=false;
		String basicID=map.get("basicID")==null?null:map.get("basicID").toString();

		if(basicID==null||basicID.toString().equals("")){
			isCreate=true;
			BigDecimal count=aircraftBasicDao.countMaininfo(map.get("overviewID").toString());
			map.put("MAININFO", count.compareTo(BigDecimal.ZERO)!=0?null:"1");
			dao.saveModelParam("BASIC", map,isCreate); //map的KEY必须都大写
			map.put("basicID", map.get("ID"));
		}else{
			dao.saveModelParam("BASIC", map,isCreate);
		}
		dao.saveModelParam("WEIGHT", map,isCreate);
		dao.saveModelParam("LAYOUT", map,isCreate);
		dao.saveModelParam("CAPABILITY", map,isCreate);
		dao.saveModelParam("SYSTEM", map,isCreate);
		dao.saveModelParam("DYNAMIC", map,isCreate);
		dao.saveModelParam("OTHER", map,isCreate);

	}
	
	private boolean validateTable(String tableID,List<SearchParam> allTags,Map<String, String> map){
		for (SearchParam tag : allTags) {
			if (tableID.equals(tag.getParent_id())&&map.containsKey(tag.getEnname())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public JSONObject addModelParamPage(DataVo vo) {
		JSONObject jo=null;
		switch(vo.getOption()){
		case "load":
//			AircraftBasic ab=aircraftBasicDao.getMainAircraftBasic(vo.getOverviewID());
//			basicID=(ab==null?"":ab.getBasicID());
			jo=dao.loadModelParam(vo);
			return jo;
		case "create":
		case "modify":
			jo=dao.findModelParam(vo);

			Map<String, List<String>> m=searchTagService.findAllCheckboxTypeList();
			for(Object obj:(JSONArray)jo.get("paramMap")){
				for(Object obj1:(JSONArray)((JSONObject)obj).get("dataMap")){
					JSONObject el=(JSONObject)obj1;
					if(el.get("elType")!=null&&el.getString("elType").equals("checkbox")){
						String s=StringUtils.join((m.get(el.get("elTypeValue"))).toArray(),",");
						el.put("checkboxVal", s);
						//System.out.println(StringUtils.join(((List)el.get("checkboxVal")).toArray(),","));
					}
				}
			}
		break;
		}
		return jo;
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
	public JSONArray findModelImageParam(String photoCategory,String overviewID) {
		AircraftPicture photo=new AircraftPicture();
		photo.setPhotoCategory(photoCategory);
		photo.setOverviewID(overviewID);
		List<AircraftPicture> photoList=aircraftPictureDao.find(photo);
		return CommonTool.arrayToJSONArray(photoList,true);
	}

	/**
	 * 保存图片
	 */
	@Transactional
	@Override
	public void saveModelParamPhotoFile(AircraftPicture photo) {
		aircraftPictureDao.saveOrUpdate(photo);		
		
		//添加备用标签
		AircraftTag tag=new AircraftTag();
		tag.setTagName(photo.getTag());
		tag.setRelationTable("RAS_AIRCRAFT_PICTURE");
		tag.setRelationID(photo.getPhotoID());
		aircraftTagDao.saveOrUpdate(tag);
	}

	@Transactional
	@Override
	public void delPictureFile(String photoIDS) {
		for(String photoID:photoIDS.split(",")){
			aircraftPictureDao.del(photoID);
			
			//删除标签
			aircraftTagDao.del(null, photoID, "RAS_AIRCRAFT_PICTURE");
		}
	}

	@Transactional
	@Override
	public void saveModelPhotoFile(AircraftOverview ao){
		dao.saveModelPhotoFile(ao);
	}

	@Transactional
	@Override
	public void delModelImageFile(String overviewID) {
		dao.delModelImageFile(overviewID);
	}

	@Override
	public List findTableSQL(String tableName) {
		return dao.findTableSQL(tableName);
	}

	/**
	 * 保存文档
	 */
	@Transactional
	@Override
	public void saveModelParamArchiveFile(AircraftArchive archive) {
		aircraftArchiveDao.saveOrUpdate(archive);	
		
		//添加备用标签
		AircraftTag tag=new AircraftTag();
		tag.setTagName(archive.getTag());
		tag.setRelationTable("RAS_AIRCRAFT_ARCHIVE");
		tag.setRelationID(archive.getArchiveID());
		aircraftTagDao.saveOrUpdate(tag);
	}

	/**
	 * 删除文档
	 */
	@Transactional
	@Override
	public void delArichiveFile(String archiveIDs) {
		String[] ids=archiveIDs.split(",");
		for(String archiveID:ids){
			aircraftArchiveDao.del(archiveID);
			
			//删除标签
			aircraftTagDao.del(null, archiveID, "RAS_AIRCRAFT_ARCHIVE");
		}

	}

	@Override
	public void findArchiveGrid(DataVo vo) {
		AircraftArchive archive=new AircraftArchive();
		archive.setOverviewID(vo.getOverviewID());
		if(vo.getTag()!=null&&!vo.getTag().equals("")){
			archive.setTag(vo.getTag());
		}
		vo.setRecordsTotal(aircraftArchiveDao.count(archive));
		vo.setData(aircraftArchiveDao.find(archive, vo.getStart(),vo.getLength()));
		
		if(vo.getData()!=null){
			for(Object obj:vo.getData()){
				AircraftArchive ao=(AircraftArchive)obj;
				User user=userService.findOne(ao.getEditor());
				ao.setEditor(user.getCname()==null?user.getUsername():user.getCname());
			}
		}
	}

	@Override
	public AircraftArchive downloadArchiveFile(String archiveID) {
		return aircraftArchiveDao.find(archiveID);
	}

	@Override
	public void findPictureGrid(DataVo vo) {
		AircraftPicture photo=new AircraftPicture();
		photo.setPhotoCategory(vo.getPhotoCategory());
		photo.setOverviewID(vo.getOverviewID());
		photo.setTag(vo.getTag());
		vo.setRecordsTotal(aircraftPictureDao.count(photo).intValue());
		vo.setData(aircraftPictureDao.find(photo));
		
		if(vo.getData()!=null){
			for(Object obj:vo.getData()){
				AircraftPicture ao=(AircraftPicture)obj;
				User user=userService.findOne(ao.getEditor());
				ao.setEditor(user.getCname()==null?user.getUsername():user.getCname());
			}
		}
	}

	@Override
	public List<?> findCategoryNameForTypeahead(String categoryName) {
		return dao.findCategoryNameForTypeahead(categoryName);
	}

	@Transactional
	@Override
	public void saveSubModel(String modelName, String parentID) {
		AircraftOverview ao=new AircraftOverview();
		ao.setOverviewID(parentID);
		List<AircraftOverview> list=aircraftOverviewDao.findByProperty(ao, null, null);
		
		ao=new AircraftOverview();
		if(list.size()==1){
			try {
				ao=CommonTool.copyObject(list.get(0));
//				BeanUtils.copyProperties(ao, list.get(0));
				
				ao.setOverviewID("");
//				ao.setAircraftBasicSet(null);
//				ao.setPrimaryAircraftBasicSet(null);
				ao.setModelName(modelName);
//				ao.setModelCname("");
//				ao.setModelEname("");
				ao.setCategory(list.get(0).getCategory());
				ao.setTag(list.get(0).getTag());
				ao.setPermissionLevel("1"); //所有新建的机型默认为个人.
				ao.setParentID(parentID);
				ao.setAircraftBasicSet(null);
				ao.setPrimaryAircraftBasicSet(null);
				aircraftOverviewDao.saveOrUpdate(ao);
				for(AircraftBasic tem:list.get(0).getAircraftBasicSet()){
					
					if(tem.getMainInfo()!=null&&tem.getMainInfo().equals("1")){
						//基本信息
						AircraftBasic ab=new AircraftBasic();
						ab.setBasicID(tem.getBasicID());
						ab=dao.copyObject(ab);
//						ab=aircraftBasicDao.copy(ab);
						ab.setBasicID("");
						ab.setOverviewID(ao.getOverviewID());
						ab.setEditor(Common.getLoginUser().getUserid());
						aircraftBasicDao.saveOrUpdate(ab);
						
						//性能信息
						AircraftCapability ac=new AircraftCapability();
						ac.setBasicID(tem.getBasicID());
						ac=dao.copyObject(ac);
//						ac=aircraftCapabilityDao.copy(ac);
						ac.setCapabilityID("");
						ac.setBasicID(ab.getBasicID());
						aircraftCapabilityDao.saveOrUpdate(ac);
						
						//动力信息
						AircraftDynamic ad=new AircraftDynamic();
						ad.setBasicID(tem.getBasicID());
						ad=dao.copyObject(ad);
						ad.setDynamicID("");
						ad.setBasicID(ab.getBasicID());
						aircraftDynamicDao.saveOrUpdate(ad);
						
						//布局信息
						AircraftLayout al=new AircraftLayout();
						al.setBasicID(tem.getBasicID());
						al=dao.copyObject(al);
						al.setLayoutID("");
						al.setBasicID(ab.getBasicID());
						aircraftLayoutDao.saveOrUpdate(al);
						
						//系统信息
						AircraftSystem as=new AircraftSystem();
						as.setBasicID(tem.getBasicID());
						as=dao.copyObject(as);
						as.setSystemID("");
						as.setBasicID(ab.getBasicID());
						aircraftSystemDao.saveOrUpdate(as);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			


		}else{
			return;
		}

		
		//添加备用标签
		AircraftTag tag=new AircraftTag();
		tag.setTagName(ao.getTag());
		tag.setRelationTable("RAS_AIRCRAFT_OVERVIEW");
		tag.setRelationID(ao.getOverviewID());
		aircraftTagDao.saveOrUpdate(tag);
		
	}

	@Override
	public JSONArray findAircraftOverviewPrivilidgeUser(String overviewID) {
		List<User> list=dao.findAircraftOverviewPrivilidgeUser(overviewID);
		JSONArray ja=new JSONArray();
		for(User u:list){
			JSONObject jo=new JSONObject();
			jo.put("userid", u.getUserid());
			jo.put("username", u.getUsername());
			jo.put("usercname", u.getCname());
			ja.add(jo);
		}
		return ja;
	}



}
