package com.chinamobile.cmss.bcse.config.dao;


import java.util.List;
import java.util.Map;

import com.chinamobile.cmss.bcse.config.bean.Rule;

/**
 * 
 * @ClassName: ShieldRuleDao 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:10:27
 */
public interface ShieldRuleDao{
	public List<Rule> show(Map<String,Object> map);
	
	public Integer deleteByRuleName(Map<String,Object> map);
	public Integer deleteByRuleNames(Map<String,Object> map);
	
	public Integer insert(Rule rule);
	public Integer update(Rule rule);
	//用来做校验的，限制规则数目以及规则名称不允许重复
	public Integer showNum(Map<String,Object> map);
	
	//传appId和userId,ruleType这三个参数
	public List<String> showInfo(Map<String,String> map);
	
}
