package hu.csdivad.xy.spring.totp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class TOTPWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

	@Override
	public TOTPWebAuthenticationDetails buildDetails(HttpServletRequest request) {
		return new TOTPWebAuthenticationDetails(request);
	}

}
