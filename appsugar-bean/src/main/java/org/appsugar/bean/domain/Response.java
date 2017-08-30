package org.appsugar.bean.domain;

/**
 *  traditional Response
 * @author NewYoung
 *
 * @param <T>
 */
public class Response<T> {
	public static final int SUCCESS_CODE = 0;
	public static final int ERROR_CODE = -1;
	public static final int UNAUTHENTICATION_CODE = 401;
	public static final int UNAUTHORIZATION_CODE = 403;
	public static final Response<Void> UNAUTHENTICATION = new Response<>(UNAUTHENTICATION_CODE, "unauthenticated");
	public static final Response<Void> UN_AUTHORIZATION = new Response<>(UNAUTHORIZATION_CODE, "unauthorized");
	public static final Response<Void> SUCCESS = new Response<>(SUCCESS_CODE, "success");
	public static final Response<Void> ERROR = new Response<>(ERROR_CODE, "error");

	private int code;
	private String msg;
	private T data;

	public Response(int code, String msg) {
		this(code, msg, null);
	}

	public Response(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	public static Response<Void> normal() {
		return SUCCESS;
	}

	public static <T> Response<T> success(T data) {
		return new Response<>(SUCCESS_CODE, "success", data);
	}

	public static Response<Void> failed() {
		return ERROR;
	}

	public static Response<Void> error(String msg) {
		return new Response<>(ERROR_CODE, msg);
	}

	public static Response<Void> error(int code, String msg) {
		return new Response<>(code, msg);
	}
}
