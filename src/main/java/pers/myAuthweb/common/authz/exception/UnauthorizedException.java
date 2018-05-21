package pers.myAuthweb.common.authz.exception;

/**
 * 没有权限的异常
 */
public class UnauthorizedException extends EtpException {
	private static final long serialVersionUID = 8109117719383003891L;

	public UnauthorizedException() {
		super(403, "没有访问权限！！！！");
	}
}
