package hu.csdivad.xy.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AccountNotBelongToUserException extends AuthenticationException {
	private static final long serialVersionUID = 4283935753174746218L;

	public AccountNotBelongToUserException(String msg) {
		super(msg);
	}

	public AccountNotBelongToUserException(String msg, Throwable t) {
		super(msg, t);
	}

}
