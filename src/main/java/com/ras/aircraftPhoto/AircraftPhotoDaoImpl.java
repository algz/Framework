package com.ras.aircraftPhoto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ras.tool.CommonTool;
import com.ras.tool.file.UploadFile;

@Repository
public class AircraftPhotoDaoImpl implements AircraftPhotoDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<AircraftPhoto> find(AircraftPhoto photo) {
		StringBuilder sql=new StringBuilder("SELECT * FROM RAS_AIRCRAFT_PHOTO PHOTO WHERE 1=1 ");
		if(photo.getBasicID()!=null){
			sql.append(" AND PHOTO.BASIC_ID='"+photo.getBasicID()+"' ");
		}
		if(photo.getPhotoCategory()!=null){
			sql.append(" AND PHOTO.PHOTO_CATEGORY='"+photo.getPhotoCategory()+"' ");
		}
		return sf.getCurrentSession().createSQLQuery(sql.toString()).addEntity(AircraftPhoto.class).list();
	}

	@Override
	public void saveOrUpdate(AircraftPhoto photo) {
		if(photo.getPhotoID()==null||photo.getPhotoID().equals("")){
			MultipartFile image=photo.getPhotoFile();
			String contentType = image.getContentType();//文件类型.image/jpeg.
	        String type = contentType.substring(contentType.indexOf("/") + 1);//文件扩展名
			String fileName=System.currentTimeMillis()+new Random().nextInt(100) + "." + type;
			try {
				photo.setPhotoName(image.getOriginalFilename());
				photo.setPhotoUrl(CommonTool.PHOTO_URL_PREFIX+fileName);
				image.transferTo(new File(CommonTool.PHOTO_DIR+fileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sf.getCurrentSession().save(photo);
		}else{
			sf.getCurrentSession().update(photo);
		}
	}

	@Override
	public void del(String photoID) {
		String sql="delete RAS_AIRCRAFT_PHOTO PHOTO where photo.id='"+photoID+"'";
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		
	}


	
}
