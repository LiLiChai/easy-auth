package pers.myAuthweb.exception;

/*
 * author : LH 2018-05-21 AM
 */
public class BusinessException extends IException {
	private static final long serialVersionUID = 5450935008012318697L;

	public BusinessException(String message) {
		super(500, message);
	}

	public BusinessException() {
		super(500, "系统未知错误");
	}
}
