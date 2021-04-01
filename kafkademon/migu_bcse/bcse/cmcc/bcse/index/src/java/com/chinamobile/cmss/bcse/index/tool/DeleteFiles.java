package com.chinamobile.cmss.bcse.index.tool;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: DeleteFiles
 * @Description: 删除LinkedHashMap里的文件
 * @author: jinjing
 * @date: 2016年2月16日 下午3:21:34
 */
public class DeleteFiles {

	/**
	 * 
	 * @Title: process
	 * @Description: 删除入口
	 * @param tableList
	 * @return: void
	 */
	public static void process(LinkedHashMap<String, String> tableList) {
		// 遍历每一张表，并且更新数据
		for (Entry<String, String> entry : tableList.entrySet()) {
			String filePath = entry.getValue();
			deleteFile(filePath);
		}
	}

	/**
	 * 删除文件
	 * 
	 * @Title: deleteFile
	 * @Description: TODO
	 * @param path
	 * @return: void
	 */
	private static void deleteFile(String path) {
		try {
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "delete " + path);
			File file = new File(path);
//			file.deleteOnExit();
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "删除csv索引文件结果"+file.delete());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
