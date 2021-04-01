package com.chinamobile.cmss.bcse.sdk.tools;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.csvreader.CsvReader;

/**
 * 
 * @ClassName: ReadFromCsv
 * @Description: 从csv文件读数据到一个List
 * @author: jinjing
 * @date: 2016年2月16日 下午3:23:50
 */
public class ReadFromCsv {

	public static void main(String[] args) {
		try {
			read("C:\\Users\\Administrator\\Desktop\\fromJson.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Title: read
	 * @Description: 读取文件到list
	 * @param filePath
	 * @return
	 * @throws Exception
	 * @return: List<String[]>
	 */
	public static List<String[]> read(String filePath) throws Exception {

		List<String[]> reList = new ArrayList<String[]>();

		CsvReader csvReader = new CsvReader(
				new InputStreamReader(FileUtils.openInputStream(new File(filePath)), "UTF-8"));

		csvReader.setSafetySwitch(false);
		System.out.println("or filePath" + filePath);

		csvReader.readHeaders();
		int fieldCount = csvReader.getHeaderCount();
		String[] header = csvReader.getHeaders();
		reList.add(header);

		while (csvReader.readRecord()) {
			if (csvReader.getColumnCount() == fieldCount) {
				String[] temp = new String[fieldCount];
				for (int i = 0; i < fieldCount; i++) {
					temp[i] = csvReader.get(header[i]);
				}
				reList.add(temp);
			} else {
				System.out.println("error line:" + csvReader.getRawRecord());
			}
		}

		System.out.println("read csv lines:" + reList.size());
		// String[] line = reList.get(reList.size() - 1);

		csvReader.close();
		return reList;
	}

}
