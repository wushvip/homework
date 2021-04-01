package com.chinamobile.cmss.bcse.index.service.impl;

import java.io.File;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.app.dao.AppTableMapBeanDao;
import com.chinamobile.cmss.bcse.index.service.IndexUploadFileService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.FileException;
import com.chinamobile.cmss.bcse.tool.tools.FileOperaUtil;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

@Service("indexUploadFileServiceImpl")
public class IndexUploadFileServiceImpl implements IndexUploadFileService {

	@Resource
	private AppTableMapBeanDao appTableMapBeanDao;

	/**
	 * 本地上传文件
	 */
	@Override
	public void localUploadFile(String savePath, String userId, String appId, String tableId, MultipartFile file,
			ResultBean resultBean) {

		boolean fileFlag = false;

		savePath = savePath + userId + File.separator + appId;
		// savePath = savePath + File.separator + userId + File.separator +
		// appId;
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "local upload file savePath:" + savePath);
		// 本地上传结果
		try {
			fileFlag = FileOperaUtil.localUploadFile(userId, appId, file, savePath);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.FileException, LogUtil.WEB_LOG, e);
			throw new FileException(ExceptionConstants.FileException);
		}

		// 文件处理中将文件名加到路径中，此处重新获取
		savePath = savePath + File.separator + file.getOriginalFilename();
		// 如果文件上传成功，则将其表名与对应的文件名保存
		boolean updateFlag = false;

		if (fileFlag) {
			updateFlag = appTableMapBeanDao.updateFile(tableId, savePath);
			if (!updateFlag) {
				resultBean.setMessage("表名与文件名对应关系更新失败");
				resultBean.setStatus(Config.RESULT_FAIL);
				return;
			}
		} else {
			resultBean.setMessage("文件上传失败");
			resultBean.setStatus(Config.RESULT_FAIL);
			return;
		}

	}

}
