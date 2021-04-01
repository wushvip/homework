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

public class BCSESearchTimes {
	private BCSEClient client;
	private String path = "/statistics/times";
	private String dimension;
	private int startDate=-1;
	private int endDate=-1;
	private JSONArray userList = new JSONArray();
	private String ct;
	private String bookType;
	
	public BCSESearchTimes(BCSEClient client) {
		this.client = client;
	}
	
	public BCSESearchTimes(){}
	
	
	public String searchcost(Map<String, Object> opts) throws ClientProtocolException, IOException {
        if(this.client==null||!this.client.isFlag()){
        	return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
        if ((opts != null) && (opts.size() > 0)) {
        	 try{
       			 if (opts.get("dimension")!=null && !"day".equals(opts.get("dimension")) && !"hour".equals(opts.get("dimension"))) {
       				 return ResultDecorate.decorate("", "FAIL", "dimension必须为day或hour", ExceptionConstants.FAIL_SERIVICE_CODE);
       			 }
       		 }catch(Exception e){
       			 e.printStackTrace();
       		 }
        	 if(opts.get("dimension")==null || opts.get("startDate")==null || opts.get("endDate")==null ){
        		 return ResultDecorate.decorate("", "FAIL", "参数dimension,startDate,endDate都不能为空", ExceptionConstants.FAIL_SERIVICE_CODE);
        	 }
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
			if (opts.get("dimension") != null) {
                this.setDimension((String) opts.get("dimension"));
			}
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
			if (opts.get("ct") != null) {
                this.setCt((String)opts.get("ct"));
			}
			if (opts.get("bookType") != null) {
                this.setBookType((String)opts.get("bookType"));
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
		opts.put("dimension", getDimension());
		opts.put("startDate", getStartDate());
		opts.put("endDate", getEndDate());
		JSONObject user = new JSONObject();
		user.put("userId", client.getUserId());
		user.put("appId", client.getAppId());
		userList.add(user);
		opts.put("userList", userList);
		opts.put("ct", getCt());
		opts.put("bookType", getBookType());
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

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
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

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	
	
}
