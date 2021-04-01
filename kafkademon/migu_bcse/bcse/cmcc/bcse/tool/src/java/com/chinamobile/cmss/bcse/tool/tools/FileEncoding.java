package com.chinamobile.cmss.bcse.tool.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

import com.chinamobile.cmss.bcse.tool.config.Config;
import cn.xddai.chardet.CharsetDetector;

/**
 * @ClassName: FileEncoding
 * @Description: 文件编码获取和转换
 * @author: yangjing
 * @date: 2016年3月17日 下午4:51:01
 */
public class FileEncoding {
	private static Logger logger = Logger.getLogger(FileEncoding.class);

	/**
	 * 重载接口
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean getFileEncoding(String filePath) {

		String encode = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			encode = getFileEncoding(fileInputStream);
			if (encode.equals(Config.ENCODE_UTF8)) {
			} else { // 不是utf-8就转换
				encode = Config.ENCODE_GBK;

				BufferedReader csvReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(new File(filePath)), encode));
				String tempFileName = filePath + "_temp";
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(new File(tempFileName)), "UTF-8"));
				String temp;
				while ((temp = csvReader.readLine()) != null) {
					bw.append(temp);
					bw.newLine();
				}
				bw.flush();
				bw.close();
				csvReader.close();
				fileInputStream.close();

				// 修改文件名
				File file = new File(tempFileName);
				File destFile = new File(filePath);
				destFile.delete();
				file.renameTo(new File(filePath));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @Title: getFileEncoding
	 * @Description: 获取文件编码
	 * @param inputStream
	 * @throws Exception
	 * @return: String
	 */
	public static String getFileEncoding(InputStream inputStream) {
		// 先用juniversalCharset探测文件编码
		String encoding = "";
		encoding = FileEncoding.getFileUniversalEncoding(inputStream);
		if (null == encoding || encoding.isEmpty() || "KOI8-R".equals(encoding) || "IBM855".equals(encoding)) {
			// 再用CharsetDetector探测文件编码
			String encoding2 = FileEncoding.getFileCharsetEncoding(inputStream);
			encoding = encoding2;
		}

		return encoding;
	}

	/**
	 * @Title: getFileUniversalEncoding
	 * @Description: 用juniversalCharset获取文件编码
	 * @param filePath
	 * @return: String
	 */
	public static String getFileUniversalEncoding(InputStream inputStream) {
		String encoding = null;
		try {
			byte[] buf = new byte[4096];
			InputStream fis = inputStream;
			UniversalDetector detector = new UniversalDetector(null);
			int nread;
			int iCount = 100;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
				iCount--;
				if (iCount <= 0) {
					break;
				}
			}
			detector.dataEnd();
			// //已经识别出编码时，才对编码赋值。否则detector.getDetectedCharset()会随机赋值。
			// if(detector.isDone()){
			// encoding = detector.getDetectedCharset();
			// }
			encoding = detector.getDetectedCharset();
			detector.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("juniversalCharset: encoding=" + encoding);
		return encoding;
	}

	/**
	 * @Title: getFileEncoding
	 * @Description: 用CharsetDetector获取文件编码
	 * @param file
	 * @return: String
	 */
	public static String getFileCharsetEncoding(InputStream inputStream) {
		CharsetDetector charDect = new CharsetDetector();
		String[] probableSet = null;
		String encoding = null;
		try {
			probableSet = charDect.detectChineseCharset(inputStream);
			encoding = probableSet[0];
			logger.info("CharsetDetector: encoding=" + encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encoding;

	}

}
