package com.ras.productSearch;


import com.ras.tool.ReturnVo;

public class ProductSearchVo<T> extends ReturnVo<T> {

	private String paramID;

	private String paramValue;
	
	public String getParamID() {
		return paramID;
	}

	public void setParamID(String paramID) {
		this.paramID = paramID;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	

	
}
