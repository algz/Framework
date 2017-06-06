package com.ras.analyze;

import com.ras.tool.ReturnVo;

/**
 * TbDeviceContractmanagement entity. @author MyEclipse Persistence Tools
 */
public class AnalyzeVo<T> extends ReturnVo<T>{

	// Fields
	
	private String overviewID;
	
	private String basicID;
	
	private String modelName;
	
	private Integer count;
	
	// Constructors

	/** default constructor */
	public AnalyzeVo() {
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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	
	
	
	
	
}