package com.chinamobile.cmss.bcse.tool.tools;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;


/** 
 * @ClassName: ResultBean 
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年4月27日 下午2:02:42  
 */
public class ResultBean {
	
	private String message=MsgConfig.SUCCESS_MESSAGE;
	private String code=Config.SUCCESS_CODE;
	private String status=Config.RESULT_SUCCESS;
	private Object result = new JSONObject();
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
	

}
