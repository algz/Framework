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
import com.ras.search.SearchTag;
import com.sun.xml.internal.ws.util.StringUtils;

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


	@Override
	public List<?> analyzeChart(String[] modelNames, String[] axis) {
		StringBuilder sql=new StringBuilder("select ");
		for(int i=0;i<axis.length&&!axis[i].equals("");i++){
			if(i!=0)
				sql.append(",");
			sql.append(" to_number("+axis[i]+")");
		}
//		for(int i=0;i<axis.length;i++){
//			SearchTag tag=(SearchTag)sf.getCurrentSession().get(SearchTag.class, axis[i]);
//			sql.append(" "+);
//		}
		sql.append(" from aircraftallparam t where t.modelname in (:modelname)");
		List<?> list=sf.getCurrentSession().createSQLQuery(sql.toString())
						.setParameterList("modelname", modelNames).list();
		return list;
	}

}
