package hu.csdivad.xy.spring.totp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

public class TOTPWebAuthenticationDetails extends WebAuthenticationDetails {

	private Integer totpKey;

	public TOTPWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		String totpKeyString = request.getParameter("TOTPKey");
		if (StringUtils.hasText(totpKeyString)) {
			try {
				this.totpKey = Integer.valueOf(totpKeyString);
			} catch (NumberFormatException e) {
				this.totpKey = null;
			}
		}
	}

	public Integer getTotpKey() {
		return this.totpKey;
	}

}
