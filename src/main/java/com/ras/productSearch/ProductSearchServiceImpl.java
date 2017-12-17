package com.ras.productSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftProductParam.AircraftProductParam;
import com.ras.aircraftProductParam.AircraftProductParamDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ProductSearchServiceImpl implements ProductSearchService{

	@Autowired
	private ProductSearchDao dao;
	
	@Autowired
	private AircraftProductParamDao aircraftProductParamDao;
	
	@Override
	public void findProductGrid(ProductSearchVo<AircraftOverview> vo) {
		dao.findProductGrid(vo);
	}

	@Override
	public JSONArray findProductCombox() {
		JSONArray ja=new JSONArray();
		List<AircraftProductParam> list= aircraftProductParamDao.findByProperty(new AircraftProductParam(), null, null);
		for(AircraftProductParam app : list){
			if(app.getParentID().equals("0")){
				JSONObject jo=new JSONObject();
				jo.put("productID", app.getProductID());
				jo.put("productName", app.getProductName());
				JSONArray jaChildren=new JSONArray();
				for(AircraftProductParam tem : list){
					if(app.getProductID().equals(tem.getParentID())){
						JSONObject joChild=new JSONObject();
						joChild.put("productID", tem.getProductID());
						joChild.put("productName", tem.getProductName());
						jaChildren.add(joChild);
					}
				}
				jo.put("childrenProducts", jaChildren);
				ja.add(jo);
			}
		}
		return ja;
	}
	
}
