package com.ras.documnet.dataManager.productData;

import com.ras.tool.ReturnVo;

/**
 * TbDeviceContractmanagement entity. @author MyEclipse Persistence Tools
 */
public class ProductManagerVo<T> extends ReturnVo<T>{

	// Fields
	
	private String overviewID;
	
	private String basicID;
	
	private String modelName;
	
//	private String archiveName;
	
	private String option;
	
	private String photoCategory;
	
	private String tag;
	
	private Integer start;
	private Integer limit;
	private Integer count;
	
	// Constructors

	/** default constructor */
	public ProductManagerVo() {
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

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getPhotoCategory() {
		return photoCategory;
	}

	public void setPhotoCategory(String photoCategory) {
		this.photoCategory = photoCategory;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	
	
	
	
	
}