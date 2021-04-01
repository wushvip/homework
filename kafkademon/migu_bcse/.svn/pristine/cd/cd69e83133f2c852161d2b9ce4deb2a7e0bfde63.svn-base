package com.chinamobile.cmss.bcse.search.controller;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.SearchBean.searchGroup;
import com.chinamobile.cmss.bcse.search.bean.SearchInput;
import com.chinamobile.cmss.bcse.search.handler.SearchHandler;
import com.chinamobile.cmss.bcse.search.log.SaveSearchLog;
import com.chinamobile.cmss.bcse.search.tool.BeanUtil;
import com.chinamobile.cmss.bcse.search.validator.SearchValidator;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;
/** 
 * @ClassName: SearchController 
 * @Description: TODO
 * @author: chenmin
 * @date: 2017年3月6日 下午5:52:25  
 */
@Controller
@RequestMapping("/search")
public class SearchController {

	@Resource
	private RequestAuthorization authService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResultBean search(@RequestBody SearchInput searchInput){

		ResultBean resultBean = new ResultBean();
		//validate params 
		boolean flag=new SearchValidator().validator(searchInput, resultBean);
		/*if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(searchInput)), true)) {

			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

			return resultBean;

		}*/

		if(!flag) return resultBean;
		//BCSEQuery
		BCSEQuery bcseQuery=new BCSEQuery();
		long startTime=System.currentTimeMillis();
		//parse query
		BeanUtil.inputToQuery(searchInput, bcseQuery);
		//search enter
		new SearchHandler(bcseQuery).search();
		long endTime=System.currentTimeMillis();
        bcseQuery.getSearchResult().setCostTime(endTime-startTime);    
        if (!"".equals(searchInput.getCt()) && searchInput.getCt() != null) {
        	bcseQuery.setUserId(bcseQuery.getUserId()+searchInput.getCt());
		}
        if (!"".equals(searchInput.getBookType()) && searchInput.getBookType() != null) {
        	bcseQuery.setUserId(bcseQuery.getUserId()+searchInput.getBookType());
		}
		//write log
	    new SaveSearchLog().saveLogInfo(bcseQuery);
	     
		
        resultBean.setResult(bcseQuery.getSearchResult());

		return resultBean;

	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/saveSearchLog", method = RequestMethod.POST)
	public ResultBean saveLog(@RequestBody SearchInput searchInput){

		ResultBean resultBean = new ResultBean();
		/*if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(searchInput)), true)) {

			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

			return resultBean;

		}*/

		BCSEQuery bcseQuery=new BCSEQuery();
		//parse query
		BeanUtil.inputToQuery(searchInput, bcseQuery);
        if (!"".equals(searchInput.getCt()) && searchInput.getCt() != null) {
        	bcseQuery.setUserId(bcseQuery.getUserId()+searchInput.getCt());
		}
        if (!"".equals(searchInput.getBookType()) && searchInput.getBookType() != null) {
        	bcseQuery.setUserId(bcseQuery.getUserId()+searchInput.getBookType());
		}
		//write log
        try {
        	new SaveSearchLog().saveSearchLog(bcseQuery);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;

	}
	
	

}
