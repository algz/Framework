package com.ras.productSearch;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;

@Repository
public class ProductSearchDaoImpl implements ProductSearchDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void findProductGrid(ProductSearchVo<AircraftOverview> vo) {
		String sql="from RAS_AIRCRAFT_OVERVIEW AO "
				+ "INNER JOIN Ras_Aircraft_Product_Inform API "
				+ "ON AO.ID=API.OVERVIEWID AND API.PARAMID='"+vo.getParamID()+"' AND API.PARAMVALUE like '%"+vo.getParamValue()+"%'";
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		
		List<AircraftOverview> list=sf.getCurrentSession().createSQLQuery("select AO.* "+sql)
									  .addEntity(AircraftOverview.class)
									  .setFirstResult(vo.getStart())
									  .setMaxResults(vo.getLength()).list();
		vo.setData(list);
	}

}
