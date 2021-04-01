package com.chinamobile.cmss.bcse.sdk.tools;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.csvreader.CsvWriter;

/**
 * 
 * @ClassName: ConvertToCSV
 * @Description: 把数据写入CSV文件
 * @author: jinjing
 * @date: 2016年2月16日 下午3:20:05
 */
public class ConvertToCSV {

	private static CsvWriter writer = null;

	/**
	 * 
	 * @Title: writeToCsv
	 * @Description: 将List<String[]>写入csv
	 * @param Result
	 * @param FilePath
	 * @throws Exception
	 * @return: void
	 */
	public static void writeToCsv(List<String[]> Result, String FilePath) throws Exception {
		writer = new CsvWriter(new FileWriterWithEncoding(new File(FilePath), "UTF-8"), ',');
		for (String[] strings : Result) {
			writer.writeRecord(strings);
		}

		System.out.println("csv filePath" + FilePath);
		writer.close();
	}

	/**
	 * 
	 * @Title: writeToCsv
	 * @Description: 重载方法，将 ResultSet写入csv
	 * @param reSet
	 * @param FilePath
	 * @throws Exception
	 * @return: void
	 */
	public static void writeToCsv(ResultSet reSet, String FilePath) throws Exception {
		ArrayList<String[]> Result = new ArrayList<String[]>();
		// 拼接表头
		int ColoumCount = reSet.getMetaData().getColumnCount();
		String[] headerString = new String[ColoumCount + 1];
		headerString[0] = "uniqueId";
		for (int i = 1; i <= ColoumCount; i++) {
			headerString[i] = reSet.getMetaData().getColumnName(i);
		}
		Result.add(headerString);

		while (reSet.next()) {
			String[] tempStrings = new String[ColoumCount + 1];
			// 遍历列增加了一列自己生成的id
			String tempMD5 = "";
			for (int i = 1; i <= ColoumCount; i++) {
				try {
					tempStrings[i] = reSet.getString(i);
					tempMD5 += reSet.getString(i);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("result to array error");
					tempStrings[i] = "";
					tempMD5 += "";
				}
			}
			// 获取一行数据的MD5编码
			tempStrings[0] = MD5.getMD5(tempMD5);
			Result.add(tempStrings);
		}

		writeToCsv(Result, FilePath);
	}

}
