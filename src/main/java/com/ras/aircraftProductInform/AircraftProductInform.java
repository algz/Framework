/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftProductInform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="RAS_AIRCRAFT_PRODUCT_INFORM")
public class AircraftProductInform{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String informID;
	
	@Column(name="OVERVIEWID")
	private String overviewID;
	
	@Column(name="PARAMID")
	private String paramID;
	
	@Column(name="PARAMVALUE")
	private String paramValue;

	public String getInformID() {
		return informID;
	}

	public void setInformID(String informID) {
		this.informID = informID;
	}

	public String getOverviewID() {
		return overviewID;
	}

	public void setOverviewID(String overviewID) {
		this.overviewID = overviewID;
	}

	public String getParamID() {
		return paramID;
	}

	public void setParamID(String paramID) {
		this.paramID = paramID;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	
	
}