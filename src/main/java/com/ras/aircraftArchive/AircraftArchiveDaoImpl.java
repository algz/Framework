package com.ras.aircraftArchive;

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
public class AircraftArchiveDaoImpl implements AircraftArchiveDao {

//	@Autowired
//	private AircraftTagDao aircraftTagDao;
	
	@Autowired
	private SessionFactory sf;
	
	public Integer count(AircraftArchive archive){
		String sql="select count(1) from RAS_AIRCRAFT_ARCHIVE raa where 1=1 ";
		if(archive.getOverviewID()!=null){
			sql+=" and raa.overviewid='"+archive.getOverviewID()+"'";
		}
		if(archive.getTag()!=null){
			sql+=" and raa.tag like '%"+archive.getTag()+"%'";
		}
		if(archive.getArchiveName()!=null){
			sql+=" and raa.ARCHIVE_NAME like '%"+archive.getArchiveName()+"%'";
		}
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}
	
	@Override
	public List<AircraftArchive> find(AircraftArchive archive,Integer start,Integer length) {
		String sql="select * from RAS_AIRCRAFT_ARCHIVE raa where 1=1 ";
		if(archive.getOverviewID()!=null){
			sql+=" and raa.overviewid='"+archive.getOverviewID()+"'";
		}
		if(archive.getTag()!=null){
			sql+=" and raa.tag like '%"+archive.getTag()+"%'";
		}
		if(archive.getArchiveName()!=null){
			sql+=" and raa.ARCHIVE_NAME like '%"+archive.getArchiveName()+"%'";
		}
		return sf.getCurrentSession().createSQLQuery(sql)
									 .addEntity(AircraftArchive.class)
								     .setFirstResult(start)
								     .setMaxResults(length)
								     .list();
	}

	@Override
	public void saveOrUpdate(AircraftArchive archive) {
		if(archive.getModelName()!=null&&archive.getArchiveFile()!=null){
			File file=CommonTool.saveFile(archive.getArchiveFile(), CommonTool.ARCHIVE_DIR+archive.getModelName()+File.separator);
			if(file!=null){
				archive.setArchiveUrl(CommonTool.ARCHIVE_URL_PREFIX+archive.getModelName()+"/"+file.getName());
				archive.setArchiveDisplayName(archive.getArchiveFile().getOriginalFilename());//原文件名称
				//编辑人
				archive.setEditor(Common.getLoginUser().getUserid());
				//默认仅个人.
				archive.setPermissionLevel("1");
				//如果文件标题没设置,则自动改为文件名
				if(archive.getArchiveName()==null||archive.getArchiveName().equals("")){
					archive.setArchiveName(archive.getArchiveDisplayName());
				}
				sf.getCurrentSession().save(archive);
				
				/*//保存标签
				if(archive.getTag()!=null&&!archive.getTag().equals("")){
					AircraftTag tag=new AircraftTag();
					tag.setTagName(archive.getTag());
					tag.setRelationID(archive.getArchiveID());
					tag.setRelationTable("RAS_AIRCRAFT_ARCHIVE");
					tag.setRelationDesc("文档");
					aircraftTagDao.saveOrUpdate(tag);
				}*/
			}
		}
	}

	@Override
	public void del(String archiveID) {

			AircraftArchive archive=(AircraftArchive)sf.getCurrentSession().get(AircraftArchive.class, archiveID);
			String pathFile=CommonTool.ARCHIVE_DIR+archive.getArchiveUrl().replaceAll(CommonTool.ARCHIVE_URL_PREFIX, "");
			//File file=CommonTool.saveFile(archive.getArchiveFile(), CommonTool.ARCHIVE_DIR+archive.getModelName()+File.separator);
			File file=new File(pathFile);
			if(file.exists()&&file.delete()){

			}
			String hql="delete AircraftArchive t where t.archiveID='"+archiveID+"'";
			sf.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public AircraftArchive find(String archiveID) {
		return (AircraftArchive)sf.getCurrentSession().get(AircraftArchive.class, archiveID);
	}


	
}
