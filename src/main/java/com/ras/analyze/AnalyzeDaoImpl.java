package com.ras.analyze;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
