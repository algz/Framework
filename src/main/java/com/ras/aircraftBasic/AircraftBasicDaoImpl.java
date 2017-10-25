/**
 * 
 */
package com.ras.aircraftBasic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.id.GUIDGenerator;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftCapability.AircraftCapability;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;

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
		String overviewID=m.get("overviewID");
		if(basicID==null||basicID.toString().equals("")){
			//新建
			param.append("INSERT INTO RAS_AIRCRAFT_BASIC ");
			sql="select rawtohex(sys_guid()) from dual";
			String idVal=(String)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
			
			StringBuilder c=new StringBuilder("id,MAININFO");
			//maininfo字段
			sql="select count(1) from RAS_AIRCRAFT_BASIC t where t.overviewid='"+overviewID+"' and t.maininfo is not null";
			BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
			StringBuilder v=new StringBuilder("'"+idVal+"',"+(count.compareTo(BigDecimal.ZERO)!=0?"null":"'1'"));
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
			//修改
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
		StringBuilder hql=new StringBuilder("from AircraftBasic where 1=1 ");
		if(ab.getOverviewID()!=null){
			hql.append(" and overviewID='"+ab.getOverviewID()+"'");
		}
		
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			hql.append(" and (editor='"+curUser.getUserid()+"' or permissionLevel in ('2','3'))");
		}
		
		return sf.getCurrentSession().createQuery(hql.toString()).list();
//		Query query= sf.getCurrentSession().createQuery(hql.toString());
//		return query.setProperties(ab).list();//.setEntity("ab", ab).list();
	}

	@Override
	public BigDecimal countMaininfo(String overviewID) {
		String sql="select count(1) from RAS_AIRCRAFT_BASIC t where t.overviewid='"+overviewID+"' and t.maininfo is not null";
		return (BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
	}

	public void setMaininfo(String basicID,String overviewID) {
		String prefix=" update ras_aircraft_basic ab set ";
		//所有数据置空.
		sf.getCurrentSession().createSQLQuery(prefix+"maininfo=''"+" where ab.overviewid='"+overviewID+"'").executeUpdate();
		//设置.
		sf.getCurrentSession().createSQLQuery(prefix+"maininfo='1'"+" where ab.id='"+basicID+"'").executeUpdate();
	}
	
	public AircraftBasic getMainAircraftBasic(String overviewID){
		String sql="select * from ras_aircraft_basic ab where ab.maininfo='1' and ab.overviewid='"+overviewID+"'";
//		
//		if(!CommonTool.isDataManager()){
//			User curUser=Common.getLoginUser();
//			sql+=" and (ab.editor='"+curUser.getUserid()+"' or ao.PERMISSION_LEVEL in ('2','3'))";
//		}
		
		return (AircraftBasic)sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftBasic.class).setMaxResults(1).uniqueResult();
	}

	@Override
	public AircraftBasic copy(AircraftBasic example) {
		String tableName=example.getClass().getAnnotation(Table.class).name();
		StringBuilder sql=new StringBuilder("select distinct * from "+tableName+" where 1=1 ");
		List<AircraftBasic> list=CommonTool.<AircraftBasic>findEntitiesByProperty(sf, sql, example, 0, 1, null);
		if(list.size()!=0){
			AircraftBasic ac=new AircraftBasic();
			try {
				BeanUtils.copyProperties(ac, list.get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return ac;
		}else{
			return null;
		}
	}
}
