package com.ras.search;

import com.ras.tool.ReturnVo;

public class SearchTagVo extends ReturnVo {

	
	private Integer start;
	
	private Integer limit;
	
	private Integer count;
	
	private String modelName;
	
	
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
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
	
	
}
