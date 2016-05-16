package hu.csdivad.xy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hu.csdivad.xy.bean.User;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory; 
	
	@Override
	public User getUserById(Long id) {
		
		return null;
	}

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

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
