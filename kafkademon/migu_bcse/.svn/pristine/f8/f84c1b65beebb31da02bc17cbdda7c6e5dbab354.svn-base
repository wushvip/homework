package com.chinamobile.cmss.bcse.config.service;

import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.config.bean.RecoveryReq;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: RecoveryService 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:11:30
 */
public interface RecoveryService {
	//纠错
	public void recovery(RecoveryReq req,ResultBean resultBean);
	
	//训练字典,用户在界面上操作时使用
	public void train(String appId,String userId,MultipartFile file,String mode,ResultBean resultBean);
	
}
