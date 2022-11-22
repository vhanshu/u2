package com.u2.common.core.exception.auth;

/**
 * 内部认证异常
 *
 * @author vhans
 */
public class InnerAuthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InnerAuthException(String message) {
        super(message);
    }
}
