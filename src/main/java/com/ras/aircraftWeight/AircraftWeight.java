/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftWeight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="RAS_AIRCRAFT_WEIGHT")
public class AircraftWeight{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String weightID;
	
	/**
	 * 使用空重(kg)
	 */
	@Column(name="EMPTY_WEIGHT")
	private Double emptyWeight;
	
	/**
	 * 正常起飞重量(kg)
	 */
	@Column(name="NORMAL_TAKEOFF_WEIGHT")
	private Double normalTakeoffWeight;

	/**
	 * 最大起飞重量(kg)
	 */
	@Column(name="MAX_TAKEOFF_WEIGHT")
	private Double maxTakeoffWeight;

	/**
	 * 设计着陆重量(kg)
	 */
	@Column(name="DESIGN_LAND_WEIGHT")
	private Double designLandWeight;

	/**
	 * 机内油量
	 */
	@Column(name="FUEL_CAPACITY")
	private Double fuelCapacity;

	/**
	 * 最大装载重量(kg)
	 */
	@Column(name="MAX_LOAD_WEIGHT")
	private Double maxLoadWeight;

	/**
	 * 起飞推重比
	 */
	@Column(name="TAKE_WEIGHT_RATIO")
	private Double takeWeightRatio;

	/**
	 * 外键
	 */
	@Column(name="BASIC_ID")
	private String basicID;

	public String getWeightID() {
		return weightID;
	}

	public void setWeightID(String weightID) {
		this.weightID = weightID;
	}


	public Double getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(Double emptyWeight) {
		this.emptyWeight = emptyWeight;
	}

	public Double getNormalTakeoffWeight() {
		return normalTakeoffWeight;
	}

	public void setNormalTakeoffWeight(Double normalTakeoffWeight) {
		this.normalTakeoffWeight = normalTakeoffWeight;
	}

	public Double getMaxTakeoffWeight() {
		return maxTakeoffWeight;
	}

	public void setMaxTakeoffWeight(Double maxTakeoffWeight) {
		this.maxTakeoffWeight = maxTakeoffWeight;
	}

	public Double getDesignLandWeight() {
		return designLandWeight;
	}

	public void setDesignLandWeight(Double designLandWeight) {
		this.designLandWeight = designLandWeight;
	}

	public Double getFuelCapacity() {
		return fuelCapacity;
	}

	public void setFuelCapacity(Double fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	public Double getMaxLoadWeight() {
		return maxLoadWeight;
	}

	public void setMaxLoadWeight(Double maxLoadWeight) {
		this.maxLoadWeight = maxLoadWeight;
	}

	public Double getTakeWeightRatio() {
		return takeWeightRatio;
	}

	public void setTakeWeightRatio(Double takeWeightRatio) {
		this.takeWeightRatio = takeWeightRatio;
	}

	public String getBasicID() {
		return basicID;
	}

	public void setBasicID(String basicID) {
		this.basicID = basicID;
	}
	
	
}