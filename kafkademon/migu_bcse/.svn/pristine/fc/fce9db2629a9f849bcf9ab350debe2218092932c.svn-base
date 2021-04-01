package com.chinamobile.cmss.bcse.config.controller;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.config.bean.RecoveryReq;
import com.chinamobile.cmss.bcse.config.service.RecoveryService;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;


/**
 * 纠错接口
 * @ClassName: RecoveryController 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:55:33
 */
@Controller
public class RecoveryController {
	@Resource
	private RecoveryService recoveryService;
	/**
	 * 纠错
	 * @Title: analyse 
	 * @Description: TODO
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/recovery", method = RequestMethod.GET)
	public ResultBean analyse(@BeanParam @Validated RecoveryReq req, BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "spellcheck ...");
		ResultBean resultBean = new ResultBean();
    	if (result.hasErrors()) {
    		Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
    	try {
			recoveryService.recovery(req, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
			e.printStackTrace();
		}
    	return resultBean;
	}	
	
	/**
	 * 训练字典
	 * @Title: trainDic 
	 * @Description: TODO
	 * @param file
	 * @param userId
	 * @param appId
	 * @param mode
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/recovery/dic", method = RequestMethod.POST)
	public ResultBean trainDic(@RequestParam(value="file", required=true) MultipartFile file,
			@RequestParam(value="userId", required=true) String userId,
			@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="mode", required=true) String mode){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "train dic ...");
		ResultBean resultBean = new ResultBean();
    	//训练字典
		try {
			recoveryService.train(appId, userId, file, mode, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
			e.printStackTrace();
		}
    	return resultBean;
	}	
}
