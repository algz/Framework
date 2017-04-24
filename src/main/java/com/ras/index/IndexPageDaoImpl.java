package com.ras.index;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IndexPageDaoImpl implements IndexPageDao {

	@Autowired
	private SessionFactory sf;
	
	public void searchIndexPage(IndexPageVo vo) {
		String sql=" from ras_aircraft_overview ao "
				+ " left join ras_aircraft_basic ab on ab.overviewid=ao.id and ab.maininfo='1' "
				+ " where lower(modelname) like '%"+vo.getModelName().toLowerCase()+"%'";
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		List list= sf.getCurrentSession().createSQLQuery("select distinct ao.id overviewid,ao.modelname,ab.aircrafttype,ab.producercountries,ab.manufacturer,ao.photourl "+sql)
				 .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				 .setFirstResult(vo.getStart())
				 .setMaxResults(vo.getLength())
				 .list();
		vo.setData(list);
	}
}
