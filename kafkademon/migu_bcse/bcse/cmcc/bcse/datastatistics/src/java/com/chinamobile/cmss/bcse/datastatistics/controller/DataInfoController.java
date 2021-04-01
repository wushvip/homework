package com.chinamobile.cmss.bcse.datastatistics.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.service.IDataInfoService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;


/** 
 * @ClassName: DataInfoController  数据量获取接口
 * @Description: 
 * @author: zhuxiang
 * @date: 2017年3月9日 下午3:05:00
 */

@Controller
@RequestMapping(value = "/statistics")
public class DataInfoController {
	
	@Resource
	private IDataInfoService dataInfoService;
    
	@Resource
	private RequestAuthorization authService;

	/**
	 * 
	 * @Title: showDataInfo 
	 * @Description: 获取数据量
	 * @param logReqBean
	 * @return
	 * @throws ParseException
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/num",method=RequestMethod.POST)
	public ResultBean showDataInfo(@RequestBody @Validated(GroupA.class) LogReqBean logReqBean, BindingResult result) throws ParseException {
		LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "DataInfo show");
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(logReqBean)), true)) {
				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
				return resultBean;
			}
			dataInfoService.showDataInfo(logReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
}