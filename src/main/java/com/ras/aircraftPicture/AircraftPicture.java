/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftPicture;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftOverview.AircraftOverview;

import algz.platform.util.json.JSONPropertyFilter;

@Table(name="RAS_AIRCRAFT_PICTURE")
@Entity
public class AircraftPicture{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String photoID;
	


	@Column(name="PHOTO_NAME")
	private String photoName;
	
	@Column(name="PHOTO_URL")
	private String photoUrl;
	
	@Column(name="PHOTO_DESC")
	private String photoDesc;

	@Column(name="PHOTO_CATEGORY")
	private String photoCategory;
	
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
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OVERVIEWID",nullable=true,updatable = false, insertable = false)
	private AircraftOverview overview;
	
	@Transient
	private MultipartFile photoFile;
	
	@Transient
	private String modelName;
	
	public String getPhotoID() {
		return photoID;
	}

	public void setPhotoID(String photoID) {
		this.photoID = photoID;
	}

	
	
	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoCategory() {
		return photoCategory;
	}

	public void setPhotoCategory(String photoCategory) {
		this.photoCategory = photoCategory;
	}


	
	public String getOverviewID() {
		return overviewID;
	}

	public void setOverviewID(String overviewID) {
		this.overviewID = overviewID;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoDesc() {
		return photoDesc;
	}

	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc;
	}

	public MultipartFile getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
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

	public AircraftOverview getOverview() {
		return overview;
	}

	public void setOverview(AircraftOverview overview) {
		this.overview = overview;
	}

	public String getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(String permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getModifyDate() {
		return modifyDate;
	}
	
	
	
}