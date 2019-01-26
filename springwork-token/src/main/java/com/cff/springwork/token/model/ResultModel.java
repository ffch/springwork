package com.cff.springwork.token.model;

/**
 * @author yujinlong
 */
public class ResultModel {

	private String error_code;
	private String message;
	private Object data;

	public ResultModel(String error_code, String message) {
		this.error_code = error_code;
		this.message = message;
	}

	public ResultModel(String error_code, String message, Object data) {
		this.error_code = error_code;
		this.message = message;
		this.data = data;
	}

	public ResultModel(ResultCode resultCodeEnum, Object data) {
		this.error_code = resultCodeEnum.getCode();
		this.message = resultCodeEnum.getDesc();
		this.data = data;
	}

	public ResultModel(ResultCode resultCodeEnum) {
		this.error_code = resultCodeEnum.getCode();
		this.message = resultCodeEnum.getDesc();
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
