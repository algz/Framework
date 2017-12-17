package com.ras.productSearch;

import java.util.List;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftProductParam.AircraftProductParam;

import net.sf.json.JSONArray;

public interface ProductSearchService {
	
	public JSONArray findProductCombox();
	
	public void findProductGrid(ProductSearchVo<AircraftOverview> vo);
}
