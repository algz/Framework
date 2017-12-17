/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftProductParam;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="RAS_AIRCRAFT_PRODUCT_PARAM")
public class AircraftProductParam{
	
	@Id
	@Column(name="ID")
	@GenericGenerator(name="ALGZGenerator", strategy = "guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String productID;
	
	/**
	 * 产品名称
	 */
	@Column(name="PRODUCTNAME")
	private String productName;
	
	/**
	 * 产品详情
	 */
	@Column(name="PRODUCTDESCRIPTION")
	private String description;
	
	/**
	 * 是否叶子结点.0不是叶子结点;1是叶子结点.
	 */
	@Column(name="ISLEAF")
	private String isLeaf;
	
	@Column(name="PARENTID")
	private String parentID;

	@Column(name="EDITOR")
	private String editor;
	
	@Column(name="PARAMNAME")
	private String paramName;
	
	@Column(name="UI_TYPE")
	private String ui_type;
	
	@Column(name="UI_VALUE")
	private String ui_value;
	
	@Column(name="UNIT")
	private String unit;
	
	@Column(name="SEQUENCE")
	private String sequence;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
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

	

}