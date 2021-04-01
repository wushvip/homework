package com.chinamobile.cmss.bcse.index.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.avro.data.Json;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean.defaultGroup;
import com.chinamobile.cmss.bcse.index.kafka.CsvToJson;
import com.chinamobile.cmss.bcse.index.kafka.KafkaProduce;
import com.chinamobile.cmss.bcse.index.service.IndexDataOperaIndexService;
import com.chinamobile.cmss.bcse.index.service.IndexUploadFileService;
import com.chinamobile.cmss.bcse.index.service.impl.IndexDataOperaIndexServiceImpl.IndexOpType;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;
import com.sun.accessibility.internal.resources.accessibility;

/**
 * 
 * @ClassName: AppController
 * @Description: 索引操作controller
 * @author: jinjing
 * @date: 2016年2月16日 下午2:19:42
 */
@Controller
@RequestMapping("/apps")
public class indexController {

	@Resource
	private IndexDataOperaIndexService indexDataOperaIndexService;

	@Resource
	private IndexUploadFileService indexUploadFileServiceImpl;

	@Resource
	private RequestAuthorization authService;

	/**
	 * 
	 * @Title: clearIndex
	 * @Description: 清空索引操作
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/index", method = RequestMethod.DELETE)
	public ResultBean clearIndex(@PathVariable(value = "appId") String appId,
			@RequestParam(value = "userId") String userId,
			@RequestParam(value = "mutiValueSplit", required = false) String mutiValueSplit,
			@RequestParam(value = "fileSplit", required = false) String fileSplit,
			@RequestParam(value = "type", required = false, defaultValue = "") String type) {

		AppReqBean appReqBean = new AppReqBean();
		appReqBean.setAppId(appId);
		appReqBean.setUserId(userId);
		appReqBean.setMutiValueSplit(mutiValueSplit);
		appReqBean.setFileSplit(fileSplit);
		appReqBean.setType(type);

		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "index clear");
		ResultBean resultBean = new ResultBean();
		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {
			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
			return resultBean;
		}

		try {
			String operaType = "";

			if (appReqBean.getType().equals("all")) {
				// 清空索引
				try {
					operaType = "2";
					indexDataOperaIndexService.clearIndex(appReqBean, resultBean);
					saveDataOperaHistory(resultBean.getStatus(), operaType, appReqBean);
					new DataApi().setNumToDataBase(userId, appId);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
					// Tool.operateExceptionInfo(resultBean, e);
				}

			} else {
				// 删除索引
				try {
					operaType = "4";
					indexDataOperaIndexService.deleteIndexFromFile(appReqBean, resultBean);
					saveDataOperaHistory(resultBean.getStatus(), operaType, appReqBean);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setMessage(MsgConfig.FAIL_MESSAGE);
			resultBean.setStatus(Config.RESULT_FAIL);
		}

		return resultBean;
	}

	/**
	 * 
	 * @Title: updateIndex
	 * @Description: json直接更新索引
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/indexs", method = RequestMethod.POST)
	public ResultBean updateIndexs(@RequestBody @Validated(defaultGroup.class) AppReqBean appReqBean,
			BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "json indexs update");
		ResultBean resultBean = new ResultBean();
		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {
			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
			return resultBean;
		}

		try {
			if (result.hasErrors()) {
				Tool.validateParamsError(resultBean, result);
				return resultBean;
			}

			indexDataOperaIndexService.updateFormJson(appReqBean, resultBean);

		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setMessage(MsgConfig.FAIL_MESSAGE);
			resultBean.setStatus(Config.RESULT_FAIL);
		}
		return resultBean;

	}

	/**
	 * 
	 * @Title: updateIndex
	 * @Description: json直接更新索引
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/indexs/delete", method = RequestMethod.POST)
	public ResultBean deleteIndexs(@RequestBody @Validated(defaultGroup.class) AppReqBean appReqBean,
			BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "indexs delete");
		ResultBean resultBean = new ResultBean();
		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {
			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
			return resultBean;
		}

		try {
			if (result.hasErrors()) {
				Tool.validateParamsError(resultBean, result);
				return resultBean;
			}
			indexDataOperaIndexService.deleteFormJson(appReqBean, resultBean);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setMessage(MsgConfig.FAIL_MESSAGE);
			resultBean.setStatus(Config.RESULT_FAIL);
		}
		return resultBean;

	}

	/**
	 * 
	 * @Title: updateIndex
	 * @Description: 更新索引
	 * @param appReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/index", method = RequestMethod.POST)
	public ResultBean updateIndex(@RequestBody @Validated(defaultGroup.class) AppReqBean appReqBean,
			BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "index update");
		ResultBean resultBean = new ResultBean();
		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {
			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
			return resultBean;
		}
		try {
			if (result.hasErrors()) {
				Tool.validateParamsError(resultBean, result);
				return resultBean;
			}

			String operaType = "";
			try {
				operaType = "1";
				if (appReqBean.getType() != null && appReqBean.getType().equals("1")) {// 部分更新逻辑

					indexDataOperaIndexService.updateIndex(appReqBean, resultBean, IndexOpType.MOD);
				} else {
					indexDataOperaIndexService.updateIndex(appReqBean, resultBean, IndexOpType.ADD);
				}
				saveDataOperaHistory(resultBean.getStatus(), operaType, appReqBean);
			} catch (Exception e) {
				Tool.operateExceptionInfo(resultBean, e);
			}

		} catch (

		Exception e)

		{
			e.printStackTrace();
			resultBean.setMessage(MsgConfig.FAIL_MESSAGE);
			resultBean.setStatus(Config.RESULT_FAIL);
		}
		return resultBean;

	}

	@ResponseBody
	@RequestMapping(value = "/{appId}/sample", method = RequestMethod.GET)
	public ResultBean getSampleData(@PathVariable("appId") String appId, @RequestParam("userId") String userId) {

		ResultBean resultBean = new ResultBean();

		try {
			AppReqBean appReqBean = new AppReqBean();
			appReqBean.setAppId(appId);
			appReqBean.setUserId(userId);
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {
				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
				return resultBean;
			}
			resultBean = indexDataOperaIndexService.getSampleData(appReqBean);

		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setMessage(MsgConfig.FAIL_MESSAGE);
			resultBean.setStatus(Config.RESULT_FAIL);
		}
		return resultBean;
	}

	@ResponseBody
	@RequestMapping(value = "/{appId}/sample", method = RequestMethod.POST)
	public ResultBean useSampleData(@RequestBody @Validated(defaultGroup.class) AppReqBean appReqBean,
			BindingResult result) {
		ResultBean resultBean = new ResultBean();
		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {
			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
			return resultBean;
		}
		try {
			if (result.hasErrors()) {
				Tool.validateParamsError(resultBean, result);
				return resultBean;
			}
			resultBean = indexDataOperaIndexService.useSampleData(appReqBean);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setMessage(MsgConfig.FAIL_MESSAGE);
			resultBean.setStatus(Config.RESULT_FAIL);
		}
		return resultBean;
	}

	/**
	 * 
	 * @Title:
	 * @Description: 本地索引数据文件上传
	 * @param file
	 * @param userId
	 * @param appId
	 * @param tableId
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/{appId}/data/local", method = RequestMethod.POST)
	public ResultBean ftpUpload(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "userId", required = false) String userId, @PathVariable("appId") String appId,
			@RequestParam(value = "tableId", required = false) String tableId) {
		AppReqBean appReqBean = new AppReqBean();
		appReqBean.setAppId(appId);
		appReqBean.setUserId(userId);

		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "dataUpload local");
		ResultBean resultBean = new ResultBean();

		if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(appReqBean)), false)) {
			Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);
			return resultBean;
		}
		try {

			if (file == null)
				throw new Exception();
			String savePath = this.getClass().getResource("").getPath();
			indexUploadFileServiceImpl.localUploadFile(savePath, userId, appId, tableId, file, resultBean);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setMessage(MsgConfig.FAIL_MESSAGE);
			resultBean.setStatus(Config.RESULT_FAIL);
		}
		return resultBean;
	}

	/**
	 * 
	 * @Title: saveDataOperaHistory
	 * @Description: 将更新，清空，重建的操作记录进行保存
	 * @param operaFlag
	 * @param operaType
	 * @param appReqBean
	 * @return
	 * @return: boolean
	 */
	private boolean saveDataOperaHistory(String operaFlag, String operaType, AppReqBean appReqBean) {
		String userId = appReqBean.getUserId();
		String appId = appReqBean.getAppId();

		if ("".equals(userId) || "".equals(appId)) {
			return false;
		}
		// 处理状态，0：成功，1：失败
		String flagStr = "1";
		if (operaFlag.equals(Config.RESULT_SUCCESS)) {
			flagStr = "0";
		}

		appReqBean.setCreateDate(Tool.getCurrentDateHour());
		appReqBean.setOperaStatus(flagStr);
		appReqBean.setDetail("");
		appReqBean.setOperaType(operaType);

		boolean resultFlag = indexDataOperaIndexService.saveDataOperaHistory(appReqBean);

		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "数据处理记录保存结果为：" + resultFlag);
		return resultFlag;
	}
}
