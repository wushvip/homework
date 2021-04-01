package com.chinamobile.cmss.bcse.config.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinamobile.cmss.bcse.config.bean.BashDeleteRuleReq;
import com.chinamobile.cmss.bcse.config.bean.FacetRuleReq;
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.service.impl.FacetRuleServiceImpl;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
/**
 * 分组规则接口
 * @ClassName: FacetRuleController 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:54:18
 */
@Controller
public class FacetRuleController {
	@Resource
	private FacetRuleServiceImpl facetRuleService;
	/**
	 * 获取分组规则
	 * @Title: getInfo 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/facet",method=RequestMethod.GET)
	public ResultBean getInfo(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "show facetRules...");
		ResultBean resultBean=new ResultBean();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("appId", appId);
			map.put("userId", userId);
			map.put("ruleType", RuleType.FACET_RULE.getValue());
			facetRuleService.show(map,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	/**
	 * 添加分组规则
	 * @Title: insert 
	 * @Description: TODO
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/facet",method=RequestMethod.POST)
	public ResultBean insert(@RequestBody @Validated FacetRuleReq req,BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "insert facetRules...");
		ResultBean resultBean=new ResultBean();
		if (result.hasErrors()) {
    		Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			facetRuleService.insert(req, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 更新分组规则
	 * @Title: update 
	 * @Description: TODO
	 * @param ruleName
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/facet/{ruleName}",method=RequestMethod.PUT)
	public ResultBean update(@PathVariable("ruleName") String ruleName,
			@RequestBody FacetRuleReq req,BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "update facetRules...");
		ResultBean resultBean=new ResultBean();
		if (result.hasErrors()) {
    		Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			req.setRuleName(ruleName);
			facetRuleService.update(req, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 删除分组规则
	 * @Title: delete 
	 * @Description: TODO
	 * @param ruleName
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/facet/{ruleName}",method=RequestMethod.DELETE)
	public ResultBean delete(@PathVariable("ruleName") String ruleName,
			@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "delete facetRules...");
		ResultBean resultBean=new ResultBean();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ruleName",ruleName);
			map.put("appId", appId);
			map.put("userId", userId);
			map.put("ruleType", RuleType.FACET_RULE.getValue());
			facetRuleService.deleteByRuleName(map, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 批量删除分组规则
	 * @Title: deleteBash 
	 * @Description: TODO
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/facet/bash",method=RequestMethod.DELETE)
	public ResultBean deleteBash(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value="ruleNames",required=true)List<String> ruleNames){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "bash delete  facetRules...");
		ResultBean resultBean=new ResultBean();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ruleNames", ruleNames);
			map.put("appId", appId);
			map.put("userId", userId);
			map.put("ruleType", RuleType.FACET_RULE.getValue());
			facetRuleService.deleteByRuleNames(map, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	@ResponseBody
	@RequestMapping(value="/config/facet/bash",method=RequestMethod.POST)
	public ResultBean deletes(@RequestBody @Validated BashDeleteRuleReq req,
			BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "bash delete  facetRules...");
		ResultBean resultBean=new ResultBean();
		if (result.hasErrors()) {
    		Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ruleNames", req.getRuleNames());
			map.put("appId", req.getAppId());
			map.put("userId", req.getUserId());
			map.put("ruleType", RuleType.FACET_RULE.getValue());
			facetRuleService.deleteByRuleNames(map, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
}
