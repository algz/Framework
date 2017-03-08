/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftDynamic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="RAS_AIRCRAFT_DYNAMIC")
public class AircraftDynamic{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String dynamicID;
	
	/**
	 * 发动机型号（Engine model ）
	 */
	@Column(name="ENGINE-MODEL")
	private String engineModel;
	
	/**
	 * 发动机类型（engine type）
	 */
	@Column(name="ENGINE-TYPE")
	private String engineType;
	
	/**
	 * 发动机数量（number of engine）
	 */
	@Column(name="ENGINE-NUMBER")
	private Integer engineNumber;
	
	/**
	 * 发动机单台最大推力（Single engine maximum thrust）
	 */
	@Column(name="ENGINE-MAX-THRUST")
	private Double engineMaxThrust;
	
	/**
	 * 发动机单台最大功率（Single engine maximum power）
	 */
	@Column(name="ENGINE-MAX-POWER")
	private Double engineMaxPower;
	
	/**
	 * 外键
	 */
	@Column(name="BASIC_ID")
	private String basicID;

	public String getDynamicID() {
		return dynamicID;
	}

	public void setDynamicID(String dynamicID) {
		this.dynamicID = dynamicID;
	}

	
	public String getEngineModel() {
		return engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public Integer getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(Integer engineNumber) {
		this.engineNumber = engineNumber;
	}

	public Double getEngineMaxThrust() {
		return engineMaxThrust;
	}

	public void setEngineMaxThrust(Double engineMaxThrust) {
		this.engineMaxThrust = engineMaxThrust;
	}

	public Double getEngineMaxPower() {
		return engineMaxPower;
	}

	public void setEngineMaxPower(Double engineMaxPower) {
		this.engineMaxPower = engineMaxPower;
	}

	public String getBasicID() {
		return basicID;
	}

	public void setBasicID(String basicID) {
		this.basicID = basicID;
	}


	
	
}