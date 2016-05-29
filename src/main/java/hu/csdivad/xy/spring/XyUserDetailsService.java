package hu.csdivad.xy.spring;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

public class XyUserDetailsService implements UserDetailsService {
	
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findUserByName(username);
		if (user==null) {
			throw new UsernameNotFoundException("The user was not found by the dao");
		}
		
		return null;
		//List<>
	
	}

}
