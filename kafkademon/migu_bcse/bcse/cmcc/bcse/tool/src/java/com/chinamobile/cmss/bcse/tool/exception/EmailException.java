package com.chinamobile.cmss.bcse.tool.exception;


/** 
 * @ClassName: RecordNotExistException 
 * @Description: 记录不存在异常
 * @author: lijingjing
 * @date: 2016年2月18日 上午10:43:20  
 */
public class EmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmailException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
