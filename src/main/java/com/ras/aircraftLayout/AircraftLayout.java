/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftLayout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="RAS_AIRCRAFT_LAYOUT")
@Entity
public class AircraftLayout{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String layoutID;
	
	/**
	 * 机长（Total length）
	 */
	@Column(name="AIRLENGTH")
	private Integer airLength;
	
	/**
	 * 机高（Total height）
	 */
	@Column(name="")
	private Integer airHeight;
	
	/**
	 * 翼展（ wingspan）
	 */
	@Column(name="WINGSPAN")
	private Integer wingSpan;
	
	/**
	 * 机翼面积（wing area）
	 */
	@Column(name="WINGAREA")
	private Integer wingArea;
	
	/**
	 * 机翼展弦比（aspect ratio）
	 */
	@Column(name="WINGASPECTRATIO")
	private Integer wingAspectRatio;
	
	/**
	 * 起飞翼载（Takeoff Wing loading）
	 */
	@Column(name="LAUNCHWINGLOADING")
	private Integer launchWingloading;
	/**
	 * 
	 */
	@Column(name="BASICID")
	private String basicID;
	
	public String getLayoutID() {
		return layoutID;
	}
	public void setLayoutID(String layoutID) {
		this.layoutID = layoutID;
	}
	public Integer getAirLength() {
		return airLength;
	}
	public void setAirLength(Integer airLength) {
		this.airLength = airLength;
	}
	public Integer getAirHeight() {
		return airHeight;
	}
	public void setAirHeight(Integer airHeight) {
		this.airHeight = airHeight;
	}
	public Integer getWingSpan() {
		return wingSpan;
	}
	public void setWingSpan(Integer wingSpan) {
		this.wingSpan = wingSpan;
	}
	public Integer getWingArea() {
		return wingArea;
	}
	public void setWingArea(Integer wingArea) {
		this.wingArea = wingArea;
	}
	public Integer getWingAspectRatio() {
		return wingAspectRatio;
	}
	public void setWingAspectRatio(Integer wingAspectRatio) {
		this.wingAspectRatio = wingAspectRatio;
	}
	public Integer getLaunchWingloading() {
		return launchWingloading;
	}
	public void setLaunchWingloading(Integer launchWingloading) {
		this.launchWingloading = launchWingloading;
	}
	public String getBasicID() {
		return basicID;
	}
	public void setBasicID(String basicID) {
		this.basicID = basicID;
	}
	
	
}