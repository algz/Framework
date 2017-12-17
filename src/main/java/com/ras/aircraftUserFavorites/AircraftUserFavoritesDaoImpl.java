package com.ras.aircraftUserFavorites;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ras.aircraftPicture.AircraftPicture;
import com.ras.tool.CommonTool;

import algz.platform.util.Common;

@Repository
public class AircraftUserFavoritesDaoImpl implements AircraftUserFavoritesDao {
	
	@Autowired
	private SessionFactory sf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AircraftUserFavorites> find(AircraftUserFavorites favorites,Integer[] arr) {
		String sql="from RAS_USER_FAVORITES where 1=1 and editor='"+favorites.getEditor()+"' ";
		if(favorites.getFavoritesName()!=null&&!favorites.getFavoritesName().equals("")){
			sql+=" and FAVORITESNAME='"+favorites.getFavoritesName()+"'";
		}
		if(favorites.getUrl()!=null&&!favorites.getUrl().equals("")){
			sql+=" and FAVORITESURL='"+favorites.getUrl()+"'";
		}
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery("select count(1) "+sql).uniqueResult();
		arr[2]=count.intValue();
		//countRecord=new Integer(count.intValue());
		
		return sf.getCurrentSession().createSQLQuery("select * "+sql)
										   .addEntity(AircraftUserFavorites.class)
										   .setFirstResult(arr[0])
										   .setMaxResults(arr[1])
										   .list();
	}

	@Override
	public String saveOrUpdate(AircraftUserFavorites favorites) {
		sf.getCurrentSession().saveOrUpdate(favorites);
		return favorites.getFavoritesID();
	}

	@Override
	public void del(AircraftUserFavorites favorites) {
		String sql="delete RAS_USER_FAVORITES where editor='"+Common.getLoginUser().getUserid()+"'";
		if(favorites.getFavoritesName()!=null&&!favorites.getFavoritesName().equals("")){
			sql+=" and FAVORITESNAME='"+favorites.getFavoritesName()+"'";
		}
		if(favorites.getUrl()!=null&&!favorites.getUrl().equals("")){
			sql+=" and FAVORITESURL='"+favorites.getUrl()+"'";
		}
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public BigDecimal count(AircraftUserFavorites favorites) {
		String sql="select count(1) from RAS_USER_FAVORITES WHERE 1=1 ";
		if(favorites.getFavoritesName()!=null&&!favorites.getFavoritesName().equals("")){
			sql+=" and FAVORITESNAME='"+favorites.getFavoritesName()+"'";
		}
		if(favorites.getUrl()!=null&&!favorites.getUrl().equals("")){
			sql+=" and FAVORITESURL='"+favorites.getUrl()+"'";
		}
		return (BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
	}
	
}
