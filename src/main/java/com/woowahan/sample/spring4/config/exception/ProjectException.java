package com.woowahan.sample.spring4.config.exception;

/**
 * Sample 어플리케이션에서 사용할 로지컬한 Exception
 */
public class ProjectException extends RuntimeException{

	private String code;
	private String msg;

	private String logMsg;

	public ProjectException(Err error, String errMsg) {
		super(error.getClass().getSimpleName().toUpperCase());

		bindParams(error, errMsg);
	}

	public ProjectException(Err error, String errMsg, String logMsg) {
		super(error.getClass().getSimpleName().toUpperCase());
		bindParams(error, errMsg);
		this.logMsg = " detail: " + logMsg;
	}

	private void bindParams(Err error, String errMsg) {
		this.code = error.code();
		this.msg = error.message() + "(" + errMsg + ")";
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getLogMsg() {
		return logMsg;
	}
}
