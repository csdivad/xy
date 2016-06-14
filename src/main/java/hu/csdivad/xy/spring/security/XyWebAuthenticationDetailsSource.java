package hu.csdivad.xy.spring.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class XyWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

	@Override
	public XyWebAuthenticationDetails buildDetails(HttpServletRequest request) {
		return new XyWebAuthenticationDetails(request);
	}

}
