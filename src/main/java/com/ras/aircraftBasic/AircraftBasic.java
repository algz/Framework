package com.ras.aircraftBasic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ras_aircraft_basic")
public class AircraftBasic {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String basicID;
    
    /**
     * 外键
     */
    @Column(name="OVERVIEWID")
    private String overviewID;
	
    /**
     * 研发厂商
     */
    @Column(name="MANUFACTURER")
	private String manufacturer;
	
    /**
     * 飞机类型
     */
	@Column(name="AIRCRAFTTYPE")
	private String aircraftType;
	
	/**
	 * 首飞年份
	 */
	@Column(name="FIRSTFLIGHTYEAR")
	private String firstFlightYear;
	
	/**
	 * 服役年份
	 */
	@Column(name="SERVICEYEAR")
	private Integer serviceYear;
	
	/**
	 * 机组人员数量
	 */
	@Column(name="CREWNUMBER")
	private Integer crewNumber;
	
	/**
	 * 最大载客量
	 */
	@Column(name="PASSENGERCAPACITY")
	private Integer passengerCapacity;
	
	/**
	 * 单价
	 */
	@Column(name="PRICE",columnDefinition="number(10,4)")
	private Double price;
	
	/**
	 * 数据来源
	 */
	@Column(name="DATASOURCES")
	private String dataSources;
	
	/**
	 * 编辑人
	 */
	@Column(name="EDITOR")
	private String editor;
	
	/**
	 * 修改日期
	 */
	@Column(name="MODIFY_DATE")
	private Date modifyDate;

	/**
	 * 研发国别
	 */
	@Column(name="PRODUCERCOUNTRIES")
	private String producerCountries;
	
	/**
	 * 使用国别
	 */
	@Column(name="USINGCOUNTRIES")
	private String usingCountries;

	public String getBasicID() {
		return basicID;
	}

	public void setBasicID(String basicID) {
		this.basicID = basicID;
	}

	public String getOverviewID() {
		return overviewID;
	}

	public void setOverviewID(String overviewID) {
		this.overviewID = overviewID;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public String getFirstFlightYear() {
		return firstFlightYear;
	}

	public void setFirstFlightYear(String firstFlightYear) {
		this.firstFlightYear = firstFlightYear;
	}

	public Integer getServiceYear() {
		return serviceYear;
	}

	public void setServiceYear(Integer serviceYear) {
		this.serviceYear = serviceYear;
	}

	public Integer getCrewNumber() {
		return crewNumber;
	}

	public void setCrewNumber(Integer crewNumber) {
		this.crewNumber = crewNumber;
	}

	public Integer getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(Integer passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDataSources() {
		return dataSources;
	}

	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
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

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getProducerCountries() {
		return producerCountries;
	}

	public void setProducerCountries(String producerCountries) {
		this.producerCountries = producerCountries;
	}

	public String getUsingCountries() {
		return usingCountries;
	}

	public void setUsingCountries(String usingCountries) {
		this.usingCountries = usingCountries;
	}
	
	
}
