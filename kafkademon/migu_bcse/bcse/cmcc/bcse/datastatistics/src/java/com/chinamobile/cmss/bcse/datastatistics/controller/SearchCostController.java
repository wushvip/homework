package com.chinamobile.cmss.bcse.datastatistics.controller;

import java.text.ParseException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.UserAppBean;
import com.chinamobile.cmss.bcse.datastatistics.service.ISearchCostService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;


/** 
 * @ClassName: SearchCostController 
 * @Description: TODO 搜索耗时接口
 * @author: zhuxiang
 * @date: 2017年3月9日 下午3:05:00  
 */
@Controller
@RequestMapping("/statistics")
public class SearchCostController {

	@Resource
	private ISearchCostService searchCostService;
	
	@Resource
	private RequestAuthorization authService;

	
	/** 
	 * @Title: getSearchCost 
	 * @Description: TODO 搜索耗时数据
	 * @param logReqBean
	 * @return
	 * @throws ParseException
	 * @return: JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/cost",method=RequestMethod.POST)
	public ResultBean showSearchCost(@RequestBody @Validated(GroupA.class) LogReqBean logReqBean, 
			BindingResult result) throws ParseException {
		LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "SearchCost show");
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try{
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(logReqBean)), true)) {
				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
				return resultBean;
			}
			if ("day".equals(logReqBean.getDimension())) {
				searchCostService.showSearchCostByDay(logReqBean,resultBean);
			} else {
				searchCostService.showSearchCostByHour(logReqBean,resultBean);
			}
		}catch(Exception e){
			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;
	}
}
