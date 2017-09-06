package com.ras.personal.report;

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

@Entity
@Table(name="RAS_REPORT_CONTENT_KEY")
public class ReportContentKey{

	@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String reportContentKeyID;
	
	@Column(name="CONTENT_KEY")
	private String contentKey;
	
	@Column(name="REPORT_ID")
	private String reportID;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "reportContentValID")
	//@OrderBy(value = "id asc") //对@OneToMany获取的关联列表排序,在@OneToMany下面加个@OrderBy,参数值要对应Bean中的属性名
//	 @LazyCollection(LazyCollectionOption.FALSE)
	private List<ReportContentVal> reportContentValSet;

	public String getReportContentKeyID() {
		return reportContentKeyID;
	}

	public void setReportContentKeyID(String reportContentKeyID) {
		this.reportContentKeyID = reportContentKeyID;
	}

	public String getContentKey() {
		return contentKey;
	}

	public void setContentKey(String contentKey) {
		this.contentKey = contentKey;
	}

	public String getReportID() {
		return reportID;
	}

	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	public List<ReportContentVal> getReportContentValSet() {
		return reportContentValSet;
	}

	public void setReportContentValSet(List<ReportContentVal> reportContentValSet) {
		this.reportContentValSet = reportContentValSet;
	}
	
	
	
}