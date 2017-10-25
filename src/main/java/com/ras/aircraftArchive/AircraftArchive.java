/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftArchive;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftOverview.AircraftOverview;

@Table(name="RAS_AIRCRAFT_ARCHIVE")
@Entity
public class AircraftArchive{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String archiveID;
	
	@Column(name="ARCHIVE_NAME")
	private String archiveName;
	
	@Column(name="ARCHIVE_URL")
	private String archiveUrl;
	
	@Column(name="ARCHIVE_DESC")
	private String archiveDesc;

	@Column(name="ARCHIVE_CATEGORY")
	private String archiveCategory;
	
	@Column(name="ARCHIVE_DISPLAYNAME")
	private String archiveDisplayName;
	
	@Column(name="TAG")
	private String tag;
	
	@Column(name="EDITOR")
	private String editor;
	
	/**
	 * 修改日期
	 */
	@Column(name="MODIFY_DATE",insertable=false,updatable=false)
	private Date modifyDate;
	
	/**
	 * 权限级别:1只能允许自己可视;2部门内允许可视;3或空完全可视
	 */
	@Column(name="PERMISSION_LEVEL")
    private String permissionLevel;
	
	/**
	 * 外键
	 */
	@Column(name="OVERVIEWID")
	private String overviewID;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="overviewid",nullable=true,updatable = false, insertable = false)
	private AircraftOverview overview;
	
	
	
	@Transient
	private MultipartFile archiveFile;
	
	@Transient
	private String modelName;



	public String getArchiveID() {
		return archiveID;
	}

	public void setArchiveID(String archiveID) {
		this.archiveID = archiveID;
	}

	public String getOverviewID() {
		return overviewID;
	}

	public void setOverviewID(String overviewID) {
		this.overviewID = overviewID;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getArchiveUrl() {
		return archiveUrl;
	}

	public void setArchiveUrl(String archiveUrl) {
		this.archiveUrl = archiveUrl;
	}

	public String getArchiveDesc() {
		return archiveDesc;
	}

	public void setArchiveDesc(String archiveDesc) {
		this.archiveDesc = archiveDesc;
	}

	public String getArchiveCategory() {
		return archiveCategory;
	}

	public void setArchiveCategory(String archiveCategory) {
		this.archiveCategory = archiveCategory;
	}

	public MultipartFile getArchiveFile() {
		return archiveFile;
	}

	public void setArchiveFile(MultipartFile archiveFile) {
		this.archiveFile = archiveFile;
	}

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

	public String getArchiveDisplayName() {
		return archiveDisplayName;
	}

	public void setArchiveDisplayName(String archiveDisplayName) {
		this.archiveDisplayName = archiveDisplayName;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public AircraftOverview getOverview() {
		return overview;
	}

	public void setOverview(AircraftOverview overview) {
		this.overview = overview;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(String permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	
	
	
	
}