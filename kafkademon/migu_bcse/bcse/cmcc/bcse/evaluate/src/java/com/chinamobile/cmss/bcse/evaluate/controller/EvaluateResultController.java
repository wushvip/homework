package com.chinamobile.cmss.bcse.evaluate.controller;

import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateResultReqBean;
import com.chinamobile.cmss.bcse.evaluate.bean.PointResBean;
import com.chinamobile.cmss.bcse.evaluate.bean.vo.PointResTransfer;
import com.chinamobile.cmss.bcse.evaluate.service.EvaluateResultService;
import com.chinamobile.cmss.bcse.evaluate.utils.ExcelUtil;
import com.chinamobile.cmss.bcse.evaluate.utils.StringUtils;
import com.chinamobile.cmss.bcse.evaluate.utils.annotation.ExcelAnnotationReader;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;
import com.ctc.wstx.util.DataUtil;

/** 
 * @ClassName: EvaluateResultController 
 * @Description: 评测结果管理的controller
 * @author: dingo
 * @date: 2016年12月1日 上午9:14:05  
 */
@Controller
@RequestMapping("/evaluation/result")
public class EvaluateResultController {

	@Resource
	private EvaluateResultService evaluateResultService;
	@Resource
	private RequestAuthorization authService;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResultBean addAppInfo(@RequestBody EvaluateResultReqBean resultReqBean,HttpServletRequest request){
		LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "evaluation getResult");
		ResultBean resultBean = new ResultBean();
		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(resultReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			evaluateResultService.executeEvaluation(resultReqBean,resultBean,request);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	
	/**
	 * 导出
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String exportExcel(@ModelAttribute("form") EvaluateResultReqBean resultReqBean, HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultBean resultBean = new ResultBean();
		PointResBean pointResBean = evaluateResultService.getExportResult(resultReqBean,resultBean,request);
		response.setContentType("octets/stream");
	    response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("评测体系结果"+DateUtils.formatDate(new Date(), "YYYYMMDD"), "UTF-8") + ".xls");
		
		ExcelUtil excelUtil = new ExcelUtil(response.getOutputStream(), new ExcelAnnotationReader(PointResTransfer.class), null);  
		excelUtil.exportEx(evaluateResultService.parseTransfer(pointResBean));  
		return null;   
	}

}
