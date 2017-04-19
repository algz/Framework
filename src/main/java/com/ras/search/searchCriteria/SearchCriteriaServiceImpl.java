package com.ras.search.searchCriteria;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.search.SearchTagVo;

import net.sf.json.JSONArray;

@Service
public class SearchCriteriaServiceImpl implements SearchCriteriaService {

	@Autowired
	private SearchCriteriaDao dao;
	
	@Override
	public void SearchCriteriaGird(SearchCriteriaVo vo) {
		dao.SearchCriteriaGird(vo);
	}


}
