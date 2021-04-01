package com.chinamobile.cmss.bcse.evaluate.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateResultReqBean;
import com.chinamobile.cmss.bcse.evaluate.bean.PointResBean;
import com.chinamobile.cmss.bcse.evaluate.bean.vo.PointResTransfer;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
@Service("evaluateResultService")
public interface EvaluateResultService {

	void executeEvaluation(EvaluateResultReqBean resultReqBean,ResultBean resultBean,HttpServletRequest request);
	
	ArrayList<PointResTransfer> parseTransfer(PointResBean result);

	PointResBean getExportResult(EvaluateResultReqBean resultReqBean, ResultBean resultBean,HttpServletRequest request);

}
