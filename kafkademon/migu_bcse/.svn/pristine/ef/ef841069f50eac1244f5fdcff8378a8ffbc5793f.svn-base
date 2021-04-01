package com.chinamobile.cmss.bcse.tool.exception;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;

/**
 *
 * @author chenmin
 * @date   2015年10月18日
 *
 * TODO   查询参数异常校验
 *
 */
public class ParamException extends Exception{
	//校验结果标识
	public boolean validateFlag=true;
	//错误信息
	public String  errorInfo;
	
	/**
	 * @param string
	 */
	public ParamException(String string) {
		super(string);
	}
	/**
	 * @param string
	 */
	public ParamException() {
	}

	/**
	 * 对查询参数bean中的参数校验
	 * @param queryBean
	 * @throws ParamException 
	 * 
	 * */
	public   void validateParams(QueryBean queryBean) throws ParamException{
	   	    
		     /*if(queryBean.getAppName()==null||queryBean.getAppName().equals("")){
		    	 this.validateFlag=false;
		    	 throw new ParamException(ExceptionConstants.parametersWrong);
		     }*/
		     if(queryBean.getSearchQuery()==null||queryBean.getSearchQuery().equals("")){
		    	 this.validateFlag=false;
		    	 errorInfo="lack of searchquery!";
		    	 if(queryBean.getSearchQuery().length()>20){
		    		 errorInfo="searchquery is too long!";
		    	 }
		    	 throw new ParamException(ExceptionConstants.ParametersError);
		     }
		     if(queryBean.getPageNum()>100){
		    	 queryBean.setPageNum(100);;
		     }
	}

}
