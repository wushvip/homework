package com.chinamobile.cmss.bcse.config.dao;


import java.util.Map;

import com.chinamobile.cmss.bcse.config.bean.RecoveryDicBean;
/**
 * 
 * @ClassName: RecoveryDicDao 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:10:16
 */
public interface RecoveryDicDao {
	public Integer showNum(RecoveryDicBean dic);
	public Integer insert(RecoveryDicBean dic);
	
	public Integer update(RecoveryDicBean dic);
	
	//查询出相应的语料,参数有appId和mode
	public String findByMode(Map<String,Object> map);
}
