/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftUserFavorites;

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

@Table(name="RAS_USER_FAVORITES")
@Entity
public class AircraftUserFavorites{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String favoritesID;
	
	/**
	 * 收藏夹名称
	 */
	@Column(name="FAVORITESNAME")
	private String favoritesName;
	
	/**
	 * 收藏夹URL
	 */
	@Column(name="FAVORITESURL")
	private String url;

	/**
	 * 用户
	 */
	@Column(name="EDITOR")
	private String editor;
	
	/**
	 * 修改日期
	 */
	@Column(name="MODIFY_DATE",insertable=false,updatable=false)
	private Date modifyDate;

	public String getFavoritesID() {
		return favoritesID;
	}

	public void setFavoritesID(String favoritesID) {
		this.favoritesID = favoritesID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getFavoritesName() {
		return favoritesName;
	}

	public void setFavoritesName(String favoritesName) {
		this.favoritesName = favoritesName;
	}
	
	
	
	
}