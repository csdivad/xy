package hu.csdivad.xy.dao;

import java.util.List;

import hu.csdivad.xy.bean.AccountTransaction;

public interface AccountTransactionDao {
	List<AccountTransaction> listAll();
	AccountTransaction findTransactionById(int id);
	void updateTransaction(AccountTransaction accountTransaction);
	void saveTransaction(AccountTransaction accountTransaction);
	void deleteTransaction(AccountTransaction accountTransaction);
}
