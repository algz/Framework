package com.ras.aircraftReport;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ras.tool.CommonTool;

import algz.platform.util.Common;

@Repository
public class AircraftReportDaoImpl implements AircraftReportDao {
	
	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<AircraftReport> findAircraftReportGrid(AircraftReport report,Integer[] arr) {
		String sql=" from RAS_AIRCRAFT_REPORT t WHERE EDITOR='"+report.getEditor()+"'";
		
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		arr[2]=count.intValue();
		
		return sf.getCurrentSession().createSQLQuery("select * "+sql)
													  .addEntity(AircraftReport.class)
													  .setFirstResult(arr[0])
													  .setMaxResults(arr[1])
													  .list();
	}

	@Override
	public List<List<String>> findAircraftReportContentGrid(String reportID) {
//		Report report=(Report)sf.getCurrentSession().get(Report.class, vo.getReportID());
		String sql="select v.content_key,v.content_val from searchReportContentView v "
				+ "where v.id='"+reportID+"'";
		List<Object[]> contentList=sf.getCurrentSession().createSQLQuery(sql).list();
		//JSONArray ja=new JSONArray();
		List<List<String>> list=new ArrayList<List<String>>();
		
		//[['机型名称','CL-1200／X-27','AT-12']] <=[['机型名称','CL-1200／X-27'],['机型名称','AT-12']]
		for(Object[] objs : contentList){
			boolean flag=true;
			for(List<String> t:list){
				if(t.get(0).equals(objs[0])){
					t.add(objs[1]==null?"":objs[1].toString());
					flag=false;
					break;
				}
			}
			if(flag){
				List<String> temList=new ArrayList<String>();
				temList.add(objs[0]==null?"":objs[0].toString());
				temList.add(objs[1]==null?"":objs[1].toString());
				list.add(temList);
			}

		}
		return list;
	}

	@Override
	public AircraftReport findAircraftReport(AircraftReport report) {
		return (AircraftReport)sf.getCurrentSession().get(AircraftReport.class, report.getReportID());
	}
	
	@Override
	public void saveAircraftReport(AircraftReport aircraftReport, String[] reportContent) {
		sf.getCurrentSession().save(aircraftReport);
		for(String rec:reportContent){
			String[] arr=rec.split("，");
			AircraftReportKey contentKey=new AircraftReportKey();
			contentKey.setReportID(aircraftReport.getReportID());
			contentKey.setContentKey(arr[0]);
			sf.getCurrentSession().save(contentKey);
			
			for(int i=1;i<arr.length;i++){
				AircraftReportVal contentVal=new AircraftReportVal();
				contentVal.setContentKeyID(contentKey.getReportContentKeyID());
				contentVal.setContentVal(arr[i]);
				contentVal.setContentValSeq(i+"");
				sf.getCurrentSession().save(contentVal);
			}
		}
		
	}

	@Override
	public void del(AircraftReport aircraftReport) {
		String sql="delete RAS_AIRCRAFT_REPORT where editor='"+aircraftReport.getEditor()+"'";
//		if(favorites.getFavoritesName()!=null&&!favorites.getFavoritesName().equals("")){
//			sql+=" and FAVORITESNAME='"+favorites.getFavoritesName()+"'";
//		}
//		if(favorites.getUrl()!=null&&!favorites.getUrl().equals("")){
//			sql+=" and FAVORITESURL='"+favorites.getUrl()+"'";
//		}
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
}
