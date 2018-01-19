package com.niit.daoimpl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDao;
import com.niit.model.User;

@Transactional
@Repository
public  class UserDaoimpl implements UserDao{
	@Autowired
	SessionFactory sessionFactory;
	
		public List<User> getAlluser() {
		Session session = sessionFactory.openSession();
		List<User> b1=session.createQuery("from User",User.class).list();
		session.close();
		return b1;
	}

	public User getUserById(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		User a1=new User();
		a1=session.get(User.class, id);
		transaction.commit();
		session.close();
		
		return a1;

	}
	public User getUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		User a1=new User();
		a1=session.get(User.class, username);
		transaction.commit();
		session.close();
		
		return a1;

	}

	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		
		session.saveOrUpdate(user);
		transaction.commit();
		session.close();
      
        }

	public void upUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.saveOrUpdate(user);
		transaction.commit();
		session.close();
        
		
	}

	
}


	


