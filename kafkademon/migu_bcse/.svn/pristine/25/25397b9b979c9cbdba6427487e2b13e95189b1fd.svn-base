package com.chinamobile.cmss.bcse.search.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.SearchInput;
import com.chinamobile.cmss.bcse.search.handler.SuggestHandler;
import com.chinamobile.cmss.bcse.search.log.SaveSearchLog;
import com.chinamobile.cmss.bcse.search.tool.BeanUtil;
import com.chinamobile.cmss.bcse.search.validator.SearchValidator;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;

@Controller
@RequestMapping("/suggestion")
public class SuggestController {
	@Resource
	private RequestAuthorization authService;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResultBean search(@RequestBody  SearchInput searchInput) {

		ResultBean resultBean = new ResultBean();
		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(searchInput)), true)) {

			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

			return resultBean;

		}
		boolean flag=new SearchValidator().validator(searchInput, resultBean);
		
		if(!flag) return resultBean;
		//BCSEQuery
		BCSEQuery bcseQuery=new BCSEQuery();
		long startTime=System.currentTimeMillis();
		//parse query
		BeanUtil.inputToSuggestQuery(searchInput, bcseQuery);
		//suggest enter
		new SuggestHandler(bcseQuery).suggest();
		//write log
	    //new SaveSearchLog().saveLogInfo(bcseQuery);
	    
		long endTime=System.currentTimeMillis();
        bcseQuery.getSearchResult().setCostTime(endTime-startTime);      
        resultBean.setResult(bcseQuery.getSearchResult());

 		return resultBean;

	}
}
