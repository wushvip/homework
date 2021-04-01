package com.chinamobile.cmss.bcse.config.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.bean.RoughBean;
import com.chinamobile.cmss.bcse.config.bean.Rule;
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.bean.ShieldRuleReq;
import com.chinamobile.cmss.bcse.config.dao.FieldsDao;
import com.chinamobile.cmss.bcse.config.dao.RoughRuleDao;
import com.chinamobile.cmss.bcse.config.dao.ShieldRuleDao;
import com.chinamobile.cmss.bcse.config.service.ShieldRuleService;
import com.chinamobile.cmss.bcse.config.util.SearchHandler;
import com.chinamobile.cmss.bcse.config.util.SolrConfigUtil;
import com.chinamobile.cmss.bcse.config.util.StringUtil;
import com.chinamobile.cmss.bcse.config.util.TypeConvertor;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: ShieldRuleServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:09:15
 */
@Service("shieldRuleService")
public class ShieldRuleServiceImpl implements ShieldRuleService {
	
	@Resource
	private RedisService redisService;
	@Resource
	private ShieldRuleDao shieldRuleDao;
	
	@Resource
	private RoughRuleDao roughRuleDao;

	@Resource
	private FieldsDao fieldsDao;
	
	@Override
	public void show(Map<String,Object> map, ResultBean resultBean) {
		try {
			List<Rule> ruleList=shieldRuleDao.show(map);
			List<ShieldRuleReq> reqList=new ArrayList<ShieldRuleReq>();
			for(Rule r:ruleList){
				ShieldRuleReq req=new ShieldRuleReq();
				req.setRuleName(r.getRuleName());
				String keywords=r.getIncludeKeywords();
				String[] field=keywords.split(";");
				List<String> includeKeywords=new ArrayList<String>();
				for(String str:field){
					includeKeywords.add(str);
				}
				req.setIncludeKeywords(includeKeywords);
				reqList.add(req);
			}
			JSONObject object=new JSONObject();
			object.put("ruleList", reqList);
			resultBean.setResult(object);
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.GetShieldRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
		
	}
	
	@Override
	public void insert(ShieldRuleReq req, ResultBean resultBean) {
		try {
			
			//业务校验
			if(!validate(req, resultBean)){
				return;
			}
			
			Rule rule=TypeConvertor.cast(req);
			shieldRuleDao.insert(rule);
			reWriteSolrConfig(req.getAppId(),req.getUserId());
			//修改默认的select handler
			modifySelectHandler(req.getAppId(),req.getUserId());
			//writeToRedis(req.getAppId(),req.getUserId());
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.AddShieldRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}


	@Override
	public void update(ShieldRuleReq req, ResultBean resultBean) {
		try {
			Rule rule=TypeConvertor.cast(req);
			Integer num=shieldRuleDao.update(rule);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_UPDATE_VALIDATION);
				return;
			}
			reWriteSolrConfig(req.getAppId(),req.getUserId());
			//修改默认的select handler
			modifySelectHandler(req.getAppId(),req.getUserId());
			//writeToRedis(req.getAppId(),req.getUserId());
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UpdateShieldRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}

	

	@Override
	public void deleteByRuleName(Map<String,Object> map, ResultBean resultBean) {
		String appId=null;
		String userId=null;
		try {
			appId=map.get("appId").toString();
			userId=map.get("userId").toString();
			Integer num=shieldRuleDao.deleteByRuleName(map);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_DELETE_VALIDATION);
				return;
			}
			
			reWriteSolrConfig(appId, userId);
			//修改默认的select handler
			modifySelectHandler(appId,userId);
			//writeToRedis(appId, userId);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.DelShieldRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}

	@Override
	public void deleteByRuleNames(Map<String,Object> map, ResultBean resultBean) {
		String appId=null;
		String userId=null;
		try {
			shieldRuleDao.deleteByRuleNames(map);
			appId=map.get("appId").toString();
			userId=map.get("userId").toString();
			reWriteSolrConfig(appId, userId);
			//修改默认的select handler
			modifySelectHandler(appId,userId);
			//writeToRedis(appId, userId);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.BashDelShieldRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	
	
	//从数据库中取出的停用词列表转化为redis需要的停用词列表
	public List<String> cast(List<String> list){
		List<String> words=new ArrayList<String>();
		for(String str:list){
			String[] strArr=str.split(";");
			addAll(words,strArr);
		}
		return words;
	}
	
	public String castForFq(List<String> list){
		StringBuffer sb=new StringBuffer();
		for(String str:list){
			String fq=str.replace(";"," -");
			sb.append(" -").append(fq);
		}
		return sb.toString();
	}

	
	public void addAll(List<String> list,String[] array){
		for(String str:array){
			if(StringUtil.isNotEmpty(str)){
				list.add(str);
			}
		}
	}
	
	/**
	 * 写redis,所有的屏蔽词都放在value中
	 * @param req
	 */
	/*private void writeToRedis(String appId,String userId) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("appId", appId);
		map.put("userId", userId);
		map.put("ruleType", RuleType.SHIELD_RULE.getValue());
		List<String> words=shieldRuleDao.showInfo(map);
		if(null==words||words.size()<1){
			return;
		}
		List<String> shieldWords=cast(words);
		String key=appId+RuleType.SHIELD_RULE.getName();
		redisService.saveOrUpdate(key, shieldWords);
	}*/
	
	private boolean validate(ShieldRuleReq req,ResultBean resultBean){
		boolean flag=false;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appId", req.getAppId());
		map.put("userId", req.getUserId());
		map.put("ruleType", RuleType.SHIELD_RULE.getValue());
		Integer totalNum=shieldRuleDao.showNum(map);
		if(totalNum>=Config.RULE_NUM_LIMIT){
			resultBean.setMessage(MsgConfig.RULE_NUM_EXCEEDS);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		map.put("ruleName", req.getRuleName());
		Integer num=shieldRuleDao.showNum(map);
		if(num>0){
			resultBean.setMessage(MsgConfig.RULE_NAME_EXIST);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		flag=true;
		return flag;
	}
	
	
	//屏蔽的实现方式
	public void reWriteSolrConfig(String appId,String userId) throws Exception{
		
		
		//1.查询出该应用的所有粗排规则
		Map<String,Object> roughMap=new HashMap<String,Object>();
		roughMap.put("appId", appId);
		roughMap.put("ruleType", RuleType.ROUGH_RULE.getValue());
		List<Rule> ruleList=roughRuleDao.show(roughMap);
		//如果没有粗排规则，则不做任何处理
		if(null==ruleList||ruleList.size()<1){
			return;
		}
		
		
		//2.查询出每个应用屏蔽词
		Map<String,String> shieldMap=new HashMap<String,String>();
		shieldMap.put("appId", appId);
		shieldMap.put("userId", userId);
		shieldMap.put("ruleType", RuleType.SHIELD_RULE.getValue());
		List<String> words=shieldRuleDao.showInfo(shieldMap);
		//得到所有的屏蔽词,如果words为空，并且solrconfig中有fq,需要删除solrconfig中的fq
		if(null==words||words.size()<1){
			List<String> handlerNames=new ArrayList<String>();
			for(Rule r:ruleList){
				handlerNames.add(Config.ROUGH_SORT_PREX+r.getRuleName());
			}
			SolrConfigUtil.deleteFq(appId, handlerNames);
			return;
		}
		
		//如果屏蔽词不为空，需要先删除fq参数，再添加fq
		String fq=castForFq(words);					
		List<SearchHandler> handlers=new ArrayList<SearchHandler>();
		for(Rule r:ruleList){
			SearchHandler handler=new SearchHandler();
			List<String> fqList=new ArrayList<String>();
			
			String includeFields=r.getIncludeFields();
			String[] array=includeFields.split(";");
			for(String str:array){
				StringBuffer sb=new StringBuffer();
				sb.append(str).append(":(").append(fq).append(")");
				fqList.add(sb.toString());
			}
			handler.setHandlerName(Config.ROUGH_SORT_PREX+r.getRuleName());
			handler.setFqs(fqList);
			handlers.add(handler);
		}
		
		//3.修改solrconfig.xml
		SolrConfigUtil.modifyHandlers(appId, handlers);
		
	}
	
	
	
	/**
	 * 修改初始化应用
	 * @Title: modifySelectHandler 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @return: void
	 */
	public void modifySelectHandler(String appId,String userId){
		try {
		
			Map<String,String> shieldMap=new HashMap<String,String>();
			shieldMap.put("appId", appId);
			shieldMap.put("userId", userId);
			shieldMap.put("ruleType", RuleType.SHIELD_RULE.getValue());
			List<String> words=shieldRuleDao.showInfo(shieldMap);
			if(null==words||words.size()<1){
				SolrConfigUtil.modifySelectHandler(appId,null);
				return;
			}
			List<String> fqs=new ArrayList<String>();
			//得到所有的屏蔽词
			String fq=castForFq(words);
			List<String> searchFields=fieldsDao.showRankFields(appId);
			for(String fieldName:searchFields){
				StringBuffer sb=new StringBuffer();
				sb.append(fieldName).append(":(").append(fq).append(")");
				fqs.add(sb.toString());
			}
			SolrConfigUtil.modifySelectHandler(appId, fqs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
