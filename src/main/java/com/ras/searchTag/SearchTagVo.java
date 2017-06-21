package com.ras.searchTag;

import com.ras.tool.ReturnVo;

public class SearchTagVo<T> extends ReturnVo<T> {
	
	/**
	 * 标签名称
	 */
	private String tag;
	
	private String tagCategory;
	
	private String modelName;

	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTagCategory() {
		return tagCategory;
	}

	public void setTagCategory(String tagCategory) {
		this.tagCategory = tagCategory;
	}
	
	
	
}
