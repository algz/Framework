/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.approvalRange;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="RAS_APPROVAL_RANGE")
@Entity
public class ApprovalRange{
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String rangeID;
	
	@Column(name="DATAID")
	private String dataID;
	
	@Column(name="DATATABLE")
	private String dataTable;
	
	@Column(name="USERID")
	private String userID;

	@Column(name="APPROVALID")
	private String approvalID;
	
	@Column(name="VALID")
	private String valid;
	
	


	public String getRangeID() {
		return rangeID;
	}

	public void setRangeID(String rangeID) {
		this.rangeID = rangeID;
	}

	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	
	
	public String getDataTable() {
		return dataTable;
	}

	public void setDataTable(String dataTable) {
		this.dataTable = dataTable;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getApprovalID() {
		return approvalID;
	}

	public void setApprovalID(String approvalID) {
		this.approvalID = approvalID;
	}
	
	
	
	
}