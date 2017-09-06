/**
 * 
 */
package com.ras.simpleSearch;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.searchParam.searchCriteria.SearchCriteriaService;
import com.ras.searchParam.searchCriteria.SearchCriteriaVo;

import net.sf.json.JSONArray;

/**
 * @author algz
 *
 */
@Service
public class SimpleSearchServiceImpl implements SimpleSearchService {

	@Autowired
	private SimpleSearchDao dao;
	
	
	/* (non-Javadoc)
	 * @see com.ras.simpleSearch.SimpleSearchService#GetSearchTreeNode(java.util.Map)
	 */
	@Override
	public JSONArray getSearchTreeNode(Map<String, String> m) {
		return dao.getSearchTreeNode(m);
	}

	@Override
	public void simpleSearchGird(SimpleSearchVo vo) {
		dao.simpleSearchGird(vo);
	}

}
