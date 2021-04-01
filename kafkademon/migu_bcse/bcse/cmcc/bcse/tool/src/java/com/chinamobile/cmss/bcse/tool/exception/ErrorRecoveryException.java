package com.chinamobile.cmss.bcse.tool.exception;

import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 *
 * @author chenmin
 * @date   2015年12月1日
 *
 * TODO 纠错异常
 *
 */
public class ErrorRecoveryException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ErrorRecoveryException(String value){
		super(value);
	}

}
