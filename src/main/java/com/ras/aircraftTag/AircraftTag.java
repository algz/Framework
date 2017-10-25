
/**
 * @author algz
 *
 */
package com.ras.aircraftTag;

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

import com.ras.aircraftArchive.AircraftArchive;
import com.ras.aircraftOverview.AircraftOverview;
import com.ras.aircraftPicture.AircraftPicture;

@Table(name="RAS_AIRCRAFT_TAG")
@Entity
public class AircraftTag{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String tagID;

	@Column(name="TAG_NAME")
	private String tagName;
	
	@Column(name="RELATION_ID")
	private String relationID;
	
	@Column(name="RELATION_TABLE")
	private String relationTable;

	@Column(name="RELATION_DESC")
	private String relationDesc;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="RELATION_ID",nullable=true,updatable = false, insertable = false)
	private AircraftOverview overview;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="RELATION_ID",nullable=true,updatable = false, insertable = false)
	private AircraftArchive archive;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="RELATION_ID",nullable=true,updatable = false, insertable = false)
	private AircraftPicture picture;
	
	public String getTagID() {
		return tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getRelationID() {
		return relationID;
	}

	public void setRelationID(String relationID) {
		this.relationID = relationID;
	}

	public String getRelationTable() {
		return relationTable;
	}

	public void setRelationTable(String relationTable) {
		this.relationTable = relationTable;
	}

	public String getRelationDesc() {
		return relationDesc;
	}

	public void setRelationDesc(String relationDesc) {
		this.relationDesc = relationDesc;
	}

	public AircraftOverview getOverview() {
		return overview;
	}

	public void setOverview(AircraftOverview overview) {
		this.overview = overview;
	}

	public AircraftArchive getArchive() {
		return archive;
	}

	public void setArchive(AircraftArchive archive) {
		this.archive = archive;
	}

	public AircraftPicture getPicture() {
		return picture;
	}

	public void setPicture(AircraftPicture picture) {
		this.picture = picture;
	}
	
	
	
}