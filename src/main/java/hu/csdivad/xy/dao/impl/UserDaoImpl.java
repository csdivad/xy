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
import hu.csdivad.xy.util.HibernateUtil;

@Repository("firstUserDaoImpl")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getUserById(Long id) {
		
		return null;
	}

	@Override
	public List<User> listAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		List<User> users = crit.list();
		transaction.commit();
		session.close();
		return users;
	}

	@Override
	public User findUserByName(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		Criterion userCrit = Restrictions.like("username", username);
		crit.add(userCrit);
		List<User> users = crit.list();
		transaction.commit();
		session.close();
		
		if(users.size()>0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
