package com.ras.comparison;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftBasic.AircraftBasicDao;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftOverview.AircraftOverviewDao;

import sun.print.resources.serviceui;


@Service
public class ComparisonServiceImpl implements ComparisonService {

	@Autowired
	private AircraftOverviewDao aircraftOverviewDao;

	@Autowired
	private AircraftBasicDao aircraftBasicDao;
	
	@Autowired
	private ComparisonDao dao;

	
	@Override
	public void findModelGird(ComparisonVo vo) {
		dao.findModelGird(vo);
//		AircraftOverview ao=new AircraftOverview();
//		ao.setModelName(vo.getModelName());
//		vo.setRecordsTotal(aircraftOverviewDao.count(ao));
//		vo.setData(aircraftOverviewDao.findByProperty(ao,vo.getStart(),vo.getLength()));
	}

	@Override
	public List<?> findComparisonDetailGrid(String[] overviewID,String[] modelNames,String[] basicID) {
		if(overviewID!=null&&overviewID.length!=0){
			basicID=new String[overviewID.length];
			for(int i=0;i<overviewID.length;i++){
				basicID[i]=aircraftBasicDao.getMainAircraftBasic(overviewID[i]).getBasicID();
			}
			
		}
		return dao.findComparisonDetailGrid(modelNames,basicID);
	}



}
