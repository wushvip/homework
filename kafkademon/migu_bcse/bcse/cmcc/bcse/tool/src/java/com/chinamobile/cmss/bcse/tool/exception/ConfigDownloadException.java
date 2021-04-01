package com.chinamobile.cmss.bcse.tool.exception;

/** 
 * @ClassName: ConfigDownloadException 
 * @Description: 下载zookeeper配置文件异常
 * @author: zhenglinfeng
 * @date: 2016年5月25日 下午3:25:12  
 */
public class ConfigDownloadException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public ConfigDownloadException() {
		// TODO Auto-generated constructor stub
	}

	public ConfigDownloadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConfigDownloadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigDownloadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigDownloadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
