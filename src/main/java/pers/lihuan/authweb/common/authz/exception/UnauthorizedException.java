package pers.lihuan.authweb.common.authz.exception;

/**
 * 没有权限的异常
 * @author Fancy
 */
public class UnauthorizedException extends EtpException {
	private static final long serialVersionUID = 8109117719383003891L;

	public UnauthorizedException() {
		super(403, "没有访问权限！！！！");
	}
}
