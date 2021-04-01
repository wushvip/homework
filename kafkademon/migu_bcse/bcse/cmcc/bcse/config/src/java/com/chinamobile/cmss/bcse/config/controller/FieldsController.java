package com.chinamobile.cmss.bcse.config.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinamobile.cmss.bcse.config.service.FieldsService;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
/**
 * 获取字段信息接口
 * @ClassName: FieldsController 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:55:19
 */
@Controller
public class FieldsController {
	@Resource
	private FieldsService fieldsService;
	@ResponseBody
	@RequestMapping(value="/config/fields",method=RequestMethod.GET)
	public ResultBean getFields(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "show fields...");
		ResultBean resultBean=new ResultBean();
		try {
			
			fieldsService.show(appId,userId,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
}
