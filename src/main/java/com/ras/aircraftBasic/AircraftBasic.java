package com.ras.aircraftBasic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ras_aircraft_basic_library")
public class AircraftBasic {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	@Column(name="MODEL")
	public String model;
	
	@Column(name="CN_NAME")
	public String cnname;
	
	@Column(name="EN_NAME")
	public String enname;
	
	public String manufacturer;
	
	@Column(name="AIRCRAFT_TYPE")
	public String aircraftType;
	
	@Column(name="FIRST_FLIGHT_YEAR")
	public String firstFlightYear;
	
	@Column(name="SERVICE_YEAR")
	public String serviceYear;
	
	@Column(name="CREW_NUMBER")
	public Integer crewNumber;
	
	@Column(name="PASSENGER_CAPACITY")
	public Integer passengerCapacity;
	
	public Double price;
}
