package com.ras.documnet.paramConfig.productParam;

import javax.persistence.Column;

import com.ras.tool.ReturnVo;

public class ProductParamVo<T> extends ReturnVo<T>{

	private String productID;
	
	private String productName;
	
	private String parentID;

	private String paramName;
	
	private String ui_type;
	
	private String ui_value;
	
	private String unit;
	
	private String sequence;
	
	private String isLeaf;
	
	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getUi_type() {
		return ui_type;
	}

	public void setUi_type(String ui_type) {
		this.ui_type = ui_type;
	}

	public String getUi_value() {
		return ui_value;
	}

	public void setUi_value(String ui_value) {
		this.ui_value = ui_value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
	
}
