package com.ras.searchParam.searchCriteria;

import java.util.Map;

import com.ras.tool.ReturnVo;

public class SearchCriteriaVo<T> extends ReturnVo<T> {

	
	private String modelName;
	
	private String onlyRead;
	
	private Map<String,String> paramMap;
	
	
	
	public String getOnlyRead() {
		return onlyRead;
	}
	public void setOnlyRead(String onlyRead) {
		this.onlyRead = onlyRead;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Map<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	
}
