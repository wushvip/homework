package com.chinamobile.cmss.bcse.datastatistics.controller;


import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean.hotKeyGroup;
import com.chinamobile.cmss.bcse.datastatistics.service.IHotWordsService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupB;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;


/** 
 * @ClassName: HotWordsController 
 * @Description:  热词获取接口
 * @author: zhuxiang
 * @date: 2017年3月9日 下午3:05:00 
 */
@Controller
@RequestMapping("/statistics")
public class HotWordsController {
	@Resource
	private IHotWordsService hotWordsService;
	
	@Resource
	private RequestAuthorization authService;

	/**
	 * 
	 * @Title: showHotWord 
	 * @Description: 展示热词接口
	 * @param logReqBean
	 * @return
	 * @return: ResultBean
	 */
    @ResponseBody
    @RequestMapping(value = "/hotWord",method=RequestMethod.GET)
	public ResultBean showHotWord(@BeanParam @Validated(GroupB.class) LogReqBean logReqBean,BindingResult result) {
    	LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "HotWord show");
    	ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
    	try {
    		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(logReqBean)), true)) {
				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
				return resultBean;
			}
    		hotWordsService.showHotWord(logReqBean,resultBean);
    		System.out.println(resultBean.getResult());
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
    	
		return resultBean;
	}
    
    
    /**
     * 
     * @Title: showHotWordByWeek 
     * @Description: 取前一周的热词数据
     * @param logReqBean
     * @return
     * @return: ResultBean
     */
    @ResponseBody
    @RequestMapping(value = "/hotWord/week",method=RequestMethod.GET)
	public ResultBean showHotWordByWeek(@BeanParam @Validated({hotKeyGroup.class}) LogReqBean logReqBean,BindingResult result) {
    	LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "HotWordByWeek show");
    	ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
    	try {
    		hotWordsService.showHotWordLastWeek(logReqBean,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
    	
		return resultBean;
	}
    
    
    /**
     * 
     * @Title: showHotWordByMonth 
     * @Description: 取前一个月的热词数据
     * @param logReqBean
     * @return
     * @return: ResultBean
     */
    @ResponseBody
    @RequestMapping(value = "/hotWord/month",method=RequestMethod.GET)
    public ResultBean showHotWordByMonth(@BeanParam @Validated({hotKeyGroup.class}) LogReqBean logReqBean,BindingResult result) {
    	LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "HotWordByMonth show");
    	ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
    	try {
    		hotWordsService.showHotWordLastMonth(logReqBean,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
    	
		return resultBean;
	}
    
    
    /**
     * 
     * @Title: showHotWordYesterday 
     * @Description: 获取昨天的热词数据
     * @param logReqBean
     * @return
     * @return: ResultBean
     */
    @ResponseBody
    @RequestMapping(value = "/hotWord/yesterday",method=RequestMethod.GET)
    public ResultBean showHotWordYesterday(@BeanParam @Validated({hotKeyGroup.class}) LogReqBean logReqBean,BindingResult result){
    	LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "HotWordYesterday show");
    	ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
    	try {
    		hotWordsService.showHotWordYesterday(logReqBean,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
    	
		return resultBean;
	}
}
