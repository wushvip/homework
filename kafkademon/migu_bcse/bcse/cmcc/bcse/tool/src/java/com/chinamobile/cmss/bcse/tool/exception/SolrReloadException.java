package com.chinamobile.cmss.bcse.tool.exception;

/** 
 * @ClassName: SolrReloadException 
 * @Description: 配置文件更新后reload异常
 * @author: zhenglinfeng
 * @date: 2016年5月25日 下午4:21:11  
 */
public class SolrReloadException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public SolrReloadException() {
		// TODO Auto-generated constructor stub
	}

	public SolrReloadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SolrReloadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public SolrReloadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SolrReloadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
