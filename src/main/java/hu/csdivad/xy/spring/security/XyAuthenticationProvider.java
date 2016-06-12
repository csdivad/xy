package hu.csdivad.xy.spring.security;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.spring.security.exception.AccountNotBoundToUserException;
import hu.csdivad.xy.spring.security.exception.MissingAccountNumberException;

public class XyAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		super.additionalAuthenticationChecks(userDetails, authentication);

		if (userDetails instanceof User) {
			Integer enteredAccountNumber = ((XyWebAuthenticationDetails) authentication.getDetails()).getAccountNumber();
			if(enteredAccountNumber==null) {
				throw new MissingAccountNumberException("Missing account number");
			}
			if(!((User) userDetails).getAccounts().contains(new Account(enteredAccountNumber))) {
				throw new AccountNotBoundToUserException("User does not have account: " + enteredAccountNumber);
			}
		} else {
			throw new InternalAuthenticationServiceException("Something wrong");
		}
	}

}
