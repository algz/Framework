package com.ras.analyze;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;
import com.ras.searchParam.SearchParam;
import com.sun.xml.internal.ws.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class AnalyzeDaoImpl implements AnalyzeDao {

	@Autowired
	private SessionFactory sf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> findComparisonDetailGrid(String[] modelNames) {
		List<String[]> list=new ArrayList<String[]>();
		String sql="select * from AIRCRAFTALLPARAM a where a.modelname in (:params)";
		List<Map<String,String>> mapList=sf.getCurrentSession().createSQLQuery(sql).setParameterList("params", modelNames)
										  .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
										  .list();
		if(mapList.size()!=0){
			sql="select u.comments,u.column_name from user_col_comments u where u.table_name='AIRCRAFTALLPARAM' and u.comments is not null";
			List<Object[]> colList=sf.getCurrentSession().createSQLQuery(sql).list();
			for(Object[] objs:colList){
				String[] arr=new String[mapList.size()+1];
				list.add(arr);
				arr[0]=objs[0].toString();
				for(int i=0;i<mapList.size();i++){
					for(Iterator<String> it=mapList.get(i).keySet().iterator();it.hasNext();){
						String s=it.next();
						if(objs[1].toString().equals(s)){
							arr[i+1]=mapList.get(i).get(s);
							break;
						}
					}
				}
				
			}
			return list;
		}
		return null;
	}

	@Override
	public List<?> findModelForTypeahead(String modelName) {
		String sql="select ov.modelname from ras_aircraft_overview ov where LOWER(ov.modelname) LIKE '%"+modelName.toLowerCase()+"%'";
		return sf.getCurrentSession().createSQLQuery(sql).list();
	}


	/**
	 * @param axis     	
	 *  axis[0]=request.getParameter("xAxis");
    	axis[1]=request.getParameter("yAxis");
	 */
	@Override
	public JSONArray analyzeChart(String[] modelNames, String[] axis) {
		//modelName=f&xAxis=modelName&yAxis=modelCname
		StringBuilder sql=new StringBuilder("select ab.dataSources||'('||modelname||')' modelname ");
		for(int i=0;i<axis.length;i++){
			if(axis[i].equals("")){
				sql.append(",0");
			}else{
				sql.append(",to_number(nvl("+axis[i]+",0))");
			}
		}


		//data="[{names:'f-32',datas:[[100,100],2000]},{names:'f-35',datas:[200,2200]}]"
		
		sql.append(" from ras_aircraft_overview ov  ");
//		sql.append(" inner join RAS_AIRCRAFT_BASIC ab on ab.overviewid=ov.id and ab.maininfo='1' ");
		sql.append(" inner join RAS_AIRCRAFT_BASIC ab on ab.overviewid=ov.id  ");
		sql.append(" left join ras_aircraft_weight aw on aw.basicid=ab.id ");
		sql.append(" left join ras_aircraft_layout al on al.basicid=ab.id ");
		sql.append(" left join ras_aircraft_capability ac on ac.basicid=ab.id ");
		sql.append(" left join ras_aircraft_dynamic ad on ad.basicid=ab.id ");
		sql.append(" left join ras_aircraft_system asys on asys.basicid=ab.id ");
//		sql.append(" where ov.modelname in (:modelname)");
		sql.append(" where ab.id in (:modelname)");
		
		List<Object[]> list=sf.getCurrentSession().createSQLQuery(sql.toString())
						.setParameterList("modelname", modelNames).list();
		//[{names:'f-32',datas:[[100,100],2000]},{names:'f-35',datas:[200,2200]}]
		JSONArray ja=new JSONArray();
		for(int i=0;i<modelNames.length;i++){
			
			JSONObject jo=new JSONObject();
			String data="";
			
			if(i<list.size()){
				Object[] objs=list.get(i);
				jo.put("name", objs[0]);
				for(int j=1;j<objs.length;j++){
					if(j!=1){
						data+=",";
					}
					data+=objs[j];
				}
			}

			jo.put("data", "[["+data+"]]");
			ja.add(jo);
		}
//		for(Object[] objs:list){
//			JSONObject jo=new JSONObject();
//			jo.put("name", objs[0]);
//			String data="";
//			for(int i=1;i<objs.length;i++){
//				if(i!=1){
//					data+=",";
//				}
//				data+=objs[i];
//			}
//			jo.put("data", "[["+data+"]]");
//			ja.add(jo);
//		}
		
		return ja;//list;
	}

	private List<Map<String,Object>> loadTableData(String tableName,String id){
		String sql="";
		switch(tableName){
		case "basic":
			sql="select ab.* from ras_aircraft_overview ov "
					+ " inner join ras_aircraft_basic ab on ab.overviewid=ov.id and ab.maininfo is null "
					+ " where ov.id='"+id+"'";
			break;
		case "weight":
		case "layout":
		case "capablity":
		case "dynamic":
		case "system":
			sql="select aw.* from ras_aircraft_overview ov "
					+ " inner join RAS_AIRCRAFT_BASIC ab on ab.overviewid=ov.id and ab.maininfo is null "
					+ " left join ras_aircraft_"+tableName+" aw on aw.basicid=ab.id "+"where ov.id='"+id+"'";
			break;
		}
		return sf.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
}
