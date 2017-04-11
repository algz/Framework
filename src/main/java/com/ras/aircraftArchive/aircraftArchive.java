/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.aircraftArchive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="RAS_AIRCRAFT_ARCHIVE")
@Entity
public class aircraftArchive{
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="ALGZGenerator",strategy="guid")
	@GeneratedValue(generator="ALGZGenerator")
	private String archiveID;
	
	/**
	 * 外键
	 */
	@Column(name="BASIC_ID")
	private String basicID;

	@Column(name="ARCHIVE_URL")
	private String archiveUrl;
	
	@Column(name="ARCHIVE_TITLE")
	private String archiveTitle;
	
	@Column(name="ARCHIVE_DESC")
	private String archiveDesc;


}