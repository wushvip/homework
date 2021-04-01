package com.chinamobile.cmss.bcse.index.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @ClassName: CopyDic
 * @Description: 拷贝文件相关
 * @author: jinjing
 * @date: 2016年2月16日 下午3:20:52
 */
public class CopyDic {

	/**
	 * 
	 * @Title: copyFile
	 * @Description: 拷贝文件
	 * @param sourcefile
	 * @param targetFile
	 * @throws IOException
	 * @return: void
	 */
	public static void copyFile(File sourcefile, File targetFile) throws IOException {

		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourcefile);
		BufferedInputStream inbuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream out = new FileOutputStream(targetFile);
		BufferedOutputStream outbuff = new BufferedOutputStream(out);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len = 0;
		while ((len = inbuff.read(b)) != -1) {
			outbuff.write(b, 0, len);
		}

		// 刷新此缓冲的输出流
		outbuff.flush();

		// 关闭流
		inbuff.close();
		outbuff.close();
		out.close();
		input.close();

	}

	/**
	 * 
	 * @Title: copyDirectiory
	 * @Description: 拷贝目录
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 * @return: void
	 */
	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {

		// 新建目标目录
		(new File(targetDir)).mkdirs();

		// 获取源文件夹当下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();

		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());

				copyFile(sourceFile, targetFile);

			}

			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();

				copyDirectiory(dir1, dir2);
			}
		}

	}
}
