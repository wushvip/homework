package com.chinamobile.cmss.bcse.config.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.bean.Rule;
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.bean.SpreadRuleReq;
import com.chinamobile.cmss.bcse.config.dao.SpreadRuleDao;
import com.chinamobile.cmss.bcse.config.service.SpreadRuleService;
import com.chinamobile.cmss.bcse.config.util.ElevateUtil;
import com.chinamobile.cmss.bcse.config.util.TypeConvertor;
import com.chinamobile.cmss.bcse.search.bean.ZkConfigFileType;
import com.chinamobile.cmss.bcse.search.cloudapi.ReloadSolrCloud;
import com.chinamobile.cmss.bcse.search.tool.ZookeeperUtil;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: SpreadRuleServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:09:25
 */
@Service("spreadRuleService")
public class SpreadRuleServiceImpl implements SpreadRuleService{
	@Resource
	private SpreadRuleDao spreadRuleDao;
	
	@Transactional(timeout=10)
	@Override
	public void insert(SpreadRuleReq req, ResultBean resultBean) {
		try {
			//业务校验
			if(!validate(req, resultBean)){
				return;
			}
			
			Rule rule=TypeConvertor.cast(req);
			spreadRuleDao.insert(rule);
			//文件操作
			String appId=req.getAppId();
			String userId=req.getUserId();
			writeFile(appId, userId);
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.AddSpreadRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	
	
	@Override
	public void show(Map<String,Object> map, ResultBean resultBean) {
		try {
			List<Rule> ruleList=spreadRuleDao.show(map);
			List<SpreadRuleReq> reqList=new ArrayList<SpreadRuleReq>();
			//
			for(Rule rule:ruleList){
				SpreadRuleReq req=new SpreadRuleReq();
				req.setRuleName(rule.getRuleName());
				
				String keywords=rule.getIncludeKeywords();
				String[] field=keywords.split(";");
				List<String> includeKeywords=new ArrayList<String>();
				for(String str:field){
					includeKeywords.add(str);
				}
				req.setIncludeKeywords(includeKeywords);
				
				String ids=rule.getSpreadIds();
				String[] idsArr=ids.split(";");
				List<String> spreadIds=new ArrayList<String>();
				for(String id:idsArr){
					spreadIds.add(id);
				}
				req.setSpreadIds(spreadIds);
				reqList.add(req);
				
			}
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("ruleList", reqList);
			resultBean.setResult(jsonObject);
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.GetSpreadRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	
	@Transactional(timeout=10)
	@Override
	public void update(SpreadRuleReq req, ResultBean resultBean) {
		try {
			Rule rule=TypeConvertor.cast(req);
			Integer num=spreadRuleDao.update(rule);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_UPDATE_VALIDATION);
				return;
			}
			//文件操作
			String appId=req.getAppId();
			String userId=req.getUserId();
			writeFile(appId, userId);
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UpdateSpreadRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	
	@Transactional(timeout=10)
	@Override
	public void deleteByRuleName(Map<String,Object> map, ResultBean resultBean) {
		 try {
			Integer num=spreadRuleDao.deleteByRuleName(map);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_DELETE_VALIDATION);
				return;
			}
			//操作文件
			String appId=map.get("appId").toString();
			String userId=map.get("userId").toString();
			writeFile(appId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}

	
	@Transactional(timeout=10)
	@Override
	public void deleteByRuleNames(Map<String,Object> map, ResultBean resultBean) {
		try {
			spreadRuleDao.deleteByRuleNames(map);
			//操作文件
			String appId=map.get("appId").toString();
			String userId=map.get("userId").toString();
			writeFile(appId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	
	/**
	 * 写文件
	 * @param req
	 * @throws Exception 
	 */
	private void writeFile(String appId,String userId) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appId",appId);
		map.put("userId", userId);
		map.put("ruleType", RuleType.SPREAD_RULE.getValue());
		List<Rule> ruleList=spreadRuleDao.showInfo(map);
		Map<String,Set<String>> elevateMap=ElevateUtil.toMap(ruleList);
		//先去下载文件
		ZookeeperUtil.getFile(appId, ZkConfigFileType.ELEVATE);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/elevate.xml";
		ElevateUtil.writeElevate(filePath, elevateMap);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.ELEVATE);
		ReloadSolrCloud.reload(Config.ZK_HOST,appId);
	}
	
	
	/**
	 * 校验
	 * @param req
	 * @param resultBean
	 * @return
	 */
	private boolean validate(SpreadRuleReq req,ResultBean resultBean){
		boolean flag=false;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appId", req.getAppId());
		map.put("userId", req.getUserId());
		map.put("ruleType", RuleType.SPREAD_RULE.getValue());
		Integer totalNum=spreadRuleDao.showNum(map);
		if(totalNum>=Config.RULE_NUM_LIMIT){
			resultBean.setMessage(MsgConfig.RULE_NUM_EXCEEDS);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		map.put("ruleName", req.getRuleName());
		Integer num=spreadRuleDao.showNum(map);
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
