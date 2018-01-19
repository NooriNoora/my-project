package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.CategoryDao;
import com.niit.model.Category;

@Repository
@Transactional
public class CategoryDaoimpl implements CategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addCategory(Category category) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(category);
		transaction.commit();
		session.close();

	}

	@Override
	public List<Category> getAllCategory() {
		Session session = sessionFactory.openSession();
		List<Category> c2 = session.createQuery("from Category", Category.class).list();
		session.close();
		return c2;

	}

	@Override
	public Category getCategoryById(int id) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Category c1 = new Category();
		c1 = session.get(Category.class, id);
		transaction.commit();
		session.close();
		return c1;

	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(getCategoryById(id));
		transaction.commit();
		session.close();
	}

	}

