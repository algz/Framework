package com.ras.search;

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
@Table(name="ras_search_tag")
public class SearchTag{

/**
 * guid 自动生成的是一串字符串
 * org.hibernate.id.GUIDGenerator generate
WARN: HHH000113: GUID identifier generated: 55E39EEA498647AD80CB79A5EF78EC9D

 */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name="ALGZSequenceGenerator",allocationSize=1,initialValue=1,sequenceName="RAS_SEARCHTAG_SEQ")
	@GeneratedValue(generator="ALGZSequenceGenerator",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ENNAME")
	private String enname;
	
	@Column(name="UI_TYPE")
	private String ui_type;
	
	@Column(name="UI_VALUE")
	private String ui_value;
	
	@Column(name="SEQUENCE")
	private Integer sequence;
	
	@Column(name="ONLYREAD")
	private String onlyRead;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@LazyToOne(LazyToOneOption.PROXY)
	@JoinColumn(name="PARENT_ID",updatable=false,insertable=false)
	private SearchTag parentTag;
	
	//延时加载:fetch = FetchType.LAZY
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "parentTag")
	@Where(clause="enname is not null") //clause:值为属性名字段
	@OrderBy(value = "id asc") //对@OneToMany获取的关联列表排序,在@OneToMany下面加个@OrderBy,参数值要对应Bean中的属性名
//	 @LazyCollection(LazyCollectionOption.FALSE)
	private Set<SearchTag> searchTags;

	//@Transient
	@Column(name="parent_id")
	private String parent_id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	
	public String getUi_type() {
		return ui_type;
	}

	public void setUi_type(String ui_type) {
		this.ui_type = ui_type;
	}

	
	public String getUi_value() {
		return ui_value;
	}

	public void setUi_value(String ui_value) {
		this.ui_value = ui_value;
	}

	
	
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	
	
	
	public String getOnlyRead() {
		return onlyRead;
	}

	public void setOnlyRead(String onlyRead) {
		this.onlyRead = onlyRead;
	}

	public SearchTag getParentTag() {
		return parentTag;
	}

	public void setParentTag(SearchTag parentTag) {
		this.parentTag = parentTag;
	}

	public Set<SearchTag> getSearchTags() {
		return searchTags;
	}

	public void setSearchTags(Set<SearchTag> searchTags) {
		this.searchTags = searchTags;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}


	
	
	
}