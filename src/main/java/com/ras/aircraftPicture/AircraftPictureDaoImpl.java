package com.ras.aircraftPicture;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;

@Repository
public class AircraftPictureDaoImpl implements AircraftPictureDao {

//	@Autowired
//	private AircraftTagDao aircraftTagDao;
	
	@Autowired
	private SessionFactory sf;
	

	@Override
	public BigDecimal count(AircraftPicture photo) {
		StringBuilder sql=new StringBuilder("SELECT count(1) FROM RAS_AIRCRAFT_PICTURE PHOTO WHERE 1=1 ");
		
		//权限控制
		if(!CommonTool.isDataManager()){
			//不是数据管理员
			User curUser=Common.getLoginUser();
			sql.append(" and (photo.editor='"+curUser.getUserid()+"'");//查看自己编辑
			sql.append("or photo.PERMISSION_LEVEL in ('2','3'))");
			
		}
		
		if(photo.getOverviewID()!=null){
			sql.append(" AND PHOTO.OVERVIEWID='"+photo.getOverviewID()+"' ");
		}
		if(photo.getTag()!=null&&!photo.getTag().equals("")){
			sql.append(" and photo.tag like '%"+photo.getTag()+"%' ");
		}
		if(photo.getPhotoCategory()!=null){
			sql.append(" AND PHOTO.PHOTO_CATEGORY='"+photo.getPhotoCategory()+"' ");
		}
		return (BigDecimal)sf.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}
	
	@Override
	public List<AircraftPicture> find(AircraftPicture photo) {
		StringBuilder sql=new StringBuilder("SELECT * FROM RAS_AIRCRAFT_PICTURE PHOTO WHERE 1=1 ");
		

		if(!CommonTool.isDataManager()){
			//不是数据管理员
			User curUser=Common.getLoginUser();
			sql.append(" and (photo.editor='"+curUser.getUserid()+"'");//查看自己编辑
			sql.append("or photo.PERMISSION_LEVEL in ('2','3'))");
			
		}
		
		if(photo.getOverviewID()!=null){
			sql.append(" AND PHOTO.OVERVIEWID='"+photo.getOverviewID()+"' ");
		}
		if(photo.getTag()!=null&&!photo.getTag().equals("")){
			sql.append(" and photo.tag like '%"+photo.getTag()+"%' ");
		}
		if(photo.getPhotoCategory()!=null){
			sql.append(" AND PHOTO.PHOTO_CATEGORY='"+photo.getPhotoCategory()+"' ");
		}
		return sf.getCurrentSession().createSQLQuery(sql.toString()).addEntity(AircraftPicture.class).list();
	}

	@Override
	public void saveOrUpdate(AircraftPicture photo) {
		if(photo.getPhotoID()==null||photo.getPhotoID().equals("")){
			MultipartFile image=photo.getPhotoFile();
			String contentType = image.getContentType();//文件类型.image/jpeg.
	        String type = contentType.substring(contentType.indexOf("/") + 1);//文件扩展名
			String fileName=System.currentTimeMillis()+new Random().nextInt(100) + "." + type;
			try {
				String dir=CommonTool.PHOTO_URL_PREFIX+photo.getModelName()+"/";
				photo.setPhotoName(image.getOriginalFilename());
				photo.setPhotoUrl(dir+fileName);
				//编辑人
				photo.setEditor(Common.getLoginUser().getUserid());
				File file=new File(CommonTool.PHOTO_DIR+photo.getModelName());
				if(!file.exists()){
					file.mkdir();
				}
				image.transferTo(new File(CommonTool.PHOTO_DIR+photo.getModelName()+"\\"+fileName));
				sf.getCurrentSession().save(photo);
				
//				//保存标签
//				if(photo.getTag()!=null&&!photo.getTag().equals("")){
//					AircraftTag tag=new AircraftTag();
//					tag.setTagName(photo.getTag());
//					tag.setRelationID(photo.getPhotoID());
//					tag.setRelationTable("RAS_AIRCRAFT_PICTURE");
//					tag.setRelationDesc("图片");
//					aircraftTagDao.saveOrUpdate(tag);
//				}
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
		AircraftPicture photo=(AircraftPicture)sf.getCurrentSession().get(AircraftPicture.class, photoID);
		if(photo==null){
			return;
		}

		String[] s=photo.getPhotoUrl().split("/");
		String fileName=s[s.length-1];
		File file=new File(CommonTool.PHOTO_DIR+photo.getOverview().getModelName()+"\\"+fileName);
		if(file.exists()){
			file.delete();
		}
				
		sf.getCurrentSession().delete(photo);
	}



	
}
