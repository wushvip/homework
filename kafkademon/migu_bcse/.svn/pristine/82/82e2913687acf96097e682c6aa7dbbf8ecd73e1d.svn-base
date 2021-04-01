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
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.bean.ShieldRuleReq;
import com.chinamobile.cmss.bcse.config.service.ShieldRuleService;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
/**
 * 屏蔽规则接口
 * @ClassName: ShieldRuleController 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:57:23
 */
@Controller
public class ShieldRuleController {
	@Resource
	private ShieldRuleService shieldRuleService;
	/**
	 * 获取屏蔽规则
	 * @Title: getInfo 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/shield",method=RequestMethod.GET)
	public ResultBean getInfo(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "show shieldRules...");
		ResultBean resultBean=new ResultBean();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("appId",appId);
			map.put("userId", userId);
			map.put("ruleType", RuleType.SHIELD_RULE.getValue());
			shieldRuleService.show(map,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	/**
	 * 插入屏蔽规则
	 * @Title: insert 
	 * @Description: TODO
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/shield",method=RequestMethod.POST)
	public ResultBean insert(@RequestBody @Validated ShieldRuleReq req,
			BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "insert shieldRules...");
		ResultBean resultBean=new ResultBean();
		if (result.hasErrors()) {
    		Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			shieldRuleService.insert(req, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 修改屏蔽规则
	 * @Title: update 
	 * @Description: TODO
	 * @param ruleName
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/shield/{ruleName}",method=RequestMethod.PUT)
	public ResultBean update(@PathVariable("ruleName") String ruleName,
			@RequestBody ShieldRuleReq req,
			BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "update shieldRules...");
		ResultBean resultBean=new ResultBean();
		if (result.hasErrors()) {
    		Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			req.setRuleName(ruleName);
			shieldRuleService.update(req, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 删除屏蔽规则
	 * @Title: delete 
	 * @Description: TODO
	 * @param ruleName
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/shield/{ruleName}",method=RequestMethod.DELETE)
	public ResultBean delete(@PathVariable("ruleName") String ruleName,
			@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "delete shieldRules...");
		ResultBean resultBean=new ResultBean();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ruleName", ruleName);
			map.put("appId", appId);
			map.put("userId",userId);
			map.put("ruleType", RuleType.SHIELD_RULE.getValue());
			shieldRuleService.deleteByRuleName(map, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 批量删除屏蔽规则
	 * @Title: deleteByIds 
	 * @Description: TODO
	 * @param req
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value="/config/shield/bash",method=RequestMethod.DELETE)
	public ResultBean deleteByRuleNames(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value="ruleNames",required=true)List<String> ruleNames){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "bash delete shieldRules...");
		ResultBean resultBean=new ResultBean();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ruleNames", ruleNames);
			map.put("appId",appId);
			map.put("userId", userId);
			map.put("ruleType", RuleType.SHIELD_RULE.getValue());
			shieldRuleService.deleteByRuleNames(map, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	@ResponseBody
	@RequestMapping(value="/config/shield/bash",method=RequestMethod.POST)
	public ResultBean deletes(@RequestBody @Validated BashDeleteRuleReq req,
			BindingResult result){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "bash delete  shieldRules...");
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
			map.put("ruleType", RuleType.SHIELD_RULE.getValue());
			shieldRuleService.deleteByRuleNames(map, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
}
