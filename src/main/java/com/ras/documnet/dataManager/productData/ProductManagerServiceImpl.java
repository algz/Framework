package com.ras.documnet.dataManager.productData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftProductInform.AircraftProductInform;
import com.ras.aircraftProductInform.AircraftProductInformDao;
import com.ras.aircraftProductParam.AircraftProductParam;
import com.ras.aircraftProductParam.AircraftProductParamDao;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ProductManagerServiceImpl implements ProductManagerService {

	@Autowired
	private AircraftProductParamDao aircraftProductParamDao;
	
	@Autowired
	private AircraftProductInformDao aircraftProductInformDao;
	
	@Override
	public JSONArray findProductInform(String overviewID) {
		JSONArray ja=new JSONArray();

//		AircraftProductParam ap=new AircraftProductParam();
//		ap.setParentID("0");
		List<Object[]> allProductInform=aircraftProductParamDao.findChilParamValue("0",overviewID);
		if(allProductInform.size()!=0){
			for(Object[] objs:allProductInform){
				//t.id productID,t.productname,t.parentid,i.id informID,i.paramvalue
				if(objs[2].toString().equals("0")){
					ja.add(getChildParam(objs,allProductInform));
				}
			}
		}
		
		return ja;
	}
	
	private JSONObject getChildParam(Object[] objs,List<Object[]> childProduct){
		JSONObject jo=new JSONObject();
		jo.put("productID",objs[0]); //标签ID
		jo.put("text",objs[1]); //标签名
		jo.put("val", objs[4]);
		
		JSONArray ja=new JSONArray();
		for(Object[] tobjs:childProduct){
			//t.id productID,t.productname,t.parentid,i.id informID,i.paramvalue
			if(tobjs[2].toString().equals(objs[0].toString())){
				ja.add(getChildParam(tobjs,childProduct));
			}
		}
		jo.put("childParams", ja); //子标签
		
		return jo;
	}
	
	@Transactional
	@Override
	public void saveProductInform(Map<String,String[]> m) {
		String overviewID=m.get("overviewID")[0];
		for(String s:m.keySet()){
			if(s.substring(0, 2).equals("pv")&&m.get(s).length!=0&&!m.get(s)[0].equals("")){
				AircraftProductInform api=new AircraftProductInform();
				api.setOverviewID(overviewID);
				api.setParamID(s.substring(2));
				api.setParamValue(m.get(s)[0]);
				aircraftProductInformDao.saveOrUpdate(api);
			}

		}
		
	}
	
}

