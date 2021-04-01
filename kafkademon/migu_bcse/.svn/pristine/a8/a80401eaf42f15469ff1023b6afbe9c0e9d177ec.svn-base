package com.chinamobile.cmss.bcse.config.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.web.multipart.MultipartFile;

import cn.xddai.chardet.CharsetDetector;


/**
 * 文件工具类
 * @ClassName: FileUtil 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:03:05
 */
public class FileUtil {
	private static Logger logger=Logger.getLogger(FileUtil.class);
	
	
	public static String getFileChart(InputStream is) throws IOException {
		CharsetDetector charDect = new CharsetDetector();
		String[] probableSet = null;
		String code = "";
		try {
			probableSet = charDect.detectChineseCharset(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != probableSet && probableSet.length > 0)
			code = probableSet[0];
		return code;
	}
	
	
	/**
	 * 本地文件上传
	 * @Title: upload 
	 * @Description: TODO
	 * @param file
	 * @param savePath
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public static boolean upload(MultipartFile file,String savePath) throws Exception {
		boolean flag=false;
		if(null==file||StringUtil.isEmpty(savePath)){
			return flag;
		}
		String fileName=file.getOriginalFilename();
			
		File targetFile = new File(savePath, fileName);
		/*if (!targetFile.exists()) {
			boolean status=targetFile.mkdirs();
			if (!status) {
				logger.error("创建路径失败");
				return false;
			}
		}	*/
		file.transferTo(targetFile);
		flag = true;
		return flag;
	}

	
	/**
	 * 创建目录
	 * @Title: mkdir 
	 * @Description: TODO
	 * @param fileDirPath
	 * @return: void
	 */
	public static  void mkdir(String fileDirPath) {
		if(StringUtil.isEmpty(fileDirPath)){
			return;
		}
		File file = new File(fileDirPath);
		if (!file.exists()) {
			file.mkdirs();
			logger.info("create dir success");
		}else{
			logger.info("dir exists");
		}
	}
	
	//将文件夹下的内容拷贝至指定文件夹下(包含原文件夹)
	public static void copyFile(String srcDirPath,String destDirPath) throws Exception{
		if(StringUtil.isEmpty(srcDirPath)||StringUtil.isEmpty(destDirPath)){
			return;
		}
		File srcDir=new File(srcDirPath);
		File destDir=new File(destDirPath);
		FileUtils.copyDirectoryToDirectory(srcDir, destDir);
	}
	
	//删除文件夹及其目录下的内容
	public static void deleteDirAndFile(String DirPath) throws Exception{
		if(StringUtil.isEmpty(DirPath)){
			return;
		}
		File directory=new File(DirPath);
		FileUtils.deleteDirectory(directory);
		
	}
	
	//将字符串写到文件中去
	public static void writeToFile(String str,String filePath) throws Exception{
		File file=new File(filePath);
		BufferedWriter bw = new BufferedWriter(new 
				  OutputStreamWriter(new  FileOutputStream(file),"utf-8"));
		if(StringUtil.isEmpty(str)){
			//写一份空文件
			bw.write("");
		}else{
			bw.write(str);
		}
		logger.info("write text success!!!");
		if(null!=bw){
			bw.close();
		}
	}
	/**
	 * 将xml写到指定文件中
	 * @Title: writeXml 
	 * @Description: TODO
	 * @param filePath
	 * @param doc
	 * @throws Exception
	 * @return: void
	 */
	public static void writeXml(String filePath,Document doc) throws Exception{
		 OutputFormat format=OutputFormat.createPrettyPrint();
		 format.setEncoding("UTF-8");
		 XMLWriter writer=new XMLWriter(new FileOutputStream(filePath),format);
		 writer.write(doc);
		 logger.info("write xml success!!!");
		 writer.close();
	}
	

		
}
