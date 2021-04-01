package com.chinamobile.cmss.bcse.evaluate.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.evaluate.bean.DataResBean;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateDataInfoBean;
import com.chinamobile.cmss.bcse.evaluate.dao.EvaluateDataInfoBeanDao;
import com.chinamobile.cmss.bcse.evaluate.service.EvaluateDataInfoService;
import com.chinamobile.cmss.bcse.evaluate.utils.FileUtil;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.FileException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
@Service("evaluateDataInfoService")
public class EvaluateDataInfoServiceImpl implements EvaluateDataInfoService{

	
	@Resource
	private EvaluateDataInfoBeanDao dataInfoBeanDao;
 
	/**
	 * 本地上传文件
	 */
	@Override
	public void localUploadFile(String savePath,MultipartFile file,ResultBean resultBean) {
		boolean fileFlag = false;

		LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "local upload file savePath:"+savePath);
		// 本地上传结果
		try {
			fileFlag = FileUtil.localUploadFile(file, savePath);
		} catch (Exception e) {
			LogUtil.loggerEntrance("Evaluation", "evaluate", ExceptionConstants.FileException, LogUtil.EVALUATE_LOG, e);
			throw new FileException(ExceptionConstants.FileException);
		}
		
		// 文件处理中将文件名加到路径中，此处重新获取
		savePath = savePath  + file.getOriginalFilename();
		// 如果文件上传成功，则将其表名与对应的文件名保存
		EvaluateDataInfoBean dataInfoBean = new EvaluateDataInfoBean();
		String id = UUID.randomUUID().toString();
		dataInfoBean.setId(id);
		dataInfoBean.setSourceDir(savePath);
		DataResBean dataResBean  = new DataResBean();
		dataResBean.setDataId(id);
		resultBean.setResult(dataResBean);
		if (fileFlag) {
			try{
				dataInfoBeanDao.insert(dataInfoBean);
			}catch(Exception e){
				resultBean.setMessage("插入数据库失败");
				resultBean.setStatus(Config.RESULT_FAIL);
			}
				
		}else{
			resultBean.setMessage("文件上传失败");
			resultBean.setStatus(Config.RESULT_FAIL);
			return;
		}

	
	}
	
}
