package com.ras.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ras.aircraftType.AircraftType;
import com.ras.aircraftType.AircraftTypeDao;
import com.ras.country.Country;
import com.ras.country.CountryDao;

@Service
public class SearchTagServiceImpl implements SearchTagService {

	@Autowired
	private SearchTagDao dao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private AircraftTypeDao aircraftTypeDao;
	
	@Override
	public List<SearchTag> findAllParent() {
		return dao.findAllParent();
	}

	@Override
	public List<SearchTag> findAllByIds(String[] ids) {
		List<SearchTag> list=dao.findAllByIds(ids);

		
		for(SearchTag tag :list){
			switch (tag.getUi_type()) {
				case "checkbox":
					if(tag.getUi_value()!=null&&tag.getUi_value().equals("algz_country")){
						//国家
						List<Country> countryList=countryDao.findAll();
						for(Country c:countryList){
							SearchTag t=new SearchTag();
							t.setId(c.getId());
							t.setName(c.getName());
							tag.getSearchTags().add(t);
						}
					}else if(tag.getUi_value()!=null&&tag.getUi_value().equals("algz_aircraft_type")){
						//飞机类型
						List<AircraftType> aircraftTypeList=aircraftTypeDao.findAll();
						for(AircraftType a:aircraftTypeList){
							SearchTag t=new SearchTag();
							t.setId(a.getId());
							t.setName(a.getName());
							tag.getSearchTags().add(t);
						}
					}else{
						String[] vals=tag.getUi_value().split(",");
						for(String v:vals){
							SearchTag t=new SearchTag();
							t.setId(null);
							t.setName(v);
							tag.getSearchTags().add(t);
						}
					}

					break;
			}
		}
	    
		return list;
	}
	
	@Override
	@Transactional
	public void save(SearchTag searchTag){
		dao.save(searchTag);
	}

	@Override
	public Map<String, String> searchSummarize(String overviewID) {
		// TODO Auto-generated method stub
		return null;
	}
}
