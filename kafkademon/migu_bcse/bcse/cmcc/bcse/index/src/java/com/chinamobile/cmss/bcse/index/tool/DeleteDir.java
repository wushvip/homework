package com.chinamobile.cmss.bcse.index.tool;

import java.io.File;

/**
 * 
 * @ClassName: DeleteDir
 * @Description: 删除文件夹
 * @author: jinjing
 * @date: 2016年2月16日 下午3:21:18
 */
public class DeleteDir {

	/**
	 * 
	 * @Title: process
	 * @Description: 删除入口
	 * @param dir
	 * @return
	 * @return: boolean
	 */
	public static boolean process(File dir) {
		try {
			if (dir.isDirectory()) {
				String[] children = dir.list(); // 递归删除目录中的子目录下
				for (int i = 0; i < children.length; i++) {
					boolean success = process(new File(dir, children[i]));
					if (!success) {
						return false;
					}
				}
			}
			// 目录此时为空，可以删除
			return dir.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
