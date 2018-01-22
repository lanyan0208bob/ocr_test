package com.daxia.dto;

/**
 * json返回
* @ClassName: Respon 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王继波 
* @date 2018年1月19日 下午3:23:38 
*
 */
public class Respon<T> {

	
	private String code;
	private String message;
	private T data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Respon(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Respon() {
	}
	
	
	
	
}
