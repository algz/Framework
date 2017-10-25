/**
 * 
 */
package com.ras.approval;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author algz
 *
 */

@Entity
@Table(name="RAS_APPROVAL")
public class Approval {
	@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String approvalID;
	
	/**
	 * 审批的数据ID
	 */
	@Column(name="DATA_ID")
	private String dataID;
	
	/**
	 * 审批的数据关联表
	 */
	@Column(name="DATA_TABLE")
	private String dataTable;
	
	/**
	 * 审批的数据权限级别:1只能允许自己可视;2部门内允许可视;3或空完全可视;4范围可视,关联范围可视用户表.
	 */
	@Column(name="PERMISSION_LEVEL")
	private String permissionLevel;

	/**
	 * 权限可视范围
	 * permissionLevel=4,userid以逗号分隔.
	 */
	@Column(name="PERMISSION_USER_RANGE")
	private String permissionUserRange;
	
	/**
	 * 审批结果:1同意;0不同意
	 */
	@Column(name="APPROVAL_RESULT")
	private String approvalResult;
	
	/**
	 * 审批意见
	 */
	@Column(name="APPROVAL_COMMENT")
	private String approvalComment;
	
	/**
	 * 审批人
	 */
	@Column(name="APPROVAL_EDITOR")
	private String approvalEditor;
	
	/**
	 * 提交人
	 */
	@Column(name="SUBMITTER")
	private String submitter;
	
	/**
	 * 创建日期(只读属性,数据库自动填写
	 */
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	/**
	 * 审批日期
	 */
	@Column(name="APPROVAL_DATE")
	private Date approvalDate;

	/**
	 * 审批状态:0或空未审批,1审批中,2审批完成
	 */
	@Column(name="APPROVAL_STATUS")
	private String approvalStatus;
	
	/**
	 * P2M返回的流程ID
	 */
	@Column(name="P2M_APPROVAL_ID")
	private String p2mApprovalID;
	
	/**
	 * P2M返回的审批结果
	 */
	@Column(name="RETURNREASON")
	private String returnReason;
	
	public String getApprovalID() {
		return approvalID;
	}

	public void setApprovalID(String approvalID) {
		this.approvalID = approvalID;
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

	public String getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(String permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getApprovalComment() {
		return approvalComment;
	}

	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}

	public String getApprovalEditor() {
		return approvalEditor;
	}

	public void setApprovalEditor(String approvalEditor) {
		this.approvalEditor = approvalEditor;
	}

	public Date getCreateDate() {
		return createDate;
	}

//	public void setCreateDate(Date createDate) {
//		this.createDate = createDate;
//	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getP2mApprovalID() {
		return p2mApprovalID;
	}

	public void setP2mApprovalID(String p2mApprovalID) {
		this.p2mApprovalID = p2mApprovalID;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getPermissionUserRange() {
		return permissionUserRange;
	}

	public void setPermissionUserRange(String permissionUserRange) {
		this.permissionUserRange = permissionUserRange;
	}


	
	
}
