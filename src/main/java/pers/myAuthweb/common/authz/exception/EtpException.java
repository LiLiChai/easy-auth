package pers.myAuthweb.common.authz.exception;

/**
 * EtpException
 */
public abstract class EtpException extends RuntimeException {
	private static final long serialVersionUID = 2413958299445359500L;
	private int code;

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public EtpException(int code, String message) {
		super(message);
		this.code = code;
	}
}
