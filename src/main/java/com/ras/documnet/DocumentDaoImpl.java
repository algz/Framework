/**
 * 
 */
package com.ras.documnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;

/**
 * @author algz
 *
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public void FindTableModelParamGrid(DocumentVo vo) {
		// TODO Auto-generated method stub
		String sql="";
		sf.getCurrentSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> addModelParamPage(DocumentVo vo) {
		String sql="";
		if(vo.getBasicID()==null){
			sql="select * from RAS_AIRCRAFT_OVERVIEW  ao where ao.id='"+vo.getOverviewID()+"'";
		}else{
			sql="select * from aircraftallparam ap where ap.basicID='"+vo.getBasicID()+"'";
		}
		return (Map<String,String>)sf.getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}

	@Override
	public void delModelParam(String[] ids) {
		for(String id:ids){
			String sql="delete from ras_aircraft_basic ab where ab.id='"+id+"'";
			sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}

		
	}

	@Override
	public AircraftOverview findModel(String overviewID){
		return (AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, overviewID);
	}

	@Override
	public void delModel(String[] ids) {
		for(String id:ids){
			String sql="delete from ras_aircraft_overview ab where ab.id='"+id+"'";
			sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
	}
	


	
}
