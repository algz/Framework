package com.ras.search;

import com.ras.tool.ReturnVo;

public class SearchTagVo<T> extends ReturnVo<T> {

	
	private String modelName;
	
	private String onlyRead;
	
	
	
	
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

	
}
