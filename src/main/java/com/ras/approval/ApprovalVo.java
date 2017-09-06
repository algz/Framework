package com.ras.approval;

import com.ras.tool.ReturnVo;

/**
 * TbDeviceContractmanagement entity. @author MyEclipse Persistence Tools
 */
public class ApprovalVo<T> extends ReturnVo<T>{

	// Fields
	
	private String approvalID;
	
	private String dataID;
	
	private String dataCategory;
	// Constructors

	/** default constructor */
	public ApprovalVo() {
	}

	public String getDataCategory() {
		return dataCategory;
	}

	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}

	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	public String getApprovalID() {
		return approvalID;
	}

	public void setApprovalID(String approvalID) {
		this.approvalID = approvalID;
	}

	
	
	
	
	
}