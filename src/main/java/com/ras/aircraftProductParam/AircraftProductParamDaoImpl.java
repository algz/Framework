/**
 * 
 */
package com.ras.aircraftProductParam;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftCapability.AircraftCapability;
import com.ras.aircraftTag.AircraftTag;
import com.ras.aircraftTag.AircraftTagDao;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;
import javafx.util.Callback;

/**
 * @author algz
 *
 */
@Repository
public class AircraftProductParamDaoImpl implements AircraftProductParamDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public List<AircraftProductParam> findAllLeaf(AircraftProductParam ap,Integer start,Integer length) {
		StringBuilder sql=new StringBuilder("select distinct * from RAS_AIRCRAFT_PRODUCT_PARAM ap where isleaf='1'  ");

		return CommonTool.findEntitiesByProperty(sf, sql, ap, start, length, null);
	}

	@Override
	public Integer count(AircraftProductParam example) {
		StringBuilder sql=new StringBuilder("select count(1) from RAS_AIRCRAFT_PRODUCT_PARAM ap where 1=1  ");

		return CommonTool.countEntitiesByProperty(sf, example).intValue();
	}
	
	@Override
	public void saveOrUpdate(AircraftProductParam ao) {
		ao.setEditor(Common.getLoginUser().getUserid());
		if (ao.getProductID()==null||ao.getProductID().equals("")) {
			sf.getCurrentSession().save(ao);
		} else {
			CommonTool.updateSingleEntityForProperty(sf, ao);
		}
	}

	@Override
	public List<AircraftProductParam> findByProperty(AircraftProductParam ap,Integer start,Integer length) {
		StringBuilder sql=new StringBuilder("select distinct * from RAS_AIRCRAFT_PRODUCT_PARAM ap where 1=1  ");

		return CommonTool.findEntitiesByProperty(sf, sql, ap, start, length, null);
	}

	@Override
	public List<AircraftProductParam> findChilren(String productID) {
		String sql="select * from RAS_AIRCRAFT_PRODUCT_PARAM t where t.isLeaf='0'  start with t.parentid='"+productID+"' connect by  t.parentid= prior t.id ";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftProductParam.class).list();
	}

	@Override
	public List<Object[]> findChilParamValue(String productID,String overviewID) {
		String sql="select t.id productID,t.productname,t.parentid,i.id informID,i.paramvalue "
				+ " from RAS_AIRCRAFT_PRODUCT_PARAM t "
				+ "left join RAS_AIRCRAFT_PRODUCT_INFORM i on t.id=i.paramid and i.overviewid='"+overviewID+"'"
				+ "start with t.parentid='"+productID+"' connect by  t.parentid= prior t.id";
		return sf.getCurrentSession().createSQLQuery(sql).list();
	}

	@Override
	public void del(String productID) {
		String sql="delete RAS_AIRCRAFT_PRODUCT_PARAM where id in (select t.id from RAS_AIRCRAFT_PRODUCT_PARAM t start with t.id='"+productID+"' connect by prior t.id=t.parentid)";
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}



}
