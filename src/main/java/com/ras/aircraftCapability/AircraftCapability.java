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
	@Column(name="TAKEOFF_SPEED")
	private Double takeoffSpeed;

	/**
	 * 着陆速度(km/hr)
	 */
	@Column(name="LANDING_SPEED",columnDefinition="number(10,4)")
	private Double landingSpeed;

	/**
	 * 起飞滑跑距离(m)
	 */
	@Column(name="TAKEOFF_DISTANCE",columnDefinition="number(10,4)")
	private Double takeoffDistance;

	/**
	 * 着陆滑跑距离(m)
	 */
	@Column(name="LANDING_DISTANCE",columnDefinition="number(10,4)")
	private Double landingDistance;

	/**
	 * 最大爬升率(m/s)
	 */
	@Column(name="MAX_CLIMB_RATE",columnDefinition="number(10,4)")
	private Double maxClimbRate;

	/**
	 * 最大稳定盘旋角速度实数	°/s
	 */
	@Column(name="MAX_SCAV",columnDefinition="number(10,4)")
	private Double maxSCAV;

	/**
	 * 最大瞬时盘旋角速度实数	°/s
	 */
	@Column(name="MAX_ICAV",columnDefinition="number(10,4)")
	private Double maxICAV;

	/**
	 * 最大过载上限
	 */
	@Column(name="MAX_OVERLOAD_UP_LIMIT",columnDefinition="number(10,4)")
	private Double maxOverloadUpLimit;

	/**
	 * 最大过载下限
	 */
	@Column(name="MAX_OVERLOAD_DOWN_LIMIT",columnDefinition="number(10,4)")
	private Double maxOverloadDownLimt;

	/**
	 * 实用升限
	 */
	@Column(name="PRACTICAL_CEILING",columnDefinition="number(10,4)")
	private Double practicalCeiling;

	/**
	 * 理论升限（theoretical ceiling）
	 */
	@Column(name="THEORETICAL_CEILING",columnDefinition="number(10,4)")
	private Double theoreticalCeiling;

	/**
	 * 最大平飞速度（max level speed）
	 */
	@Column(name="MAX_LEVEL_SPEED",columnDefinition="number(10,4)")
	private Double maxLevelSpeed;

	/**
	 * 最大平飞马赫数（The maximum level flight Mach number）
	 */
	@Column(name="MAX_LEVEL_FLIGHT_MACH",columnDefinition="number(10,4)")
	private Double maxLevelFlightMach;
	
	/**
	 * 最大航程（机内油）（maximum range）
	 */
	@Column(name="MAX_RANGE_ENGINE_OIL",columnDefinition="number(10,4)")
	private Double maxRangeEngineOil;

	/**
	 * 最大航程（带副油箱）（maximum range With drop tank）
	 */
	@Column(name="MAX_RANGE_FUEL_TANK",columnDefinition="number(10,4)")
	private Double maxRangeFuelTank;

	/**
	 * 作战半径（对空）（The air combat radius）
	 */
	@Column(name="FIGHT_RADIUS_AIR",columnDefinition="number(10,4)")
	private Double fightRadiusAir;

	/**
	 * 作战半径（对地）（The combat radius）
	 */
	@Column(name="FIGHT_RADIUS_SURFACE",columnDefinition="number(10,4)")
	private Double fightRadiusSurface;
	
	/**
	 * 最大续航时间（机内油）（maximum endurance）
	 */
	@Column(name="MAX_ENDURANCE",columnDefinition="number(10,4)")
	private Double maxEndurance;
	
	/**
	 * 外键
	 */
	@Column(name="BASIC_ID")
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