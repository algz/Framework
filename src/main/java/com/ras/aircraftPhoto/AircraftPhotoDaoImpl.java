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

import com.ras.aircraftBasic.AircraftBasic;
import com.ras.tool.CommonTool;
import com.ras.tool.file.UploadFile;

@Repository
public class AircraftPhotoDaoImpl implements AircraftPhotoDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<AircraftPhoto> find(AircraftPhoto photo) {
		StringBuilder sql=new StringBuilder("SELECT * FROM RAS_AIRCRAFT_PHOTO PHOTO WHERE 1=1 ");
		if(photo.getOverviewID()!=null){
			sql.append(" AND PHOTO.OVERVIEWID='"+photo.getOverviewID()+"' ");
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
				String dir=CommonTool.PHOTO_URL_PREFIX+photo.getModelName()+"/";
				photo.setPhotoName(image.getOriginalFilename());
				photo.setPhotoUrl(dir+fileName);
				File file=new File(dir);
				if(!file.exists()){
					file.mkdir();
				}
				image.transferTo(new File(CommonTool.PHOTO_DIR+photo.getModelName()+"\\"+fileName));
				sf.getCurrentSession().save(photo);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			sf.getCurrentSession().update(photo);
		}
	}

	@Override
	public void del(String photoID) {
		AircraftPhoto photo=(AircraftPhoto)sf.getCurrentSession().get(AircraftPhoto.class, photoID);
		String sql="select distinct ao.modelname from RAS_AIRCRAFT_PHOTO t "
				+ "inner join  ras_aircraft_basic ab on ab.id=t.basicid "
				+ "inner join ras_aircraft_overview ao on ao.id=ab.overviewid "
				+ "where t.id='"+photoID+"'";
		String modelName=(String)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
		String[] s=photo.getPhotoUrl().split("/");
		String fileName=s[s.length-1];
		File file=new File(CommonTool.PHOTO_DIR+modelName+"\\"+fileName);
		file.delete();
		sql="delete RAS_AIRCRAFT_PHOTO PHOTO where photo.id='"+photoID+"'";
		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		
	}


	
}
