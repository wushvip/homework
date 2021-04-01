package com.chinamobile.cmss.bcse.tool.exception;


/** 
 * @ClassName: RecordNotExistException 
 * @Description: 记录不存在异常
 * @author: lijingjing
 * @date: 2016年2月18日 上午10:43:20  
 */
public class FileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
