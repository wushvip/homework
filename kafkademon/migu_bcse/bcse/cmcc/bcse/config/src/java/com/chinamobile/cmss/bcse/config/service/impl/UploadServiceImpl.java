package com.chinamobile.cmss.bcse.config.service.impl;


import java.io.File;
import java.io.FileInputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.config.service.UploadService;
import com.chinamobile.cmss.bcse.config.util.FileUtil;
import com.chinamobile.cmss.bcse.search.bean.ZkConfigFileType;
import com.chinamobile.cmss.bcse.search.cloudapi.ReloadSolrCloud;
import com.chinamobile.cmss.bcse.search.tool.ZookeeperUtil;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

import cn.xddai.chardet.CharsetDetector;
/**
 * 
 * @ClassName: UploadServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:09:41
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService{

	@Override
	public void uploadStop(String appId, String userId,MultipartFile file,ResultBean resultBean) {
		try {
			if(!validate(file, resultBean)){
				return;
			}
			
			
			String fileDir=Config.ZKCONFIG_LOCALDIR+"/"+appId;
			String fileName=file.getOriginalFilename();
			System.out.println("文件名："+fileName);
			String filePath=fileDir+File.separator+fileName;
			if(FileUtil.upload(file,fileDir)){
				ZookeeperUtil.putFile(appId, filePath,ZkConfigFileType.STOPWORDS);
				ReloadSolrCloud.reload(Config.ZK_HOST, appId);
			}
			
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UploadStopError);
			throw new RuntimeException(MsgConfig.UploadStopError);
		}
	}

	@Override
	public void uploadExt(String appId,String userId, MultipartFile file,ResultBean resultBean) {
		try {
			if(!validate(file, resultBean)){
				return;
			}
			
			String fileDir=Config.ZKCONFIG_LOCALDIR+"/"+appId;
			String fileName=file.getOriginalFilename();
			String filePath=fileDir+File.separator+fileName;
			if(FileUtil.upload(file,fileDir)){
				ZookeeperUtil.putFile(appId, filePath,ZkConfigFileType.EXTDIC);
				ReloadSolrCloud.reload(Config.ZK_HOST, appId);
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UploadExtError);
			throw new RuntimeException(MsgConfig.UploadExtError);
		}
	}

	@Override
	public void uploadSynonym(String appId,String userId, MultipartFile file,ResultBean resultBean) {
		try {
			if(!validate(file, resultBean)){
				return;
			}
			
			String fileDir=Config.ZKCONFIG_LOCALDIR+"/"+appId;
			String fileName=file.getOriginalFilename();
			String filePath=fileDir+File.separator+fileName;
			if(FileUtil.upload(file,fileDir)){
				ZookeeperUtil.putFile(appId, filePath,ZkConfigFileType.SYNONYMS);
				ReloadSolrCloud.reload(Config.ZK_HOST, appId);
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UploadSynoError);		
			throw new RuntimeException(MsgConfig.UploadSynoError);
		}
		
	}
	
	@Override
	public void uploadAmbiguity(String appId, String userId,MultipartFile file,ResultBean resultBean) {
		try {
			if(!validate(file, resultBean)){
				return;
			}
			String fileDir=Config.ZKCONFIG_LOCALDIR+"/"+appId;
			String fileName=file.getOriginalFilename();
			String filePath=fileDir+File.separator+fileName;
			if(FileUtil.upload(file,fileDir)){
				ZookeeperUtil.putFile(appId, filePath,ZkConfigFileType.AMBIGUITY);
				ReloadSolrCloud.reload(Config.ZK_HOST, appId);
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UploadAmbiguityError);		
			throw new RuntimeException(MsgConfig.UploadAmbiguityError);

		}
		
	}

	private boolean validate(MultipartFile file, ResultBean resultBean) throws Exception {
		if(file.getSize()>MsgConfig.DIC_SIZE){
    		resultBean.setCode(Config.FAIL_SERIVICE_CODE);
    		resultBean.setStatus(Config.RESULT_FAIL);
    		resultBean.setMessage(MsgConfig.DIC_SIZE_EXCEEDS);
    		return false;
    	}
		/*String charset=FileUtil.getFileChart(file.getInputStream());
		if("utf-8".equals(charset.toLowerCase())){
			return true;
		}else{
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
    		resultBean.setStatus(Config.RESULT_FAIL);
    		resultBean.setMessage(MsgConfig.FileCharsetError);
    		return false;
		}*/
		return true;
		
	}
}
