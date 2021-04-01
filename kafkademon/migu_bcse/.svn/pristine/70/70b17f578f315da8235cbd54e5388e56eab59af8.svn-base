package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;

public class BCSESearchNum {
	private BCSEClient client;
	private String path = "/statistics/num";
	private String dimension ="day";
	private int startDate=-1;
	private int endDate=-1;
	private JSONArray userList = new JSONArray();
	
	public BCSESearchNum(BCSEClient client) {
		this.client = client;
	}
	
	public BCSESearchNum(){}
	
	
	public String searchcost(Map<String, Object> opts) throws ClientProtocolException, IOException {
        if(this.client==null||!this.client.isFlag()){
        	return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
       
		extract(opts);
		String result=call();
		return  result;

	}

	public String searchcost() throws ClientProtocolException, IOException {

		return searchcost(new HashMap());

	}
	
	/** 
	 * @Title: extract 
	 * @Description: TODO  通过map去set变量的值域
	 * @param opts
	 * @return: void
	 */
	public void extract(Map<String, Object> opts) {
		
		if ((opts != null) && (opts.size() > 0)) {
			if(opts.get("startDate")!=null){
				try{
					this.setStartDate(Integer.parseInt(String.valueOf(opts.get("startDate"))));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(opts.get("endDate")!=null){
				try{
					this.setEndDate(Integer.parseInt(String.valueOf(opts.get("endDate"))));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

	}
	
	/** 
	 * @Title: call 
	 * @Description: TODO 通过客户端发送请求
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return: String
	 */
	private String call() throws ClientProtocolException, IOException{
		Map<String, Object> opts = new HashMap<String, Object>();
		opts.put("dimension", dimension);
		opts.put("startDate", getStartDate());
		opts.put("endDate", getEndDate());
		JSONObject user = new JSONObject();
		user.put("userId", client.getUserId());
		user.put("appId", client.getAppId());
		userList.add(user);
		opts.put("userList", userList);
		return this.client.call(path, opts, "POST");
	}
	
	/** 
	 * 
	 * @Title: resultDecorate 
	 * @Description: TODO 封装结果
	 * @param result
	 * @return
	 * @return: String
	 * 
	 */
	public String resultDecorate(String result){
		 String status="SUCCESS";
	     String message="";
	     String code="";
	     try {
	    	 JSONObject object=JSON.parseObject(result);
	    	 message=(String) object.get("message");
	    	 code=(String) object.get("code");
	    	 status=(String) object.get("status");
	    	 result= object.get("result").toString();
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		return ResultDecorate.decorate(result, status, message, code);
	}


	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	
	
}
