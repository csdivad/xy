package hu.csdivad.xy.dao.impl;

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
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.AccountDao;

@Repository("firstAccountDaoImpl")
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Account> listAll() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(Account.class);
		List<Account> accounts = crit.list();
		transaction.commit();
		session.close();
		return accounts;
	}
	
	@Override
	public List<Account> listAccountsByUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(Account.class);
		Criterion userCrit = Restrictions.eq("username", user);
		crit.add(userCrit);
		List<Account> accounts = crit.list();
		transaction.commit();
		session.close();
		return accounts;
	}

	@Override
	public Account findAccountById(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(Account.class);
		Criterion userCrit = Restrictions.idEq(id);
		crit.add(userCrit);
		Account accounts = (Account)crit.uniqueResult();
		transaction.commit();
		session.close();
		return accounts;
	}

	@Override
	public void updateAccount(Account account) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(account);
		transaction.commit();
		session.close();
	}

	@Override
	public void saveUser(Account account) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(account);
		transaction.commit();
		session.close();		
	}

	@Override
	public void deleteUser(Account account) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(account);
		transaction.commit();
		session.close();		
	}

}
