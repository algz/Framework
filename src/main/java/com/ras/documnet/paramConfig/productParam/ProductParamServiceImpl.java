package com.ras.documnet.paramConfig.productParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftProductParam.AircraftProductParam;
import com.ras.aircraftProductParam.AircraftProductParamDao;
import com.ras.tool.CommonTool;

import algz.platform.util.Common;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ProductParamServiceImpl implements ProductParamService{

	@Autowired
	private AircraftProductParamDao aircraftProductParamDao;
	
	@Override
	public JSONArray getProductTreeNode(Map<String, String> m) {
		JSONArray ja=new JSONArray();

		AircraftProductParam ap=new AircraftProductParam();
		ap.setParentID("0");
		List<AircraftProductParam> rootList=aircraftProductParamDao.findByProperty(ap, null, null);
		
		for(AircraftProductParam root:rootList){
			List<AircraftProductParam> childProduct=aircraftProductParamDao.findChilren(root.getProductID());
			JSONObject jo=new JSONObject();
			jo.put("nodes", getChildNode(childProduct));
			jo.put("productID",root.getProductID());
			jo.put("text",root.getProductName());
			ja.add(jo);
		}

		return ja;
	}
	
	private JSONArray getChildNode(List<AircraftProductParam> list){
		JSONArray ja=new JSONArray();
		for(AircraftProductParam ap:list){
			JSONObject jo=new JSONObject();
			jo.put("text",ap.getProductName());
//			jo.put("isLeaf", ap.getIsLeaf());
			jo.put("productID",ap.getProductID());
			List<AircraftProductParam> childProduct=aircraftProductParamDao.findChilren(ap.getProductID());
			if(childProduct.size()!=0){
				jo.put("nodes", getChildNode(childProduct));
			}else{
				ja.add(jo);
			}
		}
		return ja;
	}

	@Override
	public void findProductParamGrid(ProductParamVo<AircraftProductParam> vo) {
		AircraftProductParam app=new AircraftProductParam();
		app.setParentID(vo.getProductID());
		vo.setData(aircraftProductParamDao.findAllLeaf(app, vo.getStart(), vo.getLength()));
		vo.setRecordsTotal(aircraftProductParamDao.count(app));
		
	}

	@Transactional
	@Override
	public void saveProductParam(ProductParamVo<AircraftProductParam> vo) {
		AircraftProductParam app=new AircraftProductParam();
		if(vo.getProductID()!=null){
			app.setProductID(vo.getProductID());
		}
		if(vo.getProductName()!=null){
			app.setProductName(vo.getProductName());
		}
		if(vo.getIsLeaf()!=null){
			app.setIsLeaf(vo.getIsLeaf());
		}
		if(vo.getParamName()!=null){
			app.setParamName(vo.getParamName());
		}
		if(vo.getUi_type()!=null){
			app.setUi_type(vo.getUi_type());
		}
		if(vo.getParentID()!=null){
			app.setParentID(vo.getParentID());
		}
		if(vo.getSequence()!=null){
			app.setSequence(vo.getSequence());
		}
		aircraftProductParamDao.saveOrUpdate(app);
		vo.setProductID(app.getProductID());
	}

	@Transactional
	@Override
	public void delProductParam(String productParamID) {
		aircraftProductParamDao.del(productParamID);
	}
}
