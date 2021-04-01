package com.chinamobile.cmss.bcse.config.controller;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinamobile.cmss.bcse.config.bean.AnalysisReq;
import com.chinamobile.cmss.bcse.config.service.AnalysisService;
import com.chinamobile.cmss.bcse.config.validator.AnalysisValidator;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

/**
 * 分词接口，供sdk调用
 * @ClassName: AnalysisController 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:52:54
 */
@Controller
public class AnalysisController {
	
	@Resource
	private AnalysisService analysisService;
	
	@ResponseBody
	@RequestMapping(value = "/analyzer", method = RequestMethod.GET)
	public ResultBean analyse(@BeanParam AnalysisReq req, BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "analysis ...");
    	ResultBean resultBean = new ResultBean();
    	//validate params 
    	boolean flag=new AnalysisValidator().validator(req, resultBean);
    	if(!flag){
    		return resultBean;
    	}
    	
    	try {
    		analysisService.analyse(req, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
}
