package hu.csdivad.xy.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MissingAccountNumberException extends AuthenticationException {
	private static final long serialVersionUID = 8802526129203260865L;

	public MissingAccountNumberException(String msg) {
		super(msg);
	}

	public MissingAccountNumberException(String msg, Throwable t) {
		super(msg, t);
	}

}
