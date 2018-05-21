package pers.myAuthweb.exception;

/*
 * author :　LH 2018-05-21 AM
 */
public class ParameterException extends IException {
	private static final long serialVersionUID = 7993671808524980055L;

	public ParameterException(String message) {
		super(500, message);
	}

	public ParameterException() {
		super(500, "参数错误");
	}
}
