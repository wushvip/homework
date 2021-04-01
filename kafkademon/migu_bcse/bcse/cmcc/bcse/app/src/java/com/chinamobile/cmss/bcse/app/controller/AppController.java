package com.chinamobile.cmss.bcse.app.controller;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean.desAddGroup;
import com.chinamobile.cmss.bcse.app.service.ApplicationManagerService;
import com.chinamobile.cmss.bcse.app.util.AppStructureUtil;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupD;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupF;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;


/**
 * 
 * @ClassName: AppController
 * @Description: 应用管理的controller
 * @author: jinjing
 * @date: 2016年2月16日 下午2:19:42
 */
@Controller
@RequestMapping("/apps")
public class AppController {

	@Resource
	private ApplicationManagerService applicationManagerService;
	@Resource
	private AppStructureUtil appStructureUtil;
	@Resource
	private RequestAuthorization authService;
	
	
	/**
	 * 
	 * @Title: show
	 * @Description: 展示应用列表信息
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResultBean show(@BeanParam AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appList show");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.showAppList(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除应用
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}", method=RequestMethod.DELETE)
	public ResultBean delete(@BeanParam AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app delete");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}

		try {
			if (!authService.creatingAppCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.delete(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	
	/**
	 * 
	 * @Title: update
	 * @Description: 更新应用
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}",method=RequestMethod.POST)
	public ResultBean update(@PathVariable("appId") String appId,@RequestBody @Validated({GroupF.class}) AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app update");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
		try {
			appReqBean.setAppId(appId);
			if (!authService.creatingAppCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.update(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	

	/**
	 * 
	 * @Title: showBasicInfo
	 * @Description: 展示应用基本信息
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/basicInfo",method=RequestMethod.GET)
	public ResultBean showBasicInfo(@BeanParam  @Validated(GroupA.class)AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "basicInfo show");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.showBasicInfo(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	
	/**
	 * 
	 * @Title: addBasicInfo 
	 * @Description: 添加应用基本信息
	 * @param appReqBean
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/basicInfo",method=RequestMethod.POST)
	public ResultBean addBasicInfo(@RequestBody @Validated(desAddGroup.class) AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "basicInfo add");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.addAppInfoDetail(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}

	/**
	 * 
	 * @Title: updateBasicInfo 
	 * @Description: 更新应用基本信息
	 * @param appReqBean
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/basicInfo",method=RequestMethod.PUT)
	public ResultBean updateBasicInfo(@PathVariable(value="appId") String appId,@RequestBody AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "basicInfo update");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
		try {
			appReqBean.setAppId(appId);
			if (!authService.creatingAppCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.updateAppInfoDetail(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}

	/**
	 * 
	 * @Title: showStructure
	 * @Description: 获取应用结构
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/structure",method=RequestMethod.GET)
	public ResultBean showStructure(@BeanParam AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "show structure");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
		try {
			if (!authService.creatingAppCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.showStructure(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * @Title: addStructure
	 * @Description: 添加应用结构
	 * @param appReqBean
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/structure",method=RequestMethod.POST)
	public ResultBean addStructure(@PathVariable("appId") String appId,@RequestBody  AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "add structure ");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
		try {
			appReqBean.setAppId(appId);
			if (!authService.creatingAppCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.saveAppStructure(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;
	}



	/**
	 * @Title: create
	 * @Description:
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/last",method=RequestMethod.POST)
	public ResultBean create(@PathVariable("appId") String appId, @RequestBody AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "create app ");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}
		try {
			appReqBean.setAppId(appId);
			if (!authService.creatingAppCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.createApp(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}

	/**
	 * @Title: isExsitCreating
	 * @Description: 是否有创建中的应用
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/existFlag",method=RequestMethod.GET)
	public ResultBean isExsitCreating(@BeanParam @Validated(GroupD.class) AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app isExistCreating");
		ResultBean resultBean = new ResultBean();
		if(result.hasErrors()){
			Tool.validateParamsError(resultBean,result);
			return resultBean;
		}

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.isExistCreatingApp(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}

	/**
	 * 
	 * @Title: showIndexOper
	 * @Description: 获取索引操作历史记录数据
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/index/operations",method=RequestMethod.GET)
	public ResultBean showIndexOper(@PathVariable("appId") String appId,@BeanParam AppReqBean appReqBean) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "indexOper show");
		ResultBean resultBean = new ResultBean();

		try {
			appReqBean.setAppId(appId);
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			applicationManagerService.showIndexOper(appReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	/**
	 * 
	 * @Title: setDataApi
	 * @Description: 更新数据量
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
/*	@ResponseBody
	@RequestMapping(value = "/sdk/updateDataApi")
	public ResultBean setDataApi(@RequestBody AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "sdk updateDataApi");
		ResultBean resultBean = new ResultBean();
		try {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "set date api" + appReqBean.getAppId());
			new DataApi().setNumToDataBase(appReqBean.getUserId(), appReqBean.getAppId());
			// resultBean.setStatus(Config.RESULT_SUCCESS);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}*/

	/**
	 * 
	 * @Title: getZkHost
	 * @Description: sdk请求验证
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */

/*	@ResponseBody
	@RequestMapping(value = "/sdk/getZkHost")
	public ResultBean getZkHost(@RequestBody AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "sdk getZkHost");
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setMessage(Config.ZK_HOST);
			// resultBean.setStatus(Config.RESULT_SUCCESS);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
*/
	/**
	 * 
	 * @Title: getStructure
	 * @Description: 获取应用结构及数据表
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
/*	@ResponseBody
	@RequestMapping(value = "/sdk/getStructure")
	public ResultBean getStructure(@RequestBody AppReqBean appReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "sdk getStructure");
		ResultBean resultBean = new ResultBean();
		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
		try {
			appStructureUtil.getDataTableParam(appReqBean.getUserId(), appReqBean.getAppId(), tablePropertiesList,
					sourceFileMap);
			JSONObject dataJson = new JSONObject();
			dataJson.put("tablePropertiesList", tablePropertiesList);
			dataJson.put("sourceFileMap", sourceFileMap);
			resultBean.setResult(dataJson);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}*/
}
