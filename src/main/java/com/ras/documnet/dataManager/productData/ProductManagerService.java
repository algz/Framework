package com.ras.documnet.dataManager.productData;

import java.util.Map;

import net.sf.json.JSONArray;

public interface ProductManagerService {
	
	public JSONArray findProductInform(String overviewID);
	
	public void saveProductInform(Map<String,String[]> m);
	
	
}
