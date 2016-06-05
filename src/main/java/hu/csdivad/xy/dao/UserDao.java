package hu.csdivad.xy.dao;

import java.util.List;

import hu.csdivad.xy.bean.User;

public interface UserDao {
	List<User> listAll();
	User findUserByName(String name);
	void updateUser(User user);
	void saveUser(User user);
	void deleteUser(User user);
}
