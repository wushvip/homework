package com.chinamobile.cmss.bcse.config.service;


import java.util.Map;

import com.chinamobile.cmss.bcse.config.bean.ShieldRuleReq;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: ShieldRuleService 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:11:41
 */
public interface ShieldRuleService {
	public void show(Map<String,Object> map,ResultBean resultBean);
	
	public void insert(ShieldRuleReq req,ResultBean resultBean);
	
	
	public void update(ShieldRuleReq req,ResultBean resultBean);
	
	public void deleteByRuleName(Map<String,Object> map,ResultBean resultBean);
	public void deleteByRuleNames(Map<String,Object> map,ResultBean resultBean);

	
}
