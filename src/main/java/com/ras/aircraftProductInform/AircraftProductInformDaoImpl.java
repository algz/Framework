package com.ras.aircraftProductInform;

import java.math.BigDecimal;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.tool.CommonTool;

@Repository
public class AircraftProductInformDaoImpl implements AircraftProductInformDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void saveOrUpdate(AircraftProductInform api) {
		AircraftProductInform tem=new AircraftProductInform();
		tem.setOverviewID(api.getOverviewID());
		tem.setParamID(api.getParamID());
		BigDecimal count=CommonTool.countEntitiesByProperty(sf, tem);
		if(count.intValue()==0){
			sf.getCurrentSession().save(api);
		}else{
			String sql=" update Ras_Aircraft_Product_Inform t set t.paramvalue='"+api.getParamValue()+"' "
					+ "where t.paramid='"+api.getParamID()+"' and t.overviewid='"+api.getOverviewID()+"'";
			sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}

	}

}
