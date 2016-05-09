package hu.csdivad.xy.dao;

import java.util.List;

import hu.csdivad.xy.beans.User;

public interface UserDao {
	User getUserById(Long id);
	
	List<User> listAll();
	
}
