package com.chinamobile.cmss.bcse.config.validator;

import java.util.HashSet;
import java.util.Set;

import com.chinamobile.cmss.bcse.config.bean.AnalysisReq;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.util.StringUtil;

/**
 * 
 * @ClassName: AnalysisValidator 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月24日 上午10:04:51
 */
public class AnalysisValidator {
	
	
	/**
	 * 
	 * @Title: validator 
	 * @Description: TODO
	 * @param analysisReq
	 * @param resultBean
	 * @return
	 * @return: boolean
	 */
	public boolean validator (AnalysisReq analysisReq , ResultBean resultBean ) {
		boolean  flag=true;
		if(StringUtil.isEmpty(analysisReq.getAppId())){
			resultBean.setMessage("appId is empty!");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return false;
			
		}
		if(StringUtil.isEmpty(analysisReq.getUserId())){
			resultBean.setMessage("userId is empty!");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return false;
			
		}
		
		String fieldSearch=analysisReq.getFieldSearch();
		if(StringUtil.isEmpty(fieldSearch)){
			resultBean.setMessage("fieldSearch is empty!");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return false;
		}
		if(!contains(fieldSearch)){
			resultBean.setMessage("only ANSJ,QP_NoSplit,JP_NoSplit,ANSJ_JP,ANSJ_QP,ANSJ_PY is permitted");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return false;
		}
		
		if(StringUtil.isEmpty(analysisReq.getSearchQuery())){
			resultBean.setMessage("searchQuery is empty!");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return false;
			
		}
		if(analysisReq.getSearchQuery().length()>100){
			analysisReq.setSearchQuery(analysisReq.getSearchQuery().substring(0, 100));
		}
	    return flag;
	   
	}

	
	public static boolean contains(String str){
		 Set<String> set=new HashSet<String>();
		 set.add("ANSJ");
		 set.add("QP_NoSplit");
		 set.add("JP_NoSplit");
		 set.add("ANSJ_JP");
		 set.add("ANSJ_QP");
		 set.add("ANSJ_PY");
		 if(set.contains(str)){
			 return true;
		 }else{
			 return false;
		 }
	}
}
