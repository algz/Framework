package com.ras.personal.faverites;

import javax.persistence.Column;

import com.ras.tool.ReturnVo;

/**
 * TbDeviceContractmanagement entity. @author MyEclipse Persistence Tools
 */
public class FavoritesVo<T> extends ReturnVo<T>{

	// Fields
	
	private String favoritesID;
	
	private String favoritesName;
	
	/**
	 * 收藏夹URL
	 */
	private String url;
	
	
	// Constructors

	/** default constructor */
	public FavoritesVo() {
	}


	public String getFavoritesID() {
		return favoritesID;
	}


	public void setFavoritesID(String favoritesID) {
		this.favoritesID = favoritesID;
	}


	public String getFavoritesName() {
		return favoritesName;
	}


	public void setFavoritesName(String favoritesName) {
		this.favoritesName = favoritesName;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}