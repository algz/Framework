package com.ras.comparison;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftReport.AircraftReport;
import com.ras.aircraftReport.AircraftReportKey;
import com.ras.aircraftReport.AircraftReportVal;
import com.ras.searchParam.SearchParam;

import algz.platform.util.Common;

@Repository
public class ComparisonDaoImpl implements ComparisonDao {

	@Autowired
	private SessionFactory sf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> findComparisonDetailGrid(String[] modelNames,String[] basicID) {
		List<String[]> retList=new <String[]>ArrayList();
		//加载所有Tag
		String sql="select * from RAS_SEARCH_param t where t.parent_id!=0 and t.enname is not null ";
		List<SearchParam> searchTagList=sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchParam.class).list();
		
		//读取所有指定条件的数据
		StringBuilder field=new StringBuilder();
		boolean flag=false;
		for(SearchParam tag:searchTagList){
			if(!tag.getParent_id().equals("0")){
				if(flag){
					field.append(",");
				}
				field.append(tag.getEnname());
				flag=true;
			}
		}
		StringBuilder paramSQL=new StringBuilder("select distinct "+field.toString()+" from ras_aircraft_overview ao ");
		paramSQL.append(" inner join ras_aircraft_basic ab on ab.overviewid=ao.id and ab.maininfo=1 ");
		paramSQL.append(" left join ras_aircraft_weight aw on aw.basicid=ab.id ");
		paramSQL.append(" left join ras_aircraft_layout al on al.basicid=ab.id ");
		paramSQL.append(" left join ras_aircraft_capability ac on ac.basicid=ab.id ");
		paramSQL.append(" left join ras_aircraft_dynamic ad on ad.basicid=ab.id ");
		paramSQL.append(" left join ras_aircraft_system asys on asys.basicid=ab.id ");
		paramSQL.append(" left join ras_aircraft_other aother on aother.basicid=ab.id ");
//		paramSQL.append(" where ao.modelname in (:params)");
		paramSQL.append(" where ab.id in (:params)");
		List<Map<String,String>> mapList=sf.getCurrentSession().createSQLQuery(paramSQL.toString())
//										  .setParameterList("params", modelNames)
										  .setParameterList("params", basicID)
										  .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
										  .list();
		
		for(SearchParam tag:searchTagList){
			//加载列数据格式
			String[] tem=new String[modelNames.length+1];
			tem[0]=tag.getName(); //名称
			retList.add(tem);
			
			for(int i=0;i<mapList.size();i++){
				Map<String,String> map=mapList.get(i);
				Object obj=map.get(tag.getEnname().toUpperCase());
				if(obj==null){
					continue;
				}
				if(obj instanceof BigDecimal){
					tem[i+1]=((BigDecimal)obj).intValue()+"";
				}else{
					tem[i+1]=obj.toString();
				}
//				if(map.get(tag.getEnname().toUpperCase()).equals(""))
			}
		}
		return retList;

/*		if(mapList.size()!=0){
			//sql="select u.comments,u.column_name from user_col_comments u where u.table_name='AIRCRAFTALLPARAM' and u.comments is not null";
			sql="SELECT TAG.NAME,TAG.ENNAME FROM RAS_SEARCH_PARAM TAG where tag.parent_id!=0 and tag.enname is not null";
			List<Object[]> colList=sf.getCurrentSession().createSQLQuery(sql).list();
			for(Object[] objs:colList){
				String[] arr=new String[mapList.size()+1];
				list.add(arr);
				arr[0]=objs[0].toString();
				for(int i=0;i<mapList.size();i++){
					for(Iterator<String> it=mapList.get(i).keySet().iterator();it.hasNext();){
						String s=it.next();
						if(objs[1].toString().toUpperCase().equals(s)){
							Object obj=mapList.get(i).get(s);
							arr[i+1]=obj.toString();
							break;
						}
					}
				}
				
			}
			return list;
		}
		return null;*/
	}

	

	@Override
	public void findModelGird(ComparisonVo vo) {
		
		String sql=" from ras_aircraft_overview t where t.id in (:ids)";
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1)"+sql)
									   .setParameterList("ids", vo.getOverviewID().split(","))
									   .uniqueResult();
		vo.setRecordsTotal(count.intValue());
		
		List list=sf.getCurrentSession().createSQLQuery("select t.*"+sql)
					.addEntity(AircraftOverview.class)
					.setFirstResult(vo.getStart())
					.setMaxResults(vo.getLength())
					.setParameterList("ids", vo.getOverviewID().split(","))
					.list();
		vo.setData(list);
	}

	
}
