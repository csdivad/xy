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

import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

@Repository("firstUserDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAll() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		List<User> users = crit.list();
		transaction.commit();
		session.close();
		return users;
	}

	@Override
	public User findUserByName(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		Criterion userCrit = Restrictions.idEq(username);
		crit.add(userCrit);
		User user = (User) crit.uniqueResult();
		transaction.commit();
		session.close();
		return user;
	}

	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
