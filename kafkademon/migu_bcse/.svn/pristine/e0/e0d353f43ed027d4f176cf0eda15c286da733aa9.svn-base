package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

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
public class BCSEHeatWord {

	private String dateType;

	private int pageIndex = 1;
	private int pageNum = 5;
	private String ct;
	private String bookType;
	//调bcse接口时，验证LogReqBean时用到，值没有实际意义
	/*private int startDate = -7;
	private int endDate = -1;*/

	private BCSEClient client;
	private final String DAY = "DAY";
	private final String WEEK = "WEEK";
	private final String MONTH = "MONTH";
	private final String path_month = "/statistics/hotWord/month";
	private String path = "/statistics/hotWord/yesterday";
	private final String path_day = "/statistics/hotWord/yesterday";
	private final String path_week = "/statistics/hotWord/week";
	
	public BCSEHeatWord(BCSEClient client) {
		this.client = client;
	}

	public BCSEHeatWord() {

	}

	public String heatWords(Map<String, Object> opts) throws ClientProtocolException, IOException {
		if (this.client == null || !this.client.isFlag()) {
			return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
		}

		if ((opts != null) && (opts.size() > 0)) {
			if (!DAY.equals(opts.get("dateType")) && !WEEK.equals(opts.get("dateType"))
					&& !MONTH.equals(opts.get("dateType"))) {
				return ResultDecorate.decorate("", "FAIL", "dateType不在规定范围内", ExceptionConstants.FAIL_SERIVICE_CODE);
			}
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
	public String resultDecorate(String result) {
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
	public void extract(Map<String, Object> opts) {

		if ((opts != null) && (opts.size() > 0)) {
			if (opts.get("dateType") != null) {
				this.setDateType((String) opts.get("dateType"));
			}
			if (opts.get("pageIndex") != null) {
				this.setPageIndex(Integer.parseInt(String.valueOf(opts.get("pageIndex"))));
			}
			if (opts.get("pageNum") != null) {
				this.setPageNum(Integer.parseInt(String.valueOf(opts.get("pageNum"))));
			}
			if (opts.get("ct") != null) {
                this.setCt((String)opts.get("ct"));
			}
			if (opts.get("bookType") != null) {
                this.setBookType((String)opts.get("bookType"));
			}
		}
		if (this.getDateType() == null) {
			return;
		}
		switch (this.getDateType()) {
		case DAY:
			this.setPath(path_day);
			break;
		case WEEK:
			this.setPath(path_week);
			break;
		case MONTH:
			this.setPath(path_month);
			break;
		default:
			break;
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
		opts.put("ct", getCt());
		opts.put("bookType", getBookType());
	/*	opts.put("endDate", endDate);
		opts.put("startDate", startDate);*/
		System.out.println("path:"+path);
		return this.client.call(path, opts, "GET");
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public BCSEClient getClient() {
		return client;
	}

	public void setClient(BCSEClient client) {
		this.client = client;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
