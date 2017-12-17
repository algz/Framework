package com.ras.productSearch;

import com.ras.aircraftOverview.AircraftOverview;

public interface ProductSearchDao {
	
	public void findProductGrid(ProductSearchVo<AircraftOverview> vo);
	
}
