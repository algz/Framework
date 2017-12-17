package com.ras.aircraftReport;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Where;

import com.ras.aircraftBasic.AircraftBasic;

@Entity
@Table(name="RAS_AIRCRAFT_REPORT")
public class AircraftReport{

	@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String reportID;
	
	@Column(name="REPORTNAME")
	private String reportName;
	
	@Column(name="REPORTDES")
	private String reportDes;
	
	@Column(name="MODIFY_DATE",insertable=false)
	private Date modifyDate;
	
	@Column(name="EDITOR")
	private String editor;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "reportContentKeyID")
	//@OrderBy(value = "id asc") //对@OneToMany获取的关联列表排序,在@OneToMany下面加个@OrderBy,参数值要对应Bean中的属性名
//	 @LazyCollection(LazyCollectionOption.FALSE)
	private List<AircraftReportKey> reportContentKeySet;

	public String getReportID() {
		return reportID;
	}

	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportDes() {
		return reportDes;
	}

	public void setReportDes(String reportDes) {
		this.reportDes = reportDes;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public List<AircraftReportKey> getReportContentKeySet() {
		return reportContentKeySet;
	}

	public void setReportContentKeySet(List<AircraftReportKey> reportContentKeySet) {
		this.reportContentKeySet = reportContentKeySet;
	}

	public Date getModifyDate() {
		return modifyDate;
	}


	
	
}