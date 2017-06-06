/**
 * 
 */
package com.ras.documnet.data;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.aircraftBasic.AircraftBasicDao;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.searchParam.SearchParamDao;
import com.ras.searchParam.SearchParam;
import com.ras.tool.CommonTool;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author algz
 *
 */
@Repository
public class DataDaoImpl implements DataDao {

	@Autowired
	private SearchParamDao searchTagDao;
	
	@Autowired
	private AircraftBasicDao aircraftBasicDao;
	
	@Autowired
	private SessionFactory sf;

	
	@Override
	public void findTableModelParamGrid(DataVo vo) {
		if(vo.getOverviewID()==null){
			return ;
		}
		String sql="from RAS_AIRCRAFT_BASIC ab where ab.overviewid='"+vo.getOverviewID()+"'  ";
		if(vo.getModelName()!=null){
			sql+=" and lower(ab.modelName) like '%"+vo.getModelName().toLowerCase()+"%'";
		}

		//权限控制
		if(!CommonTool.isDataManager()){
			User curUser=Common.getLoginUser();
			sql+=" and (ab.editor='"+curUser.getUserid()+"' or ab.PERMISSION_LEVEL in ('2','3'))";
		}
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		if(count.intValue()==0){
			return ;
		}
		List list= sf.getCurrentSession().createSQLQuery("select * "+sql)
					 .addEntity(AircraftBasic.class)
				     .setFirstResult(vo.getStart())
				     .setMaxResults(vo.getLength())
				     .list();//query.setProperties(ab).list();//.setEntity("ab", ab).list();
		vo.setData(list);

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
	public void delModelParam(String[] ids,String overviewID) {
		//级联删除,可以通过配置数据库作级联;也可以程序控制.
//		String[] tableNames={"WEIGHT"/*,"LAYOUT","CAPABILITY","DYNAMIC","SYSTEM"*/};
		
		String sql="delete from ras_aircraft_basic ab where ab.id in (:ids)";
		sf.getCurrentSession().createSQLQuery(sql).setParameterList("ids", ids).executeUpdate();
		
//		for(String name:tableNames){
//			sql="delete from ras_aircraft_"+name+" ab where ab.basicID in (:ids)";
//			sf.getCurrentSession().createSQLQuery(sql).setParameterList("ids", ids).executeUpdate();
//		}
		
		BigDecimal count=aircraftBasicDao.countMaininfo(overviewID);
		if(count.intValue()==0){
			sql="select * from ras_aircraft_basic where overviewid='"+overviewID+"'";
			@SuppressWarnings("unchecked")
			List<AircraftBasic> list=sf.getCurrentSession().createSQLQuery(sql).addEntity(AircraftBasic.class).list();
			if(list.size()!=0){
				list.get(0).setMainInfo("1");
			}
			
		}
		
	}

	@Override
	public void setMainModelParam(String basicID,String overviewID) {
		aircraftBasicDao.setMaininfo(basicID, overviewID);
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

	/**
	 * 查询机型参数
	 * @param option create modify del load
	 */
	@Override
	public JSONObject findModelParam(DataVo vo) {
		List<SearchParam> searchTagList=searchTagDao.findAllChildren(null);
		
		String basicID="";
		AircraftOverview overview=(AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, vo.getOverviewID());
		
		switch(vo.getOption()){
		case "load":
			//AircraftBasic ab=aircraftBasicDao.getMainAircraftBasic(vo.getOverviewID());
			//basicID=(ab==null?"":ab.getBasicID());
			basicID=vo.getBasicID();
			break;
		case "create":
			break;
		case "modify":
			basicID=vo.getBasicID();
			break;
		}
		
		//添加主要只读数据.
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("basicID", basicID);
		paramMap.put("modelName", overview.getModelName());
		paramMap.put("modelCname", overview.getModelCname());
		paramMap.put("modelEname", overview.getModelEname());
		
		
		//读取所有参数及数据
		JSONObject jo=new JSONObject();
		jo.put("modelName", overview.getModelName());//用于显示页面标题
		//basic表格,需要同时加载进overview表格数据
		

		JSONArray ja=new JSONArray();
		JSONObject tem=new JSONObject();
		tem.put("caption", "基本信息");
		tem.put("dataMap", modalParamToMap("1",paramMap,searchTagList));
		ja.add(tem);
		tem=new JSONObject();
		tem.put("caption", "重量信息");
		tem.put("dataMap", modalParamToMap("2",paramMap,searchTagList));
		ja.add(tem);
		tem=new JSONObject();
		tem.put("caption", "布局信息");
		tem.put("dataMap", modalParamToMap("3",paramMap,searchTagList));
		ja.add(tem);
		tem=new JSONObject();
		tem.put("caption", "性能信息");
		tem.put("dataMap", modalParamToMap("4",paramMap,searchTagList));
		ja.add(tem);
		tem=new JSONObject();
		tem.put("caption", "动力信息");
		tem.put("dataMap", modalParamToMap("5",paramMap,searchTagList));
		ja.add(tem);
		tem=new JSONObject();
		tem.put("caption", "系统信息");
		tem.put("dataMap", modalParamToMap("6",paramMap,searchTagList));
		ja.add(tem);
		jo.put("paramMap", ja);
		return jo;
	}
	
	private JSONArray modalParamToMap(String parentTagID,Map<String,String> paramMap,List<SearchParam> searchTagList){
		JSONArray ja=new JSONArray();

		//读取全部字段
		List<SearchParam> tags=new ArrayList<SearchParam>();
		SearchParam mTag=null;
		for(SearchParam tag:searchTagList){
			if(tag.getId().toString().equals(parentTagID)){
				mTag=tag;
			}else if(tag.getParent_id().equals(parentTagID)){
				tags.add(tag);
			}
		}
		String sql="";
		if(parentTagID.equals("1")){
			sql="select * from ras_aircraft_"+mTag.getEnname()+" ab where ab.id='"+paramMap.get("basicID")+"'";
		}else{
			sql="select * from ras_aircraft_"+mTag.getEnname()+" ab where ab.basicID='"+paramMap.get("basicID")+"'";	
		}
		//数据Map
		Map<String,String> dataMap=(Map<String,String>)sf.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		
		//ras_aircraft_basic 需多行处理
		if(parentTagID.equals("1")){
			JSONObject jo=new JSONObject();
			jo.put("elID", "dataSources");
			jo.put("elLabel", "数据来源");
			jo.put("elValue", dataMap==null?"":dataMap.get("DATASOURCES"));
			jo.put("validate", "required");//设置为必需项.
			ja.add(jo);
		}
		
		ja.addAll(loadModalParamToEl(tags));
		if(parentTagID.equals("1")){
			for(Map.Entry<String, String> entry:paramMap.entrySet()){
				for(Object obj:ja){
					JSONObject tem=(JSONObject)obj;
					if(tem.containsValue(entry.getKey())){
						tem.put("elValue", entry.getValue());
						tem.put("readonly", "true");
						break;
					}
				}
			}
		}
		if(paramMap.get("basicID")==null){
			//新建
			return ja;
		}
		else{
			//修改
			if(dataMap==null){
				return ja;
			}
			for(Object ob:ja){
				JSONObject jo=(JSONObject)ob;
				for (SearchParam tag : tags) {
					if(jo.containsValue(tag.getEnname())){
						String s=""; 
						Object obj=dataMap.get(tag.getEnname().toUpperCase());
						if(obj==null){
							break;
						}
						if(obj instanceof BigDecimal){
							s=((BigDecimal) obj).intValue()+"";
						}else if(obj instanceof String){
							s=obj.toString();
						}
						jo.put("elValue", s);
						jo.put("elTypeValue", tag.getUi_value());
					}
				}
			}
		}
		return ja;

	}

	/**
	 * 加载所有的TAG到EL
	 * @param tags
	 * @return
	 */
	private JSONArray loadModalParamToEl(List<SearchParam> tags){
		JSONArray ja=new JSONArray();
		for (SearchParam tag : tags) {
			JSONObject jo=new JSONObject();
			jo.put("elID", tag.getEnname());
			jo.put("elLabel", tag.getName());
			jo.put("elType", tag.getUi_type());
			jo.put("elTypeValue", tag.getUi_value());
			ja.add(jo);
		}
		return ja;
	}

	@Override
	public void saveModelParam(String tableName,Map<String, String> map,boolean isCreate) {
		tableName="RAS_AIRCRAFT_"+tableName;
		
		String sql="select col.COLUMN_NAME,col.DATA_TYPE,col.DATA_LENGTH from user_tab_columns col where Table_Name='"+tableName+"'";
		List<Object[]> cols=sf.getCurrentSession().createSQLQuery(sql).list();
		StringBuilder param=new StringBuilder();

		String basicID=map.get("basicID");
		if(isCreate){
			//新建
			map.put("ID", CommonTool.getGUID(sf.getCurrentSession()));//获取ID值

			param.append("INSERT INTO "+tableName);
			StringBuilder c=new StringBuilder();
			StringBuilder v=new StringBuilder();
			boolean flag=false;
			for(int i=0;i<cols.size();i++){
				String col=cols.get(i)[0].toString();
				String val=map.get(col);
				if(!map.containsKey(col)){
					continue;
				}
				
				val=transformSQLType(cols.get(i)[1].toString(),val);
				if(flag){
					c.append(",");
					v.append(",");
				}
				c.append(col);
				v.append(val);
				flag=true;
			}
			if(tableName.equals("RAS_AIRCRAFT_BASIC")){
				c.append(",editor");
				v.append(",'"+Common.getLoginUser().getUserid()+"'");
			}
			param.append(" ("+c.toString()+") values ("+v.toString()+")");
		}else{
			//修改
			if(!tableName.equals("RAS_AIRCRAFT_BASIC")){
				sql="select count(1) from "+tableName+" where basicid='"+basicID+"'";
				BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
				if(count.intValue()==0){
					sql="INSERT INTO "+tableName+"(id,basicID) values ('"+CommonTool.getGUID(sf.getCurrentSession())+"','"+basicID+"')";
					sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
				}
			}
			
			param.append("update "+tableName+" set ");
			boolean flag=false;
			for(int i=0;i<cols.size();i++){
				String col=cols.get(i)[0].toString();
				String val=map.get(col);
				//请求数据里没有修改的值,应过滤.
				if(!map.containsKey(col)){
					continue;
				}
				val=transformSQLType(cols.get(i)[1].toString(),val);
				
				if(flag){
					param.append(",");
				}
				
				param.append(col+"="+val+"");
				flag=true;
			}
			if(tableName.equals("RAS_AIRCRAFT_BASIC")){
				param.append(" where ID='"+basicID+"' ");
			}else{
				param.append(" where BASICID='"+basicID+"' ");
			}
			
		}
		sf.getCurrentSession().createSQLQuery(param.toString()).executeUpdate();
	}
	
	private String transformSQLType(String type,String val){
		if(val==null||val.equals("")){
			return null;
		}else{
			switch (type.toUpperCase()) {
			case "VARCHAR2":
				val = "'" + val + "'";
				break;
			case "NUMBER":
				break;
			case "DATE":
				break;
			}
			return val;
		}
	
	}

	@Override
	public void findTableModelGrid(DataVo vo) {
		
		String hql="from AircraftOverview where 1=1  ";
		if(vo.getModelName()!=null){
			hql+=" and lower(modelName) like '%"+vo.getModelName().toLowerCase()+"%'";
		}

		Long count=(Long)sf.getCurrentSession().createQuery("select count(1) "+hql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		if(count.intValue()==0){
			return;
		}
		List list= sf.getCurrentSession().createQuery(hql)
				     .setFirstResult(vo.getStart())
				     .setMaxResults(vo.getLength())
				     .list();//query.setProperties(ab).list();//.setEntity("ab", ab).list();
		vo.setData(list);
		
	}

	@Override
	public void saveModelPhotoFile(AircraftOverview ao) {
		if(ao.getPhotoFile()!=null){
			MultipartFile image=ao.getPhotoFile();
			//ao=(AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, ao.getOverviewID());
			String contentType = image.getContentType();//文件类型.image/jpeg.
	        String type = contentType.substring(contentType.indexOf("/") + 1);//文件扩展名
			String fileName=System.currentTimeMillis()+new Random().nextInt(100) + "." + type;
			try {
				String dir=CommonTool.PHOTO_URL_PREFIX+ao.getModelName()+"/";
				ao.setPhotoUrl(dir+fileName);
				File file=new File(dir);
				if(!file.exists()){
					file.mkdir();
				}
				String path=CommonTool.PHOTO_DIR+ao.getModelName()+"\\"+fileName;
				image.transferTo(new File(path));
				
				ao=(AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, ao.getOverviewID());
				if(ao.getPhotoUrl()!=null){
					String[] s=ao.getPhotoUrl().split("/");
					String fileName_tem=s[s.length-1];
					file=new File(CommonTool.PHOTO_DIR+ao.getModelName()+"\\"+fileName_tem);
					file.delete();
				}

				
				ao.setPhotoUrl(dir+fileName);
				
//				String sql="update RAS_AIRCRAFT_OVERVIEW set photoURL='"+path+"' WHERE ID='"+ao.getOverviewID()+"'";
//				sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sf.getCurrentSession().saveOrUpdate(ao);
		
	}

	@Override
	public void delModelImageFile(String overviewID) {
		AircraftOverview ao=(AircraftOverview)sf.getCurrentSession().get(AircraftOverview.class, overviewID);
		
		String[] s=ao.getPhotoUrl().split("/");
		String fileName_tem=s[s.length-1];
		File file=new File(CommonTool.PHOTO_DIR+ao.getModelName()+"\\"+fileName_tem);
		file.delete();
		
		ao.setPhotoUrl("");
	}

	@Override
	public List findTableSQL(String tableName) {
		return sf.getCurrentSession().createSQLQuery("select name from "+tableName).list();
		
	}
}
