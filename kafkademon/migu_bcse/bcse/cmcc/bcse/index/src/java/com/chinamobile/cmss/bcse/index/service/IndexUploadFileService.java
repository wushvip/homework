package com.chinamobile.cmss.bcse.index.service;

import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.tool.tools.ResultBean;


public interface IndexUploadFileService {

	/**
	 * 
	 * @Title: localUploadFile
	 * @Description: 本地上传文件
	 * @param savePath
	 * @param userId
	 * @param appId
	 * @param tableId
	 * @param file
	 * @return: JSONObject
	 */
//	JSONObject localUploadFile(String savePath, String userId, String appId, String tableId, MultipartFile file);
	void localUploadFile(String savePath, String userId, String appId, String tableId, MultipartFile file,ResultBean resultBean);

	
}
