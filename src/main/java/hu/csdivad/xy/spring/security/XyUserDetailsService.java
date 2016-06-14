package hu.csdivad.xy.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hu.csdivad.xy.dao.UserDao;

public class XyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		hu.csdivad.xy.bean.User user = userDao.findUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found: " + username);
		}

		return user;
	}

}
