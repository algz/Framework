/**
 * 
 */
package com.ras.aircraftBasic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.id.GUIDGenerator;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author algz
 *
 */
@Repository
public class AircraftBasicDaoImpl implements AircraftBasicDao {

	@Autowired
	private SessionFactory sf;
	
	/* (non-Javadoc)
	 * @see com.ras.aircraftBasic.AircraftBasicDao#findAll()
	 */
	@Override
	public List<AircraftBasic> findAll() {
		String sql="select * from ras_aircraft_basic";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftBasic.class).list();
	}

	@Override
	public Integer count() {
		String sql="select count(1) from ras_aircraft_basic";
		return Integer.parseInt(sf.getCurrentSession().createSQLQuery(sql).uniqueResult()+"");
	}

	public Integer count(AircraftBasic ab){
		String hql="select count(1) from AircraftBasic where overviewID=:overviewID";
		return Integer.parseInt(sf.getCurrentSession().createQuery(hql)
				      .setProperties(ab).uniqueResult()+"");
	}
	
	@Override
	public void saveOrUpdate(AircraftBasic ab) {
		if(ab.getBasicID().equals("")){
			sf.getCurrentSession().save(ab);
		}else{
			sf.getCurrentSession().update(ab);
		}
	}

	public void saveOrUpdateMap(Map<String, String> m) {
		String sql="select col.COLUMN_NAME,col.DATA_TYPE,col.DATA_LENGTH from user_tab_columns col where Table_Name='RAS_AIRCRAFT_BASIC'";
		List<Object[]> cols=sf.getCurrentSession().createSQLQuery(sql).list();
		StringBuilder param=new StringBuilder();

		String basicID=m.get("basicID");
		if(basicID==null||basicID.toString().equals("")){
			//保存
			param.append("INSERT INTO RAS_AIRCRAFT_BASIC ");
			sql="select rawtohex(sys_guid()) from dual";
			String idVal=(String)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
			
			StringBuilder c=new StringBuilder("id,dataSources,");
			StringBuilder v=new StringBuilder("'"+idVal+"',"+"'"+m.get("datasources")+"'");
			for(int i=0;i<cols.size();i++){
				String col=cols.get(i)[0].toString();
				String val=m.get(col);
				if(val==null||val.equals("")){
					continue;
				}
				switch(cols.get(i)[1].toString()){
				case "VARCHAR2":
					val="'"+val+"'";
					break;
				case "NUMBER":
					if(val.equals("")){
						continue;
					}
					break;
				case "DATE":
					break;
				}
				
				c.append(","+col);
				v.append(","+val);
			}

			param.append("("+c.toString()+") values ("+v.toString()+")");
			sf.getCurrentSession().createSQLQuery(param.toString()).executeUpdate();
		}else{
			param.append("update RAS_AIRCRAFT_BASIC ab set ");
			boolean flag=false;
			for(int i=0;i<cols.size();i++){
				String col=cols.get(i)[0].toString();
				String val=m.get(col);
				if(val==null){
					continue;
				}

				switch(cols.get(i)[1].toString()){
				case "VARCHAR2":
					val="'"+val+"'";
					break;
				case "NUMBER":
					if(val.equals("")){
						continue;
					}
					break;
				case "DATE":
					break;
				}
				
				if(flag){
					param.append(",");
				}
				param.append(col+"="+val+"");
				flag=true;
			}
			param.append(" where id='"+basicID+"' ");
			sf.getCurrentSession().createSQLQuery(param.toString()).executeUpdate();
		}
	}
	
	@Override
	public List<AircraftBasic> find(AircraftBasic ab) {
		String hql="from AircraftBasic where overviewID=:overviewID";
		Query query= sf.getCurrentSession().createQuery(hql);
		List list= query.setProperties(ab).list();//.setEntity("ab", ab).list();
		return list;
	}

	
}
