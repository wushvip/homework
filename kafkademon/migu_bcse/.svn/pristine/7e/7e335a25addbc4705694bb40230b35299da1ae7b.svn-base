package com.chinamobile.cmss.bcse.config.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.bean.FacetRuleReq;
import com.chinamobile.cmss.bcse.config.bean.Rule;
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.dao.FacetRuleDao;
import com.chinamobile.cmss.bcse.config.service.FacetRuleService;
import com.chinamobile.cmss.bcse.config.util.TypeConvertor;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: FacetRuleServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:08:13
 */
@Service("facetRuleService")
public class FacetRuleServiceImpl implements FacetRuleService{
	
	@Resource
	private RedisService redisService;
	
	@Resource
	private FacetRuleDao facetRuleDao;
	
	@Transactional
	public void insert(FacetRuleReq req, ResultBean resultBean){
			try {
				//业务校验
				if(!validate(req, resultBean)){
					return;
				}
				
				Rule rule=TypeConvertor.cast(req);
				facetRuleDao.insert(rule);
				List<String> fields=req.getIncludeFields();
				String key=req.getAppId()+RuleType.FACET_RULE.getName()+req.getRuleName();
				redisService.saveOrUpdate(key, fields);
			} catch (Exception e) {
				LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
						LogUtil.CONFIG_LOG, e,MsgConfig.AddFacetRuleError);
				throw new RuntimeException(MsgConfig.AddFacetRuleError);
			}
			/*
			List<String> list=(List)redisService.get(key);
			System.out.println(list.size());
			*/
			/*
			try{
				int i=10;
				int b=i%0;
			}catch(Exception e){
				throw new RedisSystemException("aaa", e);
			}
			*/
			
	}
	
	@Override
	public void show(Map<String,Object> map, ResultBean resultBean) {
		try {
			List<Rule> ruleList=facetRuleDao.show(map);
			List<FacetRuleReq> reqList=new ArrayList<FacetRuleReq>();
			for(Rule r:ruleList){
				FacetRuleReq req=new FacetRuleReq();
				req.setRuleName(r.getRuleName());
				String fields=r.getIncludeFields();
				String[] field=fields.split(";");
				List<String> includeFields=new ArrayList<String>();
				for(String str:field){
					includeFields.add(str);
				}
				req.setIncludeFields(includeFields);
				reqList.add(req);
			}
			JSONObject object=new JSONObject();
			object.put("ruleList", reqList);
			resultBean.setResult(object);
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("userId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.GetFacetRuleError);
			throw new RuntimeException(MsgConfig.GetFacetRuleError);
		}
	}

	@Override
	public void update(FacetRuleReq req, ResultBean resultBean) {
		try {
			
			Rule rule=TypeConvertor.cast(req);
			Integer num=facetRuleDao.update(rule);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_UPDATE_VALIDATION);
				return;
			}

			//不允许修改规则名
			String key=req.getAppId()+RuleType.FACET_RULE.getName()+req.getRuleName();
			List<String> fields=req.getIncludeFields();
			redisService.saveOrUpdate(key, fields);
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UpdateFacetRuleError);
			throw new RuntimeException(MsgConfig.UpdateFacetRuleError);
		}
	}

	@Override
	public void deleteByRuleName(Map<String,Object> map, ResultBean resultBean) {
		try {
			Integer num=facetRuleDao.deleteByRuleName(map);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_DELETE_VALIDATION);
				return;
			}
			String ruleName=map.get("ruleName").toString();
			String appId=map.get("appId").toString();
			String key=appId+RuleType.FACET_RULE.getName()+ruleName;
			redisService.delete(key);
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.DelFacetRuleError);
			throw new RuntimeException(MsgConfig.DelFacetRuleError);
		}
	}

	@Override
	public void deleteByRuleNames(Map<String,Object> map, ResultBean resultBean) {
		try {
			facetRuleDao.deleteByRuleNames(map);
			String appId=map.get("appId").toString();
			@SuppressWarnings("unchecked")
			List<String> ruleNames=(List<String>)map.get("ruleNames");
			for(String ruleName:ruleNames){
				String key=appId+RuleType.FACET_RULE.getName()+ruleName;
				redisService.delete(key);
			}
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.BashDelFacetRuleError);
			throw new RuntimeException(MsgConfig.BashDelFacetRuleError);
		}
	}

	private boolean validate(FacetRuleReq req,ResultBean resultBean){
		boolean flag=false;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appId", req.getAppId());
		map.put("userId", req.getUserId());
		map.put("ruleType", RuleType.FACET_RULE.getValue());
		Integer totalNum=facetRuleDao.showNum(map);
		if(totalNum>=Config.RULE_NUM_LIMIT){
			resultBean.setMessage(MsgConfig.RULE_NUM_EXCEEDS);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		map.put("ruleName", req.getRuleName());
		Integer num=facetRuleDao.showNum(map);
		if(num>0){
			resultBean.setMessage(MsgConfig.RULE_NAME_EXIST);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		flag=true;
		return flag;
	}
}
