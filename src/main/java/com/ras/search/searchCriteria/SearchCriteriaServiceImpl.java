package com.ras.search.searchCriteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchCriteriaServiceImpl implements SearchCriteriaService {

	@Autowired
	private SearchCriteriaDao dao;
	
	@Override
	public List<String[]> SearchCriteriaGird() {
		
		return dao.SearchCriteriaGird();
	}

}
