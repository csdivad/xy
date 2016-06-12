package hu.csdivad.xy.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AccountNotBoundToUserException extends AuthenticationException {

	public AccountNotBoundToUserException(String msg) {
		super(msg);
	}

	public AccountNotBoundToUserException(String msg, Throwable t) {
		super(msg, t);
	}

}
