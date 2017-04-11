package com.ras.tool.file;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ras.tool.CommonTool;

@Service
public class FileService {

	@Value("${org.hibernate.dialect}")
	private String dialect;
	
	public enum FileType {
		@Value("${photo_dir}")
	    POTO,
	    @Value("${archive_dir}")
	    DIR;
	}
	
	public void saveUpFile(MultipartFile file,FileType fileType) throws IOException{
		if(fileType==FileType.DIR){
			FileCopyUtils.copy(file.getBytes(), new File(CommonTool.ARCHIVE_DIR+ file.getOriginalFilename()));
		}else if(fileType==FileType.POTO){
			FileCopyUtils.copy(file.getBytes(), new File(CommonTool.PHOTO_DIR+ file.getOriginalFilename()));
		}
		
		//String fileName = file.getOriginalFilename();
	}
	
	public UploadFile saveFile(HttpServletRequest request) throws IOException {
//        logger.debug("获取上传文件...");

		
		
        // 转换为文件类型的request
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取对应file对象
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator<String> fileIterator = multipartRequest.getFileNames();

        // 获取项目的相对路径（http://localhost:8080/file）
        String requestURL = request.getRequestURL().toString();
        String prePath = "";//requestURL.substring(0, requestURL.indexOf(Variables.ctx));

        while (fileIterator.hasNext()) {
            String fileKey = fileIterator.next();
//            logger.debug("文件名为：" + fileKey);

            // 获取对应文件
            MultipartFile multipartFile = fileMap.get(fileKey);

            if (multipartFile.getSize() != 0L) {

//                validateImage(multipartFile);

                // 调用saveImage方法保存
                UploadFile file = saveImageFile(multipartFile,request);
                file.setPrePath(prePath);

                return file;
            }
        }

        return null;
    }

	/**
	 * 单个图像文件上传
	 * @param image
	 * @return
	 * @throws IOException
	 */
    public UploadFile saveImageFile(MultipartFile image,HttpServletRequest request) throws IOException {
        String originalFilename = image.getOriginalFilename();//文件原始名称
//        logger.debug("文件原始名称为:" + originalFilename);

        
        String contentType = image.getContentType();//文件类型.image/jpeg.
        String type = contentType.substring(contentType.indexOf("/") + 1);//文件扩展名
       // String fileName = /*DateUtil.getCurrentMillStr() +*/ new Random().nextInt(100) + "." + type;

        // 封装了一个简单的file对象，增加了几个属性
        UploadFile file = new UploadFile();
        file.setSaveDirectory(CommonTool.PHOTO_DIR);
        file.setFileName(System.currentTimeMillis()+new Random().nextInt(100) + "." + type);
        file.setContentType(contentType);
        file.setFileParams(request.getParameterMap());
        //logger.debug("文件保存路径：" + file.getSaveDirectory());

        // 通过org.apache.commons.io.FileUtils的writeByteArrayToFile对图片进行保存
        //FileUtils.writeByteArrayToFile(file.getFile(), image.getBytes());

        return file;
    }
}
