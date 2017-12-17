package com.ras.documnet.paramConfig.productParam;

import java.util.Map;

import com.ras.aircraftProductParam.AircraftProductParam;

import net.sf.json.JSONArray;

public interface ProductParamService {

	public JSONArray getProductTreeNode(Map<String, String> m);
	
	public void findProductParamGrid(ProductParamVo<AircraftProductParam> vo);
	
	public void saveProductParam(ProductParamVo<AircraftProductParam> vo);
	
	public void delProductParam(String productParamID);
}
