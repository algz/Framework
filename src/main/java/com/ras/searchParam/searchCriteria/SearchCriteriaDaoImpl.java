package com.ras.searchParam.searchCriteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftBasic.AircraftBasicDao;
import com.ras.searchParam.SearchParam;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;

@Repository
public class SearchCriteriaDaoImpl implements SearchCriteriaDao {

	@Autowired
	private AircraftBasicDao aircraftBasicDao;
	
	@Autowired
	private SessionFactory sf;
	
	@SuppressWarnings("unchecked")
	public void SearchCriteriaGird(SearchCriteriaVo vo){
//    	String s=java.net.URLDecoder.decode(map.get("data")[0]);
    	Map<String,String> map=vo.getParamMap();//CommonTool.stringFormToJson(s.split("&"),false);
		
		//所有Tag		
		String sql="select * from RAS_SEARCH_PARAM t where t.enname is not null";
		List<SearchParam> searchTagList=sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchParam.class).list();
		
		String tableSql=loadReqMapToTable(searchTagList,map);
		String paramSql=loadReqMapToParam(searchTagList,map);
		
		sql="from ras_aircraft_overview ao "
				+ "inner join ras_aircraft_basic ab on ab.overviewid=ao.id "+tableSql+paramSql;
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			sql+=" and (ab.editor='"+curUser.getUserid()+"' or ab.PERMISSION_LEVEL in ('2','3'))";
		}
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(distinct ao.id) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		
		List<Object[]> list=sf.getCurrentSession().createSQLQuery(" select distinct ao.id,ao.modelname,ao.modelcname,ao.modelename "+sql)
					.setFirstResult(vo.getStart())
					.setMaxResults(vo.getLength())
					.list();
		List<Map<String,String>> retList=new ArrayList<Map<String,String>>();
		for(Object[] objs:list){
			Map<String,String> dataMap=new HashMap<String,String>();
			dataMap.put("overviewID", objs[0].toString());
			dataMap.put("modelName", objs[1].toString());
			dataMap.put("modelCname", (objs[2]==null?"":objs[2].toString()));
			dataMap.put("modelEname", (objs[3]==null?"":objs[3].toString()));
			//dataMap.put("basicID", (objs[4]==null?"":objs[4].toString()));
			retList.add(dataMap);
		}
		vo.setData(retList);
	}
	
	/**
	 * 转换查询参数为字符串
	 * @param searchTagList
	 * @param reqMap 字段 Map
	 * @return
	 */
	private String loadReqMapToTable(List<SearchParam> searchTagList,Map<String,String> reqMap){
		StringBuilder paramSQL=new StringBuilder();
		Set<String> paramSet=new HashSet<String>();
		for(Map.Entry<String, String> entry:reqMap.entrySet()){
			for(SearchParam tag:searchTagList){
				if(tag.getEnname().toUpperCase().equals(entry.getKey().toUpperCase())){
					if(!paramSet.contains(tag.getParentTag().getEnname())){
						String s=tag.getParentTag().getEnname();
						paramSet.add(s);
						switch(s){
						case "BASIC":
							//paramSQL.append("inner join ras_aircraft_basic ab on ab.overviewid=ao.id and ab.maininfo=1 ");
							break;
						case "WEIGHT":
							paramSQL.append(" left join ras_aircraft_weight aw on aw.basicid=ab.id ");
							break;
						case "LAYOUT":
							paramSQL.append(" left join ras_aircraft_layout al on al.basicid=ab.id ");
							break;
						case "CAPABILITY":
							paramSQL.append(" left join ras_aircraft_capability ac on ac.basicid=ab.id ");
							break;
						case "DYNAMIC":
							paramSQL.append(" left join ras_aircraft_dynamic ad on ad.basicid=ab.id ");
							break;
						case "SYSTEM":
							paramSQL.append(" left join ras_aircraft_system asys on asys.basicid=ab.id ");
							break;
						}
					}
					break;
				}
			}
		}
		return paramSQL.toString();
	}
	
	/**
	 * 转换数据为参数字符串
	 * @param searchTagList
	 * @param reqMap
	 * @return
	 */
	private String loadReqMapToParam(List<SearchParam> searchTagList,Map<String,String> reqMap){
		StringBuilder paramSQL=new StringBuilder(" where 1=1 ");
		for(Map.Entry<String, String> entry:reqMap.entrySet()){
			for(SearchParam tag:searchTagList){
				if(tag.getEnname().toUpperCase().equals(entry.getKey().toUpperCase())){
					switch (tag.getUi_type()){
						case "text":
							paramSQL.append(" and Upper("+entry.getKey()+") like '"+entry.getValue().toUpperCase()+"%' ");
							break;
						case "number":
							paramSQL.append(" and "+entry.getKey()+" = "+entry.getValue()+" ");
							break;
						case "checkbox":
							String[] str=entry.getValue().split(",");
							String val="";
							for(int j=0;j<str.length;j++){
								if(j!=0){
									val+=",";
								}
								val+="'"+str[j]+"'";
							}
							paramSQL.append(" and "+entry.getKey()+" in("+val+") ");
							break;
						case "numberRegion":
							String[] nums=entry.getValue().split("-",-1);
							if(!nums[0].equals("")){
								paramSQL.append(" and "+entry.getKey()+">="+nums[0]);
							}
							if(!nums[1].equals("")){
								paramSQL.append(" and "+entry.getKey()+"<="+nums[1]);
							}
							break;
					}
					break;
				}
			}
		}
		return paramSQL.toString();
	}

	@Override
	public List<AircraftBasic> SearchCriteriaSubGird(String overviewID) {
		AircraftBasic ab=new AircraftBasic();
		ab.setOverviewID(overviewID);
		return aircraftBasicDao.find(ab);
	}
	
}
