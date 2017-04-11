package com.ras.search.searchCriteria;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchCriteriaDaoImpl implements SearchCriteriaDao {

	@Autowired
	private SessionFactory sf;
	
	public List<String[]> SearchCriteriaGird(Map<String,String> map){
		String s="select t.enname,t.ui_type from RAS_SEARCH_TAG t where t.enname is not null and t.ui_type is not null";
		List<String[]> tagList=sf.getCurrentSession().createSQLQuery(s).list();
		
		StringBuilder sql=new StringBuilder("select ov.overviewID,ov.modelname,ov.modelcname,ov.modelename from AIRCRAFTALLPARAM ov where 1=1 ");
		Object[] keys=map.keySet().toArray();
		for(int i=0;i<keys.length;i++){
			String val=map.get(keys[i]);
			for(Object[] arr:tagList){
				
				if(arr[0].toString().equals(keys[i])){
					switch (arr[1].toString()){
						case "text":
							sql.append(" and Upper("+keys[i].toString().toUpperCase()+") like '%"+val.toUpperCase()+"%' ");
							break;
						case "checkbox":
							String[] str=val.split(",");
							val="";
							for(int j=0;j<str.length;j++){
								if(j!=0){
									val+=",";
								}
								val+="'"+str[j]+"'";
							}
							sql.append(" and "+keys[i]+" in("+val+") ");
							break;
						case "numberRegion":
							String[] nums=val.split("-",-1);
							if(!nums[0].equals("")){
								sql.append(" and "+keys[i]+">="+nums[0]);
							}
							if(!nums[1].equals("")){
								sql.append(" and "+keys[i]+"<="+nums[1]);
							}
							break;
					}
					break;
				}
			}
			
//			sql.append(" and "+keys[i]+"='"+val+"' ");
		}
		
		return sf.getCurrentSession().createSQLQuery(sql.toString()).list();
	}
	
}
