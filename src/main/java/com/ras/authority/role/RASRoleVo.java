package com.ras.authority.role;

import com.ras.tool.ReturnVo;

/**
 * TbDeviceContractmanagement entity. @author MyEclipse Persistence Tools
 */
public class RASRoleVo<T> extends ReturnVo<T>{

	// Fields
	
	private String userid;
	
	private String roleid;
	
	private String rolename;
	
	private String rolecname;
	
	private String description;
	
	private String operate;
	
	// Constructors

	/** default constructor */
	public RASRoleVo() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String getRolename() {
		return rolename;
	}

	public String getRolecname() {
		return rolecname;
	}

	public void setRolecname(String rolecname) {
		this.rolecname = rolecname;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}





	
	
	
	
	
}