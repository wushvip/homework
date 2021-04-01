package com.chinamobile.cmss.bcse.evaluate.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.FileEncoding;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.csvreader.CsvReader;

public class FileUtil {
	/**
	 * 本地上传文件（form表单上传）
	 * 
	 * @return boolean
	 * @date:2015-11-11下午6:49:32
	 * @author:lijingjing
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static boolean localUploadFile(MultipartFile file, String savePath) throws Exception {

		if (file == null) {
			return false;
		}

		boolean fileFlag = false;
			String fileName = file.getOriginalFilename();
			File targetFile = new File(savePath, fileName);
			if (!targetFile.exists()) {
				boolean flag = targetFile.mkdirs();
				if (!flag) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "创建路径失败:"+savePath);
					return false;
				}
			}

			file.transferTo(targetFile);
			fileFlag = true;
		return fileFlag;

	}

	
	
	/**
	 * 
	 * @Title: readCsv 
	 * @Description: 读取csv
	 * @param csvFilePath
	 * @return
	 * @return: ArrayList<String[]>
	 */
	public static ArrayList<String[]> readCsv(String csvFilePath) {
		ArrayList<String[]> csvList = null;
		try {
			FileEncoding.getFileEncoding(csvFilePath);			
			csvList = new ArrayList<String[]>();
			CsvReader reader = new CsvReader(csvFilePath, ',',
					Charset.forName(Config.ENCODE_UTF8));
			while (reader.readRecord()) {
				csvList.add(reader.getValues());
			}
			reader.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return csvList;
	}
}
