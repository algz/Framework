package com.ras.personal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.personal.report.Report;
import com.ras.searchParam.SearchParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class PersonalDaoImpl implements PersonalDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public void findPersonalReportGrid(PersonalVo vo) {
		String sql=" from RAS_REPORT t";
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		vo.setRecordsTotal(count.intValue());
		
		List<Report> reportList=sf.getCurrentSession().createSQLQuery("select * "+sql)
													  .addEntity(Report.class)
													  .setFirstResult(vo.getStart())
													  .setMaxResults(vo.getLength())
													  .list();
		vo.setData(reportList);
	}

	@Override
	public void findPersonalReportContentGrid(PersonalVo vo) {
		Report report=(Report)sf.getCurrentSession().get(Report.class, vo.getReportID());
		String sql="select v.content_key,v.content_val from searchReportContentView v "
				+ "where v.id='"+vo.getReportID()+"'";
		List<Object[]> contentList=sf.getCurrentSession().createSQLQuery(sql).list();
		//JSONArray ja=new JSONArray();
		List<List<String>> list=new ArrayList<List<String>>();
		
		//[['机型名称','CL-1200／X-27','AT-12']] <=[['机型名称','CL-1200／X-27'],['机型名称','AT-12']]
		for(Object[] objs : contentList){
			boolean flag=true;
			for(List<String> t:list){
				if(t.get(0).equals(objs[0])){
					t.add(objs[1]+"");
					flag=false;
					break;
				}
			}
			if(flag){
				List<String> temList=new ArrayList<String>();
				temList.add(objs[0]+"");
				temList.add(objs[1]+"");
				list.add(temList);
			}

		}
		vo.setData(list);
	}

	@Override
	public Report findPersonalReport(PersonalVo vo) {
		return (Report)sf.getCurrentSession().get(Report.class, vo.getReportID());
	}

}
