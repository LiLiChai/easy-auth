package pers.lihuan.authweb.common.encrypt.crypto;

import pers.lihuan.authweb.common.encrypt.exception.EndecryptException;

/**
 * Base Shiro exception for problems encountered during cryptographic operations.
 *
 * @since 1.0
 */
public class CryptoException extends EndecryptException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CryptoException(String message) {
        super(message);
    }

    public CryptoException(Throwable cause) {
        super(cause);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}
