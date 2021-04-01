package com.chinamobile.cmss.bcse.config.service.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.bean.RecoveryDicBean;
import com.chinamobile.cmss.bcse.config.bean.RecoveryReq;
import com.chinamobile.cmss.bcse.config.dao.AppDao;
import com.chinamobile.cmss.bcse.config.dao.RecoveryDicDao;
import com.chinamobile.cmss.bcse.config.service.RecoveryService;
import com.chinamobile.cmss.bcse.config.util.FileUtil;
import com.chinamobile.cmss.bcse.config.util.StringUtil;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.spellcheck_multi_appliance.Querycorrect;
/**
 * 
 * @ClassName: RecoveryServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:08:37
 */
@Service("recoveryService")
public class RecoveryServiceImpl implements RecoveryService{
	@Resource
	private RecoveryDicDao recoveryDicDao;
	
	@Resource
	private AppDao appDao;
	@Override
	public void recovery(RecoveryReq req, ResultBean resultBean) {
		String appId=req.getAppId();
		String userId=req.getUserId();
		try {
			String isLoadDic=req.getIsLoadDic();
			if(StringUtil.isEmpty(req.getIsLoadDic())){
				isLoadDic="1";
			}
			String searchQuery=req.getSearchQuery();
			Querycorrect qc = Config.APP_DIC_MAP.get(appId);
			JSONObject jsonObject=new JSONObject();
			String recovery;
			if(qc!=null&&"1".equals(isLoadDic)){
				recovery=qc.sc(searchQuery);
			}else{
				recovery=searchQuery;
			}
			jsonObject.put("recovery", recovery);
			resultBean.setResult(jsonObject);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.SpellcheckError);
			throw new RuntimeException(MsgConfig.SpellcheckError);
		}
	}
	
	@Override
	public void train(String appId, String userId, MultipartFile file, String mode, ResultBean resultBean) {
		try {
			if(!validate(file, resultBean)){
				return;
			}
			List<String> words=new ArrayList<String>();
			BufferedReader br=new BufferedReader(
					new InputStreamReader(file.getInputStream()));
			String str;
			while(null!=(str=br.readLine())){
				words.add(str);
			}
			//写数据库
			writeToDb(appId, userId, words, mode);
			//训练双数组数,先要查询
			dicTrain(appId);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.TrainDicError);
			throw new RuntimeException(MsgConfig.TrainDicError);
		}
	}

	
	private void dicTrain(String appId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appId", appId);
		map.put("mode", "0");
		String commonWords=recoveryDicDao.findByMode(map);
		map.put("mode", "1");
		String userDefinedWords=recoveryDicDao.findByMode(map);
		Querycorrect qc = new Querycorrect();
		if(StringUtil.isNotEmpty(userDefinedWords)){
			String[] usrDict=userDefinedWords.split(Config.DIC_SPLIT_FLAG);
			qc.prepare(commonWords, usrDict);
		}else{
			qc.prepare(commonWords, null);
		}
		// 将训练出的词典与对应的APPID映射进行保存
		Config.APP_DIC_MAP.put(appId, qc);
	}
	

		
		
	public void writeToDb(String appId, String userId,List<String> words,String mode) {
			RecoveryDicBean bean=new RecoveryDicBean();
			bean.setAppId(appId);
			bean.setUserId(userId);
			bean.setMode(mode);
			//构造DicText
			StringBuffer sb=new StringBuffer();
			for(String str:words){
				sb.append(str).append(Config.DIC_SPLIT_FLAG);
			}
			bean.setDicText(sb.toString());
			//--------根据appId和mode去查询--------
			Integer num=recoveryDicDao.showNum(bean);
			//如果小于1，则为添加；否则为更新
			if(num<1){
				recoveryDicDao.insert(bean);
			}else{
				recoveryDicDao.update(bean);
			}
		
	}

	
	//系统初次加载时调用
	public void train(){
		List<String> appIds=appDao.findAppIds();
		for(String appId:appIds){
			dicTrain(appId);
		}
	}
	
	private boolean validate(MultipartFile file, ResultBean resultBean)throws Exception {
		if(file.getSize()>MsgConfig.SPELLDIC_SIZE){
    		resultBean.setCode(Config.FAIL_SERIVICE_CODE);
    		resultBean.setStatus(Config.RESULT_FAIL);
    		resultBean.setMessage(MsgConfig.SPELLDIC_SIZE_EXCEEDS);
    		return false;
    	}
		String charset=FileUtil.getFileChart(file.getInputStream());
		if("utf-8".equals(charset.toLowerCase())){
			return true;
		}else{
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
    		resultBean.setStatus(Config.RESULT_FAIL);
    		resultBean.setMessage(MsgConfig.FileCharsetError);
    		return false;
		}
	}
	
	
}
