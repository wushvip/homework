package com.chinamobile.cmss.bcse.evaluate.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoReqBean;
import com.chinamobile.cmss.bcse.evaluate.service.EvaluateAppInfoService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @ClassName: EvaluateAppInfoController
 * @Description: 评测体系应用信息的controller
 * @author: lw
 * @date: 2016年11月29日09:40:29
 */
@Controller
@RequestMapping("/evaluation/app")
public class EvaluateAppInfoController {
	@Resource
	private EvaluateAppInfoService evaluateAppInfoService;
	@Resource
	private RequestAuthorization authService;
	
	@ResponseBody
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResultBean addAppInfo(@RequestBody EvaluateAppInfoReqBean appReqBean) {
		LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "evaluate appinfo add");
		ResultBean resultBean = new ResultBean();
		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			evaluateAppInfoService.addAppInfoDetail(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	public static void main(String[] args) {
		XStream x = new XStream();
		x.autodetectAnnotations(true);
		x.alias("EvaluateAppInfoReqBean", EvaluateAppInfoReqBean.class); 
		EvaluateAppInfoReqBean a = new EvaluateAppInfoReqBean();
		a.setAppId("adsfasdf");
		a.setEvaluateField("ID,TITLE");
		System.out.println(x.toXML(a));
		
	}

}
