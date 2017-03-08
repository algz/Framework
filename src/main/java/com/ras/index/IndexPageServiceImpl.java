package com.ras.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexPageServiceImpl implements IndexPageService{

	@Autowired
	private IndexPageDao dao;
	
	@Override
	public List<?> searchIndexPage(String modelname) {
		// TODO Auto-generated method stub
		return dao.searchIndexPage(modelname);
	}

}
