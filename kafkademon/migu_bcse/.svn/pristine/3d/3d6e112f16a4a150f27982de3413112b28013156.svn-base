package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;


/**
 * @ClassName: BCSEHeatWord
 * @Description: TODO 熱詞接口
 * @author: chenmin
 * @date: 2016年4月13日 上午9:44:51
 */
public class BCSEHeatWordByDate {
	
	private int pageIndex = 1;
	private int pageNum = 5;
	
	//默认前一天的数据
	private String startDate = getDayTime(-1);
	private String endDate = getDayTime(-1);
	private int IntStartDate =-1;
	private int IntEndDate = -1;
	private String ct;
	private String bookType;

	private BCSEClient client;
	private String path = "/statistics/hotWord";
	
	public BCSEHeatWordByDate(BCSEClient client) {
		this.client = client;
	}

	public BCSEHeatWordByDate() {

	}

	public String heatWords(Map<String, Object> opts) throws ClientProtocolException, IOException {
		if (this.client == null || !this.client.isFlag()) {
			return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
		}
		extract(opts);
		String result = call();
		return result;
	}

	public String heatWords() throws ClientProtocolException, IOException {
		return heatWords(new HashMap());
	}

	/**
	 * @Title: resultDecorate
	 * @Description: TODO 封装结果
	 * @param result
	 * @return
	 * @return: String
	 */
	private String resultDecorate(String result) {
		String status = "SUCCESS";
		String message = "";
		String code = "";
		try {
			JSONObject object = JSON.parseObject(result);
			boolean flag = (boolean) object.get("success");
			if (!flag) {
				status = "FAIL";
				code = "6000000";
				message = object.getString("message");
			}
			result = object.getString("heatWordsList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDecorate.decorate(result, status, message, code);
	}

	/**
	 * @Title: extract
	 * @Description: TODO 通过map去set变量的值域
	 * @param opts
	 * @return: void
	 */
	private void extract(Map<String, Object> opts) {

		if ((opts != null) && (opts.size() > 0)) {
			if (opts.get("pageIndex") != null) {
				this.setPageIndex(Integer.parseInt(String.valueOf(opts.get("pageIndex"))));
			}
			if (opts.get("pageNum") != null) {
				this.setPageNum(Integer.parseInt(String.valueOf(opts.get("pageNum"))));
			}
			if (opts.get("startDate") != null) {
				this.IntStartDate = getDifferDate(String.valueOf(opts.get("startDate")));
			}
			if (opts.get("endDate") != null) {
				this.IntEndDate = getDifferDate(String.valueOf(opts.get("endDate")));
			}
			if (opts.get("ct") != null) {
                this.setCt((String)opts.get("ct"));
			}
			if (opts.get("bookType") != null) {
                this.setBookType((String)opts.get("bookType"));
			}
		}else{
			
			if (this.startDate != null) {
				this.IntStartDate = getDifferDate(this.startDate);
			}
			if (this.endDate != null) {
				this.IntEndDate = getDifferDate(this.endDate);
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
	private String call() throws ClientProtocolException, IOException {
		Map<String, Object> opts = new HashMap();
		opts.put("pageIndex", getPageIndex());
		opts.put("pageNum", getPageNum());
		opts.put("startDate", this.IntStartDate);
		opts.put("endDate", this.IntEndDate);
		opts.put("ct", getCt());
		opts.put("bookType", getBookType());
	/*	opts.put("endDate", endDate);
		opts.put("startDate", startDate);*/
		System.out.println("path:"+path);
		return this.client.call(path, opts, "GET");
	}
	
	private int getDifferDate(String date) {
		if (date == null || "".equals(date)) {
			throw new RuntimeException("Illegality date value");
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fDate = null;
		try {
			fDate = formatter.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
			throw new RuntimeException("date value parse exception,date="+date);
		}
		
		long date_long = fDate.getTime();

		Date oDate = new Date();
		long curr_long = oDate.getTime();

		int differ = (int)((date_long-curr_long) / 1000 / 60 / 60 / 24);
		return differ;
	}
	
	private String getDayTime(int time){
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, time);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
