/**
 * 
 */
package com.ras.documnet.paramConfig.dataParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.searchParam.SearchParam;

import algz.platform.util.sql.Column;
import algz.platform.util.sql.SQLUtil;

/**
 * @author algz
 *
 */
@Repository
public class ParamConfigDaoImpl implements ParamConfigDao {

	@Autowired
	private SessionFactory sf;
	
	/**
	 * 增加标签
	 */
	@Override
	public String addTag(SearchParam tag) {
		
		//增加数据库字段
		String tableName="RAS_AIRCRAFT_"+(String)sf.getCurrentSession().createSQLQuery("select t.enname from RAS_SEARCH_PARAM t where t.id='"+tag.getParent_id()+"'").uniqueResult();
		String columnName=tag.getName();
		String sql="select count(1) from user_tab_columns where table_name='"+tableName+"' and lower(column_name)='"+columnName.toLowerCase()+"'";
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
		
		if(count.intValue()==0){
			sql="ALTER TABLE "+tableName+" ADD ( "+columnName;
			switch(tag.getUi_type()){
			case "checkbox":
			case "select":
			case "textArea":
			case "text":
				//文本
				sql+=" VARCHAR2("+(tag.getUi_value().equals("")?"32":tag.getUi_value())+") NULL  )";
				break;
			case "number":
			case "numberRegion":
				//数值
				sql+=" NUMBER("+(tag.getUi_value().equals("")?"10":tag.getUi_value())+") NULL  )";
				break;
			}
			sf.getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();	
			
			tag.setOnlyRead("0");
			sf.getCurrentSession().save(tag);
			
			return null;
		}else{
			return "列名已存在";
		}

	}
	
	/**
	 * 修改数据标签
	 */
	@Override
	public void modifyTag(SearchParam tag)throws Exception {
		if(tag.getOnlyRead()!=null&&tag.getOnlyRead().equals("0")){
			String sql="select t.parent_id from RAS_SEARCH_PARAM t where t.id="+tag.getId();
			BigDecimal orignParentID=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
			//SearchParam origntag=(SearchParam)sf.getCurrentSession().get(SearchParam.class, tag.getId());
			if(!tag.getParent_id().equals(orignParentID.intValue()+"")){
				delTag(new String[]{tag.getId()+""});
				addTag(tag);
			}

			
//			if(tag.getParent_id()!=null){
//				tag.setParentTag((SearchParam)sf.getCurrentSession().get(SearchParam.class, Long.parseLong(tag.getParent_id())));
//			}
			sf.getCurrentSession().saveOrUpdate(tag);
		}
	}

	/**
	 * 删除数据标签
	 */
	@Override
	public void delTag(String[] ids) throws Exception{
		for(String id:ids){
			SearchParam tag=(SearchParam)sf.getCurrentSession().createSQLQuery("select * from RAS_SEARCH_PARAM t where t.id='"+id+"'")
									   .addEntity(SearchParam.class)
									   .uniqueResult();
			if(tag!=null){
				//删除数据库字段
				String tableName="RAS_AIRCRAFT_"+tag.getParentTag().getEnname();
				String columnName=tag.getEnname();
				String sql="select count(1) from user_tab_columns where table_name='"+tableName+"' and lower(column_name)='"+columnName.toLowerCase()+"'";
				BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
				if(count.intValue()!=0){
					//删除字段,不要添加引号.
					sf.getCurrentSession().createSQLQuery("alter table "+tableName+" drop column "+columnName).executeUpdate();
				}
				
				sf.getCurrentSession().delete(tag);
			}
			
		}
	}

	private void delTableColumn(String tableName,String Column){
		
	}

	@Override
	public String addTagCategory(SearchParam tag) {
		Column[] cols=new Column[2];
		cols[0]=new Column();
		cols[0].setName("ID");
		cols[0].setDataType("VARCHAR2(32)");
		cols[1]=new Column();
		cols[1].setName("BASICID");
		cols[1].setDataType("VARCHAR2(32)");
		String sql=SQLUtil.createTable("RAS_AIRCRAFT_"+tag.getName(), cols);
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();	

		tag.setOnlyRead("0");
		sf.getCurrentSession().save(tag);
		return "";
	}

	
}
