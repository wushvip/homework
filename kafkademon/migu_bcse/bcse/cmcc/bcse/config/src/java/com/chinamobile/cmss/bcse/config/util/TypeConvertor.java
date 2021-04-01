package com.chinamobile.cmss.bcse.config.util;

import java.util.List;

import com.chinamobile.cmss.bcse.config.bean.FacetRuleReq;
import com.chinamobile.cmss.bcse.config.bean.RoughBean;
import com.chinamobile.cmss.bcse.config.bean.RoughRuleReq;
import com.chinamobile.cmss.bcse.config.bean.Rule;
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.bean.ShieldRuleReq;
import com.chinamobile.cmss.bcse.config.bean.SpreadRuleReq;
import com.chinamobile.cmss.bcse.config.bean.SuggestRuleReq;
/**
 * 实体bean转换类
 * @ClassName: TypeConvertor 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:04:57
 */
public class TypeConvertor {
	/**
	 * 推广规则请求转化为规则实体
	 * @Title: cast 
	 * @Description: TODO
	 * @param req
	 * @return
	 * @return: Rule
	 */
	public static Rule cast(SpreadRuleReq req){
		Rule rule=new Rule();
		String appId=req.getAppId();
		if(StringUtil.isNotEmpty(appId)){
			rule.setAppId(appId);
		}
		String userId=req.getUserId();
		if(StringUtil.isNotEmpty(userId)){
			rule.setUserId(userId);
		}
		String ruleName=req.getRuleName();
		if(StringUtil.isNotEmpty(ruleName)){
			rule.setRuleName(ruleName);
		}
		
		List<String> words=req.getIncludeKeywords();
		if(null!=words&&words.size()>0){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<words.size();i++){
				sb.append(words.get(i)).append(";");
			}
			sb.deleteCharAt(sb.length()-1);
			rule.setIncludeKeywords(sb.toString());
		}
		
		List<String> ids=req.getSpreadIds();
		if(null!=ids&&ids.size()>0){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<ids.size();i++){
				sb.append(ids.get(i)).append(";");
			}
			sb.deleteCharAt(sb.length()-1);
			rule.setSpreadIds(sb.toString());
		}
		rule.setRuleType(RuleType.SPREAD_RULE.getValue());
		return rule;
	}
	
	
	
	public static Rule cast(RoughRuleReq req){
		Rule rule=new Rule();
		String appId=req.getAppId();
		if(StringUtil.isNotEmpty(appId)){
			rule.setAppId(appId);
		}
		String userId=req.getUserId();
		if(StringUtil.isNotEmpty(userId)){
			rule.setUserId(userId);
		}
		String ruleName=req.getRuleName();
		if(StringUtil.isNotEmpty(ruleName)){
			rule.setRuleName(ruleName);
		}
		
		List<RoughBean> beanList=req.getFields();
		if(null!=beanList&&beanList.size()>0){
			StringBuffer fields=new StringBuffer();
			StringBuffer weights=new StringBuffer();
			for(int i=0;i<beanList.size();i++){
				RoughBean bean=beanList.get(i);
				fields.append(bean.getFieldName()).append(";");
				weights.append(bean.getFieldWeight()).append(";");
			}
			fields.deleteCharAt(fields.length()-1);
			weights.deleteCharAt(weights.length()-1);
			rule.setIncludeFields(fields.toString());
			rule.setFieldWeights(weights.toString());
		}
		rule.setRuleType(RuleType.ROUGH_RULE.getValue());
		return rule;
	}
	
	
	public static Rule cast(SuggestRuleReq req){
		Rule rule=new Rule();
		String appId=req.getAppId();
		if(StringUtil.isNotEmpty(appId)){
			rule.setAppId(appId);
		}
		String userId=req.getUserId();
		if(StringUtil.isNotEmpty(userId)){
			rule.setUserId(userId);
		}
		String ruleName=req.getRuleName();
		if(StringUtil.isNotEmpty(ruleName)){
			rule.setRuleName(ruleName);
		}
		
		List<String> suggestFields=req.getIncludeFields();
		if(null!=suggestFields&&suggestFields.size()>0){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<suggestFields.size();i++){
				sb.append(suggestFields.get(i)).append(";");
			}
			sb.deleteCharAt(sb.length()-1);
			rule.setIncludeFields(sb.toString());
		}
		
		String showField=req.getShowField();
		if(StringUtil.isNotEmpty(showField)){
			rule.setShowField(showField);
		}
		rule.setRuleType(RuleType.SUGGEST_RULE.getValue());
		return rule;
	}
	
	
	public static Rule cast(FacetRuleReq req){
		Rule rule=new Rule();
		String appId=req.getAppId();
		if(StringUtil.isNotEmpty(appId)){
			rule.setAppId(appId);
		}
		String userId=req.getUserId();
		if(StringUtil.isNotEmpty(userId)){
			rule.setUserId(userId);
		}
		String ruleName=req.getRuleName();
		if(StringUtil.isNotEmpty(ruleName)){
			rule.setRuleName(ruleName);
		}
		
		List<String> includeFields=req.getIncludeFields();
		if(null!=includeFields&&includeFields.size()>0){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<includeFields.size();i++){
				sb.append(includeFields.get(i)).append(";");
			}
			sb.deleteCharAt(sb.length()-1);
			rule.setIncludeFields(sb.toString());
		}
		
		rule.setRuleType(RuleType.FACET_RULE.getValue());
		return rule;
	}
	
	
	public static Rule cast(ShieldRuleReq req){
		Rule rule=new Rule();
		String appId=req.getAppId();
		if(StringUtil.isNotEmpty(appId)){
			rule.setAppId(appId);
		}
		String userId=req.getUserId();
		if(StringUtil.isNotEmpty(userId)){
			rule.setUserId(userId);
		}
		String ruleName=req.getRuleName();
		if(StringUtil.isNotEmpty(ruleName)){
			rule.setRuleName(ruleName);
		}
		
		List<String> words=req.getIncludeKeywords();
		if(null!=words&&words.size()>0){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<words.size();i++){
				sb.append(words.get(i)).append(";");
			}
			sb.deleteCharAt(sb.length()-1);
			rule.setIncludeKeywords(sb.toString());
		}
		rule.setRuleType(RuleType.SHIELD_RULE.getValue());
		return rule;
	}
	
	
}
