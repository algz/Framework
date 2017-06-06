package com.ras.searchParam;

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
public class SearchParamServiceImpl implements SearchParamService {

	@Autowired
	private SearchParamDao dao;
	

	
	@Override
	public List<SearchParam> findAllParent() {
		return dao.findAllParent();
	}

	@Override
	public List<SearchParam> findAllByIds(String[] ids) {
		List<SearchParam> list=dao.findAllByIds(ids);
		return list;
	}
	
	@Override
	@Transactional
	public void save(SearchParam searchTag){
		dao.save(searchTag);
	}

	@Override
	public JSONObject addNoteForTagInput(String overviewID, String[] inputNames) {
		return dao.addNoteForTagInput(overviewID, inputNames);
	}
}
