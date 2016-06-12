package hu.csdivad.xy.dao;

import java.util.List;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;

public interface AccountDao {
	List<Account> listAll();
	List<Account> listAccountsByUser(User user);
	Account findAccountById(int id);
	void updateAccount(Account account);
	void saveUser(Account account);
	void deleteUser(Account account);
}
