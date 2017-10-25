package com.ras.personal.report;

import com.ras.tool.ReturnVo;

/**
 * TbDeviceContractmanagement entity. @author MyEclipse Persistence Tools
 */
public class ReportVo<T> extends ReturnVo<T>{

	// Fields
	
	private String reportID;
	
	private Integer count;
	
	// Constructors

	/** default constructor */
	public ReportVo() {
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public String getReportID() {
		return reportID;
	}


	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	
	
	
	
	
}