package com.ras.searchParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftType.AircraftType;
import com.ras.aircraftType.AircraftTypeDao;
import com.ras.country.Country;
import com.ras.country.CountryDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class SearchParamDaoImpl implements SearchParamDao {

	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private AircraftTypeDao aircraftTypeDao;
	
	@Autowired
	private SessionFactory sf;
	
	@SuppressWarnings("unchecked")
	@Override
	public void findAll(SearchParamVo<SearchParam> vo) {
		String sql=" from ras_search_param tag where 1=1 ";
		if(vo.getOnlyRead()!=null&&!vo.getOnlyRead().equals("1")){
			sql+= " and tag.onlyRead='"+vo.getOnlyRead()+"' ";
		}
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		List<SearchParam> list=sf.getCurrentSession().createSQLQuery("select * "+sql+" order by tag.PARENT_ID,tag.sequence,tag.id")
				.addEntity(SearchParam.class)
				.setFirstResult(vo.getStart())
				.setMaxResults(vo.getLength())
				.list();
		vo.setData(list);
	}

	@Override
	public List<SearchParam> findAllParent() {
		String sql="select * from ras_search_param tag where tag.parent_id=0 order by tag.id";
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchParam.class).list();
	}

	@Override
	public List<SearchParam> findAllByIds(String[] ids) {
		String sql="select * from ras_search_param tag where tag.id in(:ids) order by tag.id";
		List<SearchParam> tagList= sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchParam.class).setParameterList("ids", ids).list();
	
		for(SearchParam tag :tagList){
			switch (tag.getUi_type()) {
				case "checkbox":
					List<String> list=new ArrayList<String>();
					sql="select count(1) from user_tables where upper(table_name)='"+tag.getUi_value().toUpperCase()+"'";
					BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
					if(count.intValue()>0){
						sql="select name from "+tag.getUi_value();
						list=sf.getCurrentSession().createSQLQuery(sql).list();
					}else{
						String[] vals=tag.getUi_value().split(",");
						Collections.addAll(list, vals);
					}
					
					for(String v:list){
						SearchParam t=new SearchParam();
						t.setId(null);
						t.setName(v);
						tag.getSearchTags().add(t);
					}

					break;
			}
		}
		return tagList;
	}

	public List<?> findAttribute(String tableName){
		String sql="select * from "+tableName;
		return sf.getCurrentSession().createSQLQuery(sql).list();
	}
	
	@Override
	public void save(SearchParam searchTag){
		sf.getCurrentSession().save(searchTag);
	}

	@Override
	public Map<String, String> searchSummarize(String overviewID) {
		String sql="";
		if(overviewID==null){
			sql="select * from RAS_AIRCRAFT_OVERVIEW  ao where ao.id='"+overviewID+"'";
		}else{
			sql="select * from aircraftallparam ap where ap.basicID='"+overviewID+"'";
		}
		return (Map<String,String>)sf.getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
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

	@Override
	public List<SearchParam> findAllChildren(String parentID) {
		String sql="select * from RAS_SEARCH_param t where t.enname is not null "+(parentID==null?"":" and PARENT_ID='"+parentID+"'");
		return sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchParam.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,List<String>> findAllCheckboxTypeList() {
		String hql="from SearchParam where ui_type='checkbox'";
		List<SearchParam> list=sf.getCurrentSession().createQuery(hql).list();
		Map<String,List<String>> m=new HashMap<String,List<String>>();
		for(SearchParam param:list){
			String sql="select name from "+param.getUi_value();
			List<String> tem=sf.getCurrentSession().createSQLQuery(sql).list();
			m.put(param.getUi_value(), tem);
		}
		return m;
	}


}
