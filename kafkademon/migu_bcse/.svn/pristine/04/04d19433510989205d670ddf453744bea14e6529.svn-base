package com.chinamobile.cmss.bcse.evaluate.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoReqBean;
import com.chinamobile.cmss.bcse.evaluate.service.EvaluateDataInfoService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;

/** 
 * @ClassName: EvaluateDataInfoController 
 * @Description:评测体系语料管理controller
 * @author: dingo
 * @date: 2016年11月30日 上午10:06:35  
 */
@Controller
@RequestMapping("/evaluation/dic")
public class EvaluateDataInfoController {

	
	@Resource
	private EvaluateDataInfoService evaluateDataInfoService;
	@Resource
	private RequestAuthorization authService;
	/**
	 * 
	 * @Title: ftpUpload 
	 * @Description: TODO
	 * @param file
	 * @param userId
	 * @param appId
	 * @param tableId
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResultBean ftpUpload(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "userId", required = true) String userId) {
		LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "evaluation dataUpload local");
		ResultBean resultBean = new ResultBean();
		try {
			EvaluateAppInfoReqBean appReqBean = new EvaluateAppInfoReqBean();
			appReqBean.setUserId(userId);
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			String savePath = this.getClass().getResource("").getPath();
			evaluateDataInfoService.localUploadFile(savePath, file, resultBean);
		} catch (Exception e) {
			e.printStackTrace();
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
}
