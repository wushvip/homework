package com.chinamobile.cmss.bcse.search.validator;

import com.chinamobile.cmss.bcse.search.bean.SearchInput;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

/** 
 * @ClassName: SearchValidator 
 * @Description: TODO 
 * @author: chenmin
 * @date: 2017年3月6日 下午5:15:20  
 */
public class SearchValidator {
	
	
	/** 
	 * @Title: validator 
	 * @Description: TODO 
	 * @param searchInput
	 * @param resultBean
	 * @return: boolean
	 */
	public boolean validator ( SearchInput searchInput , ResultBean resultBean ) {
		boolean  flag=true;
		if(searchInput.getUserId()==null||searchInput.getUserId().equals("")){
			resultBean.setMessage("userId不能为空!");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			resultBean.setStatus(Config.RESULT_FAIL);
			return false;
			
		}
		if(searchInput.getAppId()==null||searchInput.getAppId().equals("")){
			resultBean.setMessage("appId不能为空!");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			resultBean.setStatus(Config.RESULT_FAIL);
			return false;
			
		}
		if(searchInput.getSearchQuery()==null||searchInput.getSearchQuery().equals("")){
			resultBean.setMessage("searchQuery is empty!");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			resultBean.setStatus(Config.RESULT_FAIL);
			return false;
			
		}
		if(searchInput.getSearchQuery().length()>100){
			searchInput.setSearchQuery(searchInput.getSearchQuery().substring(0, 100));
		}
		if(searchInput.getPageIndex()<1){
			resultBean.setMessage("pageIndex must be > 0");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			resultBean.setStatus(Config.RESULT_FAIL);
			return false;
		}
		if(searchInput.getPageNum()<0||searchInput.getPageNum()>200){
			resultBean.setMessage("pageNum must be [0 200]");
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			resultBean.setStatus(Config.RESULT_FAIL);
			return false;
		}
	    return flag;
		
	}

}
