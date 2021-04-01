package com.chinamobile.cmss.bcse.tool.exception;

/** 
 * @ClassName: ConfigUploadException 
 * @Description: 上传配置文件至zookeeper异常
 * @author: zhenglinfeng
 * @date: 2016年5月25日 下午3:21:46  
 */
public class ConfigUploadException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public ConfigUploadException() {
		// TODO Auto-generated constructor stub
	}

	public ConfigUploadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConfigUploadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigUploadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigUploadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
