package com.chinamobile.cmss.bcse.datastatistics.controller;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.service.IErrorLogService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupB;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;



/** 
 * @ClassName: ErrorLogController 
 * @Description: TODO 错误日志接口
 * @author: zhuxiang
 * @date: 2017年3月9日 下午2:56:00  
 */
@Controller
@RequestMapping("/statistics")

public class ErrorLogController {
	@Resource
	private IErrorLogService errorLogService;
	
	@Resource
	private RequestAuthorization authService;
	/** 
	 * @Title: getErrorLog 
	 * @Description: TODO 展示错误日志接口
	 * @param logReqBean
	 * @return
	 * @return: JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/errorLog",method=RequestMethod.GET)
	public ResultBean getErrorLog(@BeanParam @Validated({GroupB.class})LogReqBean logReqBean, BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "ErrorLog show");
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(logReqBean)), false)) {
				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
				return resultBean;
			}
			errorLogService.showErrorLogs(logReqBean,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
}
