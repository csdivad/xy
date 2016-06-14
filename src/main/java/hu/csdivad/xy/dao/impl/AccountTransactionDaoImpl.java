package hu.csdivad.xy.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.AccountTransaction;
import hu.csdivad.xy.dao.AccountTransactionDao;

@Repository("firstAccountTransactionDaoImpl")
public class AccountTransactionDaoImpl implements AccountTransactionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountTransaction> listAll() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(AccountTransaction.class);
		List<AccountTransaction> accountTransactions = crit.list();
		transaction.commit();
		session.close();
		return accountTransactions;
	}

	@Override
	public AccountTransaction findTransactionById(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(AccountTransaction.class);
		Criterion userCrit = Restrictions.idEq(id);
		crit.add(userCrit);
		AccountTransaction accountTransaction = (AccountTransaction) crit.uniqueResult();
		transaction.commit();
		session.close();
		return accountTransaction;
	}

	@Override
	public void updateTransaction(AccountTransaction accountTransaction) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(accountTransaction);
		transaction.commit();
		session.close();
	}

	@Override
	public void saveTransaction(AccountTransaction accountTransaction) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(accountTransaction);
		transaction.commit();
		session.close();
	}

	@Override
	public void deleteTransaction(AccountTransaction accountTransaction) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(accountTransaction);
		transaction.commit();
		session.close();
	}

	@Override
	public boolean moneyTransfer(Account from, Account to, int amount, Calendar when) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			AccountTransaction accountTransaction = new AccountTransaction(from, to, when, amount);
			session.save(accountTransaction);
			from.setBalance(from.getBalance() - amount);
			to.setBalance(to.getBalance() + amount);
			session.update(from);
			session.update(to);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}

}
