/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftCapability;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="RAS_AIRCRAFT_CAPABILITY")
@Entity
public class AircraftCapability{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String capabilityID;
	
	/**
	 * 起飞速度(km/hr)
	 */
	@Column(name="TAKEOFFSPEED")
	private Double takeoffSpeed;

	/**
	 * 着陆速度(km/hr)
	 */
	@Column(name="LANDINGSPEED",columnDefinition="number(10,4)")
	private Double landingSpeed;

	/**
	 * 起飞滑跑距离(m)
	 */
	@Column(name="TAKEOFFDISTANCE",columnDefinition="number(10,4)")
	private Double takeoffDistance;

	/**
	 * 着陆滑跑距离(m)
	 */
	@Column(name="LANDINGDISTANCE",columnDefinition="number(10,4)")
	private Double landingDistance;

	/**
	 * 最大爬升率(m/s)
	 */
	@Column(name="MAXCLIMBRATE",columnDefinition="number(10,4)")
	private Double maxClimbRate;

	/**
	 * 最大稳定盘旋角速度实数	°/s
	 */
	@Column(name="MAXSCAV",columnDefinition="number(10,4)")
	private Double maxSCAV;

	/**
	 * 最大瞬时盘旋角速度实数	°/s
	 */
	@Column(name="MAXICAV",columnDefinition="number(10,4)")
	private Double maxICAV;

	/**
	 * 最大过载上限
	 */
	@Column(name="MAXOVERLOADUPLIMIT",columnDefinition="number(10,4)")
	private Double maxOverloadUpLimit;

	/**
	 * 最大过载下限
	 */
	@Column(name="MAXOVERLOADDOWNLIMIT",columnDefinition="number(10,4)")
	private Double maxOverloadDownLimt;

	/**
	 * 实用升限
	 */
	@Column(name="PRACTICALCEILING",columnDefinition="number(10,4)")
	private Double practicalCeiling;

	/**
	 * 理论升限（theoretical ceiling）
	 */
	@Column(name="THEORETICALCEILING",columnDefinition="number(10,4)")
	private Double theoreticalCeiling;

	/**
	 * 最大平飞速度（max level speed）
	 */
	@Column(name="MAXLEVELSPEED",columnDefinition="number(10,4)")
	private Double maxLevelSpeed;

	/**
	 * 最大平飞马赫数（The maximum level flight Mach number）
	 */
	@Column(name="MAXLEVELFLIGHTMACH",columnDefinition="number(10,4)")
	private Double maxLevelFlightMach;
	
	/**
	 * 最大航程（机内油）（maximum range）
	 */
	@Column(name="MAXRANGEENGINEOIL",columnDefinition="number(10,4)")
	private Double maxRangeEngineOil;

	/**
	 * 最大航程（带副油箱）（maximum range With drop tank）
	 */
	@Column(name="MAXRANGEFUELTANK",columnDefinition="number(10,4)")
	private Double maxRangeFuelTank;

	/**
	 * 作战半径（对空）（The air combat radius）
	 */
	@Column(name="FIGHTRADIUSAIR",columnDefinition="number(10,4)")
	private Double fightRadiusAir;

	/**
	 * 作战半径（对地）（The combat radius）
	 */
	@Column(name="FIGHTRADIUSSURFACE",columnDefinition="number(10,4)")
	private Double fightRadiusSurface;
	
	/**
	 * 最大续航时间（机内油）（maximum endurance）
	 */
	@Column(name="MAXENDURANCE",columnDefinition="number(10,4)")
	private Double maxEndurance;
	
	/**
	 * 外键
	 */
	@Column(name="BASICID")
	private String basicID;

	public String getCapabilityID() {
		return capabilityID;
	}

	public void setCapabilityID(String capabilityID) {
		this.capabilityID = capabilityID;
	}

	public Double getTakeoffSpeed() {
		return takeoffSpeed;
	}

	public void setTakeoffSpeed(Double takeoffSpeed) {
		this.takeoffSpeed = takeoffSpeed;
	}

	public Double getLandingSpeed() {
		return landingSpeed;
	}

	public void setLandingSpeed(Double landingSpeed) {
		this.landingSpeed = landingSpeed;
	}

	public Double getTakeoffDistance() {
		return takeoffDistance;
	}

	public void setTakeoffDistance(Double takeoffDistance) {
		this.takeoffDistance = takeoffDistance;
	}

	public Double getLandingDistance() {
		return landingDistance;
	}

	public void setLandingDistance(Double landingDistance) {
		this.landingDistance = landingDistance;
	}

	public Double getMaxClimbRate() {
		return maxClimbRate;
	}

	public void setMaxClimbRate(Double maxClimbRate) {
		this.maxClimbRate = maxClimbRate;
	}

	public Double getMaxSCAV() {
		return maxSCAV;
	}

	public void setMaxSCAV(Double maxSCAV) {
		this.maxSCAV = maxSCAV;
	}

	public Double getMaxICAV() {
		return maxICAV;
	}

	public void setMaxICAV(Double maxICAV) {
		this.maxICAV = maxICAV;
	}

	public Double getMaxOverloadUpLimit() {
		return maxOverloadUpLimit;
	}

	public void setMaxOverloadUpLimit(Double maxOverloadUpLimit) {
		this.maxOverloadUpLimit = maxOverloadUpLimit;
	}

	public Double getMaxOverloadDownLimt() {
		return maxOverloadDownLimt;
	}

	public void setMaxOverloadDownLimt(Double maxOverloadDownLimt) {
		this.maxOverloadDownLimt = maxOverloadDownLimt;
	}

	public Double getPracticalCeiling() {
		return practicalCeiling;
	}

	public void setPracticalCeiling(Double practicalCeiling) {
		this.practicalCeiling = practicalCeiling;
	}

	public Double getTheoreticalCeiling() {
		return theoreticalCeiling;
	}

	public void setTheoreticalCeiling(Double theoreticalCeiling) {
		this.theoreticalCeiling = theoreticalCeiling;
	}

	public Double getMaxLevelSpeed() {
		return maxLevelSpeed;
	}

	public void setMaxLevelSpeed(Double maxLevelSpeed) {
		this.maxLevelSpeed = maxLevelSpeed;
	}

	public Double getMaxEndurance() {
		return maxEndurance;
	}

	public void setMaxEndurance(Double maxEndurance) {
		this.maxEndurance = maxEndurance;
	}

	public Double getFightRadiusSurface() {
		return fightRadiusSurface;
	}

	public void setFightRadiusSurface(Double fightRadiusSurface) {
		this.fightRadiusSurface = fightRadiusSurface;
	}

	public Double getFightRadiusAir() {
		return fightRadiusAir;
	}

	public void setFightRadiusAir(Double fightRadiusAir) {
		this.fightRadiusAir = fightRadiusAir;
	}

	public Double getMaxRangeEngineOil() {
		return maxRangeEngineOil;
	}

	public void setMaxRangeEngineOil(Double maxRangeEngineOil) {
		this.maxRangeEngineOil = maxRangeEngineOil;
	}

	public Double getMaxLevelFlightMach() {
		return maxLevelFlightMach;
	}

	public void setMaxLevelFlightMach(Double maxLevelFlightMach) {
		this.maxLevelFlightMach = maxLevelFlightMach;
	}

	public String getBasicID() {
		return basicID;
	}

	public void setBasicID(String basicID) {
		this.basicID = basicID;
	}

	public Double getMaxRangeFuelTank() {
		return maxRangeFuelTank;
	}

	public void setMaxRangeFuelTank(Double maxRangeFuelTank) {
		this.maxRangeFuelTank = maxRangeFuelTank;
	}
	
	
}