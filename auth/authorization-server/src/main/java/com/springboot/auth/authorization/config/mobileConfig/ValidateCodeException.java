package com.springboot.auth.authorization.config.mobileConfig;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author weichonglu
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
