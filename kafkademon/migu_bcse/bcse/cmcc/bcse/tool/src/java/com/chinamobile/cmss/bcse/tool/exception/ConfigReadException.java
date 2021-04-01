package com.chinamobile.cmss.bcse.tool.exception;

/** 
 * @ClassName: ConfigReadException 
 * @Description: XmlTool类读取配置文件异常
 * @author: zhenglinfeng
 * @date: 2016年5月25日 下午4:33:12  
 */
public class ConfigReadException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public ConfigReadException() {
		// TODO Auto-generated constructor stub
	}

	public ConfigReadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConfigReadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigReadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
