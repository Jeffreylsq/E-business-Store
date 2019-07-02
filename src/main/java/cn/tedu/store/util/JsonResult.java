package cn.tedu.store.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 向客户端响应操作的结果的数据类型
 * @author soft01
 *
 * @param <T>
 */

//谁为空 谁就不被输出
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult<T> {

	  
	private Integer status;
	   private String message;
	   private T data;
	   
	   
	   
	public JsonResult() {
		super();
	}
	
	public JsonResult(Integer status) {
		super();
		this.status = status;
	}
	
	 public JsonResult(Integer status, T data) {
			super();
			this.status = status;
			this.data = data;
		}
	 
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	   
}
