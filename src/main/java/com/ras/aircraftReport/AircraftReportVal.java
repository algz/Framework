package com.ras.aircraftReport;

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

@Entity
@Table(name="RAS_AIRCRAFT_REPORT_VAL")
public class AircraftReportVal{

	@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String reportContentValID;
	
	@Column(name="CONTENT_VAL")
	private String contentVal;
	
	@Column(name="CONTENT_VAL_SEQ")
	private String contentValSeq;
	
	@Column(name="CONTENT_KEY_ID")
	private String contentKeyID;

	public String getReportContentValID() {
		return reportContentValID;
	}

	public void setReportContentValID(String reportContentValID) {
		this.reportContentValID = reportContentValID;
	}

	public String getContentVal() {
		return contentVal;
	}

	public void setContentVal(String contentVal) {
		this.contentVal = contentVal;
	}

	public String getContentValSeq() {
		return contentValSeq;
	}

	public void setContentValSeq(String contentValSeq) {
		this.contentValSeq = contentValSeq;
	}

	public String getContentKeyID() {
		return contentKeyID;
	}

	public void setContentKeyID(String contentKeyID) {
		this.contentKeyID = contentKeyID;
	}

	


	
	
	
}