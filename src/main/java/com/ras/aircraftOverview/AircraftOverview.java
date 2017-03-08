/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftOverview;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="ras_aircraft_overview")
@Entity
public class AircraftOverview{
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ALGZGenerator")
    @GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	private String overviewID;
	
	@Column(name="MODELNAME")
	private String modelName;
	
	@Column(name="MODELCNAME")
	private String modelCname;
	
	@Column(name="MODELENAME")
	private String modelEname;


	public String getOverviewID() {
		return overviewID;
	}

	public void setOverviewID(String overviewID) {
		this.overviewID = overviewID;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelCname() {
		return modelCname;
	}

	public void setModelCname(String modelCname) {
		this.modelCname = modelCname;
	}

	public String getModelEname() {
		return modelEname;
	}

	public void setModelEname(String modelEname) {
		this.modelEname = modelEname;
	}

	
	
	
}