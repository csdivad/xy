package hu.csdivad.xy.spring.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

public class XyWebAuthenticationDetails extends WebAuthenticationDetails {
	private static final long serialVersionUID = 1L;

	private Integer accountNumber;

	public XyWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		String accontNumberString = request.getParameter("account-number");
		if (StringUtils.hasText(accontNumberString)) {
			try {
				this.accountNumber = Integer.valueOf(accontNumberString);
			} catch (NumberFormatException e) {
				this.accountNumber = null;
			}
		}
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

}
