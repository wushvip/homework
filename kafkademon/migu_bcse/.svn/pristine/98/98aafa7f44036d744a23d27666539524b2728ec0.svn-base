package com.chinamobile.cmss.bcse.config.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.bean.RoughBean;
import com.chinamobile.cmss.bcse.config.bean.RoughRuleReq;
import com.chinamobile.cmss.bcse.config.bean.Rule;
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.dao.FieldsDao;
import com.chinamobile.cmss.bcse.config.dao.RoughRuleDao;
import com.chinamobile.cmss.bcse.config.dao.ShieldRuleDao;
import com.chinamobile.cmss.bcse.config.service.RoughRuleService;
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
 * @ClassName: RoughRuleServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:09:04
 */
@Service("roughRuleService")
public class RoughRuleServiceImpl implements RoughRuleService{
	
	@Resource
	private RoughRuleDao roughRuleDao;
	@Resource
	private ShieldRuleDao shieldRuleDao;
	@Resource
	private FieldsDao fieldsDao;
	
	@Transactional(timeout=10)
	@Override
	public void insert(RoughRuleReq req, ResultBean resultBean) {
		try {
			//业务校验
			if(!validate(req, resultBean)){
				return;
			}
			Rule rule=TypeConvertor.cast(req);
			roughRuleDao.insert(rule);
			
			SearchHandler handler=getHandler(req);
			
			List<String> fqs=genFq(req.getAppId(), req.getUserId(), req.getFields());
			//设置fq参数
			if(null!=fqs&&fqs.size()>0){
				handler.setFqs(fqs);
			}
			
			String fl=genFl(req.getAppId());
			if(StringUtil.isNotEmpty(fl)){
				handler.setFl(fl);
			}
			SolrConfigUtil.addSearchHandler(req.getAppId(), handler);
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.AddRoughRuleError);
			throw new RuntimeException(MsgConfig.AddRoughRuleError);
		}
	}

	@Override
	public void show(Map<String,Object> map, ResultBean resultBean) {
		try {
			List<Rule> ruleList=roughRuleDao.show(map);
			List<RoughRuleReq> reqList=new ArrayList<RoughRuleReq>();
			for(Rule r:ruleList){
				String fields=r.getIncludeFields();
				String weights=r.getFieldWeights();
				String[] fieldsArr=fields.split(";");
				String[] weightsArr=weights.split(";");
				List<RoughBean> roughList=new ArrayList<RoughBean>();
			    for(int i=0;i<fieldsArr.length;i++){
			    	RoughBean bean=new RoughBean();
			    	bean.setFieldName(fieldsArr[i]);
			    	bean.setFieldWeight(weightsArr[i]);
			    	roughList.add(bean);
			    }
				RoughRuleReq req=new RoughRuleReq();
				req.setRuleName(r.getRuleName());
				req.setFields(roughList);
				reqList.add(req);
			}
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("ruleList", reqList);
			resultBean.setResult(jsonObject);
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.GetRoughRuleError);
			throw new RuntimeException(MsgConfig.GetRoughRuleError);
		}
	}
	
	@Transactional(timeout=10)
	@Override
	public void update(RoughRuleReq req, ResultBean resultBean) {
		try {
			Rule rule=TypeConvertor.cast(req);
			Integer num=roughRuleDao.update(rule);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_UPDATE_VALIDATION);
				return;
			}

			SearchHandler handler=getHandler(req);
			//修改操作也需要去获取屏蔽相关的内容
			List<String> fqs=genFq(req.getAppId(), req.getUserId(), req.getFields());
			//设置fq参数
			if(null!=fqs&&fqs.size()>0){
				handler.setFqs(fqs);
			}
			SolrConfigUtil.modifyRoughSortHandler(req.getAppId(), handler);
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UpdateRoughRuleError);
			throw new RuntimeException(MsgConfig.UpdateRoughRuleError);
		}
	}
	
	@Transactional(timeout=10)
	@Override
	public void  deleteByRuleName(Map<String,Object> map, ResultBean resultBean) {
		try {
			Integer num=roughRuleDao.deleteByRuleName(map);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_DELETE_VALIDATION);
				return;
			}
			String appId=map.get("appId").toString();
			String ruleName=map.get("ruleName").toString();
			String handlerName=Config.ROUGH_SORT_PREX+ruleName;
			SolrConfigUtil.deleteHandler(appId, handlerName);
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.DelRoughRuleError);
			throw new RuntimeException(MsgConfig.DelRoughRuleError);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(timeout=10)
	@Override
	public void deleteByRuleNames(Map<String,Object> map, ResultBean resultBean) {
		try {
			List<String> ruleNames=(List<String>)map.get("ruleNames");
			
			Integer num=roughRuleDao.deleteByRuleNames(map);
			if(num<ruleNames.size()){
				resultBean.setMessage(MsgConfig.RULE_BASHDELETE_VALIDATION);
				return;
			}
			
			String appId=map.get("appId").toString();
			for(String ruleName:ruleNames){
				String handlerName=Config.ROUGH_SORT_PREX+ruleName;
				SolrConfigUtil.deleteHandler(appId, handlerName);
			}
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.BashDelRoughRuleError);
			throw new RuntimeException(MsgConfig.BashDelRoughRuleError);
		}
	}
	
	private SearchHandler getHandler(RoughRuleReq req){
		String ruleName=req.getRuleName();
		List<RoughBean> beanList=req.getFields();
		StringBuffer qf=new StringBuffer();
		for(RoughBean bean:beanList){
			String name=bean.getFieldName();
			String weight=bean.getFieldWeight();
			qf.append(name).append("^").append(weight).append(" ");
		}
		qf.deleteCharAt(qf.length()-1);
		SearchHandler handler=new SearchHandler();
		handler.setHandlerName(Config.ROUGH_SORT_PREX+ruleName);
		handler.setQf(qf.toString());
		return handler;
	}
	

	private boolean validate(RoughRuleReq req,ResultBean resultBean){
		boolean flag=false;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appId", req.getAppId());
		map.put("userId", req.getUserId());
		map.put("ruleType", RuleType.ROUGH_RULE.getValue());
		Integer totalNum=roughRuleDao.showNum(map);
		if(totalNum>=Config.RULE_NUM_LIMIT){
			resultBean.setMessage(MsgConfig.RULE_NUM_EXCEEDS);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		map.put("ruleName", req.getRuleName());
		Integer num=roughRuleDao.showNum(map);
		if(num>0){
			resultBean.setMessage("该规则名已经存在");
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		flag=true;
		return flag;
	}
	
	
	private List<String> genFq(String appId,String userId,List<RoughBean> roughBeanList){
		List<String> fqs=new ArrayList<String>();

		Map<String,String> shieldMap=new HashMap<String,String>();
		shieldMap.put("appId", appId);
		shieldMap.put("userId", userId);
		shieldMap.put("ruleType", RuleType.SHIELD_RULE.getValue());
		List<String> words=shieldRuleDao.showInfo(shieldMap);
		if(null==words||words.size()<1){
			return fqs;
		}
		//得到所有的屏蔽词
		String fq=castForFq(words);	
		for(RoughBean bean:roughBeanList){
			String fieldName=bean.getFieldName();
			StringBuffer sb=new StringBuffer();
			sb.append(fieldName).append(":(").append(fq).append(")");
			fqs.add(sb.toString());
			
		}
		
		return fqs;
	}
	
	
	private String castForFq(List<String> list){
		StringBuffer sb=new StringBuffer();
		for(String str:list){
			String fq=str.replace(";"," -");
			sb.append(" -").append(fq);
		}
		return sb.toString();
	}

	private String genFl(String appId){
		StringBuffer fl=new StringBuffer();
		List<String> showFields=fieldsDao.showShowFields(appId);
		if(null==showFields||showFields.size()<1){
			return null;
		}
		for(String str:showFields){
			fl.append(str).append(" ");
		}
		
		return fl.toString();
	}
	
	
	
}
