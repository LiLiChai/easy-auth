package pers.lihuan.authweb.common;

import java.util.HashMap;

/**
 * @author Fancy
 */
public class ResultEntity extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	private ResultEntity() {};
	
	/**
	 * 返回成功
	 */
	public static ResultEntity ok() {
		return ok("操作成功！");
	}

	/**
	 * 返回成功
	 */
	public static ResultEntity ok(String message) {
		return ok(200, message);
	}
	
	/**
	 * 返回成功
	 */
	public static ResultEntity ok(int code,String message) {
		ResultEntity resultMap = new ResultEntity();
		resultMap.put("code", code);
		resultMap.put("msg", message);
		return resultMap;
	}
	
	/**
	 * 返回失败
	 */
	public static ResultEntity error() {
		return error("操作失败！");
	}
	
	/**
	 * 返回失败
	 */
	public static ResultEntity error(String messag) {
		return error(500, messag);
	}

	/**
	 * 返回失败
	 */
	public static ResultEntity error(int code, String message) {
		return ok(code, message);
	}
	
	/**
	 * 设置code
	 */
	public ResultEntity setCode(int code){
		super.put("code", code);
		return this;
	}
	
	/**
	 * 设置message
	 */
	public ResultEntity setMessage(String message){
		super.put("msg", message);
		return this;
	}
	
	/**
	 * 放入object
	 */
	@Override
	public ResultEntity put(String key, Object object){
		super.put(key, object);
		return this;
	}

}
