package com.ras.personal.faverites;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftUserFavorites.AircraftUserFavorites;
import com.ras.aircraftUserFavorites.AircraftUserFavoritesDao;

import algz.platform.util.Common;

@Service
public class FavoritesServiceImpl implements FavoritesService {

	@Autowired
	private AircraftUserFavoritesDao dao;

	@Transactional
	@Override
	public String addFavorites(FavoritesVo vo) {
		AircraftUserFavorites favorites=new AircraftUserFavorites();
		favorites.setFavoritesName(vo.getFavoritesName());
		try {
			favorites.setUrl(URLEncoder.encode(vo.getUrl(),"UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		favorites.setEditor(Common.getLoginUser().getUserid());
		return dao.saveOrUpdate(favorites);
	}

	@Transactional
	@Override
	public void delFavorites(FavoritesVo vo) {
		AircraftUserFavorites favorites=new AircraftUserFavorites();
		favorites.setFavoritesName(vo.getFavoritesName());
		try {
			favorites.setUrl(URLEncoder.encode(vo.getUrl(),"UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		favorites.setEditor(Common.getLoginUser().getUserid());
		dao.del(favorites);
	}

	@Override
	public void findFavoritesGrid(FavoritesVo vo) {
		AircraftUserFavorites favorites=new AircraftUserFavorites();
		favorites.setFavoritesName(vo.getFavoritesName());
		favorites.setUrl(vo.getUrl());
		favorites.setEditor(Common.getLoginUser().getUserid());
		Integer[] arr={vo.getStart(),vo.getLength(),0};
		List<AircraftUserFavorites> list=dao.find(favorites, arr);
		vo.setData(list);
		vo.setRecordsTotal(arr[2]);
	}

	@Override
	public Boolean showFavorites(FavoritesVo vo) {
		AircraftUserFavorites favorites=new AircraftUserFavorites();
		favorites.setFavoritesName(vo.getFavoritesName());
		try {
			favorites.setUrl(URLEncoder.encode(vo.getUrl(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		favorites.setEditor(Common.getLoginUser().getUserid());
		BigDecimal count=dao.count(favorites);
		if(count.intValue()>0){
			return true;
		}else{
			return false;
		}
	}

	
	
	
}
