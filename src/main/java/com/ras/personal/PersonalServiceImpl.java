package com.ras.personal;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.personal.report.Report;

@Service
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDao dao;

	@Override
	public void findPersonalReportGrid(PersonalVo vo) {
		dao.findPersonalReportGrid(vo);
	}

	@Override
	public void findPersonalReportContentGrid(PersonalVo vo) {
		dao.findPersonalReportContentGrid(vo);
	}

	@Override
	public Report findPersonalReport(PersonalVo vo) {
		return dao.findPersonalReport(vo);
	}
	
	
}
