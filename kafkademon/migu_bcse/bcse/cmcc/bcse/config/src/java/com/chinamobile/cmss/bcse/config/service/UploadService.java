package com.chinamobile.cmss.bcse.config.service;

import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: UploadService 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:11:58
 */
public interface UploadService {
	public void uploadStop(String appId, String userId,MultipartFile file,ResultBean resultBean);
	
	public void uploadExt(String appId, String userId,MultipartFile file,ResultBean resultBean);

	public void uploadSynonym(String appId,String userId,MultipartFile file,ResultBean resultBean);
	
	public void uploadAmbiguity(String appId,String userId,MultipartFile file,ResultBean resultBean);

}
