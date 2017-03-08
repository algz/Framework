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
	 * 
	 */
	@Column(name="")
	private Integer airLength;
	/**
	 * 
	 */
	@Column(name="")
	private Integer airHeight;
	/**
	 * 
	 */
	@Column(name="")
	private Integer wingSpan;
	/**
	 * 
	 */
	@Column(name="")
	private Integer wingArea;
	/**
	 * 
	 */
	@Column(name="")
	private Integer wingAspectRatio;
	/**
	 * 
	 */
	@Column(name="")
	private Integer launchWingloading;
	/**
	 * 
	 */
	@Column(name="")
	private String basicID;
	
	
}