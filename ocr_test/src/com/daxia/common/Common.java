package com.daxia.common;

public enum Common {
	/** 用户登录相关 */
	SUCCESS("1", "success"),
	NO_RESPONSE( "0", "Service without response" ),
	USER_NOT_EXIST( "-1", "the user does not exist" ), 
	ERROR_PWD( "-2", "error password" ), 
	ERROR_STATE( "-3", "error user" ),
	ERROR_SERVER( "-4", "error server" ),
	
	/**
	 * 查询相关
	 */
	NO_SELECT( "-11", "please perfect data " ),
	FAIL( "-1", "fail" ),
	HAS_AGAIN( "-02", "org code already exists" ); 
	private String code;
	private String msg;
	
	Common(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}}
