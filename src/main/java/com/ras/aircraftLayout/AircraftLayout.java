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
	@Column(name="")
	private String basicID;
	
	
}