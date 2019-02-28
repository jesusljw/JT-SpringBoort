package com.jt.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
@Service
public class FileServcieImpl implements FileServcie{
	
	@Value("${image.localpath}")
	private String localPath;
	@Value("${image.urlpath}")
	private String urlPath;
	/**
	 * 文件上传实现思路
	 * 1.校验文件类型jpg/png/gif...
	 * 2.校验是否为恶意程序
	 * 3.为了防止图片检索速度慢,采用分文件储存   yyyy/MM/dd
	 * 4.防止文件重名   UUID+随机数(3)
	 * 5.实现文件上传
	 */
	@Override
	public PicUploadResult upload(MultipartFile uploadFile) {
		PicUploadResult result = new PicUploadResult();
		//1.获取文件名称
		String fileName = uploadFile.getOriginalFilename();
		//将字符全部小写
		fileName = fileName.toLowerCase();
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			result.setError(1);//不是图片
			return result;
		}
		//2.校验是否为恶意程序
		try {
			BufferedImage image = ImageIO.read(uploadFile.getInputStream());
			int width = image.getWidth();
			int height = image.getHeight();
			if(width==0 || height==0) {
				result.setError(1);//一定不是图片
				return result;
			}
		//3.实现分文件储存
		String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		String localPathDir = localPath + dateDir;
		//判断文件夹是否存在
		File fileDir = new File(localPathDir);
		if(!fileDir.exists()) {
			fileDir.mkdirs();//创建文件夹
		}
		
		//4.定义文件名称
		String uuid = UUID.randomUUID().toString().replace("-", "");
		int random = new Random().nextInt(1000);
		String fileType = fileName.substring(fileName.lastIndexOf("."));//截取最后一个.的图片格式
		//形成最终的文件名称
		String realName = uuid + random + fileType;
		//5.实现文件上传
		String localPathReal = localPathDir + "/" + realName;
		uploadFile.transferTo(new File(localPathReal));
		//定义url
		//String url = "https://img14.360buyimg.com/n0/jfs/t1/7301/36/10557/363153/5c231de0E0a5565dd/2e8054392374dc29.jpg";
		String url = urlPath+dateDir+"/"+realName;
		result.setUrl(url);
		
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);//文件上传失败
			return result;
		}
		System.out.println(result.toString());
		
		
		
		return result;
	}

}
