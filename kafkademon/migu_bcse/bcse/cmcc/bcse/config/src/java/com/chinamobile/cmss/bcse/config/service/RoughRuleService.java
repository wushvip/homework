package com.chinamobile.cmss.bcse.config.service;


import java.util.Map;

import com.chinamobile.cmss.bcse.config.bean.RoughRuleReq;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: RoughRuleService 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:11:35
 */
public interface RoughRuleService {
	public void insert(RoughRuleReq req,ResultBean resultBean);
	public void show(Map<String,Object> map,ResultBean resultBean);
	
	public void update(RoughRuleReq req,ResultBean resultBean);
	
	public void deleteByRuleName(Map<String,Object> map,ResultBean resultBean);
	public void deleteByRuleNames(Map<String,Object> map,ResultBean resultBean);

	
}
