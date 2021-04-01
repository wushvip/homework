package com.chinamobile.cmss.bcse.evaluate.service;

import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoBean;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoReqBean;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

@Service("evaluateAppInfoService")
public interface EvaluateAppInfoService {

	void addAppInfoDetail(EvaluateAppInfoReqBean appReqBean, ResultBean resultBean);

	EvaluateAppInfoBean getAppInfoById(String id);
}
