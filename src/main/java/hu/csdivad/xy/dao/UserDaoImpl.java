package hu.csdivad.xy.dao;

import java.util.List;

import javax.jws.WebService;

import org.springframework.stereotype.Repository;

import hu.csdivad.xy.beans.User;

@WebService
@Repository("elsoDao")
public class UserDaoImpl implements UserDao {

	@Override
	public User getUserById(Long id) {
		
		return null;
	}

	@Override
	public List<User> listAll() {
		
		return null;
	}

}
