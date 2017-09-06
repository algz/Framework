package com.ras.simpleSearch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.searchParam.SearchParam;
import com.ras.searchParam.searchCriteria.SearchCriteriaVo;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class SimpleSearchDaoImpl implements SimpleSearchDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public JSONArray getSearchTreeNode(Map<String, String> m) {
		JSONArray ja=new JSONArray();

		String sql="select distinct aircrafttype from simpleSearchView v where aircrafttype is not null";
		getChildNode(sql,ja,"飞机类型","aircrafttype");

		sql="select distinct v.firstflightyear from simpleSearchView v where v.firstflightyear is not null";
		getChildNode(sql,ja,"首飞年份","firstflightyear");
		
		sql="select distinct v.producercountries from simpleSearchView v where v.producercountries is not null";
		getChildNode(sql,ja,"研发国别","producercountries");
		
		sql="select distinct substr(v.modelname,1,1) modelname from simpleSearchView v where v.modelname is not null order by modelname";
		getChildNode(sql,ja,"机型首字母","modelname");
		
		return ja;
	}

	private void getChildNode(String sql,JSONArray rootJa,String text,String tags){
		String sql1="select distinct "+tags+" from simpleSearchView v where "+tags+" is not null order by "+tags;
		JSONArray ja=new JSONArray();
		List<Object> list=sf.getCurrentSession().createSQLQuery(sql).list();
		for(Object obj:list){
			JSONObject jo=new JSONObject();
			String[] arr=obj.toString().split(",");
//			if(arr.length>1){
				for(String s:arr){
					boolean flag=true;
					for(Object tem:ja){
						JSONObject temjo=(JSONObject)tem;
						if(temjo.getString("text").equals(s)){
							flag=false;
							break;
						}
					}
					if(flag){
						jo.put("text", s);
						ja.add(jo);
					}
				}
		}
		
		JSONObject jo=new JSONObject();
		jo.put("nodes", ja);
		jo.put("tags",tags);
		jo.put("text",text);
		rootJa.add(jo);
	}
	
	@SuppressWarnings("unchecked")
	public void simpleSearchGird(SimpleSearchVo vo){
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
			String[] str=entry.getValue().split(",");
			String val="";

			if(entry.getKey().equals("modelname")){
				for(int j=0;j<str.length;j++){
					if(j!=0){
						val+=" or ";
					}
					val+=""+str[j]+"_";
				}
				paramSQL.append(" and CONTAINS(modelname, '"+val+"')>0 ");
			}else{
				for(int j=0;j<str.length;j++){
					if(j!=0){
						val+=",";
					}
					val+="'"+str[j]+"'";
				}
				paramSQL.append(" and "+entry.getKey()+" in("+val+") ");
			}
			
			/*for(SearchParam tag:searchTagList){
				if(tag.getEnname().toUpperCase().equals(entry.getKey().toUpperCase())){
					switch (tag.getUi_type()){
						case "numberRegion":
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
						case "numberRegion1":
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
			}*/
		}
		return paramSQL.toString()+" order by modelname";
	}
}
