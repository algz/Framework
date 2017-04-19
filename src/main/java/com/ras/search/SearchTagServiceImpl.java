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

import net.sf.json.JSONObject;

@Service
public class SearchTagServiceImpl implements SearchTagService {

	@Autowired
	private SearchTagDao dao;
	

	
	@Override
	public List<SearchTag> findAllParent() {
		return dao.findAllParent();
	}

	@Override
	public List<SearchTag> findAllByIds(String[] ids) {
		List<SearchTag> list=dao.findAllByIds(ids);
		return list;
	}
	
	@Override
	@Transactional
	public void save(SearchTag searchTag){
		dao.save(searchTag);
	}

	@Override
	public JSONObject addNoteForTagInput(String overviewID, String[] inputNames) {
		return dao.addNoteForTagInput(overviewID, inputNames);
	}
}
