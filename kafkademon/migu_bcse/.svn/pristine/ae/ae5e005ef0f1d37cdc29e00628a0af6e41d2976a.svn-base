package com.chinamobile.cmss.bcse.evaluate.dao;

import java.util.ArrayList;

import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoBean;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoReqBean;

public interface EvaluateAppInfoBeanDao {
	
    int insert(EvaluateAppInfoReqBean record);

    int insertSelective(EvaluateAppInfoReqBean record);
    
	ArrayList<EvaluateAppInfoBean> isExistApp(EvaluateAppInfoReqBean record);
	
	int update(EvaluateAppInfoReqBean record);
	
	ArrayList<EvaluateAppInfoBean> getAppInfoById(String id);
	
}