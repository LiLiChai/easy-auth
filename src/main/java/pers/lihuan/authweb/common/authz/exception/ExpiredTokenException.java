package pers.lihuan.authweb.common.authz.exception;

/**
 * token过期异常
 * @author Fancy
 */
public class ExpiredTokenException extends EtpException {
	private static final long serialVersionUID = -8019541050781876369L;

	public ExpiredTokenException() {
		super(401, "登录已过期");
	}
}
