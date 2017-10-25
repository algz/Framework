/**
 * @author algz
 *
 */
package com.ras.aircraftSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="RAS_AIRCRAFT_SYSTEM")
@Entity
public class AircraftSystem{
	
	@Id
	@Column(name="ID")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String systemID;
	
	/**
	 * 雷达型号
	 */
	@Column(name="RADARMODEL")
	private String radarModel;
	
	/**
	 * 雷达作用距离
	 */
	@Column(name="RADARRANGE")
	private Double radarRange;
	
	/**
	 * 飞控系统
	 */
	@Column(name="FLIGHTCONTROLSYSTEM")
	private String flightControlSystem;

	/**
	 * 航电系统特点（有源相控阵雷达、无源相控阵雷达、机械雷达、头盔瞄准、头盔显示、孔径综合、光电系统
）
	 */
	@Column(name="AVIONICSSYSTEM")
	private String avionicsSystem;
	
	/**
	 * 机炮型号
	 */
	@Column(name="MACHINEGUNMODEL")
	private String MachineGunModel;
	
	/**
	 * 最大挂点数目
	 */
	@Column(name="MAXMOUNTPOINT")
	private Integer maxMountPoint;
	
	/**
	 * 武器挂载方式（内置、半埋、外挂）
	 */
	@Column(name="WEAPONSMOUNT")
	private String weaponsMount;
	
	/**
	 * 可挂载武器
	 */
	@Column(name="OPTIONALWEAPONSMOUNT")
	private String optionalWeaponsMount;
	
	/**
	 * 外键
	 */
	@Column(name="BASICID")
	private String basicID;

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String id) {
		this.systemID = id;
	}

	public String getRadarModel() {
		return radarModel;
	}

	public void setRadarModel(String radarModel) {
		this.radarModel = radarModel;
	}

	public Double getRadarRange() {
		return radarRange;
	}

	public void setRadarRange(Double radarRange) {
		this.radarRange = radarRange;
	}

	public String getFlightControlSystem() {
		return flightControlSystem;
	}

	public void setFlightControlSystem(String flightControlSystem) {
		this.flightControlSystem = flightControlSystem;
	}

	public String getAvionicsSystem() {
		return avionicsSystem;
	}

	public void setAvionicsSystem(String avionicsSystem) {
		this.avionicsSystem = avionicsSystem;
	}

	public String getMachineGunModel() {
		return MachineGunModel;
	}

	public void setMachineGunModel(String machineGunModel) {
		MachineGunModel = machineGunModel;
	}

	public Integer getMaxMountPoint() {
		return maxMountPoint;
	}

	public void setMaxMountPoint(Integer maxMountPoint) {
		this.maxMountPoint = maxMountPoint;
	}

	public String getWeaponsMount() {
		return weaponsMount;
	}

	public void setWeaponsMount(String weaponsMount) {
		this.weaponsMount = weaponsMount;
	}

	public String getOptionalWeaponsMount() {
		return optionalWeaponsMount;
	}
 
	public void setOptionalWeaponsMount(String optionalWeaponsMount) {
		this.optionalWeaponsMount = optionalWeaponsMount;
	}

	public String getBasicID() {
		return basicID;
	}

	public void setBasicID(String basicID) {
		this.basicID = basicID;
	}
	
	
	
}