package com.ras.simpleSearch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftOverview.AircraftOverview;
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

		//权限控制
		String authoritySQL="";
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			authoritySQL=" and ((editor='"+curUser.getUserid()+"' and PERMISSION_LEVEL='1') or PERMISSION_LEVEL in ('2','3'))";
		}
		
		String sql="select distinct aircrafttype from simpleSearchView v where aircrafttype is not null"+authoritySQL;
		getChildNode(sql,ja,"飞机类型","aircrafttype");

		sql="select distinct v.firstflightyear from simpleSearchView v where v.firstflightyear is not null"+authoritySQL+" order by firstflightyear desc ";
		getChildNode(sql,ja,"首飞年份","firstflightyear");
		
		sql="select distinct v.producercountries from simpleSearchView v where v.producercountries is not null"+authoritySQL;
		getChildNode(sql,ja,"研发国别","producercountries");
		
		sql="select distinct substr(v.modelname,1,1) modelname from simpleSearchView v where v.modelname is not null"+authoritySQL+" order by modelname";
		getChildNode(sql,ja,"机型首字母","modelname");
		
		return ja;
	}

	private void getChildNode(String sql,JSONArray rootJa,String text,String tags){
		//String sql1="select distinct "+tags+" from simpleSearchView v where "+tags+" is not null order by "+tags;
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
				+ "inner join ras_aircraft_basic ab on ab.overviewid=ao.id ";
		if(vo.getShowPerson()!=null&&vo.getShowPerson().equals("0")){
			sql+=" and ao.permission_level!=1 ";
		}
		sql+=tableSql+paramSql;
		
		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			sql+=" and ((ao.editor='"+curUser.getUserid()+"' and ao.PERMISSION_LEVEL='1') or ao.PERMISSION_LEVEL in ('2','3'))";
		}
		
		
		
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(distinct ao.id) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		sql=" select distinct ao.ID overviewID,ao.modelCname,ao.modelEname,ao.modelName,ao.permission_Level permissionLevel,"+"(select count(1) from ras_aircraft_overview ao1 where ao1.parent_id=ao.id) subModelTotal "+sql;
		List list=sf.getCurrentSession().createSQLQuery(sql+" order by ao.modelName")
//					.addEntity("ao",AircraftOverview.class)
					.addScalar("overviewID")
					.addScalar("modelName")
					.addScalar("modelCname")
					.addScalar("modelEname")
					.addScalar("permissionLevel")
					.addScalar("subModelTotal")
					
//					.addScalar("basicid",StandardBasicTypes.STRING)
					.setResultTransformer(Transformers.aliasToBean(AircraftOverview.class))
					.setFirstResult(vo.getStart())
					.setMaxResults(vo.getLength())
					.list();
		
		/*List<Map<String,String>> retList=new ArrayList<Map<String,String>>();
		for(Object[] objs:list){
			Map<String,String> dataMap=new HashMap<String,String>();
			dataMap.put("overviewID", objs[0].toString());
			dataMap.put("modelName", objs[1].toString());
			dataMap.put("modelCname", (objs[2]==null?"":objs[2].toString()));
			dataMap.put("modelEname", (objs[3]==null?"":objs[3].toString()));
			//dataMap.put("basicID", (objs[4]==null?"":objs[4].toString()));
			retList.add(dataMap);
		}*/

		vo.setData(list);
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
						val+="|";
					}
//					val+=""+str[j]+"_";
					val+=str[j];
				}
//				paramSQL.append(" and CONTAINS(modelname, '"+val+"')>0 ");
				paramSQL.append(" and REGEXP_LIKE (modelname,'^["+val+"]','i') ");
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
		return paramSQL.toString();
	}
	
	@Override
	public JSONObject addNoteForTagInput(String overviewID, String[] inputNames) {
		JSONObject retJO=new JSONObject();
//		String sql="select * from RAS_SEARCH_PARAM tag where tag.parent_id!=0 and tag.enname is not null";
//		List<SearchTag> tagList=sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchTag.class).list();
		
		//String sql="select ov.modelname,ov.modelcname,ov.modelename,ab.* from ras_aircraft_overview ov inner join ras_aircraft_basic ab on ab.overviewid=ov.id and ab.maininfo!='1' where ov.id='"+overviewID+"'";
//		List<Map<String,Object>> basicList=sf.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
//		List<Map<String,Object>> weightList=loadTableData("weight",basicID);
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list.addAll(loadTableData("basic",overviewID));
		list.addAll(loadTableData("weight",overviewID));
		list.addAll(loadTableData("layout",overviewID));
		list.addAll(loadTableData("capability",overviewID));//CAPABILITY
		list.addAll(loadTableData("dynamic",overviewID));
		list.addAll(loadTableData("system",overviewID));
		list.addAll(loadTableData("other",overviewID));
		JSONArray inputs=new JSONArray();
		for(String inputName:inputNames){
			JSONObject input=new JSONObject();
			JSONArray vals=new JSONArray();
			for(Map m:list){
				if(m.get(inputName.toUpperCase())==null){
					continue;
				}
				String val=m.get(inputName.toUpperCase()).toString();
				String dataSources=m.get("DATASOURCES")==null?"":m.get("DATASOURCES").toString();
				JSONObject jo=new JSONObject();
				jo.put("dataSources", dataSources);
				jo.put("inputValue", val);
				vals.add(jo);
			}
			if(vals.size()==0){
				continue;
			}
			input.put("name", inputName);
			input.put("vals", vals);
			inputs.add(input);
		}
		//inputs:[{name:'aircraftType',vals:[{dataSource:'飞机手册1',inputValue:'美国1'},{dataSource:'飞机手册2',inputValue:'美国2'}]}]
		retJO.put("inputs", inputs);
		return retJO;
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
		case "capability":
		case "dynamic":
		case "system":
		case "other":
			sql="select ab.datasources,aw.* from ras_aircraft_overview ov "
					+ " inner join RAS_AIRCRAFT_BASIC ab on ab.overviewid=ov.id and ab.maininfo is null "
					+ " left join ras_aircraft_"+tableName+" aw on aw.basicid=ab.id "+"where ov.id='"+id+"'";
			break;
		}
		return sf.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
}
