package com.chinamobile.cmss.bcse.config.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.config.service.UploadService;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
/**
 * 词典管理
 * @ClassName: DicUploadController 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:53:11
 */
@Controller
@RequestMapping("/config/dic")
public class DicUploadController {
	@Resource
	private UploadService uploadService;
	/**
	 * 屏蔽词字典管理
	 * @Title: uploadStop 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @param file
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public ResultBean uploadStop(
			@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value="file",required=true) MultipartFile file){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "upload stopwords ...");
    	ResultBean resultBean = new ResultBean();
    	try {
    		uploadService.uploadStop(appId,userId, file,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}

	
	
	
	/**
	 * 同义词字典管理
	 * @Title: uploadSynonym 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @param file
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/synonym", method = RequestMethod.POST)
	public ResultBean uploadSynonym(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value="file",required=true) MultipartFile file){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "upload synonyms ...");
    	ResultBean resultBean = new ResultBean();
 
    	try {
    		uploadService.uploadSynonym(appId,userId, file,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 扩展词字典管理
	 * @Title: uploadExt 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @param file
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/extention", method = RequestMethod.POST)
	public ResultBean uploadExt(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value="file",required=true) MultipartFile file){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "upload ext ...");
    	ResultBean resultBean = new ResultBean();
    	try {
    		uploadService.uploadExt(appId, userId,file,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 歧义字典管理
	 * @Title: uploadAmbiguity 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @param file
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/ambiguity", method = RequestMethod.POST)
	public ResultBean uploadAmbiguity(@RequestParam(value="appId",required=true) String appId,
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value="file",required=true) MultipartFile file){
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG, "upload ambiguity ...");
    	ResultBean resultBean = new ResultBean();
    	try {
    		uploadService.uploadAmbiguity(appId, userId,file,resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
}
