package com.ras.documnet.data;

import com.ras.tool.ReturnVo;

/**
 * TbDeviceContractmanagement entity. @author MyEclipse Persistence Tools
 */
public class DataVo<T> extends ReturnVo<T>{

	// Fields
	
	private String overviewID;
	
	private String basicID;
	
	private Integer start;
	private Integer limit;
	private Integer count;
	
	// Constructors

	/** default constructor */
	public DataVo() {
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOverviewID() {
		return overviewID;
	}

	public void setOverviewID(String overviewID) {
		this.overviewID = overviewID;
	}

	public String getBasicID() {
		return basicID;
	}

	public void setBasicID(String basicID) {
		this.basicID = basicID;
	}

	
	
	
	
	
}