package com.ras.searchTag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftArchive.AircraftArchive;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftPicture.AircraftPicture;
import com.ras.aircraftPicture.AircraftPictureDao;

@Service
public class SearchTagServiceImpl implements SearchTagService{

//	@Autowired
//	private AircraftTagDaoImpl aircraftTagDao;
	
	@Autowired
	private AircraftPictureDao aircraftPictureDao;
	
	@Autowired
	private SearchTagDao dao;
	
	@Override
	public void searchIndexPage(SearchTagVo vo) {
		dao.searchIndexPage(vo);
	}

	@Override
	public void findTagSearchGird(SearchTagVo vo) {
		
		if(vo.getTagCategory()!=null&&vo.getTag()!=null){
			switch(vo.getTagCategory()){
			case "param":
//				AircraftTag tag=new AircraftTag();
//				tag.setTagName(vo.getTagName());
//				tag.setRelationTable(vo.getTagCategory());
//				vo.setRecordsTotal(aircraftTagDao.count(tag).intValue());
//				if(vo.getRecordsTotal()!=0){
//					List<AircraftTag> listTag=aircraftTagDao.find(tag,vo.getStart(),vo.getLength());
//					vo.setData(dao.findTagSearchForParamGird(listTag));
//				}
				dao.findTagSearchForParamGird(vo);
				break;
			case "picture":
				dao.findTagSearchForPictureGird(vo);
				break;
			case "archive":
				dao.findTagSearchForArchiveGird(vo);
				break;
			}
			
		}
	}

}
