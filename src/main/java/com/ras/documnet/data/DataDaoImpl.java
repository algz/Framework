/**
 * 
 */
package com.ras.documnet.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.search.SearchTag;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author algz
 *
 */
@Repository
public class DataDaoImpl implements DataDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public void FindTableModelParamGrid(DataVo vo) {
		// TODO Auto-generated method stub
		String sql="";
		sf.getCurrentSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> addModelParamPage(DataVo vo) {
		String sql="";
		if(vo.getBasicID()==null){
			//AircraftOverview ao=(AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, vo.getOverviewID());
			sql="select * from aircraftallparam  ap where ap.overviewID='"+vo.getOverviewID()+"'";
			//sql="select * from RAS_AIRCRAFT_OVERVIEW  ao where ao.id='"+vo.getOverviewID()+"'";
		}else{
			
			sql="select * from aircraftallparam ap where ap.basicID='"+vo.getBasicID()+"'";
			
		}
		return (Map<String,String>)sf.getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}

	

	
	@Override
	public void delModelParam(String[] ids) {
		for(String id:ids){
			String sql="delete from ras_aircraft_basic ab where ab.id='"+id+"'";
			sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}

		
	}

	@Override
	public AircraftOverview findModel(String overviewID){
		return (AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, overviewID);
	}

	@Override
	public void delModel(String[] ids) {
		for(String id:ids){
			String sql="delete from ras_aircraft_overview ab where ab.id='"+id+"'";
			sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
	}

	@Override
	public JSONObject findModelParam(DataVo vo) {
		String sql="select * from RAS_SEARCH_TAG t where t.enname is not null";
		List<SearchTag> searchTagList=sf.getCurrentSession().createSQLQuery(sql).addEntity(SearchTag.class).list();
		
		AircraftOverview overview=(AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, vo.getOverviewID());
		if(vo.getBasicID()==null){
			vo.setBasicID(overview.getAircraftBasicSet().get(0).getBasicID());
		}
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("basicID", vo.getBasicID());
		paramMap.put("modelName", overview.getModelName());
		paramMap.put("modelCname", overview.getModelCname());
		paramMap.put("modelEname", overview.getModelEname());
		//AircraftBasic basic=(AircraftBasic)sf.getCurrentSession().get(AircraftBasic.class, vo.getBasicID());
		
		
		//		Map<String, JSONArray> m=new HashMap();
		JSONObject jo=new JSONObject();
		jo.put("modelName", overview.getModelName());
		jo.put("basicMap", modalParamToMap("1",paramMap,searchTagList));
		jo.put("weightMap", modalParamToMap("2",paramMap,searchTagList));
		jo.put("layoutMap", modalParamToMap("3",paramMap,searchTagList));
		jo.put("capablityMap", modalParamToMap("4",paramMap,searchTagList));
		jo.put("dynamicMap", modalParamToMap("5",paramMap,searchTagList));
		jo.put("systemMap", modalParamToMap("6",paramMap,searchTagList));
		return jo;
	}
	
	private JSONArray modalParamToMap(String parentTagID,Map<String,String> paramMap,List<SearchTag> searchTagList){
		JSONArray ja=new JSONArray();

		//读取全部字段
		List<SearchTag> tags=new ArrayList<SearchTag>();
		SearchTag mTag=null;
		for(SearchTag tag:searchTagList){
			if(tag.getId().toString().equals(parentTagID)){
				mTag=tag;
			}else if(tag.getParent_id().equals(parentTagID)){
				tags.add(tag);
			}
		}
		
		if(paramMap.get("basicID")==null){
			//新建
			ja=loadModalParamToEl(tags);
		}
		else{
			//修改
			ja=new JSONArray();
			String sql="select * from ras_aircraft_"+mTag.getEnname()+" ab where ab.id='"+paramMap.get("basicID")+"'";
			Map<String,String> map=(Map<String,String>)sf.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
			if(map==null){
				return null;
			}
			
			//AircraftBasic 表需要关联处理.
//			AircraftOverview ov=null;
			if(mTag.getEnname().equals("BASIC")){
				JSONObject jo=new JSONObject();
				jo.put("elID", "dataSources");
				jo.put("elLabel", "数据来源");
				jo.put("elValue", map.get("DATASOURCES"));
				ja.add(jo);
			}
			for (SearchTag tag : tags) {
				JSONObject jo=new JSONObject();
				jo.put("elID", tag.getEnname());
				jo.put("elLabel", tag.getName());
				
				if(tag.getEnname().equals("modelName")){
					jo.put("elValue", paramMap.get("modelName"));
					jo.put("readonly", "true");
				}else if(tag.getEnname().equals("modelCname")){
					jo.put("elValue", paramMap.get("modelCname"));
					jo.put("readonly", "true");
				}else if(tag.getEnname().equals("modelEname")){
					jo.put("elValue", paramMap.get("modelEname"));
					jo.put("readonly", "true");
				}else{
					String s=""; 
					Object obj=map.get(tag.getEnname().toUpperCase());
					if(obj instanceof BigDecimal){
						s=((BigDecimal) obj).intValue()+"";
					}else if(obj instanceof String){
						s=obj.toString();
					}
					jo.put("elValue", s);
				}
				ja.add(jo);
			}
		}
		return ja;

	}

	/**
	 * 加载所有的TAG到EL
	 * @param tags
	 * @return
	 */
	private JSONArray loadModalParamToEl(List<SearchTag> tags){
		JSONArray ja=new JSONArray();
		for (SearchTag tag : tags) {
			JSONObject jo=new JSONObject();
			jo.put("elID", tag.getEnname());
			jo.put("elLabel", tag.getName());
			ja.add(jo);
		}
		return ja;
	}
}
