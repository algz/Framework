/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.userDataPrivilidge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="RAS_USER_DATA_PRIVILIDGE")
@Entity
public class UserDataPrivilidge{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String userDataPrivilidgeID;
	
	@Column(name="USERID")
	private String userID;
	
	@Column(name="DATAID")
	private String dataID;
	
	@Column(name="DATATABLE")
	private String dataTable;

	public String getUserDataPrivilidgeID() {
		return userDataPrivilidgeID;
	}

	public void setUserDataPrivilidgeID(String userDataPrivilidgeID) {
		this.userDataPrivilidgeID = userDataPrivilidgeID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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
	
	
}