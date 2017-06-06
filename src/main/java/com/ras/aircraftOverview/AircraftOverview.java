/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftOverview;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftBasic.AircraftBasic;

import algz.platform.util.json.JSONPropertyFilter;

@Table(name="ras_aircraft_overview")
@Entity
public class AircraftOverview{
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String overviewID;
	
	@Column(name="MODELNAME")
	private String modelName;
	
	@Column(name="MODELCNAME")
	private String modelCname;
	
	@Column(name="MODELENAME")
	private String modelEname;
	
	@Column(name="PHOTOURL")
	private String photoUrl;
	
	@Column(name="EDITOR")
	private String editor;
	
	@JSONPropertyFilter //用于JSON生成时,过滤属性.
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "overviewID")
	//@OrderBy(value = "id asc") //对@OneToMany获取的关联列表排序,在@OneToMany下面加个@OrderBy,参数值要对应Bean中的属性名
//	 @LazyCollection(LazyCollectionOption.FALSE)
	private List<AircraftBasic> aircraftBasicSet;
	
	@JSONPropertyFilter
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "overviewID")
	@Where(clause = "mainInfo=1")
	private List<AircraftBasic> primaryAircraftBasicSet;
	
	@Transient
	private MultipartFile photoFile;
	
	public String getOverviewID() {
		return overviewID;
	}

	public void setOverviewID(String overviewID) {
		this.overviewID = overviewID;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelCname() {
		return modelCname;
	}

	public void setModelCname(String modelCname) {
		this.modelCname = modelCname;
	}

	public String getModelEname() {
		return modelEname;
	}

	public void setModelEname(String modelEname) {
		this.modelEname = modelEname;
	}

	public List<AircraftBasic> getAircraftBasicSet() {
		return aircraftBasicSet;
	}

	public void setAircraftBasicSet(List<AircraftBasic> aircraftBasicSet) {
		this.aircraftBasicSet = aircraftBasicSet;
	}

	


	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public MultipartFile getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}

	public List<AircraftBasic> getPrimaryAircraftBasicSet() {
		return primaryAircraftBasicSet;
	}

	public void setPrimaryAircraftBasicSet(List<AircraftBasic> primaryAircraftBasicSet) {
		this.primaryAircraftBasicSet = primaryAircraftBasicSet;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

}