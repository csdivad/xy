package hu.csdivad.xy.dao;

import java.util.List;

import hu.csdivad.xy.bean.User;

public interface UserDao {
	User getUserById(Long id);
	
	List<User> listAll();
	User findUserByName(String name);
	
}
