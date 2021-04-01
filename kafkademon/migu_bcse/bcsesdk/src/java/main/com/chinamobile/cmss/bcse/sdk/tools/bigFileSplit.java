package com.chinamobile.cmss.bcse.sdk.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * 将达文件切分
 * 
 * @author jinjing
 *
 */
public class bigFileSplit {

	public static void main(String[] args) {
		
		
		
		ArrayList<String> files = process("D:\\1111\\textah");
		// ArrayList<String> files =
		// process("C:\\Users\\Administrator\\Desktop\\main.csv");
		for (String string : files) {
			System.out.println(string);
		}
	}

	// 100M文件
	private final static int splitSize = 100000000;

	/**
	 * 处理入口
	 * 
	 * @param fileName
	 * @return
	 */
	public static ArrayList<String> process(String fileName) {

		ArrayList<String> fileList = new ArrayList<String>();

		File f = new File(fileName);
		int fileNum = 0;

		if (f.exists() && f.isFile()) {
			fileNum = (int) (f.length() / splitSize) + 1;

		} else {
			return fileList;
		}
		// 判断文件大小，如果小于100M不切分
		if (f.length() > splitSize) {
			try {
				ArrayList<String> temp = cutFile(fileName, fileNum, f);
				fileList = temp;
			} catch (Exception e) {
				e.printStackTrace();
				fileList.add(fileName);
			}
		} else {// 不切分
			fileList.add(fileName);
		}

		return fileList;
	}

	/**
	 * 切分大文件
	 * 
	 * @param fileName
	 * @param fileNum
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> cutFile(String fileName, int fileNum, File f) throws Exception {
		ArrayList<String> fileList = new ArrayList<String>();

		String parentPath = f.getParentFile().getPath();
		String dicPath = parentPath + File.separator + f.getName() + "_split";

		// 删除旧的文件夹
		DeleteDir.process(new File(dicPath));

		// 建立文件夹
		File dic = new File(dicPath);
		dic.mkdirs();

		BufferedReader csvReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(fileName)), "UTF-8"));

		int lineCount = getLineCount(fileName);
		System.out.println("lineCount:" + lineCount);
		System.out.println("fileNum:" + fileNum);

		// 得到文件多少行
		int lineNum = lineCount - 1;
		// 前N-1个文件的行
		int erreryFileLine = lineNum / fileNum;
		// 最后一个文件的行
		int lastFileLine = erreryFileLine + lineNum % fileNum;

		// 文件头
		String header = csvReader.readLine();

		int i = 0;
		// 写入文件
		for (; i < fileNum - 1; i++) {
			String tempFileName = dicPath + File.separator + "temp" + i;
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(tempFileName)), "UTF-8"));
			// 写入文件头
			bw.append(header);
			bw.newLine();
			for (int j = i * erreryFileLine; j < ((i + 1) * erreryFileLine); j++) {
				bw.write(csvReader.readLine());
				bw.newLine();
			}
			bw.flush();
			bw.close();
			fileList.add(tempFileName);
		}
		// 写入最后一个文件
		String tempFileName = dicPath + File.separator + "temp" + i;
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File(tempFileName)), "UTF-8"));
		// 写入文件头
		bw.append(header);
		bw.newLine();

		for (int j = 0; j < lastFileLine; j++) {
			bw.write(csvReader.readLine());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		csvReader.close();
		fileList.add(tempFileName);

		return fileList;
	}

	/**
	 * 获取文件有多少行
	 * 
	 * @param fileName
	 * @return
	 */
	private static int getLineCount(String fileName) {
		int count = 0;
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "UTF-8"));
			while (fileReader.readLine() != null) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

}
